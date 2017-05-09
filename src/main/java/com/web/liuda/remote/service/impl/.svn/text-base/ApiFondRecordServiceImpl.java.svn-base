package com.web.liuda.remote.service.impl;

import com.web.liuda.business.entity.FondRecord;
import com.web.liuda.business.entity.UserCard;
import com.web.liuda.remote.vo.FondRecordVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.repository.FondRecordRepository;
import com.web.liuda.business.repository.UserCardRepository;
import com.web.liuda.remote.service.ApiFondRecordService;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIFondRecord接口实现类
 **/
@Service("ApiFondRecordService")
@Transactional(readOnly = false)
public class ApiFondRecordServiceImpl extends BaseService<FondRecord> implements ApiFondRecordService{

	@Autowired
	FondRecordRepository fondRecordRepository;
	@Autowired
	UserCardRepository userCardRepository;
	
	@Override
	public XaResult<FondRecordVo> findOne(Long tId) throws BusinessException {
		FondRecord obj = fondRecordRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<FondRecordVo> xr = new XaResult<FondRecordVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),FondRecordVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<FondRecordVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<FondRecord> page = fondRecordRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), FondRecord.class), pageable);
		XaResult<List<FondRecordVo>> xr = new XaResult<List<FondRecordVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), FondRecordVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<FondRecordVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<FondRecord> page = fondRecordRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), FondRecord.class), pageable);
		XaResult<List<FondRecordVo>> xr = new XaResult<List<FondRecordVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), FondRecordVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<FondRecordVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<FondRecordVo> xr = new XaResult<FondRecordVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				FondRecord obj = fondRecordRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = fondRecordRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), FondRecordVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<FondRecordVo> createModel(FondRecord model)
			throws BusinessException {
		XaResult<FondRecordVo> xr = new XaResult<FondRecordVo>();
		FondRecord obj = fondRecordRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), FondRecordVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<FondRecordVo> updateModel(FondRecord model)
			throws BusinessException {
		FondRecord obj = fondRecordRepository.findOne(model.getId());
		XaResult<FondRecordVo> xr = new XaResult<FondRecordVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setPrice(model.getPrice());
		obj.setOrigin(model.getOrigin());
		obj.setUserId(model.getUserId());
		obj.setType(model.getType());
			obj = fondRecordRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), FondRecordVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	//资产记录
	@Override
	public XaResult<FondRecordVo> findFondRecordListByUserId(
			Pageable pageable, Long userId) {
		XaResult<FondRecordVo> xr = new XaResult<FondRecordVo>();
		StringBuffer sql = new StringBuffer("");
		sql.append("select fr.id,fr.origin,fr.price,fr.type,fr.present_state from tb_xa_fondrecord fr where fr.`status`=1 and fr.user_id="+userId+"");
		StringBuffer sqlCount = new StringBuffer("");
		sqlCount.append("select COUNT(fr.id) from tb_xa_fondrecord fr where fr.`status`=1 and fr.user_id="+userId+"");
		
		List<Object[]> objs = this.query(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize());
		List<FondRecordVo> fondRecordList = new ArrayList<FondRecordVo>();
		Double generalIncome = 0.00;
		for(Object[] obj:objs){
			FondRecordVo vo =new FondRecordVo();
			vo.setId(Long.parseLong(obj[0]+""));
			vo.setOrigin((String)obj[1]);
			vo.setType((Integer)obj[3]);
			vo.setPrice(Double.parseDouble(obj[2]+""));
			if((Integer)obj[3]==1){//收入
				generalIncome+=Double.parseDouble(obj[2]+"");
			}else if((Integer)obj[3]==2){//支出
				if((Integer)obj[4]!=3){//提现成功的
					generalIncome-=Double.parseDouble(obj[2]+"");
				}
				vo.setPresentState((Integer)obj[4]);
			}
			fondRecordList.add(vo);
		}
		Page<FondRecordVo> page = new MyPage<FondRecordVo>(pageable.getPageNumber(),pageable.getPageSize(),fondRecordList,Integer.parseInt(this.query(sqlCount.toString(), 0, 0).get(0)+""));
		FondRecordVo newVo = new FondRecordVo();
		newVo.setPageFondRecordVo(page);
		newVo.setGeneralIncome(generalIncome);
		xr.setObject(newVo);
		return xr;
	}
	//提现中
	@Transactional
	public XaResult<String> withdrawCashIng(Long userId, Double cash,Long cardId) {
		XaResult<String> xr = new XaResult<String>();
		UserCard uc = userCardRepository.findByIdAndStatus(cardId, XaConstant.Status.valid);
		if(XaUtil.isNotEmpty(uc)){
			if(cash<=0){
				xr.error("提现金额必须大于等于0");
			}else{
				StringBuffer sql = new StringBuffer("");
				sql.append("select fr.id,fr.origin,fr.price,fr.type,fr.present_state from tb_xa_fondrecord fr where fr.`status`=1 and fr.user_id="+userId+"");
				List<Object[]> objs = this.query(sql.toString(), 0, 0);
				Double generalIncome = 0.00;
				for(Object[] obj:objs){
					if((Integer)obj[3]==1){//收入
						generalIncome+=Double.parseDouble(obj[2]+"");
					}else if((Integer)obj[3]==2){//支出
						if((Integer)obj[4]!=3){//提现成功的
							generalIncome-=Double.parseDouble(obj[2]+"");
						}
					}
				}
				//资产记录表中插入数据
				StringBuffer sqlInsert = new StringBuffer("");
				sqlInsert.append("insert into tb_xa_fondrecord(create_time,status,price,origin,user_id,type,present_state,bank_card_id,card_holder,idcard,opening_bank,opening_area,sub_bank_nam)" +
						" values('"+XaUtil.getToDayStr()+"',"+XaConstant.Status.valid+","+cash+",'提现至银行卡',"+userId+",2,1,'"+uc.getBankCardId()+"'," +
								"'"+uc.getCardHolder()+"','"+uc.getIdcard()+"','"+uc.getOpeningBank()+"','"+uc.getOpeningArea()+"','"+uc.getSubBankNam()+"')");
				//修改用户表中用户资产
				String sqlUpdateUser="update tb_xa_user u set u.balance=u.balance-"+cash+" where u.id="+userId+"";
				
				if(cash<=generalIncome){
					this.insert(sqlInsert.toString());
					this.insert(sqlUpdateUser);
					xr.setObject("提现已提交,正在审核中");
				}else{
					xr.error("余额不足,提现失败。");
				}
			}
		}else{
			xr.error("银行卡不存在,提现失败。");
		}
		return xr;
	}

}
