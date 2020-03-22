package com.sb.demo.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sb.demo.dao.CardMapper;
import com.sb.demo.domain.Card;
import com.sb.demo.service.ICardService;

/**
 * 表card 服务层-接口实现类
 * @date 2020年3月22日 下午4:14:46
 * @author jdr
 */
@Service
public class CardServiceImpl implements ICardService{
	
	@Autowired
	CardMapper cardMapper;
	
	/**
	 * 查询一条数据信息
	 * @param id
	 * @return
	 */
	@Override
	public Card selectCardById(Long id) {
		return cardMapper.selectCardById(id);
	}
	
	/**
	 * 查询所有数据
	 * @param 
	 * @return List<Card>
	 */
	@Override
	public List<Card> selectCardAll() {
		return cardMapper.selectCardAll();
	}
	
	/**
	 * 添加数据
	 * @param Card
	 * @return
	 */
	@Override
	public int insertCard(Card card) {
		return cardMapper.insertCard(card);
	}
	
	/**
	 * 修改数据
	 * @param Card
	 * @return
	 */
	@Override
	public int updateCard(Card card) {
		return cardMapper.updateCard(card);
		
	}
	
	/**
	 * 根据ID删除本条数据
	 * @param id
	 * @return
	 */
	@Override
	public int deleteCardById(Long id) {
		return cardMapper.deleteCardById(id);
	}
	
	/**
	 * 小实验  
	 * 声明事务 -- @Transactional
	 * 测试事务回滚；
	 * @return
	 */
	@Override
	@Transactional
	public int updateCardToCard() throws RuntimeException{
		/** 1.给C1用户卡号减10 */
		Card c1 = new Card();
		c1.setId(1L);
		c1.setName("aaa_1");
		c1.setCardNumber("990");
		cardMapper.updateCard(c1);
		/** 2.制造错误异常 */
		int i=1/0;
		/** 3.给C2用户卡号加10 */
		Card c2 = new Card();
		c2.setId(2L);
		c2.setName("aaa_2");
		c2.setCardNumber("2010");
		return cardMapper.updateCard(c2);
	}
}
