package test;

import dao.dao;

public class update implements dao{
	
	static String [] arr = {"A","B","C","D"};
	
	public static void main(String[]args){
		usersDAO.getAllUsers("FROM users");
		showTable();
		String x = get(1);
		x = "F";
		upgrade(1,x);
		showTable();
	}
	
	public static void showTable(){
		System.out.println();
		for(String s : arr){
			System.out.print(s + " ");
		}
		System.out.println();
	}
	public static String get(int i){
		return arr[i];
	}
	
	public static void upgrade(int ind,String s){
		if(ind<arr.length&&ind>-1){
			String temp = arr[ind];
			temp = s;
			arr[ind] = temp;
		}
	}
}
