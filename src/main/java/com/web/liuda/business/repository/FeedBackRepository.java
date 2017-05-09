package com.web.liuda.business.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.FeedBack;


public interface FeedBackRepository extends
		PagingAndSortingRepository<FeedBack, Long>,
		JpaSpecificationExecutor<FeedBack> {
	//查询反馈意见
	public Page<FeedBack> findByUserIdAndStatusNot(Long userId,Integer status,Pageable pageable);
	public FeedBack findByIdAndStatusNot(Long id,Integer status);
	
	@Query(value="select count(f.id) from tb_xa_feedback f where f.`status`<>3 and f.user_id=?",nativeQuery=true)
	public int getCountPage(Long userId);
	//用户信息显示
	@Query(value="select new FeedBack(c.id,c.createTime,c.content,c.userId,u.userName) from User u,FeedBack c where u.id=c.userId and u.status<>3 and c.status<>3 order by c.createTime desc",
    		countQuery="select count(*) from FeedBack c,User u where c.id is not null and u.id=c.userId and u.status<>3 and c.status<>3")
	public Page<FeedBack> findUser(Pageable p);
	//用户信息显示
    @Query(value="select new FeedBack(c.id,c.createTime,c.content,c.userId,u.userName) from User u,FeedBack c where u.id=c.userId and u.status<>3 and c.status<>3 and u.userName like ?1 and c.createTime > ?2 order by c.createTime desc",
    		countQuery="select count(*) from FeedBack c,User u where c.id is not null and u.id=c.userId and u.status<>3 and c.status<>3 and u.userName like ?1 and c.createTime > ?2")
	public Page<FeedBack> findUser1(String username,String createtime,Pageable p);
   //用户信息显示
    @Query(value="select new FeedBack(c.id,c.createTime,c.content,c.userId,u.userName) from User u,FeedBack c where u.id=c.userId and u.status<>3 and c.status<>3 and u.userName like ?1 and c.createTime < ?2 order by c.createTime desc",
    		countQuery="select count(*) from FeedBack c,User u where c.id is not null and u.id=c.userId and u.status<>3 and c.status<>3 and u.userName like ?1 and c.createTime < ?2")
	public Page<FeedBack> findUser2(String username,String createtime,Pageable p);
  //用户信息显示
    @Query(value="select new FeedBack(c.id,c.createTime,c.content,c.userId,u.userName) from User u,FeedBack c where u.id=c.userId and u.status<>3 and c.status<>3 and u.userName like ?1 and c.createTime between ?2 and ?3 order by c.createTime desc",
    		countQuery="select count(*) from FeedBack c,User u where c.id is not null and u.id=c.userId and u.status<>3 and c.status<>3 and u.userName like ?1 and c.createTime  between ?2 and ?3")
	public Page<FeedBack> findUser3(String username,String createtime,String endTime,Pageable p);
    //查询单个用户的详细信息
    @Query("select new FeedBack(c.id,c.createTime,c.content,u.userName) from User u,FeedBack c where u.id=c.userId and u.status<>3 and c.status<>3 and c.id=?1 and c.createTime=?2")
	public FeedBack findOneUser(Long id,String CreateTime);
}
