package ma.ensao.util;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilHibernate {

	private static final SessionFactory sessionFactory=buildSessionFactory();
	private static SessionFactory buildSessionFactory(){

     
    	try {
  	      // Create de SessionFactory par hibernate.cfg.xml
  	      return new Configuration().configure().buildSessionFactory();
  	      
  	    } catch (Throwable ex) {
  	      // Make sure you log the exception, as it might be swallowed
  	      System.err.println("Initial SessionFactory creation failed." + ex);
  	      throw new ExceptionInInitializerError(ex);
  	    }
    }
	
    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
