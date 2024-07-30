package pojo;

import java.util.Date;
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
public class purchase {
	private int purchase_id;
	private suppliers supplier;
	private Date purchased_date;
	
	private Set<purchase_details> purchase_details=new HashSet<purchase_details>();

	public purchase() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public purchase(suppliers supplier, Date purchased_date,
			Set<pojo.purchase_details> purchase_details) {
		super();
		this.supplier = supplier;
		this.purchased_date = purchased_date;
		this.purchase_details = purchase_details;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}
	@ManyToOne
	@JoinColumn(name="supplier_id", nullable=false)
	public suppliers getSupplier() {
		return supplier;
	}

	public void setSupplier(suppliers supplier) {
		this.supplier = supplier;
	}

	@Column
	public Date getPurchased_date() {
		return purchased_date;
	}

	public void setPurchased_date(Date purchased_date) {
		this.purchased_date = purchased_date;
	}
	@OneToMany(mappedBy="purchase")
	public Set<purchase_details> getPurchase_details() {
		return purchase_details;
	}

	public void setPurchase_details(Set<purchase_details> purchase_details) {
		this.purchase_details = purchase_details;
	}
}