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
public class brands {
	//
	private int brand_id;
	private String brand_name;
	
	private Set<categories_brands> categories_brands=new HashSet<categories_brands>();
	
	//constructor
	public brands() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public brands(String brand_name,
			Set<pojo.categories_brands> categories_brands) {
		super();
		this.brand_name = brand_name;
		this.categories_brands = categories_brands;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	
	@Column
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	@OneToMany(mappedBy="brand")
	public Set<categories_brands> getCategories_brands() {
		return categories_brands;
	}
	public void setCategories_brands(Set<categories_brands> categories_brands) {
		this.categories_brands = categories_brands;
	}
}