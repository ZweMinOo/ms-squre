package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class categories_brandsDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public categories_brandsDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertCategoriesBrands(categories_brands test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updateCategoriesBrands(categories_brands test)
		{
			
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			categories_brands temp = new categories_brands();
			mySession.load(temp,test.getCategories_brands_id());
			temp.setBrand(test.getBrand());
			temp.setCategory(test.getCategory());
			//temp.setModels(test.getModels());
			
			mySession.save(temp);
			mySession.getTransaction().commit();

		}
		
		public void deleteCategoriesBrands(categories_brands test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<categories_brands> getAllCategoriesBrands(String query) 
		{
			List<categories_brands>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q=mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}
