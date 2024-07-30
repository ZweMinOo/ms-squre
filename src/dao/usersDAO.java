package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.users;

public class usersDAO {

	private Session mySession=null;
	private SessionFactory sessionFactory=null;
	
	public usersDAO()
	{
		sessionFactory=HibernateUtils.getSessionFactory();
	}
	
	public void insertUsers(users test)
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.save(test);
		mySession.getTransaction().commit();
	}
	
	public void updateUsers(users test)
	{
		
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		users temp = new users();
		mySession.load(temp,test.getUser_id());
		temp.setPassword(test.getPassword());
		temp.setUser_role(test.getUser_role());
		temp.setUsername(test.getUsername());
		
		mySession.save(temp);
		mySession.getTransaction().commit();

	}
	
	public void deleteUsers(users test) 
	{
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		mySession.delete(test);
		mySession.getTransaction().commit();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<users> getAllUsers(String query) 
	{
		List<users>testList=null;
		mySession=sessionFactory.getCurrentSession();
		mySession.beginTransaction();
		Query q=mySession.createQuery(query);
		testList=q.list();
		mySession.getTransaction().commit();
		return testList;
	}
}
