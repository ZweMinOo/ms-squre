package dao;

import org.hibernate.cfg.*;

import org.hibernate.*;
public class HibernateUtils {
	private static Configuration myConfig=null;
	private static SessionFactory sessionFactory=null;
	public static Configuration configure()
	{
		if(myConfig==null){
			myConfig=new Configuration();
			myConfig.configure("hibernate.cfg.xml");
		}
		return myConfig;
	}
	public static SessionFactory getSessionFactory(){
		
		if(sessionFactory==null)
		{ 
			sessionFactory=new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return sessionFactory;
	}
	
	public static void shutdown(){
		sessionFactory.close();
	}
	
}
