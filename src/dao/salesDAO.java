package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class salesDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public salesDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertSales(sales test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updateSales(sales test)
		{
			
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			sales temp = new sales();
			mySession.load(temp,test.getSales_id());
			temp.setCustomer(test.getCustomer());
			temp.setDate(test.getDate());
			temp.setSales_details(test.getSales_details());
			
			mySession.save(temp);
			mySession.getTransaction().commit();

		}
		
		public void deleteSales(sales test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<sales> getAllSales(String query) 
		{
			List<sales>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q=mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}