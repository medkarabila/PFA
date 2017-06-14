package ma.ensao.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.ensao.entity.Chapitre;
import ma.ensao.entity.User;
import ma.ensao.hibernateDAO.ChapitreDAO;

public class ChapitreServlet extends HttpServlet{
	ArrayList liste=null;
	Map ma=null;
	User user=new User();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("matiere")!=null){
			Integer matiere=Integer.parseInt(req.getParameter("matiere"));
			
			ChapitreDAO CDAO = new ChapitreDAO();
			List<Chapitre> listeChapitre=new ArrayList();
			liste=new ArrayList();
			
			listeChapitre=CDAO.selectByMatiere(matiere);
			for(Chapitre ch:listeChapitre){
				ma=new HashMap();
				ma.put("id", ch.getId());
				ma.put("chapitre",ch.getChapitre());
				ma.put("question", ch.getQuestion());
				ma.put("matiere", ch.getMatiere().getMatiere());
				ma.put("fichier", ch.getFichier());
				
				liste.add(ma);
				
			}
			req.setAttribute("listeChapitre", liste);
			
		}
		try {

			user = (User) req.getSession(true).getAttribute("user");

			if (user == null) {
				req.getRequestDispatcher("login.jsp").forward(req, resp);

			} else {
				if (user.getStatus().equals("admin")) {
					 req.getRequestDispatcher("Admin").include(req, resp);
				}
				if (user.getStatus().equals("etudiant")) {
					req.getRequestDispatcher("/WEB-INF/frontOffice/Chapitre.jsp").forward(req, resp);

				}
				if (user.getStatus().equals("prof")) {
					req.getRequestDispatcher("prof.jsp").forward(req, resp);

				}
				
			}
		} catch (Exception e) {
			System.out.println("Error");

		}

	}
}
