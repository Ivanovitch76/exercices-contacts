package be.steformations.java_data.contacts.tests.dao.tests;


import org.junit.Test;

import be.steformations.java_data.contacts.interfaces.beans.Country;
import be.steformations.java_data.contacts.interfaces.dao.CountryDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;
import static org.junit.Assert.*;

import org.junit.Before;


public class _06_TestGetAllCountries {
	
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
	public void selectAllPays() {
		try {
			CountryDao dao = daoFactory.getCountryDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getCountryDao()", dao);
			
			Country usa = dao.getCountryByAbbreviation("US");	
			assertNotNull("CountryDao.getCountryByAbbreviation(\"US\")", usa);
			assertEquals("CountryDao.getCountryByAbbreviation(\"US\")", "US", usa.getAbbreviation());
			
			java.util.List<? extends Country> all = dao.getAllCountries();
			assertNotNull("CountryDao.getAllCountries()", all);
			assertTrue("CountryDao.getAllCountries().contains("+usa+")", all.contains(usa));
			
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
