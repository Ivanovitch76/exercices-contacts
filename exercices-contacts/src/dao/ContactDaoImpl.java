package dao;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.jdbc.core.JdbcTemplate;

import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.beans.Country;
import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.beans.Tag;
import be.steformations.java_data.contacts.interfaces.dao.ContactDao;
import mapper.ContactMapper;
import mapper.CountryMapper;
import mapper.TagMapper;

public class ContactDaoImpl implements ContactDao {
	
	private JdbcTemplate _jdbcTemplate;

	public ContactDaoImpl(JdbcTemplate jdbcTemplate) {
		this._jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Contact getContactByFirstnameAndName(String firstname, String name) {
		Contact contact = null;
        if (firstname != null && name != null) {
            String sql = "select c.id, c.prenom, c.nom, c.email, c.pays, p.id, p.abreviation, p.nom "
            		+ "from contacts as c left join pays as p on c.pays = p.id "
            	    + "where c.prenom = ? and c.nom = ?";

            try {
            	    ContactMapper mapper = new ContactMapper();
            	    contact = this._jdbcTemplate.queryForObject(sql, mapper, firstname, name);
            } catch(org.springframework.dao.EmptyResultDataAccessException e) {e.getMessage();}
        }
        return contact;
	}

	@Override
	public Contact getContactById(int id) {
		Contact contact = null;
        if (id != 0) {
            String sql = "select c.id, c.prenom, c.nom, c.email, c.pays, p.id, p.abreviation, p.nom "
            		+ "from contacts as c left join pays as p on c.pays = p.id "
            	    + "where c.id = ?";

            try {
            	    ContactMapper mapper = new ContactMapper();
            	    contact = this._jdbcTemplate.queryForObject(sql, mapper, id);
            } catch(org.springframework.dao.EmptyResultDataAccessException e) {e.getMessage();}
        }
        return contact;
	}

	@Override
	public List<? extends Contact> getContactsByCountry(String abbreviation) {
        List<Contact> contact = null;
        String sql = "select c.id, c.prenom, c.nom, c.email, c.pays, p.id, p.abreviation, p.nom "
        		+ "from contacts as c left join pays as p on c.pays = p.id "
        	    + "where p.abreviation = ?";
        try {
        		ContactMapper mapper = new ContactMapper();
                contact = this._jdbcTemplate.query(sql, mapper, abbreviation);
        } catch(org.springframework.dao.EmptyResultDataAccessException e) {e.getMessage();}

        return contact;
	}

	@Override
	public List<? extends Contact> getAllContacts() {
        List<Contact> contact = null;
        String sql = "select c.id, c.prenom, c.nom, c.email, c.pays, p.id, p.abreviation, p.nom "
        		+ "from contacts as c left join pays as p on c.pays = p.id";

        try {
        		ContactMapper mapper = new ContactMapper();
                contact = this._jdbcTemplate.query(sql, mapper);
        } catch(org.springframework.dao.EmptyResultDataAccessException e) {e.getMessage();}

        return contact;
	}

	@Override
	public List<? extends Tag> getTagsByContact(int id) {
        List<Tag> tag = null;
        if (id != 0) {
            String sql = "select * from tags as t join contacts_tags as ct on ct.tag = t.id "
                       + "join contacts as c on ct.contact = c.id "
                       + "where c.id = (?)";
            try {
                    TagMapper mapper = new TagMapper();
                    tag = this._jdbcTemplate.query(sql, mapper, id);
            } catch(org.springframework.dao.EmptyResultDataAccessException e) {e.getMessage();}
        }

        return tag;
	}

	@Override
	public Contact createAndSaveContact(String firstname, String name, String email, String countryAbbreviation,
			List<String> tagValues) {
		Contact contact = null;
		Contact result = null;
		CountryDaoImpl cDI = new CountryDaoImpl(_jdbcTemplate);
		TagDaoImpl tDI = new TagDaoImpl(_jdbcTemplate);
		
		Country country = null;
		Tag tag = null;
		String sql = null;
		country = cDI.getCountryByAbbreviation(countryAbbreviation);
		result = getContactByFirstnameAndName(firstname, name);
		if (firstname != null && email != null && name != null && (country != null || countryAbbreviation == null) && result == null) {

			sql = "insert into contacts(prenom, nom, email, pays) values(?, ?, ?, ?)";

			try {
				if (country == null){					
					this._jdbcTemplate.update(sql, firstname, name, email, null);
				} else {
					this._jdbcTemplate.update(sql, firstname, name, email, country.getId());
				}
				contact = getContactByFirstnameAndName(firstname, name);
				if (tagValues != null){ 
				Iterator<String> tagValuesIterator = tagValues.iterator();
				while (tagValuesIterator.hasNext()) {
				    String tagVal = tagValuesIterator.next();
				    if (tagVal != null){
				    	tag = tDI.getTagByValue(tagVal);
				    	if (tag == null){
				    		tDI.createAndSaveTag(tagVal);
				    		tag = tDI.getTagByValue(tagVal);
				    		createAndSaveContactsTags(tag.getId(), contact.getId());
				    	} else {
				    		tag = tDI.getTagByValue(tagVal);
				    		createAndSaveContactsTags(tag.getId(), contact.getId());
				    	}
				    }
				}
				}
			} catch (org.springframework.dao.EmptyResultDataAccessException e) {
				e.getMessage();
			}
		} 

		return contact;
	}

	private void createAndSaveContactsTags(Integer tagId, Integer contactId) {

		String sqlInsert = null;

		sqlInsert = "insert into contacts_tags(contact, tag) values(?, ?)";

		try {
			this._jdbcTemplate.update(sqlInsert, contactId, tagId);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			e.getMessage();
		}

	}


	@Override
	public boolean removeContact(int id) {
		boolean resultat = false;
		String sql = null;
        if (id != 0 && getContactById(id) != null) {
        	sql = "delete from contacts where id = ?";
        	this._jdbcTemplate.update(sql, id);
        	if (getContactById(id) == null){
        		resultat = true;
        	}
    		
        }
		return resultat;
	}

}
