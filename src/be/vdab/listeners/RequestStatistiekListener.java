package be.vdab.listeners;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestStatistiekListener implements ServletRequestListener, ServletContextListener {

	private final static String STATISTIEK = "statistiek";
	private final static Set<String> UITGESLOTEN_EXTENSIES = new
			CopyOnWriteArraySet<>(Arrays.asList("png","gif","jpg","css","js","ico"));

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		if (event.getServletRequest() instanceof HttpServletRequest) {
			HttpServletRequest request =
					(HttpServletRequest) event.getServletRequest();
			String url = request.getRequestURI();
			boolean verwerkRequest = true;
			int laatstePuntIndex = url.lastIndexOf('.');
			if (laatstePuntIndex != -1) {
				String extensie =
						url.substring(laatstePuntIndex + 1).toLowerCase();
				if (UITGESLOTEN_EXTENSIES.contains(extensie)) {
					verwerkRequest = false;
				}
			}
			if (verwerkRequest) {
				int index = url.indexOf(";jsessionid=");
				if (index != -1) {
					url = url.substring(0, index);
				}
				@SuppressWarnings("unchecked")
				ConcurrentHashMap<String, AtomicInteger> statistiek =
				(ConcurrentHashMap<String, AtomicInteger>) request.getServletContext()
				.getAttribute(STATISTIEK);
				AtomicInteger aantalReedsAanwezig =
						statistiek.putIfAbsent(url, new AtomicInteger(1));
				if (aantalReedsAanwezig != null) {
					aantalReedsAanwezig.incrementAndGet();
				}
			}
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event)  { 
		event.getServletContext().setAttribute(STATISTIEK, new ConcurrentHashMap<String, AtomicInteger>());
	}



	@Override
	public void contextDestroyed(ServletContextEvent event)  { 
	}

	@Override
	public void requestDestroyed(ServletRequestEvent event) {
	}

}
