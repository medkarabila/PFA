package ma.ensao.hibernateDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import ma.ensao.entity.Categories;
import ma.ensao.entity.Matiere;
import ma.ensao.entity.Module;
import ma.ensao.util.HibernateUtil;

public class MatiereDAO {
Session session;
	private Session setSession(){
		Session session=HibernateUtil.getSessionFactory().openSession();
		return session;
	}
	//enregistrer des donnees dans la table categories
	public void saveMatiere(Matiere matiere){
		session=setSession();
		try{
			session.beginTransaction();
			session.persist(matiere);
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
	public List<Matiere> selectAllData(){
		List<Matiere> matieres=new ArrayList();
		
		session=setSession();
		try{
			session.beginTransaction();
			
			Query<Matiere> query=session.createQuery("From Matiere");
			matieres=query.list();
			session.getTransaction().commit();
			
			return matieres;
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
	public List<Matiere> getMatieresById(Long id){
		List<Matiere> matieres=new ArrayList();
		
		session=setSession();
		try{
			session.beginTransaction();
			
			Query<Matiere> query=session.createQuery("From Matiere where editeur="+id);
			matieres=query.list();
			session.getTransaction().commit();
			
			return matieres;
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
	
	
	public Matiere getMatiereById(Integer id){
		Matiere matiere=null;
		Session session=setSession();
		try{
			session.beginTransaction();
			matiere=(Matiere)session.get(Matiere.class, id);
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
	
		return matiere;
	}
	public void updateData(int id , String matiere, String description , String icon , boolean archiver){
		Matiere mat = new Matiere() ; 
		Session session = setSession();
		
		try{
			session.beginTransaction();
			mat = session.get(Matiere.class, id);
			mat.setId(id);
			mat.setMatiere(matiere);
			mat.setDescription(description);
			mat.setIcon(icon);
			mat.setArchiver(archiver);
			session.update(mat);
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
