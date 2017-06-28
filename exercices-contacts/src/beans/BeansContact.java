package beans;

import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.beans.Country;

public class BeansContact implements Contact {
	private int id; 
	private String name;
	private String firstname;
	private String email;
	private Country country;
	
	public BeansContact(int id, String name, String firstname, String email, Country country){		
		super();
		this.id = id;	
		this.name = name;
		this.firstname = firstname;
		this.email = email;
		this.country = country;
	}
	
	@Override
	public Integer getId() {
		
		return this.id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getFirstname() {
		return firstname;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		BeansContact other = (BeansContact) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Country getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return "BeansContact [id=" + id + ", name=" + name + ", firstname=" + firstname + ", email=" + email
				+ ", country=" + country + "]";
	}

}
