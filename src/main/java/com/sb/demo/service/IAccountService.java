package com.sb.demo.service;

import java.util.List;
import com.sb.demo.domain.Account;

/**
 * 表account 服务层-接口类
 * @date 2020年3月21日 下午8:58:18
 * @author jdr
 */
public interface IAccountService {
	
	/**
	 * 添加数据
	 * @param account
	 * @return
	 */
	int insertAccount(Account account);
	
	/**
	 * 修改数据
	 * @param account
	 * @return
	 */
	int updateAccount(Account account);
	
	/**
	 * 根据ID删除本条数据
	 * @param id
	 * @return
	 */
	int deleteAccountById(Long id);
	
	/**
	 * 根据ID查询本条数据信息
	 * @param id
	 * @return
	 */
	Account selectAccountById(Long id);
	
	/**
	 * 查询所有数据
	 * @param 
	 * @return List<Account>
	 */
	List<Account> selectAccountAll();

}
