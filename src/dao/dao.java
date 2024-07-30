package dao;

public interface dao {
	brandsDAO brandsDAO = new brandsDAO();
	categories_brandsDAO categories_brandsDAO = new categories_brandsDAO();
	categoriesDAO categoriesDAO = new categoriesDAO();
	customersDAO customersDAO = new customersDAO();
	itemsDAO itemsDAO = new itemsDAO();
	
	modelsDAO modelsDAO = new modelsDAO();
	purchase_detailsDAO purchase_detailsDAO = new purchase_detailsDAO();
	purchaseDAO purchaseDAO = new purchaseDAO();
	sales_detailsDAO sales_detailsDAO = new sales_detailsDAO();
	salesDAO salesDAO = new salesDAO();
	
	services_detailsDAO services_detailsDAO = new services_detailsDAO();
	servicesDAO servicesDAO = new servicesDAO();
	suppliersDAO suppliersDAO = new suppliersDAO();
	usersDAO usersDAO = new usersDAO();
	customDAO customDAO = new customDAO();
}