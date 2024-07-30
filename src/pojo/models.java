package pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class models {
	private int model_id;
	private String model_name;
	private categories_brands categories_brands = new categories_brands();
	private Set<items> items=new HashSet<items>();

	//constructor
	public models() {
		super();
		// TODO Auto-generated constructor stub
	}	

	public models(String model_name, pojo.categories_brands categories_brands,
			Set<pojo.items> items) {
		super();
		this.model_name = model_name;
		this.categories_brands = categories_brands;
		this.items = items;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getModel_id() {
		return model_id;
	}

	public void setModel_id(int model_id) {
		this.model_id = model_id;
	}
	
	
	@Column
	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	@OneToMany(mappedBy="model")
	public Set<items> getItems() {
		return items;
	}

	public void setItems(Set<items> items) {
		this.items = items;
	}

	@ManyToOne
	@JoinColumn(name="categories_brands_id",nullable=false)
	public categories_brands getCategories_brands() {
		return categories_brands;
	}

	public void setCategories_brands(categories_brands categories_brands) {
		this.categories_brands = categories_brands;
	}
}