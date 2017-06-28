package be.steformations.java_data.contacts.tests.dao.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;







import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.beans.Tag;
import be.steformations.java_data.contacts.interfaces.dao.ContactDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;
import be.steformations.java_data.contacts.interfaces.dao.TagDao;

public class _15_TestRemoveTag {
	
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
	public void testDeleteTagInexistant() {
		try {
			TagDao tagDao = daoFactory.getTagDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getTagDao()", tagDao);
			tagDao.removeTag(Integer.MAX_VALUE);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteTagExistantNonAssocieAUnContact() {
		try {
			TagDao dao = daoFactory.getTagDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getTagDao()", dao);
			
			long millis = System.currentTimeMillis();
			String value = "sexy-" + millis;
			Tag tag = dao.createAndSaveTag(value);
			
			assertNotNull("TagDao.createAndSaveTag(\""+value+"\")", tag);
			assertEquals("TagDao.createAndSaveTag(\""+value+"\").getValue()", value, tag.getValue());
			assertTrue("TagDao.createAndSaveTag(\""+value+"\").getId() > 0", tag.getId() > 0);
			assertEquals("TagDao.getTagByValue(\""+value+"\")", tag, dao.getTagByValue(value));
			assertEquals("TagDao.getTagById(\""+tag.getId()+"\")", tag, dao.getTagById(tag.getId()));
			
			dao.removeTag(tag.getId());
			
			assertNull("TagDao.getTagById(\""+tag.getId()+"\")", dao.getTagById(tag.getId()));
			assertNull("TagDao.getTagByValue(\""+value+"\")", dao.getTagByValue(tag.getValue()));
			
			
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteTagExistantAssocieAUnContact() {
		try {
			TagDao dao = daoFactory.getTagDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getTagDao()", dao);
			
			long millis = System.currentTimeMillis();
			String value = "sexy-" + millis;
			Tag newtag = dao.createAndSaveTag(value);
			
			assertNotNull("TagDao.createAndSaveTag(\""+value+"\")", newtag);
			assertEquals("TagDao.createAndSaveTag(\""+value+"\").getValue()", value, newtag.getValue());
			assertTrue("TagDao.createAndSaveTag(\""+value+"\").getId() > 0", newtag.getId() > 0);
			assertEquals("TagDao.getTagByValue(\""+value+"\")", newtag, dao.getTagByValue(value));
			assertEquals("TagDao.getTagById(\""+newtag.getId()+"\")", newtag, dao.getTagById(newtag.getId()));
			
			String firstname = "Princesse-" + System.currentTimeMillis();
			String name = "Pandha";
			String email = "pandha@accomics";
			
			ContactDao contactDao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", contactDao);
		
			java.util.List<String> tags = java.util.Arrays.asList(new String[] {value});
			
			Contact _new = contactDao.createAndSaveContact(firstname, name, email, null, tags);
			assertNotNull("ContactDao.createAndSaveContact(\"" + firstname + "\", \""+ name +"\", \""+ email +"\", null, "+ tags +")", _new);
		
			java.util.List<? extends Tag> found = contactDao.getTagsByContact(_new.getId());
			assertNotNull("ContactDao.getTagsByContact("+_new.getId()+") != null", found);
			assertTrue("ContactDao.getTagsByContact("+_new.getId()+").contains("+value+")", found.contains(newtag));
			
			dao.removeTag(newtag.getId());
			found = contactDao.getTagsByContact(_new.getId());
			assertNotNull("ContactDao.getTagsByContact("+_new.getId()+") != null", found);
			assertEquals("ContactDao.getTagsByContact("+_new.getId()+").size()", 0, found.size());
			

		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
