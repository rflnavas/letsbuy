package com.rnr.letsbuy.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class JdbcRepository {
	
	protected final JdbcTemplate jdbc;
	
	@Autowired
	public JdbcRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
}
