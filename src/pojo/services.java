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
public class services 
{
	private int service_id;
	private customers customer;
	private String model_name;
	private String error;
	private String service_note;
	private	double service_fee;
	private Date received_date;
	private Date released_date;
	
	private Set<services_details> services_detials=new HashSet<services_details>();

	public services() {
		super();
		// TODO Auto-generated constructor stub
	}

	public services(customers customer, String model_name, String error,
			String service_note, double service_fee, Date received_date,
			Date released_date, Set<services_details> services_detials) {
		super();
		this.customer = customer;
		this.model_name = model_name;
		this.error = error;
		this.service_note = service_note;
		this.service_fee = service_fee;
		this.received_date = received_date;
		this.released_date = released_date;
		this.services_detials = services_detials;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getService_id() {
		return service_id;
	}
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	public customers getCustomer() {
		return customer;
	}

	public void setCustomer(customers customer) {
		this.customer = customer;
	}
	@Column
	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	@Column
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	@Column
	public String getService_note() {
		return service_note;
	}

	public void setService_note(String service_note) {
		this.service_note = service_note;
	}
	@Column
	public double getService_fee() {
		return service_fee;
	}

	public void setService_fee(double service_fee) {
		this.service_fee = service_fee;
	}
	@Column
	public Date getReceived_date() {
		return received_date;
	}

	public void setReceived_date(Date received_date) {
		this.received_date = received_date;
	}
	@Column
	public Date getReleased_date() {
		return released_date;
	}

	public void setReleased_date(Date released_date) {
		this.released_date = released_date;
	}
	@OneToMany(mappedBy="service")
	public Set<services_details> getServices_detials() {
		return services_detials;
	}

	public void setServices_detials(Set<services_details> services_detials) {
		this.services_detials = services_detials;
	}	
}