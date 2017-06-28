package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import be.steformations.java_data.contacts.interfaces.beans.Country;
import beans.BeansCountry;

public class CountryMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet rs, int row) throws SQLException {
        int id = rs.getInt("id");
        if(rs.wasNull()) return null;
        String abb  = rs.getString("abreviation");
        String nom = rs.getString("nom");
        return new BeansCountry(id, abb, nom);
    }

}
