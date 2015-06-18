package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.dao.SausDAO;
import be.vdab.entities.Saus;
import be.vdab.entities.SausRadenSpel;

@WebServlet("/sausraden.htm")
public class SausRadenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sausraden.jsp";
	private static final String SPEL = "sausRadenSpel";
	private final transient SausDAO sausDAO = new SausDAO();
	
	
	@Resource(name = SausDAO.JNDI_NAME)
	public void setDataSource(DataSource dataSource) {
		sausDAO.setDataSource(dataSource);
	} 
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(SPEL) == null) {
			List<Saus> sauzen = new ArrayList<>();
			for (Saus saus : sausDAO.findAll()) {
				sauzen.add(saus);
			}
			Saus randomSaus = sauzen.get(new Random().nextInt(sauzen.size()));
			SausRadenSpel spel = new SausRadenSpel(randomSaus.getNaam());
			session.setAttribute(SPEL, spel);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("nieuwSpel") != null) {
			session.removeAttribute(SPEL);
		} else {
			String letter = request.getParameter("letter");
			SausRadenSpel spel = (SausRadenSpel) session.getAttribute(SPEL);
			if (spel != null && letter != null && ! letter.isEmpty()) {
				spel.doeGok(letter.charAt(0));
				session.setAttribute(SPEL, spel);
			}
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}
}