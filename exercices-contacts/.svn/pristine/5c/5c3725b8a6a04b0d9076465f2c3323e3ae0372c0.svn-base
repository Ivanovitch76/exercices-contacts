package be.steformations.java_data.contacts.tests.dao.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.dao.ContactDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;

public class _10_TestGetContactsByCountry {
	
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
	public void testSelectContactsUS() {
		try {
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
		
			List<? extends Contact> contacts = dao.getContactsByCountry("US");
			assertNotNull("ContactDao.getContactsByCountry(\"US\")", contacts);
			assertTrue("ContactDao.getContactsByCountry(\"US\").size() > 0", contacts.size() > 0 );
			for (Contact contact : contacts) {
				assertEquals("ContactDao.getContactsByCountry(\"US\").iterator().next().getCountry().getAbbreviation()", "US", contact.getCountry().getAbbreviation() );
			}
			
			Contact bettyBoop = dao.getContactByFirstnameAndName("Betty", "Boop");
			assertNotNull("ContactDao.getContactByFirstnameAndName(\"Betty\", \"Boop\")", bettyBoop);
			assertTrue("ContactDao.getContactsByCountry(\"US\").contains( ContactDao.getContactByFirstnameAndName(\"Betty\", \"Boop\") )", 
						contacts.contains(bettyBoop));
			
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSelectContactsAbbreviationInexistante() {
		try {
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
		
			List<? extends Contact> contacts = dao.getContactsByCountry("ZZ");
			assertNotNull("ContactDao.getContactsByCountry(\"ZZ\")", contacts);
			assertEquals("ContactDao.getContactsByCountry(\"ZZ\").size()", 0, contacts.size());
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSelectContactsAbbreviationNulle() {
		try {
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
		
			List<? extends Contact> contacts = dao.getContactsByCountry(null);
			assertNotNull("ContactDao.getContactsByCountry(null)", contacts);
			assertEquals("ContactDao.getContactsByCountry(null).size()", 0, contacts.size());
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}	
}
