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
public class customers
{
	private int customer_id ;
	private String customer_name;
	private String phone;
	private String email;
	private String address;
	private String city;
	
	private Set<sales> sales=new HashSet<sales>();
	private Set<services> services=new HashSet<services>();
	public customers()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	
	public customers(String customer_name, String phone, String email,
			String address, String city, Set<pojo.sales> sales,
			Set<pojo.services> services) {
		super();
		this.customer_name = customer_name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.city = city;
		this.sales = sales;
		this.services = services;
	}


	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	@Column
	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	@Column
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@OneToMany(mappedBy="customer")
	public Set<sales> getSales() {
		return sales;
	}
	public void setSales(Set<sales> sales) {
		this.sales = sales;
	}
	@OneToMany(mappedBy="customer")
	public Set<services> getServices() {
		return services;
	}
	public void setServices(Set<services> services) {
		this.services = services;
	}
}
