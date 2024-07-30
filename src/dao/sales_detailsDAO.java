package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class sales_detailsDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public sales_detailsDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertSalesDetails(sales_details test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updateSalesDetails(sales_details test)
		{
			
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			sales_details temp = new sales_details();
			mySession.load(temp,test.getSaldetails_id());
			temp.setItem(test.getItem());
			temp.setPrice(test.getPrice());
			temp.setQuantity(test.getQuantity());
			temp.setSale(test.getSale());
			
			mySession.save(temp);
			mySession.getTransaction().commit();

		}
		
		public void deleteSalesDetails(sales_details test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<sales_details> getAllSalesDetails(String query) 
		{
			List<sales_details>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q=mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}