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

import ma.ensao.entity.Categories;
import ma.ensao.entity.Module;
import ma.ensao.entity.User;
import ma.ensao.hibernateDAO.CategorieDAO;
import ma.ensao.hibernateDAO.ModuleDAO;

public class FrontServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		Map cat;
		ArrayList categories = new ArrayList();
		CategorieDAO DAO = new CategorieDAO();
		List<Categories> lCategories = new ArrayList();
		lCategories = DAO.selectAllData();
		Integer categorie=null;
		User user=new User();
		int i=0;

		for (Categories categoriee : lCategories) {
			cat = new HashMap();
			cat.put("id", categoriee.getId());
			if(i==0){
				categorie=categoriee.getId();
			}
			cat.put("categorie", categoriee.getCategorie());
			cat.put("icon", categoriee.getIcon());
			cat.put("archiver", categoriee.isArchiver());
			categories.add(cat);
			i++;
		}
		if(req.getParameter("idCategorie")!=null){
			categorie=Integer.parseInt(req.getParameter("idCategorie"));
			
		}
		List<Module> listModule=new ArrayList();
		
		ModuleDAO mdao=new ModuleDAO();
		listModule= mdao.selectByCategorie(categorie);
		ArrayList ListeModule =new ArrayList();
		
		for (Module module : listModule) {
			cat = new HashMap();
			cat.put("id", module.getId());
			
			cat.put("categorie", module.getCategorie().getCategorie());
			cat.put("module", module.getModule());
			cat.put("image", module.getImage());
			cat.put("description", module.getDescription());
			ListeModule.add(cat);
			
		}
		
		req.setAttribute("listModule", ListeModule);
		req.setAttribute("categories", categories);
		try {

			user = (User) req.getSession(true).getAttribute("user");

			if (user == null) {
				req.getRequestDispatcher("login.jsp").forward(req, resp);

			} else {
				if (user.getStatus().equals("admin")) {
					 req.getRequestDispatcher("Admin").include(req, resp);
				}
				if (user.getStatus().equals("etudiant")) {
					req.getRequestDispatcher("/WEB-INF/frontOffice/indexx.jsp").forward(req, resp);

				}
				if (user.getStatus().equals("prof")) {
					req.getRequestDispatcher("prof.jsp").forward(req, resp);

				}
				
			}
		} catch (Exception e) {
			System.out.println("Error");

		}
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	

}