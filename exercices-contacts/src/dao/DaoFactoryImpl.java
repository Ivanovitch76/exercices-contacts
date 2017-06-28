package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import be.steformations.java_data.contacts.interfaces.dao.ContactDao;
import be.steformations.java_data.contacts.interfaces.dao.CountryDao;
import be.steformations.java_data.contacts.interfaces.dao.DaoFactory;
import be.steformations.java_data.contacts.interfaces.dao.TagDao;


public class DaoFactoryImpl implements DaoFactory {
    private ContactDaoImpl _daoContact;
    private CountryDaoImpl _daoCountry;
    private TagDaoImpl _daoTag;

    public DaoFactoryImpl(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost/contacts", "postgres", "postgres");
    //DataSource dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:postgresql://PRIM2014-14/contact", "postgres", "postgres");
    JdbcTemplate jdbcTemplate = new JdbcTemplate((javax.sql.DataSource) dataSource);
        this._daoContact = new ContactDaoImpl(jdbcTemplate);
        this._daoCountry = new CountryDaoImpl(jdbcTemplate);
        this._daoTag = new TagDaoImpl(jdbcTemplate);
    }

    @Override
    public ContactDao getContactDao() {
        return this._daoContact;
    }

    @Override
    public CountryDao getCountryDao() {
        return this._daoCountry;
    }

    @Override
    public TagDao getTagDao() {
        return this._daoTag;
    }

}

