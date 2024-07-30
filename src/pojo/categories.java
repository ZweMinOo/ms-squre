package pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class categories {
	
	//Declaration parameters
	private int category_id;
	private String category_name;
	private Set<categories_brands> categories_brands=new HashSet<categories_brands>();
	
	//Constructor
	public categories() {
		super();
	}
	public categories(int category_id, String category_name,Set<pojo.categories_brands> categories_brands) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.categories_brands = categories_brands;
	}
	
	//Creating methods
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	@Column
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	@OneToMany(mappedBy="category")
	public Set<categories_brands> getCategories_brands() {
		return categories_brands;
	}
	public void setCategories_brands(Set<categories_brands> categories_brands) {
		this.categories_brands = categories_brands;
	}
}
