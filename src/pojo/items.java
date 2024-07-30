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
public class items {
	private int item_id;
	private models model;
	private String color;
	private double unit_price;
	private int stock_quantity;
	private int reorder_level;
	
	private Set<sales_details> sales_details=new HashSet<sales_details>();
	private Set<purchase_details> purchase_details=new HashSet<purchase_details>();
	
	//constructors
	public items() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public items(models model, String color, double unit_price,
			int stock_quantity, int reorder_level,
			Set<pojo.sales_details> sales_details,
			Set<pojo.purchase_details> purchase_details) {
		super();
		this.model = model;
		this.color = color;
		this.unit_price = unit_price;
		this.stock_quantity = stock_quantity;
		this.reorder_level = reorder_level;
		this.sales_details = sales_details;
		this.purchase_details = purchase_details;
	}

	//methods
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	
	@ManyToOne
	@JoinColumn(name="model_id", nullable=false)
	public models getModel() {
		return model;
	}
	public void setModel(models model) {
		this.model = model;
	}
	
	@Column
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@Column
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	
	@Column
	public int getReorder_level() {
		return reorder_level;
	}
	
	public void setReorder_level(int reorder_level) {
		this.reorder_level = reorder_level;
	}
	
	@OneToMany(mappedBy="item")
	public Set<sales_details> getSales_details() {
		return sales_details;
	}
	public void setSales_details(Set<sales_details> sales_details) {
		this.sales_details = sales_details;
	}
	@OneToMany(mappedBy="item")
	public Set<purchase_details> getPurchase_details() {
		return purchase_details;
	}
	public void setPurchase_details(Set<purchase_details> purchase_details) {
		this.purchase_details = purchase_details;
	}
	@Column
	public int getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	@Override
	public String toString() {
		return "items [item_id=" + item_id + ", model=" + model + ", color="
				+ color + ", unit_price=" + unit_price + ", stock_quantity="
				+ stock_quantity + ", reorder_level=" + reorder_level + "]";
	}

	
}
