package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class categoriesDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public categoriesDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertCategories(categories test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updateCategories(categories test)
		{
			
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			categories temp = new categories();
			mySession.load(temp,test.getCategory_id());
			temp.setCategories_brands(test.getCategories_brands());
			temp.setCategory_name(test.getCategory_name());
			
			mySession.save(temp);
			mySession.getTransaction().commit();

		}
		
		public void deleteCategories(categories test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<categories> getAllCategories(String query) 
		{
			List<categories>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q = mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}
