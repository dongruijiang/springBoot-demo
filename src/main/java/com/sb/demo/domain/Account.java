package com.sb.demo.domain;

/**
 * 表 Account实体类
 * @date 2020年3月21日 下午8:15:08
 * @author jdr
 */
public class Account {
	private Long id ;
    private String name ;
    private Double money;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
}
