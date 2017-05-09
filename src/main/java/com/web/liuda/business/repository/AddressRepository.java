package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Address;


public interface AddressRepository extends
		PagingAndSortingRepository<Address, Long>,
		JpaSpecificationExecutor<Address> {
	public Address findByIdAndStatusNot(Long id,Integer status);

	public List<Address> findByUserIdAndStatusNot(Long userId, Integer status);
}
