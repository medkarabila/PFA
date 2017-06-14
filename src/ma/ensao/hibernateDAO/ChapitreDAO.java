package ma.ensao.hibernateDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import ma.ensao.entity.Categories;
import ma.ensao.entity.Chapitre;
import ma.ensao.entity.Matiere;
import ma.ensao.util.HibernateUtil;

public class ChapitreDAO {
	Session session;

	private Session setSession() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return session;
	}

	// enregistrer des donnees dans la table categories
	public void saveChapitre(Chapitre chapitre) {
		session = setSession();
		try {
			session.beginTransaction();
			session.persist(chapitre);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// selectionner la liste des donnees
	public List<Chapitre> selectAllData() {
		List<Chapitre> chapitres = new ArrayList();

		session = setSession();
		try {
			session.beginTransaction();

			Query<Chapitre> query = session.createQuery("From Chapitre");
			chapitres = query.list();
			session.getTransaction().commit();

			return chapitres;
		} catch (HibernateException he) {

			he.printStackTrace();
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}

			return null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Chapitre getChapitreById(Integer id) {
		Chapitre chapitre = null;
		Session session = setSession();
		try {
			session.beginTransaction();
			chapitre = (Chapitre) session.get(Chapitre.class, id);
			session.getTransaction().commit();

		} catch (HibernateException he) {
			he.printStackTrace();
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return chapitre;
	}

	public void updateData(int id, String chapitre, String description, String fichier, String question,
			Matiere matiere, boolean archiver) {
		Chapitre chap = new Chapitre();
		Session session = setSession();

		try {
			session.beginTransaction();
			chap = session.get(Chapitre.class, id);
			chap.setId(id);
			chap.setChapitre(chapitre);
			;
			chap.setDescription(description);
			chap.setFichier(fichier);
			chap.setQuestion(question);
			chap.setMatiere(matiere);
			chap.setArchiver(archiver);
			session.update(chap);
			session.getTransaction().commit();

		} catch (HibernateException hb) {
			hb.printStackTrace();
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}

		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public List<Chapitre> selectByMatiere(Integer matiere) {

		session = setSession();
		List<Chapitre> liste = new ArrayList();

		try {
			session.beginTransaction();
			Query<Chapitre> query = session.createQuery("From Chapitre as ch where ch.matiere=" + matiere);
			liste = query.list();
			session.getTransaction().commit();
			return liste;

		} catch (HibernateException he) {

			he.printStackTrace();
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}

			return null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
