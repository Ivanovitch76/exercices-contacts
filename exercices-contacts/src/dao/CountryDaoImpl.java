package dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import be.steformations.java_data.contacts.interfaces.beans.Country;
import be.steformations.java_data.contacts.interfaces.beans.Tag;
import be.steformations.java_data.contacts.interfaces.dao.CountryDao;
import mapper.CountryMapper;
import mapper.TagMapper;

public class CountryDaoImpl implements CountryDao {

	private JdbcTemplate _jdbcTemplate;
	
	public CountryDaoImpl(JdbcTemplate jdbcTemplate) {
		this._jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Country getCountryByAbbreviation(String abbreviation) {
        Country country = null;
        if (abbreviation != null) {
            String sql = "select * from pays where abreviation = ?";

            try {
                    CountryMapper mapper = new CountryMapper();
                    country = this._jdbcTemplate.queryForObject(sql, mapper, abbreviation);
            } catch(org.springframework.dao.EmptyResultDataAccessException e) {e.getMessage();}
        }

        return country;
	}

	@Override
	public List<? extends Country> getAllCountries() {
        List<Country> country = null;
        String sql = "select * from pays";
        try {
        		CountryMapper mapper = new CountryMapper();
                country = this._jdbcTemplate.query(sql, mapper);
        } catch(org.springframework.dao.EmptyResultDataAccessException e) {e.getMessage();}

        return country;
	}

	@Override
	public Country createAndSaveCountry(String abbreviation, String name) {
		Country country = null;
		
		Country result = null;
		String sql = null;
		Object[] parametres = null;
		
		if (abbreviation != null && name != null && getCountryByAbbreviation(abbreviation) == null) {

			sql = "insert into pays(abreviation, nom) values(?, ?)";

			parametres = new Object[] { abbreviation, name };
			try {
				TagMapper mapper = new TagMapper();
				this._jdbcTemplate.update(sql, abbreviation, name);
				country = getCountryByAbbreviation(abbreviation);
			} catch (org.springframework.dao.EmptyResultDataAccessException e) {
				e.getMessage();
			}
		}

		return country;
	}

}
