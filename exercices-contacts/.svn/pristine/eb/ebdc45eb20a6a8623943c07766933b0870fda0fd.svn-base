package be.steformations.java_data.contacts.tests.dao.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.beans.Country;
import be.steformations.java_data.contacts.interfaces.beans.Tag;
import be.steformations.java_data.contacts.interfaces.dao.ContactDao;
import be.steformations.java_data.contacts.interfaces.dao.CountryDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;
import be.steformations.java_data.contacts.interfaces.dao.TagDao;

public class _13_TestCreateAndSaveContact {
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
	public void testInsertContactDejaExistantKo() {
		insertKo("Betty", "Boop", "betty.boop@hollywood.com", "US", java.util.Arrays.asList( new String[] {"vamp", "sex-symbol"} ));
	}

	@Test
	public void testInsertContactPrenomNullKo() {
		long l = System.currentTimeMillis();
		insertKo(null, "X-" + l, "xxx@hollywood.com", "US", java.util.Arrays.asList( new String[] {"vamp", "sex-symbol"} ));
	}	
	
	@Test
	public void testInsertContactNomNullKo() {
		long l = System.currentTimeMillis();
		insertKo("X-" + l, null, "xxx@hollywood.com", "US", java.util.Arrays.asList( new String[] {"vamp", "sex-symbol"} ));
	}	

	@Test
	public void testInsertContactEmailNullKo() {
		long l = System.currentTimeMillis();
		insertKo("X-" + l, "X", null, "US", java.util.Arrays.asList( new String[] {"vamp", "sex-symbol"} ));
	}		
	
	@Test
	public void testInsertContactPaysInexistantKo() {
		long l = System.currentTimeMillis();
		insertKo("X-" + l, "X", "xxx@hollywood.com", "USA", java.util.Arrays.asList( new String[] {"vamp", "sex-symbol"} ));
	}	
	
	@Test
	public void testInsertContactPaysNullEtTagsNullOk() {
		long l = System.currentTimeMillis();
		insertOk("Nura-" + l, "Nal", "dreamgirl@dccomics.com", null, null);
	}
	
	@Test
	public void testInsertContactPaysExistantEtTagsNullOk() {
		long l = System.currentTimeMillis();
		insertOk("Pamela-" + l, "Isley", "poison-ivy@dccomics.com", "US", null);
	}	

	@Test
	public void testInsertContactPaysExistantEtTagsExistantsOk() {
		long l = System.currentTimeMillis();
		insertOk("Betty-" + l, "Kane", "batgirl@dccomics.com", "US", 
				java.util.Arrays.asList( new String[] {"vamp", "sex-symbol"} ));
	}	
	
	@Test
	public void testInsertContactPaysExistantEtTagsInexistantsOk() {
		long l = System.currentTimeMillis();
		insertOk("Diana-" + l, "Prince", "wonder-woman@dccomics.com", "US", 
				java.util.Arrays.asList( new String[] {"vamp", "sex-symbol", "pin-up-" + l} ));
	}		
	
	private void insertKo(String firstname, String name, String email, 
			String countryAbbreviation, java.util.List<String> tagValues) {
		try {
			ContactDao dao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", dao);
			String msg = String.format(
					"ContactDao.createAndSaveContact(\"%s\", \"%s\", \"%s\", \"%s\", %s)", 
					firstname, name, email, countryAbbreviation, tagValues);
			Contact _null = dao.createAndSaveContact(firstname, name, email, countryAbbreviation, tagValues);
			assertNull(msg, _null);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	private void insertOk(String firstname, String name, String email, 
			String abbreviation, java.util.List<String> tagValues) {
		try {
			ContactDao contactDao = daoFactory.getContactDao();
			assertNotNull("TestsDaoFactory.getDaoFactory().getContactDao()", contactDao);
			
			String msg = String.format(
					"ContactDao.createAndSaveContact(\"%s\", \"%s\", \"%s\", \"%s\", %s)", 
					firstname, name, email, abbreviation, tagValues);
			
			Contact contact = contactDao.createAndSaveContact(firstname, name, email, abbreviation, tagValues);
			assertNotNull(msg, contact);
			
			assertEquals(msg + ".getFirtname()", firstname, contact.getFirstname());
			assertEquals(msg + ".getName()", name, contact.getName());
			assertEquals(msg + ".getEmail()", email, contact.getEmail());
			assertTrue(msg + ".getId > 0", contact.getId() > 0);
			
			if ( abbreviation != null ) {
				CountryDao countryDao = daoFactory.getCountryDao();
				assertNotNull("TestsDaoFactory.getDaoFactory().getCountryDao()", countryDao);
				
				Country country = countryDao.getCountryByAbbreviation(abbreviation);
				assertNotNull("CountryDao.getCountryByAbbreviation(\""+abbreviation+"\")", country);
				
				assertNotNull(msg + ".getCountry()", contact.getCountry());
				assertEquals(msg + ".getCountry()", country, contact.getCountry());
			}
			
			if ( tagValues != null && ! tagValues.isEmpty() ) {
				TagDao tagDao = daoFactory.getTagDao();
				assertNotNull("TestsDaoFactory.getDaoFactory().getTagDao()", tagDao);
				
				java.util.List<? extends Tag> contactTags = contactDao.getTagsByContact(contact.getId());
				assertNotNull("ContactDao.getTagsByContact("+contact.getId()+")", contactTags);
				assertEquals("ContactDao.getTagsByContact("+contact.getId()+").size()", tagValues.size(), contactTags.size());
				
				for (String value : tagValues) {
					Tag tag = tagDao.getTagByValue(value);
					assertNotNull("TagDao.getTagByValue(\""+value+"\")", tag);
					assertTrue("ContactDao.getTagsByContact("+contact.getId()+").contains( TagDao.getTagByValue(\""+value+"\") )", 
								contactTags.contains(tag));
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
