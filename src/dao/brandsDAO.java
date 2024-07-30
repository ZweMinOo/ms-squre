package dao;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class brandsDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public brandsDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertBrands(brands test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updateBrands(brands test)
		{
			
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			brands temp = new brands();
			mySession.load(temp,test.getBrand_id());
			temp.setBrand_name(test.getBrand_name());
			temp.setCategories_brands(test.getCategories_brands());
			mySession.save(temp);
			mySession.getTransaction().commit();

		}
		
		public void deleteBrands(brands test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<brands> getAllBrands(String query) 
		{
			List<brands>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q=mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}
