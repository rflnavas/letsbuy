package com.rnr.letsbuy.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rnr.letsbuy.model.Order;

/**
 * With the aim of increasing examples of many different features that Spring provides, 
 * I want to show a repository which is JDBCTemplate based.
 * @author rafael.navas.ruiz
 *
 */
@Repository
public class OrderJDBCRepository extends JdbcRepository{
	
	public OrderJDBCRepository(JdbcTemplate jdbc) {
		super(jdbc);
	}

	public Order findFirst(){
		final String sql = "select * from orders limit 1";
		return this.jdbc.queryForObject(sql, new OrderMapper());
		
	}

	public Order findById(Long id) {
		final String sql = "SELECT * from orders where id=?";
		return this.jdbc.queryForObject(sql, new Object[]{id}, new OrderMapper());
	}
	
	public Set<Order> findAllOrderByTimestampOnDesc(){
		final String sql = "select * from orders order by datetime desc";
		return this.jdbc.query(sql, new OrderMapper()).stream().collect(Collectors.toSet());
	}
	
	private class OrderMapper implements RowMapper<Order>{
		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setId(rs.getLong("id"));
			order.setTimestamp(rs.getDate("datetime"));
			order.setOrderId(rs.getString("order_id"));
//			order.setCustomer(rs.getLong("customer_id"));
//			order.setProduct(rs.getLong("product_id"));
			return order;
		}
	}
	
}
