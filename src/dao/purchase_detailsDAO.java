package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class purchase_detailsDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public purchase_detailsDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertPurchaseDetails(purchase_details test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updatePurchaseDetails(purchase_details test)
		{
			
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			purchase_details temp = new purchase_details();
			mySession.load(temp,test.getPurdetails_id());
			temp.setItem(test.getItem());
			temp.setPurchase(test.getPurchase());
			temp.setQuantity(test.getQuantity());
			temp.setTotal_amount(test.getTotal_amount());
			
			mySession.save(temp);
			mySession.getTransaction().commit();

		}
		
		public void deletePurchaseDetails(purchase_details test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<purchase_details> getAllPurchaseDetails(String query) 
		{
			List<purchase_details>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q=mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}