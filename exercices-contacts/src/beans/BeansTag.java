package beans;

import be.steformations.java_data.contacts.interfaces.beans.Tag;

public class BeansTag implements Tag {

	private int id;
	private String value;
	
	public BeansTag(int id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "BeansTag [id=" + id + ", value=" + value + "]";
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		BeansTag other = (BeansTag) obj;
		if (id != other.id)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
