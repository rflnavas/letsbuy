package com.rnr.letsbuy.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rnr.letsbuy.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	List<Product> findAllByOrderByNameAsc();

	List<Product> findAllByOrderByPriceAsc();
	
	List<Product> findAllByTagsIn(Collection<String> keyword);
	
	@Query(value = "select * from product where name like %:name% and price >= :price", nativeQuery=true)
	public List<Product> findProductsByNameAndPriceFrom(@Param("name") String name, @Param("price") double priceFrom);
}
