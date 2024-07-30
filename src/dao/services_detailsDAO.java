package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class services_detailsDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public services_detailsDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertServicesDetails(services_details test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updateServicesDetails(services_details test)
		{
			
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			services_details temp = new services_details();
			mySession.load(temp,test.getServices_details_id());
			temp.setAccessory_name(test.getAccessory_name());
			temp.setPrice(test.getPrice());
			temp.setQuantity(test.getQuantity());
			temp.setService(test.getService());

			mySession.save(temp);
			mySession.getTransaction().commit();

		}
		
		public void deleteServicesDetails(services_details test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<services_details> getAllServicesDetails(String query) 
		{
			List<services_details>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q=mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}