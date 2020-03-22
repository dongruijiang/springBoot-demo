package com.sb.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sb.demo.dao.AccountMapper;
import com.sb.demo.domain.Account;
import com.sb.demo.service.IAccountService;

/**
 * 表account 服务层-接口实现类
 * @date 2020年3月21日 下午8:58:18
 * @author jdr
 */
@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	 AccountMapper accountmp;
	
	/**
	 * 添加数据
	 * @param account
	 * @return
	 */
	public int insertAccount(Account account) {
		return accountmp.insertAccount(account);
	}
	
	/**
	 * 修改数据
	 * @param account
	 * @return
	 */
	public int updateAccount(Account account) {
		return accountmp.updateAccount(account);
	}
	
	/**
	 * 根据ID删除本条数据
	 * @param id
	 * @return
	 */
	public int deleteAccountById(Long id) {
		return accountmp.deleteAccountById(id);
	}
	
	/**
	 * 根据ID查询本条数据信息
	 * @param id
	 * @return
	 */
	public Account selectAccountById(Long id) {
		return accountmp.selectAccountById(id);
	}
	
	/**
	 * 查询所有数据
	 * @param 
	 * @return List<Account>
	 */
	public List<Account> selectAccountAll(){
		return accountmp.selectAccountAll();
	}
	
}
