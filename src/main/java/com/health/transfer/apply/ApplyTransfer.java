package com.health.transfer.apply;

import com.health.entity.apply.ApplyEntity;

/**
 * @author henry
 */
public class ApplyTransfer extends ApplyEntity {
	/**
	 * APP用户姓名
	 */
	private String appUserName;
	/**
	 * 预约开始时间点hh:mm
	 */
	private String scheduleStart;
	/**
	 * 预约结束时间点hh:mm
	 */
	private String scheduleEnd;
	
	public String getAppUserName() {
		return appUserName;
	}
	
	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}
	
	public String getScheduleStart() {
		return scheduleStart;
	}
	
	public void setScheduleStart(String scheduleStart) {
		this.scheduleStart = scheduleStart;
	}
	
	public String getScheduleEnd() {
		return scheduleEnd;
	}
	
	public void setScheduleEnd(String scheduleEnd) {
		this.scheduleEnd = scheduleEnd;
	}
	
}
