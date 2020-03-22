package com.sb.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sb.demo.domain.Account;
import com.sb.demo.domain.result;
import com.sb.demo.serviceimpl.AccountServiceImpl;

/**
 * 表account
 * @date 2020年3月21日 下午9:06:17
 * @author jdr
 */
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountServiceImpl accountService;
	
	/**
	 * 查询所有数据-不分页
	 * @param 
	 * @return List<Account>
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Account> getAll(){
		return accountService.selectAccountAll();
	}
	
	/**
	 * 根据ID查询本条数据的信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Account getInfo(@PathVariable("id") Long id) {
		return accountService.selectAccountById(id);
	}
	
	/**
	 * 添加数据 接收数据格式为 application/json; charset=UTF-8
	 * 
	 * 后端参数：@RequestBody Account account -接收json对象
	 * 
	 * 前端：传递json对象格式
	 * $.ajax({
	 * 		contentType:"application/json; charset=UTF-8",//数据提交格式
	 * 		data:JSON.stringify({"name":"eee-9","money":9000.01}), //参数值
	 * 		...
	 * @param account--json
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public result addSave(@RequestBody Account account) {
		int rows = accountService.insertAccount(account);
		return rows > 0 ? new result("200", "success!") : new result("500", "fail!");
	}
	
	/**
	 * 添加数据 接收数据格式为 application/x-www-form-urlencoded; charset=UTF-8
	 * 
	 * 后端参数：Account account -接收json对象
	 * 
	 * 前端：传递json对象格式
	 * $.ajax({
	 * 		contentType:"application/x-www-form-urlencoded; charset=UTF-8",//数据提交格式
	 * 		data:{"name":"eee-9","money":9000.01}, //参数值
	 * 		...
	 * @param account
	 * @return
	 */
//	@RequestMapping(value = "", method = RequestMethod.POST)
//	public result addSave(Account account) {
//		int rows = accountService.insertAccount(account);
//		return rows > 0 ? new result("200", "success!") : new result("500", "fail!");
//	}
	
	
	/**
	 * 修改数据
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public result editSave(Account account) {
		int rows = accountService.updateAccount(account);
		return rows > 0 ? new result("200", "success!") : new result("500", "fail!");
	}
	
	/**
	 * 根据ID删除本条数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public result remove(@PathVariable(value = "id") Long id) {
		int rows = accountService.deleteAccountById(id);
		return rows > 0 ? new result("200", "success!") : new result("500", "fail!");
	}

}
