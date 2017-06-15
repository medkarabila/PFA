package ma.ensao.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import ma.ensao.entity.Categories;
import ma.ensao.entity.Chapitre;
import ma.ensao.entity.Matiere;
import ma.ensao.entity.MatiereModule;
import ma.ensao.entity.Module;
import ma.ensao.entity.User;
import ma.ensao.hibernateDAO.CategorieDAO;
import ma.ensao.hibernateDAO.ChapitreDAO;
import ma.ensao.hibernateDAO.LoginService;
import ma.ensao.hibernateDAO.MatiereDAO;
import ma.ensao.hibernateDAO.MatiereModuleDAO;
import ma.ensao.hibernateDAO.ModuleDAO;

public class AdminServlet extends HttpServlet {
	// StringBuffer path;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Categories c = new Categories();
		Chapitre ch = new Chapitre();
		ArrayList categories = new ArrayList();
		CategorieDAO DAO = new CategorieDAO();
		Map cat;
		int id = 0;
		String categorie = null;
		String chapitre = null;
		String mod = null;
		String description = null;
		String icon = null;
		String mat = null;
		ChapitreDAO chdao = new ChapitreDAO();
		String fichier = null;
		String question = null;
		Matiere matiere = new Matiere();
		User user=new User();
		Module mo = new Module();
		ModuleDAO modao = new ModuleDAO();
		MatiereDAO madao = new MatiereDAO();
		user = (User) req.getSession(true).getAttribute("user");
		
		
		
		
		if (req.getParameter("idC") != null) {
			if (req.getParameter("archiver").equals("1")) {

				// JOptionPane.showMessageDialog(null,request.getParameter("idC")
				// );

				// Categories c1 = new Categories();
				c = DAO.getCategorieById(Integer.parseInt(req.getParameter("idC")));
				id = c.getId();
				categorie = c.getCategorie();
				description = c.getDescription();
				icon = c.getIcon();

				DAO.updateData(id, categorie, description, icon, true);
			}
			if (req.getParameter("archiver").equals("0")) {

				c = DAO.getCategorieById(Integer.parseInt(req.getParameter("idC")));
				id = c.getId();
				categorie = c.getCategorie();
				description = c.getDescription();
				icon = c.getIcon();
				DAO.updateData(id, categorie, description, icon, false);
			}

		}

		if (req.getParameter("idCh") != null) {

			if (req.getParameter("archiver").equals("1")) {

				// JOptionPane.showMessageDialog(null,request.getParameter("idC")
				// );

				// Categories c1 = new Categories();
				ch = chdao.getChapitreById(Integer.parseInt(req.getParameter("idCh")));
				id = ch.getId();
				chapitre = ch.getChapitre();
				description = ch.getDescription();
				fichier = ch.getFichier();
				question = ch.getQuestion();
				matiere = ch.getMatiere();

				chdao.updateData(id, chapitre, description, fichier, question, matiere, true);
			}
			if (req.getParameter("archiver").equals("0")) {

				ch = chdao.getChapitreById(Integer.parseInt(req.getParameter("idCh")));
				id = ch.getId();
				chapitre = ch.getChapitre();
				description = ch.getDescription();
				fichier = ch.getFichier();
				question = ch.getQuestion();
				matiere = ch.getMatiere();
				chdao.updateData(id, chapitre, description, fichier, question, matiere, false);
			}

		}
		
		
		
		if (req.getParameter("idMo") != null) {

			if (req.getParameter("archiver").equals("1")) {

				
				mo = modao.getModuleById(Integer.parseInt(req.getParameter("idMo")));
				id = mo.getId();
				mod = mo.getModule();
				description = mo.getDescription();
				fichier = mo.getImage();
				c = mo.getCategorie();

				modao.updateData(id, mod, description, fichier, c, true);
			}
			if (req.getParameter("archiver").equals("0")) {

				mo = modao.getModuleById(Integer.parseInt(req.getParameter("idMo")));
				id = mo.getId();
				mod = mo.getModule();
				description = mo.getDescription();
				fichier = mo.getImage();
				c = mo.getCategorie();

				modao.updateData(id, mod, description, fichier, c, false);
			}

		}
		if (req.getParameter("idMa") != null) {

			if (req.getParameter("archiver").equals("1")) {

				
				matiere = madao.getMatiereById(Integer.parseInt(req.getParameter("idMa")));
				id = matiere.getId();
				mat = matiere.getMatiere();
				description = matiere.getDescription();
				fichier = matiere.getIcon();
				

				madao.updateData(id, mat, description, fichier, true);
			}
			if (req.getParameter("archiver").equals("0")) {

				matiere = madao.getMatiereById(Integer.parseInt(req.getParameter("idMa")));
				id = matiere.getId();
				mat = matiere.getMatiere();
				description = matiere.getDescription();
				fichier = matiere.getIcon();
				

				madao.updateData(id, mat, description, fichier, false);
			}

		}
		
		

