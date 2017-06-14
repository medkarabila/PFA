
package ma.ensao.hibernateDAO;

import ma.ensao.entity.User;
import ma.ensao.util.UtilHibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterService {
        
    public boolean register(User user){
        Session session = UtilHibernate.openSession();
        if(isUserExists(user)) return false;        
        
        Transaction tx = null;        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.saveOrUpdate(user);            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }        
        return true;
    }
    
    public boolean isUserExists(User user){
        Session session = UtilHibernate.openSession();
        boolean result = false;
        Transaction tx = null;
        try{
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from User where userId='"+user.getUserId()+"'");
            User u = (User)query.uniqueResult();
            tx.commit();
            if(u!=null) result = true;
        }catch(Exception ex){
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.close();
        }
        return result;
    }
}
