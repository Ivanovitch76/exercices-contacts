package be.steformations.java_data.contacts.tests.jdbc.tests;

import be.steformations.java_data.contacts.interfaces.jdbc.ContactJdbcManager;

public class ContactJdbcTestFactory {
	
	public static ContactJdbcManager getContactJdbcManager() {
		return new be.steformations.it.java_data.contacts.ContactsJdbcImpl();
	}
}
