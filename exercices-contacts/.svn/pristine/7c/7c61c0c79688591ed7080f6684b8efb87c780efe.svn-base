package be.steformations.java_data.contacts.tests.dao.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

import be.steformations.java_data.contacts.interfaces.beans.Tag;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;
import be.steformations.java_data.contacts.interfaces.dao.TagDao;

public class _01_TestGetTagByValue {

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
	public void testGetTagExistant() {
		try {
			TagDao dao = daoFactory.getTagDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getTagDao()", dao);
		
			String value = "vamp";
			Tag tag = null;
			tag = dao.getTagByValue("vamp");
			assertNotNull("TagDao.getTagByValue(\""+value+"\")", tag);
			assertEquals("TagDao.getTagByValue(\""+value+"\")", value, tag.getValue());
		
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSelectTagNull() {
		try {
			TagDao dao = daoFactory.getTagDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getTagDao()", dao);
			
			Tag tag = dao.getTagByValue(null);
			assertNull("TagDao.getTagByValue(null)", tag);
			
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSelectTagInexistant() {
		try {
			TagDao dao = daoFactory.getTagDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getTagDao()", dao);
			
			Tag tag = dao.getTagByValue("xxx");
			assertNull("TagDao.getTagByValue(\"xxx\")", tag);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