		List<Categories> lCategories = new ArrayList();
		lCategories = DAO.selectAllData();

		for (Categories categoriee : lCategories) {
			cat = new HashMap();
			cat.put("id", categoriee.getId());
			cat.put("categorie", categoriee.getCategorie());
			cat.put("icon", categoriee.getIcon());
			cat.put("archiver", categoriee.isArchiver());
			categories.add(cat);
		}
		req.setAttribute("categories", categories);

		List<Module> modules = new ArrayList();
		ModuleDAO MDAO = new ModuleDAO();
		modules = MDAO.getModulesById(user.getId());

		ArrayList listeModule = new ArrayList();

		Map map;
		
		for (Module module : modules) {
			
			map = new HashMap();
			map.put("id", module.getId());
			map.put("module", module.getModule());
			map.put("description", module.getDescription());
			map.put("categorie", module.getCategorie());
			map.put("image", module.getImage());
			map.put("archiver", module.isArchiver());
			map.put("editeur", user.getId());
			
			listeModule.add(map);
		}
		req.setAttribute("listeModule", listeModule);
		
		List<Matiere> matieres = new ArrayList();
		MatiereDAO MatDAO = new MatiereDAO();
		matieres = MatDAO.getMatieresById(user.getId());

		ArrayList listeMatiere = new ArrayList();

		Map map1;
		for (Matiere matiere1 : matieres) {
			map1 = new HashMap();
			map1.put("id", matiere1.getId());
			map1.put("matiere", matiere1.getMatiere());
			map1.put("description", matiere1.getDescription());
			map1.put("image", matiere1.getIcon());
			map1.put("archiver", matiere1.isArchiver());
			map1.put("editeur", user.getId());
			listeMatiere.add(map1);
		}
		req.setAttribute("listeMatiere", listeMatiere);
		
		
		
		
		
		List<Chapitre> chapitres = new ArrayList();
		ChapitreDAO CHDAO = new ChapitreDAO();
		chapitres = CHDAO.selectAllData();

		ArrayList listeChapitre = new ArrayList();

		Map map3;
		for (Chapitre chapitre1 : chapitres) {
			map3 = new HashMap();
			map3.put("id", chapitre1.getId());
			map3.put("chapitre", chapitre1.getChapitre());
			map3.put("description", chapitre1.getDescription());
			map3.put("archiver", chapitre1.isArchiver());
			map3.put("fichier", chapitre1.getFichier());
			listeChapitre.add(map3);
		}
		req.setAttribute("listeChapitre", listeChapitre);
		if (req.getParameter("idMatiereForAdd") != null) {
			try {
				Integer matieree = Integer.parseInt(req.getParameter("idMatiereForAdd"));
				Integer module = Integer.parseInt(req.getParameter("module"));

				ModuleDAO MoDAO = new ModuleDAO();
				MatiereDAO MaDAO = new MatiereDAO();
				MatiereModuleDAO MaMoDAO = new MatiereModuleDAO();

				Module Module = new Module();
				Matiere Matiere = new Matiere();

				Module = MoDAO.getModuleById(module);
				Matiere = MaDAO.getMatiereById(matieree);

				MatiereModule MatMod = new MatiereModule(Module, Matiere);
				MaMoDAO.saveMatiere(MatMod);

				ArrayList<Map> ListeDesModule = new ArrayList();
				ListeDesModule = selectModule(module);
				for (Map mod1 : ListeDesModule) {
					System.out.println("module:" + mod1.get("module"));
				}

				req.setAttribute("moduleForUpdate", ListeDesModule);
				ArrayList<Map> ListeModuleMatiere = new ArrayList();
				ListeModuleMatiere = moduleMatiere(module);
				req.setAttribute("moduleMatieres", ListeModuleMatiere);

			} catch (Exception e) {
				System.out.println("aucune matiere a ajouter");
			}
		}
		if (req.getParameter("idModuleForAdd") != null) {
			try {
				Integer modulee = Integer.parseInt(req.getParameter("idModuleForAdd"));
				Integer matieree = Integer.parseInt(req.getParameter("matiere"));

				ModuleDAO MoDAO = new ModuleDAO();
				MatiereDAO MaDAO = new MatiereDAO();
				MatiereModuleDAO MaMoDAO = new MatiereModuleDAO();

				Module Module = new Module();
				Matiere Matiere = new Matiere();

				Matiere = MaDAO.getMatiereById(matieree);
				Module = MoDAO.getModuleById(modulee);

				MatiereModule MatMod = new MatiereModule(Matiere, Module);
				MaMoDAO.saveMatiere(MatMod);

				ArrayList<Map> ListeDesMatiere = new ArrayList();
				ListeDesMatiere = selectMatiere(matieree);
				for (Map mat1 : ListeDesMatiere) {
					System.out.println("matiere:" + mat1.get("matiere"));
				}

				req.setAttribute("matiereForUpdate", ListeDesMatiere);
				ArrayList<Map> ListeModuleMatiere = new ArrayList();
				ListeModuleMatiere = matiereModule(matieree);
				req.setAttribute("matiereModules", ListeModuleMatiere);

			} catch (Exception e) {
				System.out.println("aucun module a ajouter");
			}
		}
		

		

