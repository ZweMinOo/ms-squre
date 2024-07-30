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
public class sales
{
	private int sales_id;
	private customers customer;
	private Date date;
	private Set<sales_details> sales_details=new HashSet<sales_details>();
	
	public sales() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public sales(customers customer, Date date,
			Set<pojo.sales_details> sales_details) {
		super();
		this.customer = customer;
		this.date = date;
		this.sales_details = sales_details;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getSales_id() {
		return sales_id;
	}
	public void setSales_id(int sales_id) {
		this.sales_id = sales_id;
	}
	@ManyToOne
	@JoinColumn(name="customer_id",nullable=false)
	public customers getCustomer() {
		return customer;
	}
	public void setCustomer(customers customer) {
		this.customer = customer;
	}
	@Column
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@OneToMany(mappedBy="sale")
	public Set<sales_details> getSales_details() {
		return sales_details;
	}
	public void setSales_details(Set<sales_details> sales_details) 
	{
		this.sales_details = sales_details;
	}

	
}
