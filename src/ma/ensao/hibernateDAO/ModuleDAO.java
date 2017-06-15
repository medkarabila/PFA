package ma.ensao.hibernateDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import ma.ensao.entity.Categories;
import ma.ensao.entity.MatiereModule;
import ma.ensao.entity.Module;
import ma.ensao.util.HibernateUtil;

public class ModuleDAO {

	private Session setSession(){
		Session session=HibernateUtil.getSessionFactory().openSession();
		return session;
	}
	public Module getModuleById(Integer id){
		Module module=null;
		Session session=setSession();
		try{
			session.beginTransaction();
			module=session.get(Module.class, id);
			session.getTransaction().commit();
		}catch(HibernateException he){
			he.printStackTrace();
			if(session.getTransaction()!=null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
		return module;
	}
	public List<Module> getModulesById(Long id){
		List<Module> modules=null;
		Session session=setSession();
		try{
			modules=new ArrayList();
			session.beginTransaction();
			Query<Module> query=session.createQuery("From Module where editeur="+id);
			modules=query.list();
			session.getTransaction().commit();
		}catch(HibernateException he){
			he.printStackTrace();
			if(session.getTransaction()!=null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return modules;
	}
	public List<Module> getModules(){
		List<Module> modules=null;
		Session session=setSession();
		try{
			modules=new ArrayList();
			session.beginTransaction();
			Query<Module> query=session.createQuery("From Module");
			modules=query.list();
			session.getTransaction().commit();
		}catch(HibernateException he){
			he.printStackTrace();
			if(session.getTransaction()!=null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return modules;
	}
	
	public void saveModule(Module module){
		Session session=setSession();
		try{
			session.beginTransaction();
			session.persist(module);
			session.getTransaction().commit();
		}catch(HibernateException he){
			he.printStackTrace();
			if(session.getTransaction()!=null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}
	
	public void updateData(int id , String module, String description , String image ,Categories categorie , boolean archiver){
		Module mod = new Module() ; 
		Session session = setSession();
		
		try{
			session.beginTransaction();
			mod = session.get(Module.class, id);
			mod.setId(id);
			mod.setModule(module);
			mod.setDescription(description);
			mod.setImage(image);
			mod.setCategorie(categorie);
			mod.setArchiver(archiver);
			session.update(mod);
			session.getTransaction().commit();
			
			
		}catch(HibernateException hb){
			hb.printStackTrace();
			if(session.getTransaction()!=null){
				session.getTransaction().rollback();
			}
			
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
	}
	public List<Module> selectByCategorie(Integer categorie){
		List<Module> listModule=new ArrayList();
		Session session = setSession();

		session=setSession();
		try{
			session.beginTransaction();
			
			Query<Module> query=session.createQuery("From Module as module where module.categorie="+categorie);
			listModule=query.list();
			session.getTransaction().commit();
			
			return listModule;
		}catch(HibernateException he){
			
			he.printStackTrace();
			if(session.getTransaction()!=null){
				session.getTransaction().rollback();
			}
			
			return null;
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}
	
}
