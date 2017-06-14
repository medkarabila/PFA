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

import ma.ensao.entity.Matiere;
import ma.ensao.entity.MatiereModule;
import ma.ensao.entity.Module;
import ma.ensao.entity.User;
import ma.ensao.hibernateDAO.MatiereModuleDAO;
import ma.ensao.hibernateDAO.ModuleDAO;

public class FrontMatiere extends HttpServlet{
	private Map ma=null;
	ArrayList liste=null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ModuleDAO MoDAO=new ModuleDAO();
		List<Module> listeModule=new ArrayList();
		User user=new User();
		listeModule=MoDAO.getModules();
		liste=new ArrayList();
		for(Module module:listeModule){
			ma=new HashMap();
			ma.put("idModule", module.getId());
			ma.put("module",module.getModule());
			ma.put("image", module.getImage());
			liste.add(ma);
		}
		
		req.setAttribute("listeModules", liste);
		
		if(req.getParameter("module")!=null){
			List<MatiereModule> listeMatiere=new ArrayList();
			MatiereModuleDAO MaMoDAO=new MatiereModuleDAO();
			Integer Module=Integer.parseInt(req.getParameter("module"));
			listeMatiere=MaMoDAO.selectByModule(Module);
			liste=new ArrayList();
			for(MatiereModule module:listeMatiere){
				ma=new HashMap();
				ma.put("id", module.getMatiere().getId());
				ma.put("matiere", module.getMatiere().getMatiere());
				ma.put("description", module.getMatiere().getDescription());
				ma.put("icon", module.getMatiere().getIcon());
				ma.put("module", module.getModule().getModule());
				liste.add(ma);
			}
			req.setAttribute("listeMatieres", liste);	
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
					req.getRequestDispatcher("/WEB-INF/frontOffice/matieres.jsp").forward(req, resp);

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