		if (req.getParameter("moduleMatiere") != null) {
			try {
				Integer moduleMatiere = Integer.parseInt(req.getParameter("moduleMatiere"));
				System.out.println("Module matiere:" + moduleMatiere);
				MatiereModule matiereModule = new MatiereModule();
				MatiereModuleDAO MaMoDAO = new MatiereModuleDAO();
				matiereModule = MaMoDAO.selectById(moduleMatiere);

				MaMoDAO.deleteModuleMatiere(matiereModule);

				if (!req.getParameter("module").equals(null)) {

					Integer idModule = Integer.parseInt(req.getParameter("module"));

					ArrayList<Map> ListeDesModule = new ArrayList();
					ListeDesModule = selectModule(idModule);
					for (Map mod1 : ListeDesModule) {
						System.out.println("module:" + mod1.get("module"));
					}

					req.setAttribute("moduleForUpdate", ListeDesModule);
					ArrayList<Map> ListeModuleMatiere = new ArrayList();
					ListeModuleMatiere = moduleMatiere(idModule);
					req.setAttribute("moduleMatieres", ListeModuleMatiere);
				}
			} catch (Exception e) {
				System.out.println("Module introuvable");
			}
		}
		
		if (req.getParameter("matiereModuleForDelete") != null) {
			try {
				Integer matiereModule = Integer.parseInt(req.getParameter("matiereModuleForDelete"));
				System.out.println("Matiere module:" + matiereModule);
				MatiereModule moduleMatiere = new MatiereModule();
				MatiereModuleDAO MaMoDAO = new MatiereModuleDAO();
				moduleMatiere = MaMoDAO.selectById(matiereModule);
				
				MaMoDAO.deleteModuleMatiere(moduleMatiere);

				if (!req.getParameter("matiere").equals(null)) {

					Integer idMatiere = Integer.parseInt(req.getParameter("matiere"));

					ArrayList<Map> ListeDesMatiere = new ArrayList();
					ListeDesMatiere = selectMatiere(idMatiere);
					for (Map mat1 : ListeDesMatiere) {
						System.out.println("matiere:" + mat1.get("matiere"));
					}

					req.setAttribute("matiereForUpdate", ListeDesMatiere);
					ArrayList<Map> ListeModuleMatiere = new ArrayList();
					ListeModuleMatiere = moduleMatiere(idMatiere);
					req.setAttribute("matiereModules", ListeModuleMatiere);
				}
			} catch (Exception e) {
				System.out.println("Matiere introuvable");
			}
		}
		
