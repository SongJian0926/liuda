package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.MatchComment;


public interface MatchCommentRepository extends
		PagingAndSortingRepository<MatchComment, Long>,
		JpaSpecificationExecutor<MatchComment> {
	public MatchComment findByIdAndStatusNot(Long id,Integer status);

//  @Query(value="select new com.web.liuda.remote.vo.MatchCommentVo(m.id,m.createTime,m.userId,m.content,m.matchId,m.commentId,m.praiseNum," +
//    		" new com.web.liuda.remote.vo.UserVo(u.id,u.userName,u.photo)) from MatchComment m , User u where m.userId=u.id and m.status=?1 and m.matchId=?2 order by m.createTime desc",
//    		countQuery="select count(1) from MatchComment m , User u where m.userId=u.id and m.status=?1 and m.matchId=?2")
//	public Page<MatchCommentVo> findByMatchId(Integer status, Long matchId, Pageable p);
	
    @Query(value="select m.id,m.createTime,m.userId,m.content,m.matchId,m.commentId,m.praiseNum,u.userName,u.photo " +
    		"from MatchComment m ,User u where m.commentId is null and m.userId=u.id and m.status=?1 and m.matchId=?2 order by m.createTime desc",
    		countQuery="select count(1) from MatchComment m ,User u where m.userId=u.id and m.status=?1 and m.matchId=?2")// m.commentId is null and
	public Page<Object[]> findByMatchId(Integer status, Long matchId, Pageable p);

    @Query(value="select m.id,m.createTime,m.userId,m.content,m.matchId,m.commentId,m.praiseNum,u.userName,u.photo " +
    		"from MatchComment m ,User u where m.commentId is null and m.userId=u.id and m.status=?1 and m.matchId=?2 order by m.createTime desc",
    		countQuery="select count(1) from MatchComment m ,User u where m.userId=u.id and m.status=?1 and m.matchId=?2")// m.commentId is null and
	public List<Object[]> findByMatchId(Integer status, Long matchId);
	
//	@Query(value="select count(1) from MatchComment m ,User u where m.userId=u.id and m.status=?1 and m.matchId=?2")
//	public Integer countCommentAndRepayByMatchId(Integer status, Long matchId);

	@Query(value="select m.id,m.createTime,m.userId,m.content,m.matchId,m.commentId,m.praiseNum,u.userName,u.photo " +
	    		"from MatchComment m ,User u where m.userId=u.id and m.status=?1 and m.matchId=?2 and m.commentId in ?3 order by m.createTime desc")
	public List<Object[]> findRepayByMatchId(Integer status, Long matchId, List<Long> commentIds);
}

