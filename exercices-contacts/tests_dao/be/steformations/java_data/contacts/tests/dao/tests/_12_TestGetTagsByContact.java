package be.steformations.java_data.contacts.tests.dao.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.beans.Tag;
import be.steformations.java_data.contacts.interfaces.dao.ContactDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;
import be.steformations.java_data.contacts.interfaces.dao.TagDao;


public class _12_TestGetTagsByContact {
	
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
	public void selectTagsBettyBoop() {
		try {
		
			ContactDao contactDao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", contactDao);
			
			TagDao tagDao = daoFactory.getTagDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getTagDao()", tagDao);
			
			Tag vampTag = tagDao.getTagByValue("vamp");
			assertNotNull("TagDao.getTagByValue(\"vamp\")", vampTag);
			Tag sexSymbolTag = tagDao.getTagByValue("sex-symbol");
			assertNotNull("TagDao.getTagByValue(\"sex-symbol\")", sexSymbolTag);
	
			Contact bettyBoop = contactDao.getContactByFirstnameAndName("Betty", "Boop");
			assertNotNull("ContactDao.getContactByFirstnameAndName(\"Betty\", \"Boop\")", bettyBoop);
			
			java.util.List<? extends Tag>	tagsBettyBoop = contactDao.getTagsByContact(bettyBoop.getId());
			String query = "ContactDao.getTagsByContact("+bettyBoop.getId()+")";
			assertNotNull(query, tagsBettyBoop);
			assertEquals(query + ".size()", 2, tagsBettyBoop.size());
			assertTrue(query + ".contains( TagDao.getTagByValue(\"vamp\") )", tagsBettyBoop.contains(vampTag));
			assertTrue(query + ".contains( TagDao.getTagByValue(\"sex-symbol\") )", tagsBettyBoop.contains(sexSymbolTag));
		
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void selectTagsSallyJupiter() {	
		try {
			ContactDao contactDao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", contactDao);
			
			Contact sallyJupiter = contactDao.getContactByFirstnameAndName("Sally", "Jupiter");
			assertNotNull("ContactDao.getContactByFirstnameAndName(\"Sally\", \"Jupiter\")", sallyJupiter);
			
			java.util.List<? extends Tag> tags = contactDao.getTagsByContact(sallyJupiter.getId());
			assertNotNull("ContactDao.getTagsByContact("+sallyJupiter.getId()+")", tags);
			assertEquals("ContactDao.getTagsByContact("+sallyJupiter.getId()+").size()", 0, tags.size());
			
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
		
	@Test
	public void selectTagsContactInexistant() {	
		try {
			ContactDao contactDao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", contactDao);
			
			java.util.List<? extends Tag> tags = contactDao.getTagsByContact(-1);
			assertNotNull("ContactDao.getTagsByContact(-1)", tags);
			assertEquals("ContactDao.getTagsByContact(-1).size()", 0, tags.size());
			
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
}
