package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class services_details
{
	private int services_details_id;
	private services service;
	private String accessory_name;
	private double price;
	private int quantity ;
	
	public services_details() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public services_details(services service, String accessory_name,
			double price, int quantity) {
		super();
		this.service = service;
		this.accessory_name = accessory_name;
		this.price = price;
		this.quantity = quantity;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getServices_details_id() {
		return services_details_id;
	}
	public void setServices_details_id(int services_details_id) {
		this.services_details_id = services_details_id;
	}
	@ManyToOne
	@JoinColumn(name="service_id", nullable=false)
	public services getService() {
		return service;
	}
	public void setService(services service) {
		this.service = service;
	}
	@Column
	public String getAccessory_name() {
		return accessory_name;
	}
	public void setAccessory_name(String accessory_name) {
		this.accessory_name = accessory_name;
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