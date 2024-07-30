package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class customersDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public customersDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertCustomers(customers test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updateCustomers(customers test)
		{
			
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			customers temp = new customers();
			mySession.load(temp,test.getCustomer_id());
			temp.setPhone(test.getPhone());
			temp.setCity(test.getCity());
			temp.setAddress(test.getAddress());		
			temp.setEmail(test.getEmail());
			temp.setSales(test.getSales());
			temp.setServices(test.getServices());
			
			mySession.save(temp);
			mySession.getTransaction().commit();

		}
		
		public void deleteCustomers(customers test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<customers> getAllCustomers(String query) 
		{
			List<customers>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q=mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}
