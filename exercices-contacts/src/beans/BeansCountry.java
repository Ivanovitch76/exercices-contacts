package beans;

import be.steformations.java_data.contacts.interfaces.beans.Country;

public class BeansCountry implements Country {

	private int id;
	private String abbreviation;
	private String name;
	
	
	public BeansCountry(int id, String abbreviation, String name){		
		super();
		this.id = id;
		this.abbreviation = abbreviation;	
		this.name = name;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getAbbreviation() {
		return abbreviation;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abbreviation == null) ? 0 : abbreviation.hashCode());
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
		BeansCountry other = (BeansCountry) obj;
		if (abbreviation == null) {
			if (other.abbreviation != null)
				return false;
		} else if (!abbreviation.equals(other.abbreviation))
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
	public String toString() {
		return "BeansCountry [id=" + id + ", abbreviation=" + abbreviation + ", name=" + name + "]";
	}

}
