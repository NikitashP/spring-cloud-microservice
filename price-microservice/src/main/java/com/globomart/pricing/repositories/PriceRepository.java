package com.globomart.pricing.repositories;

import com.globomart.pricing.domain.Price;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "price", path = "price")
public interface PriceRepository extends PagingAndSortingRepository<Price, Long> {

	List<Price> findByProductName(@Param("name") String name);

}
