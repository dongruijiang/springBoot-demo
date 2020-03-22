package com.sb.demo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sb.demo.domain.Card;

/**
 * 表card 数据层-接口类
 * @date 2020年3月22日 下午2:56:22
 * @author jdr
 */
@Mapper
public interface CardMapper {
	
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
	int deleteCardById(@Param("id") Long id);
	
	/**
	 * 根据ID查询本条数据信息
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
}
