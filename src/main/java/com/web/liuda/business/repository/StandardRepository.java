package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Standard;


public interface StandardRepository extends
		PagingAndSortingRepository<Standard, Long>,
		JpaSpecificationExecutor<Standard> {
	public Standard findByIdAndStatusNot(Long id,Integer status);
	
	public List<Standard> findByShopIdAndStatus(Long shopId,Integer status);
	/**
	 * 根据订单号查询订单详情信息
	 * @param orderNo
	 * @param status
	 * @return
	 */
	@Query(value="select s.stocks,s.id,s.shop_id,od.shop_number,o.user_id from tb_xa_standard s inner join tb_xa_orderdetail od on od.standard_id=s.id " +
			"inner join tb_xa_order o on o.order_no=od.order_no where od.order_no=? and od.status=1 and s.status=1",nativeQuery=true)
	public List<Object[]> findByOrderNo(String orderNo);
	
	public Standard findByIdAndValidityGreaterThanEqualAndStatusNotAndGroupBuy(Long id,String validity,Integer status,Integer groupBuy);
}
