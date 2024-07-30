package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class servicesDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public servicesDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertServices(services test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updateServices(services test)
		{
			
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			services temp = new services();
			mySession.load(temp,test.getService_id());
			temp.setCustomer(test.getCustomer());
			temp.setError(test.getError());
			temp.setModel_name(test.getModel_name());
			temp.setReceived_date(test.getReceived_date());
			temp.setReleased_date(test.getReleased_date());
			temp.setService_fee(test.getService_fee());
			temp.setService_note(test.getService_note());
			temp.setServices_detials(test.getServices_detials());
			
			mySession.save(temp);
			mySession.getTransaction().commit();

		}
		
		public void deleteServices(services test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<services> getAllServices(String query) 
		{
			List<services>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q=mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}