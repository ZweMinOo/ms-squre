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
public class suppliers {
	private int supplier_id;
	private String supplier_name;
	private String contact_name;
	private String phone;
	private String address;
	private String city;
	
	private Set<purchase> purchase=new HashSet<purchase>();

	public suppliers() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public suppliers(String supplier_name, String contact_name, String phone,
			String address, String city, Set<pojo.purchase> purchase) {
		super();
		this.supplier_name = supplier_name;
		this.contact_name = contact_name;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.purchase = purchase;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}
	@Column
	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	@Column
	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	@Column
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
	@OneToMany(mappedBy="supplier")
	public Set<purchase> getPurchase() {
		return purchase;
	}

	public void setPurchase(Set<purchase> purchase) {
		this.purchase = purchase;
	}

	@Override
	public String toString() {
		return "suppliers [supplier_id=" + supplier_id + ", supplier_name="
				+ supplier_name + ", contact_name=" + contact_name + ", phone="
				+ phone + ", address=" + address + ", city=" + city
				+ ", purchase=" + purchase + "]";
	}
}
