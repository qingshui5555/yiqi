package com.health.transfer.schedule;

import com.health.entity.schedule.ScheduleEntity;
import io.swagger.models.auth.In;

/**
 * @author henry
 */
public class ScheduleTransfer extends ScheduleEntity{
	/**
	 * 当前已预约人数
	 */
	private Integer current;
	/**
	 * 是否约满
	 */
	private Boolean isFull;
	
	public Integer getCurrent() {
		return current;
	}
	
	public void setCurrent(Integer current) {
		this.current = current;
	}
	
	public Boolean getFull() {
		return isFull;
	}
	
	public void setFull(Boolean full) {
		isFull = full;
	}
}
