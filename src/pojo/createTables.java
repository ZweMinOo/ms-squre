package pojo;

//import HibernateUtils;
import dao.HibernateUtils;
import org.hibernate.Session;


public class createTables
{
	public static void main(String[]args)
	{
		Session mySession=HibernateUtils.getSessionFactory().getCurrentSession();
		mySession.beginTransaction();
		
		HibernateUtils.shutdown();
	}

}
