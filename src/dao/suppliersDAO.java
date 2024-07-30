package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class suppliersDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public suppliersDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertSuppliers(suppliers test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updateSuppliers(suppliers test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			suppliers temp = new suppliers();
			mySession.load(temp,test.getSupplier_id());
			temp.setAddress(test.getAddress());
			temp.setCity(test.getCity());
			temp.setContact_name(test.getContact_name());
			temp.setPhone(test.getPhone());
			temp.setSupplier_name(test.getSupplier_name());
			temp.setPurchase(test.getPurchase());
			
			mySession.save(temp);
			mySession.getTransaction().commit();

		}
		
		public void deleteSuppliers(suppliers test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<suppliers> getAllSuppliers(String query) 
		{
			List<suppliers>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q=mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}