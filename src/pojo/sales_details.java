package pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class sales_details
{
	private int saldetails_id;
	private sales sale;
	private items item;
	private double price;
	private int quantity;
	
	//constructors
	public sales_details() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public sales_details(sales sale, items item, double price, int quantity,
			double discount) {
		super();
		this.sale = sale;
		this.item = item;
		this.price = price;
		this.quantity = quantity;
	}

	//methods
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getSaldetails_id() {
		return saldetails_id;
	}
	public void setSaldetails_id(int saldetails_id) {
		this.saldetails_id = saldetails_id;
	}
	@ManyToOne
	@JoinColumn(name="sales_id", nullable=false)
	public sales getSale() {
		return sale;
	}
	public void setSale(sales sale) {
		this.sale = sale;
	}
	@ManyToOne
	@JoinColumn(name="item_id", nullable=false)
	public items getItem() {
		return item;
	}

	public void setItem(items item) {
		this.item = item;
	}
	@Column
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@Column
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}