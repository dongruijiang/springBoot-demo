package com.sb.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sb.demo.domain.Account;

/**
 * 表account 数据层-接口类
 * @date 2020年3月21日 下午8:25:30
 * @author jdr
 */
@Mapper
public interface AccountMapper {
	
	/**
	 * 添加数据
	 * @param account
	 * @return
	 */
	@Insert("insert into account(name,money) values(#{name},#{money})")
	int insertAccount(Account account);
	
	/**
	 * 修改数据
	 * @param account
	 * @return
	 */
	@Update("update account set name=#{name},money=#{money} where id=#{id}")
	int updateAccount(Account account);
	
	/**
	 * 根据ID删除本条数据
	 * @param id
	 * @return
	 */
	@Delete("delete from account where id = #{id}")
	int deleteAccountById(@Param("id") Long id);
	
	/**
	 * 根据ID查询本条数据信息
	 * @param id
	 * @return
	 */
	@Select("select id,name,money from account where id = #{id}")
	Account selectAccountById(@Param("id") Long id);
	
	/**
	 * 查询所有数据
	 * @param 
	 * @return List<Account>
	 */
	@Select("select id,name,money from account")
	List<Account> selectAccountAll();
}
