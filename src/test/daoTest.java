package test;
import dao.dao;
import pojo.*;

public class daoTest implements dao{

	public static void main(String[]args){
		categories objCategories = new categories();
		objCategories.setCategory_name("Phone");
		
		categoriesDAO.insertCategories(objCategories);
		
		objCategories.setCategory_name("Earphone");
		
		categoriesDAO.insertCategories(objCategories);
	}
}
