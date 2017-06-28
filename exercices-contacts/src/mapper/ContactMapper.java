package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import be.steformations.java_data.contacts.interfaces.beans.Contact;
import be.steformations.java_data.contacts.interfaces.beans.Country;
import beans.BeansContact;
import beans.BeansCountry;

public class ContactMapper implements RowMapper<Contact> {

	@Override
	public Contact mapRow(ResultSet rs, int row) throws SQLException {
		int id = rs.getInt(1);
        //if(rs.wasNull()) return null; 
        String name = rs.getString("nom");
        String firstname = rs.getString("prenom");
        String email = rs.getString("email");
        int idPays = rs.getInt(5);
        Country beansPays = null;
        if(!rs.wasNull()){
            String abb = rs.getString(7);
            String nomPays = rs.getString(8);
            beansPays = new BeansCountry(idPays, abb, nomPays);
        }
        BeansContact bc = null;
        if( name == null || firstname == null || email == null) return bc;
        bc = new BeansContact(id, name, firstname, email, beansPays);
        return bc;
	}

    
}


