package be.steformations.java_data.contacts.tests.dao.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.contacts.interfaces.beans.Tag;
import be.steformations.java_data.contacts.interfaces.dao.TagDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;


public class _04_TestCreateAndSaveTag {

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
	public void testInsertTagNull() {
		try {
			TagDao dao = daoFactory.getTagDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getTagDao()", dao);
			
			Tag tag = dao.createAndSaveTag(null);
			assertNull("TagDao..createAndSaveTag(null)", tag);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}	
	}
	
	@Test
	public void testInsertTagDejaExistant() {
		try {
			TagDao dao = daoFactory.getTagDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getTagDao()", dao);
			
			Tag tag = dao.createAndSaveTag("vamp");
			assertNotNull("TagDao.createAndSaveTag(\"vamp\")", tag);
			assertEquals("TagDao.createAndSaveTag(\"vamp\").getValue()", "vamp", tag.getValue());
			
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}	
	}	
	
	@Test
	public void testInsertTagInexistant() {
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
			
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
