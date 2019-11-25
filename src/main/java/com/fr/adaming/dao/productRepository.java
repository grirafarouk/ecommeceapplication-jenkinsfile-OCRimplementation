package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fr.adaming.Model.Product;

@CrossOrigin("*")
@RepositoryRestResource(path = "grira")
public interface productRepository extends JpaRepository<Product, Long> {
	@RestResource(path = "/selectedproduct")
	public List<Product> findBySelectedIsTrue();

	@RestResource(path = "/promotion")
	public List<Product> findByPromotionIsTrue();

	@RestResource(path = "/available")
	public List<Product> findByAvailableIsTrue();

	@RestResource(path = "/all")
	@Query("select p from Product p")
	public List<Product> findAll();

	@RestResource(path = "/findByKeyword")
	@Query("select p from Product p where p.name like :x")
	public List<Product> chercher(@Param("x") String mc);

}
