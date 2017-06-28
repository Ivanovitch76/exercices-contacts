package be.steformations.java_data.contacts.tests.dao.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;





import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.beans.Country;
import be.steformations.java_data.contacts.interfaces.dao.ContactDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;

public class _09_TestGetContactById {
	
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
	public void testSelectContactBettyBoopId() {
		try {
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
			
			Contact bettyBoopByName = dao.getContactByFirstnameAndName("Betty", "Boop");
			assertNotNull("ContactDao.getContactByFirstnameAndName(\"Betty\", \"Boop\")", bettyBoopByName);
			assertTrue("ContactDao.getContactByFirstnameAndName(\"Betty\", \"Boop\").getId() != 0", bettyBoopByName.getId() != 0 );

			Contact bettyBoopById = dao.getContactById( bettyBoopByName.getId() );
			
			assertEquals("ContactDao.getContactByFirstnameAndName(\"Betty\", \"Boop\").equals(ContactDao.getContactById("+bettyBoopByName.getId()+"))", 
						 bettyBoopByName, bettyBoopById);
			
			String query = "ContactDao.getContactById("+bettyBoopByName.getId()+")";
			
			assertNotNull(query, bettyBoopById);
			assertEquals(query + ".getId()", bettyBoopByName.getId(), bettyBoopById.getId() );
			assertEquals(query + ".getFirstname()", "Betty", bettyBoopById.getFirstname());
			assertEquals(query + ".getName()", "Boop", bettyBoopById.getName());
			assertEquals(query + ".getEmail()", "betty.boop@hollywood.com", bettyBoopById.getEmail());
			
			Country pays = bettyBoopById.getCountry();
			assertNotNull(query + ".getCountry()", pays);
			assertEquals(query + ".getCountry().getAbbreviation()", "US", pays.getAbbreviation());
			assertEquals(query + ".getCountry().getName()", "Etats-Unis", pays.getName());
			
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSelectContactIdInexistant() {
		try {
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
			
			Contact c = dao.getContactById(-1);
			assertNull("ContactDao.getContactById(0)", c);

		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
