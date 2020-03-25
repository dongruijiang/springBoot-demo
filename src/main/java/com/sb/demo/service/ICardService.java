package com.sb.demo.service;

import java.util.List;
import com.sb.demo.domain.Card;

/**
 * 表card 服务层-接口类
 * @date 2020年3月22日 下午4:14:46
 * @author jdr
 */
public interface ICardService {
	
	/**
	 * 查询一条数据信息
	 * @param id
	 * @return
	 */
	Card selectCardById(Long id);
	
	/**
	 * 查询所有数据
	 * @param 
	 * @return List<Card>
	 */
	List<Card> selectCardAll();
	
	/**
	 * 添加数据
	 * @param Card
	 * @return
	 */
	int insertCard(Card card);
	
	/**
	 * 修改数据
	 * @param Card
	 * @return
	 */
	int updateCard(Card card);
	
	/**
	 * 根据ID删除本条数据
	 * @param id
	 * @return
	 */
	int deleteCardById(Long id);
	
	/**
	 * 小实验  
	 * 声明事务 -- @Transactional
	 * 测试事务回滚；
	 * @return
	 */
	int updateCardToCard();
	
	/**
	 * 测试Redis--添加
	 * @param key
	 * @param value
	 */
	void set(String key,String value);
	
	/**
	 * 测试Redis--获取
	 * @param key
	 * @return
	 */
	String get(String key);
}
