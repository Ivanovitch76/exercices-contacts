package be.steformations.it.java_data.contacts;

import be.steformations.java_data.contacts.interfaces.jdbc_plus.JdbcContact;

public class JdbcContactImplPlus implements JdbcContact {
	String firstname = "";
	String name = "";
	String email = "";
	
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getFirstname() {
		// TODO Auto-generated method stub
		return firstname;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

}
