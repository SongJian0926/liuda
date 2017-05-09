package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.BusinessInfo;


public interface BusinessInfoRepository extends
		PagingAndSortingRepository<BusinessInfo, Long>,
		JpaSpecificationExecutor<BusinessInfo> {
	public BusinessInfo findByIdAndStatusNot(Long id,Integer status);
	
	@Query(value="select b.* from tb_xa_businessinfo b where b.id=? and b.status=?",nativeQuery=true)
	public BusinessInfo findByIdAndStatus(Long id,Integer status);
	
	public BusinessInfo findByAccountAndStatusNot(String accout,Integer status);
}
