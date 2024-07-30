package dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class customDAO {

	private Session mySession=null;
	private SessionFactory sessionFactory=null;
	
	public customDAO(){
		sessionFactory=HibernateUtils.getSessionFactory();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> get(String query) 
	{
		List<String> list=null;
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		
		Query q=mySession.createQuery(query);
		list=q.list();
		
		mySession.getTransaction().commit();
		return list;
	}
}