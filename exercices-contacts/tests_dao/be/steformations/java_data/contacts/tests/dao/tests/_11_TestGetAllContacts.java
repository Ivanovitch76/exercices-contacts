package be.steformations.java_data.contacts.tests.dao.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.dao.ContactDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;

public class _11_TestGetAllContacts {	
	
	private DaoFactory daoFactory = null;
	
	@Before
	public void init() {
		try {
			daoFactory = TestsDaoFactory.getDaoFactory();
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testSelectAllContacts() {
		try {
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
			
			Contact betty = dao.getContactByFirstnameAndName("Betty", "Boop");
			assertNotNull("ContactDao.getContactByFirstnameAndName(\"Betty\", \"Boop\")", betty);
			
			java.util.List<? extends Contact> all = dao.getAllContacts();
			assertNotNull("ContactDao.getAllContacts()", all);
			assertTrue("ContactDao.getAllContacts().contains( ContactDao.getContactByFirstnameAndName(\"Betty\", \"Boop\") )", 
						all.contains(betty));
			
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
