package com.sb.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sb.demo.domain.Account;
import com.sb.demo.domain.result;
import com.sb.demo.service.AccountService;

/**
 * 表account
 * @date 2020年3月21日 下午9:06:17
 * @author jdr
 */
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	/**
	 * 查询所有数据
	 * @param 
	 * @return List<Account>
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Account> getAccounts(){
		return accountService.findAccountList();
	}
	
	/**
	 * 查询一条数据信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Account getAccountById(@PathVariable("id") int id) {
		return accountService.findAccount(id);
	}
	
	/**
	 * 添加数据
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public result postAccount(Account account) {
		int rows = accountService.add(account);
		return rows > 0 ? new result("200", "success!") : new result("500", "fail!");
	}
	
	/**
	 * 修改数据
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public result updAccount(Account account) {
		int rows = accountService.update(account);
		return rows > 0 ? new result("200", "success!") : new result("500", "fail!");
	}
	
	/**
	 * 删除一条数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public result delAccount(@PathVariable(value = "id") int id) {
		int rows = accountService.delete(id);
		return rows > 0 ? new result("200", "success!") : new result("500", "fail!");
	}

}
