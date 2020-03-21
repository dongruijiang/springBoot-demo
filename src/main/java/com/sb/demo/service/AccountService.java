package com.sb.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.demo.dao.AccountMapper;
import com.sb.demo.domain.Account;

/**
 * 表account
 * @date 2020年3月21日 下午8:58:18
 * @author jdr
 */
@Service
public class AccountService {
	
	@Autowired
	 AccountMapper accountmp;
	
	/**
	 * 添加数据
	 * @param account
	 * @return
	 */
	public int add(Account account) {
		return accountmp.add(account);
	}
	
	/**
	 * 修改数据
	 * @param account
	 * @return
	 */
	public int update(Account account) {
		return accountmp.update(account);
	}
	
	/**
	 * 删除一条数据
	 * @param id
	 * @return
	 */
	public int delete(int id) {
		return accountmp.delete(id);
	}
	
	/**
	 * 查询一条数据信息
	 * @param id
	 * @return
	 */
	public Account findAccount(@Param("id") int id) {
		return accountmp.findAccount(id);
	}
	
	/**
	 * 查询所有数据
	 * @param 
	 * @return List<Account>
	 */
	public List<Account> findAccountList(){
		return accountmp.findAccountList();
	}
	
}
