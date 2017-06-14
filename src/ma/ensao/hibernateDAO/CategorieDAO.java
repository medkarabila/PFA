package ma.ensao.hibernateDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import ma.ensao.entity.Categories;
import ma.ensao.util.HibernateUtil;

public class CategorieDAO {
Session session;
	private Session setSession(){
		Session session=HibernateUtil.getSessionFactory().openSession();
		return session;
	}
	//enregistrer des donnees dans la table categories
	public void saveData(Categories categorie){
		session=setSession();
		try{
			session.beginTransaction();
			session.persist(categorie);
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
	//selectionner la liste des donnees
	public List<Categories> selectAllData(){
		List<Categories> categories=new ArrayList();
		
		session=setSession();
		try{
			session.beginTransaction();
			
			Query<Categories> query=session.createQuery("From Categories");
			categories=query.list();
			session.getTransaction().commit();
			
			return categories;
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
	public Categories getCategorieById(Integer id){
		Categories categorie=null;
		Session session=setSession();
		try{
			session.beginTransaction();
			categorie=(Categories)session.get(Categories.class, id);
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
	
		return categorie;
	}
	public void updateData(int id , String categorie, String description , String icon , boolean archiver){
		Categories cat = new Categories() ; 
		Session session = setSession();
		
		try{
			session.beginTransaction();
			cat = session.get(Categories.class, id);
			cat.setId(id);
			cat.setCategorie(categorie);
			cat.setDescription(description);
			cat.setIcon(icon);
			cat.setArchiver(archiver);
			session.update(cat);
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
	
}
