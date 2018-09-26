package com.health.entity.security;

import com.health.entity.BaseEntity;

/**
 * @author Henry
 * @date 2018/7/12 23:15
 */
public class RandomEntity extends BaseEntity {
	/**
	 * 随机ID
	 */
	private Long id;
	/**
	 * 随机账号
	 */
	private String account;
	/**
	 * 使用情况 0 未使用 1 已使用
	 */
	private Integer useStatus;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public Integer getUseStatus() {
		return useStatus;
	}
	
	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}
}
