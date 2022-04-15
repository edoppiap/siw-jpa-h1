package it.uniroma3.siw.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Company {
	
	public Company() {
		
	}
	
	public Company(String bussinesName, String phoneNumber) {
		super();
		this.businessName = bussinesName;
		this.phoneNumber = phoneNumber;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String businessName;
	
	/* 
	 * La compagnia e l'indirizzo sono entity strettamente collegate quindi e' 
	 * utile creare un evento a cascata per la gestione nel db
	 */
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Address address;
	
	@Column(length = 9)
	private String phoneNumber;

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String bussinesName) {
		this.businessName = bussinesName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (businessName == null) {
			if (other.businessName != null)
				return false;
		} else if (!businessName.equals(other.businessName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		
		sb.append("Company");
        sb.append("{id=").append(id);
        sb.append(", businessName=").append(businessName);
        sb.append(", address=").append(address);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append("}\n");
		
		return sb.toString();}

}
