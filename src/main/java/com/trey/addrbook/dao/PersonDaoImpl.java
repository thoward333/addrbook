package com.trey.addrbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.WebApplicationInitializer;

import com.trey.addrbook.domain.Person;
import com.trey.addrbook.exception.PersonNotFoundException;

@Repository
public class PersonDaoImpl implements PersonDao {
	
	private static final Logger logger = LoggerFactory.getLogger(WebApplicationInitializer.class);

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Person findById(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);

		List<Person> list = jdbcTemplate.query("select * from person where id = :id", params, new PersonRowMapper());
		if (list.isEmpty()) {
			throw new PersonNotFoundException("No person found for id: " + id);
		} else {
			return list.get(0);
		}
	}

	public void insert(Person person) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		logger.debug("inserting person into database");
		jdbcTemplate.update(
				"insert into person (user_name, first_name, last_name) values (:userName, :firstName, :lastName)",
				new BeanPropertySqlParameterSource(person), keyHolder);

		Integer newId = keyHolder.getKey().intValue();

		// populate the id
		person.setId(newId);
	}

	public void update(Person person) {
		int numRowsAffected = jdbcTemplate.update(
				"update person set user_name = :userName, first_name = :firstName, last_name = :lastName where id = :id",
				new BeanPropertySqlParameterSource(person));
		
		if (numRowsAffected == 0) {
			throw new PersonNotFoundException("No person found for id: " + person.getId());
		}
	}

	private static class PersonRowMapper implements RowMapper<Person> {
		public Person mapRow(ResultSet res, int rowNum) throws SQLException {
			Person p = new Person();
			p.setId(res.getInt("id"));
			p.setUserName(res.getString("user_name"));
			p.setFirstName(res.getString("first_name"));
			p.setLastName(res.getString("last_name"));
			return p;
		}
	}

}
