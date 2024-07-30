package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class modelsDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public modelsDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertModels(models test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updateModels(models test)
		{
			
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			models temp = new models();
			mySession.load(temp,test.getModel_id());
			temp.setModel_name(test.getModel_name());
			temp.setItems(test.getItems());
			//temp.setCategories_Brands(test.getCategories_Brands());
			
			mySession.save(temp);
			mySession.getTransaction().commit();

		}
		
		public void deleteModels(models test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<models> getAllModels(String query) 
		{
			List<models>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q=mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}