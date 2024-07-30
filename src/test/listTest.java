package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class listTest {
	public static void main(String[]args){
		List<dog> dogList = new ArrayList<dog>();
		
		dogList.add(new dog(1,"A",10));
		dogList.add(new dog(2,"B",9));
		dogList.add(new dog(3,"C",4));
		System.out.println(dogList.size());
		print(dogList);
		List<dog> dogToRemove = new ArrayList<dog>();
		Iterator<dog> i = dogList.iterator();
		while(i.hasNext()){
			dog dog1 = i.next();
			if(dog1.getName().equals("A")){
				dogToRemove.add(dog1);
			}	
		}
		for(dog dog1:dogToRemove){
			dogList.remove(dog1);
		}
		System.out.println(dogList.size());
		print(dogList);
	}
	
	public static void print(List<dog> list){
		for(dog d:list){
			System.out.println(d.toString());
		}
		
	}
}
