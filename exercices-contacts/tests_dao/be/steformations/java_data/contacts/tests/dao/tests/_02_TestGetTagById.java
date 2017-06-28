package be.steformations.java_data.contacts.tests.dao.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.contacts.interfaces.beans.Tag;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;
import be.steformations.java_data.contacts.interfaces.dao.TagDao;


public class _02_TestGetTagById {

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
	public void selectTagExistant() {
		try {	
			TagDao dao = daoFactory.getTagDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getTagDao()", dao);
		
			Tag vampByValue = dao.getTagByValue("vamp");
			assertNotNull("TagDao.getTagByValue(\"vamp\")", vampByValue);
			assertEquals("TagDao.getTagByValue(\"vamp\").getValue()", "vamp", vampByValue.getValue());
			
			Tag vampById = dao.getTagById(vampByValue.getId());	
			assertNotNull("TagDao.getTagById(" + vampByValue.getId() + ")", vampById);
			assertEquals("TagDao.getTagById(" + vampByValue.getId() + ").getValue()", "vamp", vampById.getValue());	

		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void selectTagInexistant() {
		try {
			TagDao dao = daoFactory.getTagDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getTagDao()", dao);
			
			Tag tag = dao.getTagById(-1);
			assertNull("TagDao.getTagById(-1)", tag);
			
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
