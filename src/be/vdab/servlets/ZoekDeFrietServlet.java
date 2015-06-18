package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.ZoekDeFrietSpel;

@WebServlet("/zoekdefriet.htm")
public class ZoekDeFrietServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/zoekdefriet.jsp";
	private static final String SPEL = "zoekDeFrietSpel";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(SPEL) == null) {
			session.setAttribute(SPEL, new ZoekDeFrietSpel());
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("nieuwSpel") != null) {
			session.removeAttribute(SPEL);
		} else {
			int volgNummer = Integer.parseInt(request.getParameter("volgnummer"));
			ZoekDeFrietSpel spel = (ZoekDeFrietSpel) session.getAttribute(SPEL);
			if (spel != null) {
				spel.openDeur(volgNummer);
				session.setAttribute(SPEL, spel);
			}
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}

}
