package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class purchaseDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public purchaseDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertPurchase(purchase test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updatePurchase(purchase test)
		{
			
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			purchase temp = new purchase();
			mySession.load(temp,test.getPurchase_id());
			temp.setPurchased_date(test.getPurchased_date());
			temp.setPurchase_details(test.getPurchase_details());
			temp.setSupplier(test.getSupplier());
			
			mySession.save(temp);
			mySession.getTransaction().commit();

		}
		
		public void deletePurchase(purchase test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<purchase> getAllPurchase(String query) 
		{
			List<purchase>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q=mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}