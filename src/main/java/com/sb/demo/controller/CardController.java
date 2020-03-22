package com.sb.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sb.demo.domain.Card;
import com.sb.demo.domain.result;
import com.sb.demo.service.ICardService;

/**
 * 表card- Controller类
 * @date 2020年3月22日 下午4:29:11
 * @author jdr
 */
@RestController
@RequestMapping("/card")
public class CardController {
	
	@Autowired
	ICardService cardService;
	
	/**
	 * 查询所有数据-不分页
	 * @param 
	 * @return List<Card>
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Card> getAll(){
		return cardService.selectCardAll();
	}
	
	/**
	 * 根据ID查询本条数据的信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Card getInfo(@PathVariable("id") Long id) {
		return cardService.selectCardById(id);
	}
	
	/**
	 * 添加数据
	 * @param Card
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public result addSave(Card card) {
		int rows = cardService.insertCard(card);
		return rows > 0 ? new result("200", "success!") : new result("500", "fail!");
	}
	
	/**
	 * 修改数据
	 * @param Card
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public result editSave(Card card) {
		int rows = cardService.updateCard(card);
		return rows > 0 ? new result("200", "success!") : new result("500", "fail!");
	}
	
	/**
	 * 根据ID删除本条数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public result remove(@PathVariable(value = "id") Long id) {
		int rows = cardService.deleteCardById(id);
		return rows > 0 ? new result("200", "success!") : new result("500", "fail!");
	}

	/**
	 * 小实验  
	 * 声明事务 -- @Transactional
	 * 测试事务回滚；
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.PUT)
	public result editSaveTest(Card card) {
		int rows = cardService.updateCardToCard();
		return rows > 0 ? new result("200", "success!") : new result("500", "fail!");
	}
	
//	 @GetMapping(value = { "/", "/{userId}" })
//	    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId)
//	 public AjaxResult changeStatus(@RequestBody SysUser user)
}
