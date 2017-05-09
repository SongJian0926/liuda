package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.DictItem;


public interface DictItemRepository extends
		PagingAndSortingRepository<DictItem, Long>,
		JpaSpecificationExecutor<DictItem> {
	public DictItem findByIdAndStatusNot(Long id,Integer status);
	/**
	 * 查询该类型的选项是否存在
	 * @param id
	 * @param dictName
	 * @param status
	 * @return
	 */
	public List<DictItem> findByDictIdAndDictNameAndStatusAndIdNot(Long dictId,String dictName,Integer status,Long id);
	/**
	 * 查询该类型的排序是否存在
	 * @param id
	 * @param dictName
	 * @param status
	 * @return
	 */
	public List<DictItem> findByDictIdAndSortAndStatusAndIdNot(Long dictId,Integer sort,Integer status,Long id);
	
	/**
	 * 查询所有字典选项列表
	 * author：changlu
	 * time:2016-05-06 10:32:00
	 * @param dictName
	 * @param status
	 * @param pageable
	 * @return
	 *//*
	@Query(value="select new DictItem( di.id,di.dictName,di.sort,di.dictId,d.dictName) from DictItem di, Dict d where di.dictId=d.id and di.status=?1 and d.status=?1 ",
			countQuery ="select count(*) from DictItem di, Dict d where di.dictId=d.id and di.status=?1 and d.status=?1 ")
	public Page<DictItem> findDictItem(Integer status,Pageable pageable);
	*//**
	 * 根据字典选项名称查询
	 * author：changlu
	 * time:2016-05-06 10:32:00
	 * @param dictName
	 * @param status
	 * @param pageable
	 * @return
	 *//*
	@Query(value="select new DictItem( di.id,di.dictName,di.sort,di.dictId,d.dictName) from DictItem di, Dict d where di.dictId=d.id and di.dictName=?1 and di.status=?2 and d.status=?2 ",
			countQuery ="select count(*) from DictItem di, Dict d where di.dictId=d.id and di.dictName=?1 and di.status=?2 and d.status=?2 ")
	public Page<DictItem> findDictItemByDictName(String dictName,Integer status,Pageable pageable);
	*//**
	 * 根据字典名称查询
	 * author：changlu
	 * time:2016-05-06 10:32:00
	 * @param dictName
	 * @param status
	 * @param pageable
	 * @return
	 *//*
	@Query(value="select new DictItem(di.id,di.dictName,di.sort,di.dictId,d.dictName) from DictItem di, Dict d where di.dictId=d.id and d.dictName=?1 and di.status=?2 and d.status=?2 ",
			countQuery ="select count(*) from DictItem di, Dict d where di.dictId=d.id and d.dictName=?1 and di.status=?2 and d.status=?2 ")
	public Page<DictItem> findDictItemByDictId(String dictName,Integer status,Pageable pageable);
	
	*//**
	 * 根据字典名称和字典选项名称 查询
	 * author：changlu
	 * time:2016-05-06 11:32:00
	 * @param dictName
	 * @param status
	 * @param pageable
	 * @return
	 *//*
	@Query(value="select new DictItem(di.id,di.dictName,di.sort,di.dictId,d.dictName) from DictItem di, Dict d where di.dictId=d.id and di.dictName=?1 and d.dictName=?2 and di.status=?3 and d.status=?3 ",
			countQuery ="select count(*) from DictItem di, Dict d where di.dictId=d.id and di.dictName=?1 and d.dictName=?2 and di.status=?3 and d.status=?3 ")
	public Page<DictItem> findDictItemByDictNameAndDictId(String dictName,String dictId,Integer status,Pageable pageable);
	*/
}
