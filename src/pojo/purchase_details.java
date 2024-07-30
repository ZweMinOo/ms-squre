package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class purchase_details {
	private int purdetails_id;
	private items item;
	private purchase purchase;
	private int quantity;
	private double total_amount;
	
	public purchase_details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public purchase_details(items item, pojo.purchase purchase, int quantity,
			double total_amount) {
		super();
		this.item = item;
		this.purchase = purchase;
		this.quantity = quantity;
		this.total_amount = total_amount;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getPurdetails_id() {
		return purdetails_id;
	}
	public void setPurdetails_id(int purdetails_id) {
		this.purdetails_id = purdetails_id;
	}
	@ManyToOne
	@JoinColumn(name="item_id", nullable=false)
	public items getItem() {
		return item;
	}
	public void setItem(items item) {
		this.item = item;
	}
	@ManyToOne
	@JoinColumn(name="purchase_id", nullable=false)
	public purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(purchase purchase) {
		this.purchase = purchase;
	}
	@Column
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Column
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	
}
