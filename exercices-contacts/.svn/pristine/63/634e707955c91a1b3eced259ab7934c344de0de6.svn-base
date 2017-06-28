package be.steformations.java_data.contacts.tests.dao.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.contacts.interfaces.beans.Tag;
import be.steformations.java_data.contacts.interfaces.dao.TagDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;


public class _03_TestGetAllTags {

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
	public void testSelectAll() {
		try {
			TagDao dao = daoFactory.getTagDao();
			assertNotNull("TestsDaoFactory.getDaoFactory()getTagDao()", dao);
		 
			Tag vamp = dao.getTagByValue("vamp");	
			assertNotNull("TagDao.getTagByValue(\"vamp\")", vamp);
			assertEquals("TagDao.getTagByValue(\"vamp\").getValue()", "vamp", vamp.getValue());
			
			java.util.List<? extends Tag> all = dao.getAllTags();
			assertNotNull("TagDao.getAllTags()", all);
			assertTrue("TagDao.getAllTags().contains("+vamp+")", all.contains(vamp));

		 
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
