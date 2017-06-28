package dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import be.steformations.java_data.contacts.interfaces.beans.Tag;
import be.steformations.java_data.contacts.interfaces.dao.TagDao;
import mapper.TagMapper;

public class TagDaoImpl implements TagDao{
	
	private JdbcTemplate _jdbcTemplate;

	public TagDaoImpl(JdbcTemplate jdbcTemplate) {
		this._jdbcTemplate = jdbcTemplate;
	}

	@Override
    public Tag getTagByValue(String value) {
        Tag tag = null;
        if (value != null) {
            String sql = "select * "
                       + "from Tags "
                       + "where Tags.tag = (?)";
            try {
                    TagMapper mapper = new TagMapper();
                    tag = this._jdbcTemplate.queryForObject(sql, mapper, value);
            } catch(org.springframework.dao.EmptyResultDataAccessException e) {e.getMessage();}
        }

        return tag;
    }

	
	@Override
	public Tag getTagById(int id) {
        Tag tag = null;
        if (id != 0) {
            String sql = "select * "
                       + "from Tags "
                       + "where Tags.id = (?)";
            try {
                    TagMapper mapper = new TagMapper();
                    tag = this._jdbcTemplate.queryForObject(sql, mapper, id);
            } catch(org.springframework.dao.EmptyResultDataAccessException e) {e.getMessage();}
        }

        return tag;
	}

	@Override
	public List<? extends Tag> getAllTags() {
        List<Tag> tag = null;
            String sql = "select * "
                       + "from Tags ";
            try {
                    TagMapper mapper = new TagMapper();
                    tag = this._jdbcTemplate.query(sql, mapper);
            } catch(org.springframework.dao.EmptyResultDataAccessException e) {e.getMessage();}

        return tag;
	}

	@Override
	public Tag createAndSaveTag(String value) {
		Tag tag = null;
		
		Tag result = null;
		String sqlInsert = null;
		Object[] parametres = null;
		
		if (value != null){
			result = getTagByValue(value);
			if (result == null){ 
				
				sqlInsert = "insert into tags(tag) values(?)";

				parametres = new Object[] { value };
				try {
					TagMapper mapper = new TagMapper();
					this._jdbcTemplate.update(sqlInsert, value);
					tag = getTagByValue(value);
				} catch (org.springframework.dao.EmptyResultDataAccessException e) {
					e.getMessage();
				}
			} else {
				tag = result;
				
			}
		}
		
		
		return tag;
	}

	@Override
	public void removeTag(int id) {
		String sql = null;
        if (id != 0) {
        	sql = "delete from tags where id = ?";
        	this._jdbcTemplate.update(sql, id);
    		
        }
	}

}