		try {

			user = (User) req.getSession(true).getAttribute("user");

			if (user == null) {
				req.getRequestDispatcher("login.jsp").forward(req, resp);

			} else {
				if (user.getStatus().equals("admin")) {
					 req.getRequestDispatcher("/WEB-INF/backOffice/index.jsp").include(req, resp);
				}
				if (user.getStatus().equals("etudiant")) {
					req.getRequestDispatcher("Front").forward(req, resp);

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
		String fc = null;
		String idM = null;
		String fichier = null;
		String mod = null;
		ArrayList<Map> listMatiere = new ArrayList();
		String choix = null;
		String valueChoix = null;
		int id = 0;
		String mat = null;
		String descriptionn = null;
		String imagee = null;
		String choiss = null;
		boolean archive = false;
		int nbrSelect = 1;
		Chapitre chapitre = new Chapitre();
		ArrayList<String> modules = new ArrayList();
		MatiereDAO mdao = new MatiereDAO();
		Map map;
		String f = null;
		Matiere matiere = new Matiere();
		ArrayList<Map> listModule = new ArrayList();
		List<FileItem> items = null;
		String description = null;
		String image = null;
		String catt = null;
		String img = null;
		String idC = null;
		Categories categorie = new Categories();
		CategorieDAO CD = new CategorieDAO();
		String matt = null;
		String categ = null;
		Integer editeur = null;
		Module module = new Module();
		String chap = null;
		String question = null;
		ArrayList<String> list = new ArrayList();
		ChapitreDAO chdao = new ChapitreDAO();
		ModuleDAO MODAO =new ModuleDAO();

		/*------- update Module ----------*/

		/*		
				*/
		/*------- fin update Module -------*/

		try {
			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
			for (FileItem item : items) {

				if (item.isFormField()) {

					String nom = item.getFieldName();
					if (nom.equals("choix")) {
						valueChoix = item.getString();

						if (valueChoix.equals("nlleCategorie")) {
							try {
								if (items != null) {
									// System.out.println("aaa");
									for (FileItem itemm : items) {
										if (itemm.isFormField()) {

											String nom1 = itemm.getFieldName();

											if (nom1.equals("categorie")) {
												catt = itemm.getString();
												// System.out.println(catt);

											}
											if (nom1.equals("desc")) {
												description = itemm.getString();
											}
											if (nom1.equals("icon")) {
												image = itemm.getString();
											}

										}

										else {

											img = FilenameUtils.getName(itemm.getName());

											if (!img.equals("")) {
												image = img;
												// JOptionPane.showMessageDialog(null,image);
												File uploadDir = new File(
														"/Users/mac/Documents/workspacepfa/e_learning_pfa_2/WebContent/images");
												File file = File.createTempFile("img", image, uploadDir);

												image = file.getName();
												list.add(file.getName());
												itemm.write(file);

											}

										}

									}

								}

							} catch (FileUploadException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							categorie.setCategorie(catt);
							categorie.setDescription(description);
							categorie.setIcon(image);

							CD.saveData(categorie);

							doGet(req, resp);
						}

						if (valueChoix.equals("nouveauModule")) {

							try {

								if (items != null) {
									// System.out.println("aaaa");
									for (FileItem itemm : items) {
										map = new HashMap();
										if (itemm.isFormField()) {

											String nom1 = itemm.getFieldName();

											map.put("id", itemm.getFieldName());
											map.put("value", itemm.getString());
											listMatiere.add(map);

											if (nom1.equals("module")) {
												mod = itemm.getString();
												// System.out.println(mat);
											}

											if (nom1.equals("description")) {
												descriptionn = itemm.getString();
											}

											if (nom1.equals("categorie")) {
												categ = itemm.getString();
												
											}
											if (nom1.equals("editeur")) {
											
												editeur = Integer.parseInt(itemm.getString());
												
											}

										} else {

											img = FilenameUtils.getName(itemm.getName());

											if (!img.equals("")) {
												image = img;
												// JOptionPane.showMessageDialog(null,image);
												File uploadDir = new File(
														"/Users/mac/Documents/workspacepfa/e_learning_pfa_2/WebContent/images");
												File file = File.createTempFile("img", image, uploadDir);

												image = file.getName();
												list.add(file.getName());
												itemm.write(file);

											}
										}
									}
								}

							} catch (FileUploadException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							Categories categoriee = new Categories();
							CategorieDAO cdao = new CategorieDAO();
							categoriee = cdao.getCategorieById(Integer.parseInt(categ));

							module.setDescription(descriptionn);
							module.setImage(image);
							module.setModule(mod);
							module.setCategorie(categoriee);
							module.setArchiver(archive);
							module.setEditeur(editeur);
							ModuleDAO ModDAO = new ModuleDAO();
							ModDAO.saveModule(module);
							List<Module> Modules = new ArrayList();
							Modules = ModDAO.getModules();
							Module Module = null;
							for (Module Mod : Modules) {
								Module = Mod;
							}
							MatiereDAO MatDAO = null;
							MatiereModule matiereModule = null;
							MatiereModuleDAO MaMoDAO = new MatiereModuleDAO();

							for (Map ma : listMatiere) {

								if (ma.get("id").equals(ma.get("value"))) {

									try {
										Matiere matieree = new Matiere();
										MatDAO = new MatiereDAO();
										matieree = MatDAO.getMatiereById(Integer.parseInt(ma.get("id").toString()));

										matiereModule = new MatiereModule(Module, matieree);
										MaMoDAO.saveModule(matiereModule);
									} catch (Exception e) {
										System.out.println("Module Introuvable dans la table des modules");
									}

								}

							}
							doGet(req, resp);
						}
						if (valueChoix.equals("nlleMatiere")) {

							try {

								if (items != null) {
									// System.out.println("aaaa");
									for (FileItem itemm : items) {
										map = new HashMap();
										if (itemm.isFormField()) {

											String nom1 = itemm.getFieldName();

											map.put("id", itemm.getFieldName());
											map.put("value", itemm.getString());
											listModule.add(map);

											if (nom1.equals("matiere")) {
												mat = itemm.getString();
												// System.out.println(mat);
											}

											if (nom1.equals("description")) {
												descriptionn = itemm.getString();
											}
											if (nom1.equals("editeur")) {
												
												editeur = Integer.parseInt(itemm.getString());
												
											}

										} else {

											imagee = FilenameUtils.getName(itemm.getName());

											File uploadDir = new File(
													"/Users/mac/Documents/workspacepfa/e_learning_pfa_2/WebContent/images");
											File file = File.createTempFile("img", imagee, uploadDir);

											imagee = file.getName();

											list.add(file.getName());
											itemm.write(file);
										}
									}
								}

							} catch (FileUploadException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							// System.out.println(mat);
							// System.out.println(description);

							// System.out.println(choiss);

							matiere.setDescription(descriptionn);
							matiere.setIcon(imagee);
							matiere.setMatiere(mat);
							matiere.setArchiver(archive);
							matiere.setEditeur(editeur);

							MatiereDAO MDAO = new MatiereDAO();
							MDAO.saveMatiere(matiere);
							List<Matiere> Matieres = new ArrayList();
							Matieres = MDAO.selectAllData();
							Matiere Matiere = null;
							for (Matiere Mat : Matieres) {
								Matiere = Mat;
							}
							ModuleDAO MoDAO = null;
							MatiereModule matiereModule = null;
							MatiereModuleDAO MaMoDAO = new MatiereModuleDAO();

							for (Map ma : listModule) {

								if (ma.get("id").equals(ma.get("value"))) {

									try {
										Module module1 = new Module();
										MoDAO = new ModuleDAO();
										module1 = MoDAO.getModuleById(Integer.parseInt(ma.get("id").toString()));

										matiereModule = new MatiereModule(Matiere, module1);
										MaMoDAO.saveMatiere(matiereModule);
									} catch (Exception e) {
										System.out.println("Module Introuvable dans la table des modules");
									}

								}

							}
							doGet(req, resp);
						}
						if (valueChoix.equals("updateCategorie")) {

							try {
								if (items != null) {
									// System.out.println("aaa");
									for (FileItem itemm : items) {
										if (itemm.isFormField()) {

											String nom1 = itemm.getFieldName();
											if (nom1.equals("idCategorie")) {
												idC = itemm.getString();
												// System.out.println(catt);

											}

											if (nom1.equals("categorie")) {
												catt = itemm.getString();
												// System.out.println(catt);

											}
											if (nom1.equals("desc")) {
												description = itemm.getString();
											}
											if (nom1.equals("img")) {
												image = itemm.getString();
											}
											if (nom1.equals("icon")) {
												image = itemm.getString();
											}

											String nomPrenomValue = itemm.getString();
											list.add(nomPrenomValue);

										}

										else {

											img = FilenameUtils.getName(itemm.getName());

											if (!img.equals("")) {
												image = img;
												// JOptionPane.showMessageDialog(null,image);
												File uploadDir = new File(
														"/Users/mac/Documents/workspacepfa/e_learning_pfa_2/WebContent/images");
												File file = File.createTempFile("img", image, uploadDir);

												image = file.getName();
												list.add(file.getName());
												itemm.write(file);

											}

										}

									}

								}

							} catch (FileUploadException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							CD.updateData(Integer.parseInt(idC), catt, description, image, false);
							doGet(req, resp);

						}
						
						if (valueChoix.equals("updateModule")) {

							try {
								if (items != null) {
									// System.out.println("aaa");
									for (FileItem itemm : items) {
										if (itemm.isFormField()) {

											String nom1 = itemm.getFieldName();
											
											if (nom1.equals("idCategorie")) {
												idM = itemm.getString();
												// System.out.println(catt);

											}

											if (nom1.equals("module")) {
												mod = itemm.getString();
												// System.out.println(catt);

											}
											if (nom1.equals("description")) {
												description = itemm.getString();
											}
											if (nom1.equals("icon")) {
												image = itemm.getString();
											}
											if (nom1.equals("img")) {
												image = itemm.getString();
											}
											
											if (nom1.equals("categorie")) {
												catt = itemm.getString();
											}
											String nomPrenomValue = itemm.getString();
											list.add(nomPrenomValue);

										}

										else {

											img = FilenameUtils.getName(itemm.getName());

											if (!img.equals("")) {
												image = img;
												// JOptionPane.showMessageDialog(null,image);
												File uploadDir = new File(
														"/Users/mac/Documents/workspacepfa/e_learning_pfa_2/WebContent/images");
												File file = File.createTempFile("img", image, uploadDir);

												image = file.getName();
												list.add(file.getName());
												itemm.write(file);

											}

										}

									}

								}

							} catch (FileUploadException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							categorie=CD.getCategorieById(Integer.parseInt(catt));
							MODAO.updateData(Integer.parseInt(idM), mod, description, image,categorie, false);
							doGet(req, resp);

						}
						
						if (valueChoix.equals("updateMatiere")) {

							try {
								if (items != null) {
									// System.out.println("aaa");
									for (FileItem itemm : items) {
										if (itemm.isFormField()) {

											String nom1 = itemm.getFieldName();
											
											if (nom1.equals("idCategorie")) {
												idM = itemm.getString();
												// System.out.println(catt);

											}

											if (nom1.equals("matiere")) {
												mat = itemm.getString();
												// System.out.println(catt);

											}
											if (nom1.equals("description")) {
												description = itemm.getString();
											}
											if (nom1.equals("icon")) {
												image = itemm.getString();
											}
											if (nom1.equals("img")) {
												image = itemm.getString();
											}
											
											
											String nomPrenomValue = itemm.getString();
											list.add(nomPrenomValue);

										}

										else {

											img = FilenameUtils.getName(itemm.getName());

											if (!img.equals("")) {
												image = img;
												// JOptionPane.showMessageDialog(null,image);
												File uploadDir = new File(
														"/Users/mac/Documents/workspacepfa/e_learning_pfa_2/WebContent/images");
												File file = File.createTempFile("img", image, uploadDir);

												image = file.getName();
												list.add(file.getName());
												itemm.write(file);

											}

										}

									}

								}

							} catch (FileUploadException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							mdao.updateData(Integer.parseInt(idM), mat, description, image, false);
							doGet(req, resp);

						}
						
						if (valueChoix.equals("nouveauChapitre")) {

							try {
								if (items != null) {
									// System.out.println("aaa");
									for (FileItem itemm : items) {
										if (itemm.isFormField()) {

											String nom1 = itemm.getFieldName();

											if (nom1.equals("chapitre")) {
												chap = itemm.getString();
												// System.out.println(catt);

											}
											if (nom1.equals("desc")) {
												description = itemm.getString();
											}
											if (nom1.equals("question")) {
												question = itemm.getString();
											}

											if (nom1.equals("selectMatiereFormUpdate")) {
												idM = itemm.getString();
											}

										}

										else {

											fichier = FilenameUtils.getName(itemm.getName());

											File uploadDir = new File(
													"/Users/mac/Documents/workspacepfa/e_learning_pfa_2/WebContent/images");
											File file = File.createTempFile("pdf", fichier, uploadDir);

											fichier = file.getName();

											list.add(file.getName());
											itemm.write(file);

										}

									}

								}

							} catch (FileUploadException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							matiere = mdao.getMatiereById(Integer.parseInt(idM));
							chapitre.setChapitre(chap);
							chapitre.setDescription(description);
							chapitre.setQuestion(question);
							chapitre.setMatiere(matiere);
							chapitre.setFichier(fichier);

							chdao.saveChapitre(chapitre);

							doGet(req, resp);

						}
						if (valueChoix.equals("updateChapitre")) {

							try {
								if (items != null) {
									// System.out.println("aaa");
									for (FileItem itemm : items) {
										if (itemm.isFormField()) {

											String nom1 = itemm.getFieldName();
											if (nom1.equals("idChapitre")) {
												idC = itemm.getString();
												// System.out.println(catt);

											}

											if (nom1.equals("chapitre")) {
												chap = itemm.getString();
												// System.out.println(catt);

											}
											if (nom1.equals("desc")) {
												description = itemm.getString();
											}
											if (nom1.equals("question")) {
												question = itemm.getString();
											}
											if (nom1.equals("img")) {
												image = itemm.getString();
											}
											if (nom1.equals("icon")) {
												image = itemm.getString();
											}

											if (nom1.equals("selectMatiereFormUpdate")) {
												idM = itemm.getString();
											}

										}

										else {

											img = FilenameUtils.getName(itemm.getName());

											if (!img.equals("")) {
												image = img;
												// JOptionPane.showMessageDialog(null,image);
												File uploadDir = new File(
														"/Users/mac/Documents/workspacepfa/e_learning_pfa_2/WebContent/images");
												File file = File.createTempFile("pdf", image, uploadDir);

												image = file.getName();
												list.add(file.getName());
												itemm.write(file);

											}

										}

									}

								}

							} catch (FileUploadException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							matiere = mdao.getMatiereById(Integer.parseInt(idM));
							chdao.updateData(Integer.parseInt(idC), chap, description, image, question, matiere, false);
							doGet(req, resp);

						}
					}

				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * System.out.println("" + "Module:"+mat + "\nDescription:"+description
		 * +"\nimage:"+image);
		 * 
		 * 
		 * 
		 * 
		 * matiere.setDescription(description); matiere.setIcon(image);
		 * matiere.setMatiere(mat); matiere.setArchiver(archive);
		 * 
		 * MatiereDAO MDAO=new MatiereDAO(); MDAO.saveMatiere(matiere);
		 * List<Matiere> Matieres=new ArrayList();
		 * Matieres=MDAO.selectAllData(); Matiere Matiere=null; for(Matiere
		 * Mat:Matieres){ Matiere=Mat; } ModuleDAO MoDAO=null; MatiereModule
		 * matiereModule=null; MatiereModuleDAO MaMoDAO=new MatiereModuleDAO();
		 * 
		 * for (Map ma : listModule) {
		 * 
		 * if(ma.get("id").equals(ma.get("value"))){ System.out.println(
		 * "id Module:"+ma.get("id")+"| Value Module:"+ma.get("value")); try{
		 * Module module=new Module(); MoDAO=new ModuleDAO();
		 * module=MoDAO.getModuleById(Integer.parseInt(ma.get("id").toString()))
		 * ;
		 * 
		 * matiereModule=new MatiereModule(Matiere, module);
		 * MaMoDAO.saveMatiere(matiereModule); }catch(Exception e){
		 * System.out.println("Module Introuvable dans la table des modules"); }
		 * 
		 * }
		 * 
		 * 
		 * }
		 */

		if (req.getParameter("selectCategorieFormUpdate") != null) {
			// id = Integer.parseInt(request.getParameter("categorie"));
			// request.setAttribute("id", id);
			Map catego = new HashMap();
			CategorieDAO cdao = new CategorieDAO();
			Categories categoriee = new Categories();
			id = Integer.parseInt(req.getParameter("selectCategorieFormUpdate"));
			categoriee = cdao.getCategorieById(id);
			catego.put("id", categoriee.getId());
			catego.put("categorie", categoriee.getCategorie());
			catego.put("description", categoriee.getDescription());
			catego.put("icon", categoriee.getIcon());

			req.setAttribute("categorie", categoriee);
			doGet(req, resp);

		}
		if (req.getParameter("selectChapitreFormUpdate") != null) {
			// id = Integer.parseInt(request.getParameter("categorie"));
			// request.setAttribute("id", id);
			Map chapi = new HashMap();
			id = Integer.parseInt(req.getParameter("selectChapitreFormUpdate"));
			chapitre = chdao.getChapitreById(id);
			chapi.put("id", chapitre.getId());
			chapi.put("categorie", chapitre.getChapitre());
			chapi.put("description", chapitre.getDescription());
			chapi.put("fichier", chapitre.getFichier());

			req.setAttribute("chapitre", chapitre);
			doGet(req, resp);

		}
		if (req.getParameter("selectModuleForUpdate") != null) {
			Integer idModule = Integer.parseInt(req.getParameter("selectModuleForUpdate").toString());
			ArrayList<Map> ListeDesModule = new ArrayList();
			ListeDesModule = selectModule(idModule);
			for (Map mod1 : ListeDesModule) {
				System.out.println("module:" + mod1.get("module"));
			}

			req.setAttribute("moduleForUpdate", ListeDesModule);
			ArrayList<Map> ListeModuleMatiere = new ArrayList();
			ListeModuleMatiere = moduleMatiere(idModule);
			req.setAttribute("moduleMatieres", ListeModuleMatiere);
			doGet(req, resp);
			// req.getRequestDispatcher("/WEB-INF/backOffice/index.jsp").forward(req,
			// resp);
		}
		if (req.getParameter("selectMatiereForUpdate") != null) {
			Integer idMatiere = Integer.parseInt(req.getParameter("selectMatiereForUpdate").toString());
			ArrayList<Map> ListeDesMatiere = new ArrayList();
			ListeDesMatiere = selectMatiere(idMatiere);
			for (Map mat1 : ListeDesMatiere) {
				System.out.println("matiere:" + mat1.get("matiere"));
			}

			req.setAttribute("matiereForUpdate", ListeDesMatiere);
			ArrayList<Map> ListeModuleMatiere = new ArrayList();
			ListeModuleMatiere = matiereModule(idMatiere);
			req.setAttribute("matiereModules", ListeModuleMatiere);
			doGet(req, resp);
			// req.getRequestDispatcher("/WEB-INF/backOffice/index.jsp").forward(req,
			// resp);
		}

	}

	private ArrayList moduleMatiere(Integer module) {

		ArrayList listeMatiereModule = new ArrayList();
		MatiereModuleDAO MaMoDAO = new MatiereModuleDAO();

		List<MatiereModule> matiereModule = new ArrayList();
		matiereModule = MaMoDAO.selectByModule(module);
		Map ma = null;
		for (MatiereModule matiere : matiereModule) {
			ma = new HashMap();

			ma.put("id", matiere.getId());
			ma.put("matiere", matiere.getMatiere().getMatiere());
			ma.put("module", matiere.getModule().getId());
			ma.put("idMatiere", matiere.getMatiere().getId());
			listeMatiereModule.add(ma);

		}

		return listeMatiereModule;
	}
	private ArrayList matiereModule(Integer matiere) {

		ArrayList listeMatiereModule = new ArrayList();
		MatiereModuleDAO MaMoDAO = new MatiereModuleDAO();

		List<MatiereModule> matiereModule = new ArrayList();
		matiereModule = MaMoDAO.selectByMatiere(matiere);
		Map ma = null;
		for (MatiereModule module : matiereModule) {
			ma = new HashMap();

			ma.put("id", module.getId());
			ma.put("module", module.getModule().getModule());
			ma.put("matiere", module.getMatiere().getId());
			ma.put("idModule", module.getModule().getId());
			//JOptionPane.showMessageDialog(null, ma);
			listeMatiereModule.add(ma);

		}

		return listeMatiereModule;
	}

	private ArrayList selectModule(Integer idModule) {
		ArrayList module = new ArrayList();
		Module Module = new Module();
		ModuleDAO MDAO = new ModuleDAO();
		Map ma = new HashMap();

		Module = MDAO.getModuleById(idModule);

		ma.put("id", Module.getId());
		ma.put("module", Module.getModule());
		ma.put("description", Module.getDescription());
		ma.put("image", Module.getImage());
		ma.put("idCategorie", Module.getCategorie().getId());
		ma.put("categorie", Module.getCategorie().getCategorie());

		module.add(ma);

		return module;
	}
	private ArrayList selectMatiere(Integer idMatiere) {
		ArrayList matiere = new ArrayList();
		Matiere Matiere = new Matiere();
		MatiereDAO MDAO = new MatiereDAO();
		Map ma = new HashMap();

		Matiere = MDAO.getMatiereById(idMatiere);

		ma.put("id", Matiere.getId());
		ma.put("matiere", Matiere.getMatiere());
		ma.put("description", Matiere.getDescription());
		ma.put("image", Matiere.getIcon());
		//ma.put("idCategorie", Module.getCategorie().getId());
		//ma.put("categorie", Module.getCategorie().getCategorie());

		matiere.add(ma);

		return matiere;
	}

}
