package ma.ensao.hibernateDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import ma.ensao.entity.Categories;
import ma.ensao.entity.Matiere;
import ma.ensao.entity.MatiereModule;
import ma.ensao.entity.Module;
import ma.ensao.util.HibernateUtil;

public class MatiereModuleDAO {
	Session session;
	private Session setSession(){
		Session session=HibernateUtil.getSessionFactory().openSession();
		return session;
	}
	
	public void saveMatiere(MatiereModule matiereModule){
		session=setSession();
		try{
			session.beginTransaction();
			session.persist(matiereModule);
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
	
	public void saveModule(MatiereModule matiereModule){
		session=setSession();
		try{
			session.beginTransaction();
			session.persist(matiereModule);
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
	
	public List<MatiereModule> selectByModule(Integer module){
		List<MatiereModule> listeMatiereModule=new ArrayList();
		
		session=setSession();
		try{
			session.beginTransaction();
			
			Query<MatiereModule> query=session.createQuery("From MatiereModule as matiere where matiere.module="+module);
			listeMatiereModule=query.list();
			session.getTransaction().commit();
			
			return listeMatiereModule;
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
	public List<MatiereModule> selectByMatiere(Integer matiere){
		List<MatiereModule> listeMatiereModule=new ArrayList();
		
		session=setSession();
		try{
			session.beginTransaction();
			
			Query<MatiereModule> query=session.createQuery("From MatiereModule as module where module.matiere="+matiere);
			listeMatiereModule=query.list();
			session.getTransaction().commit();
			
			return listeMatiereModule;
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
	
	public MatiereModule selectById(Integer id){
		MatiereModule moduleMatiere=null;
		session=setSession();
		try{
			
			session.beginTransaction();
			moduleMatiere=(MatiereModule)session.get(MatiereModule.class,id);
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			if(session.getTransaction()!=null){
				session.getTransaction().rollback();
			}
			he.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return moduleMatiere;
	}
	
	public void deleteModuleMatiere(MatiereModule MatiereModule){
		session=setSession();
		try{
			session.beginTransaction();
			session.delete(MatiereModule);
			session.getTransaction().commit();
			
		}catch(HibernateException he){
			if(session.getTransaction()!=null){
				session.getTransaction().rollback();
				
			}
			he.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
	}
	public List<MatiereModule> selectByMatiereModule(Integer module){
		List<MatiereModule> listMatiereModule=new ArrayList();
		Session session = setSession();

		session=setSession();
		try{
			session.beginTransaction();
			
			Query<MatiereModule> query=session.createQuery("From MatiereModule as matieremodule where matieremodule.module="+module);
			listMatiereModule=query.list();
			session.getTransaction().commit();
			
			return listMatiereModule;
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
