package com.globomart.catalog.repository;

import com.globomart.catalog.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "products", path = "catalog")
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

	List<Product> findByProductName(@Param("name") String name);

	List<Product> findByProductType(@Param("type") String type);

}
