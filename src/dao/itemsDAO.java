package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.*;

public class itemsDAO
{
	
		private Session mySession=null;
		private SessionFactory sessionFactory=null;
		
		public itemsDAO()
		{
			sessionFactory=HibernateUtils.getSessionFactory();
		}
		
		public void insertItems(items test)
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.save(test);
			mySession.getTransaction().commit();
		}
		
		public void updateItems(items test)
		{			
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			items temp = new items();
			mySession.load(temp,test.getItem_id());
			temp.setModel(test.getModel());
			temp.setColor(test.getColor());
			temp.setUnit_price(test.getUnit_price());
			temp.setReorder_level(test.getReorder_level());
			temp.setSales_details(test.getSales_details());
			temp.setPurchase_details(test.getPurchase_details());
			temp.setStock_quantity(test.getStock_quantity());

			System.out.println(temp.toString());
			mySession.save(temp);
			mySession.getTransaction().commit();
		}
		
		public void deleteItems(items test) 
		{
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			mySession.delete(test);
			mySession.getTransaction().commit();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<items> getAllItems(String query) 
		{
			List<items>testList=null;
			mySession=sessionFactory.getCurrentSession();
			mySession.beginTransaction();
			Query q=mySession.createQuery(query);
			testList=q.list();
			mySession.getTransaction().commit();
			return testList;
		}
}
