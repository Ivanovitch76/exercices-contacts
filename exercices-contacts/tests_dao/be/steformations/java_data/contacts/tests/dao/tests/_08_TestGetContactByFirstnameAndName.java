package be.steformations.java_data.contacts.tests.dao.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;




import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.beans.Country;
import be.steformations.java_data.contacts.interfaces.dao.ContactDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;


public class _08_TestGetContactByFirstnameAndName {
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
	public void testSelectBettyBoop() {
		try {		
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
			
			Contact contact = dao.getContactByFirstnameAndName("Betty", "Boop");

			String query = "ContactDao.getContactByFirstnameAndName(\"Betty\", \"Boop\")";
			
			assertNotNull(query, contact);
			assertTrue(query + ".getId() != 0", contact.getId() != 0 );
			assertEquals(query + ".getFirstname()", "Betty", contact.getFirstname());
			assertEquals(query + ".getName()", "Boop", contact.getName());
			assertEquals(query + ".getEmail()", "betty.boop@hollywood.com", contact.getEmail());
			
			Country pays = contact.getCountry();
			assertNotNull(query + ".getCountry()", pays);
			assertEquals(query + ".getCountry().getAbbreviation()", "US", pays.getAbbreviation());
			assertEquals(query + ".getCountry().getName()", "Etats-Unis", pays.getName());
			
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}	
	}
	
	@Test
	public void testSelectSallyJupiter() {
		try {		
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
			
			Contact contact = dao.getContactByFirstnameAndName("Sally", "Jupiter");

			String query = "ContactDao.getContactByFirstnameAndName(\"Sally\", \"Jupiter\")";
			
			assertNotNull(query, contact);
			assertTrue(query + ".getId() != 0", contact.getId() != 0 );
			assertEquals(query + ".getFirstname()", "Sally", contact.getFirstname());
			assertEquals(query + ".getName()", "Jupiter", contact.getName());
			assertEquals(query + ".getEmail()", "SilkSpectre@watchmen.com", contact.getEmail());
			
			Country pays = contact.getCountry();
			assertNull(query + ".getCountry()", pays);
			
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}	
	}
		
	@Test
	public void testSelectContactPrenomNull() {
		try {		
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
				
			Contact contact = dao.getContactByFirstnameAndName(null, "Boop");
			assertNull("ContactDao.getContactByFirstnameAndName(null, \"Boop\")", contact);	
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}	
	}
	
	@Test
	public void testSelectContactMauvaisPrenom() {
		try {		
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
				
			Contact contact = dao.getContactByFirstnameAndName("Pretty", "Boop");
			assertNull("ContactDao.getContactByFirstnameAndName(\"Pretty\", \"Boop\")", contact);	
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}	
	}
	
	@Test
	public void testSelectContactNomNull() {
		try {		
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
				
			Contact contact = dao.getContactByFirstnameAndName("Betty", null);
			assertNull("ContactDao.getContactByFirstnameAndName(\"Betty\", null)", contact);	
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}	
	}	
	
	@Test
	public void testSelectContactMauvaisNom() {
		try {		
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
				
			Contact contact = dao.getContactByFirstnameAndName("Betty", "Boo");
			assertNull("ContactDao.getContactByFirstnameAndName(\"Betty\", \"Boo\")", contact);	
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}	
	}
}
