package be.steformations.java_data.contacts.tests.dao.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;





import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.dao.ContactDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;

public class _14_TestRemoveContact {
	
	private DaoFactory daoFactory = null;
	
	@Before
	public void init() {
		try {
			daoFactory = TestsDaoFactory.getDaoFactory();
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteContactInexistant() {
		try {
			ContactDao contactDao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", contactDao);
		
			boolean deleted = contactDao.removeContact(-1);
			assertFalse("ContactDao..removeContact(-1)", deleted);
			
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteContactExistant() {
		try {
			String firstname = "Princesse-" + System.currentTimeMillis();
			String name = "Pandha";
			String email = "pandha@accomics";
			
			ContactDao contactDao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", contactDao);
		
			Contact _new = contactDao.createAndSaveContact(firstname, name, email, null, null);
			assertNotNull("ContactDao.createAndSaveContact(\"" + firstname + "\", \""+ name +"\", \""+ email +"\", null, null)", _new);
		
			assertTrue("ContactDao.removeContact("+_new.getId()+")", contactDao.removeContact(_new.getId()));	
			assertNull("ContactDao.getContactById("+_new.getId()+")", contactDao.getContactById(_new.getId()));
			assertNull("ContactDao.getContactByFirstnameAndName(\""+firstname+"\", \""+name+"\")", contactDao.getContactByFirstnameAndName(firstname, name));

		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
