package com.web.liuda.business.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.BusinessInfo;
import com.web.liuda.remote.vo.BusinessInfoVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface BusinessInfoService extends BaseServiceInterFace<BusinessInfo>{
	//账户密码验证
	public XaResult<Object> findBusinessByAccount(String account,String password) throws BusinessException ;

	//手机号码验证
	public XaResult<Object> mobileCheck(String mobile)throws BusinessException;
	
	//忘记密码，修改密码
	public XaResult<Boolean> updataByMobile(String mobile,String password)throws BusinessException;
	
	//修改手机号
	public XaResult<Boolean> updatMobile(String oldmobile,String password,String newmobile,Integer type,Long id);
	
	/**
	 * 修改原始密码
	 * @author sj
	 * @param 商户id
	 * @param password
	 * @param newPassword
	 * @return Boolean
	 */
	public XaResult<Boolean> oldpwdUpdata(Long id,String password,String newPassword);
	
	/**
	 * 获取商家信息
	 * @author zhanglin
	 * @param filterParams
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<Page<BusinessInfoVo>> getBusinessInfos(Map<String, Object> filterParams, Pageable pageable) throws BusinessException;
	
	/**
	 * 生成商家账号
	 * @author zhanglin
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<BusinessInfo> createAccount(Integer numbers) throws BusinessException;
	/**
	 * 后台结算管理列表
	 * @author changlu
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<Page<BusinessInfoVo>> findBalanceList(
			Map<String, Object> filterParams, Pageable pageable) throws BusinessException;
	/**
	 * 后台结算管理保存
	 * @author changlu
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<BusinessInfo> createBalance(BusinessInfo model)
			throws BusinessException;
	/**
	 * 根据Id查询一条数据
	 * @author changlu
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<BusinessInfoVo> findBalanceById(Long modelId)
			throws BusinessException;
}
