package pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class categories_brands {
	
	//Declaration parameters
	private int categories_brands_id;
	private categories category;
	private brands brand;
	private Set<models> models = new HashSet<models>();
	
	//Constructors
	public categories_brands() {
		super();
		// TODO Auto-generated constructor stub
	}

	public categories_brands(categories category, brands brand,
			Set<pojo.models> models) {
		super();
		this.category = category;
		this.brand = brand;
		this.models = models;
	}

	//Creating methods
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getCategories_brands_id() {
		return categories_brands_id;
	}
	public void setCategories_brands_id(int categories_brands_id) {
		this.categories_brands_id = categories_brands_id;
	}
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	public categories getCategory() {
		return category;
	}
	public void setCategory(categories category) {
		this.category = category;
	}
	
	@ManyToOne
	@JoinColumn(name="brand_id", nullable=false)
	public brands getBrand() {
		return brand;
	}
	public void setBrand(brands brand) {
		this.brand = brand;
	}

	@OneToMany(mappedBy="categories_brands")
	public Set<models> getModels() {
		return models;
	}

	public void setModels(Set<models> models) {
		this.models = models;
	}
}