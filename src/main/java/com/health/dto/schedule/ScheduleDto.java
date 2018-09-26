package com.health.dto.schedule;

import java.util.Date;
import java.util.List;

/**
 * @author henry
 */
public class ScheduleDto {
    /**
     * 排班ID
     */
    private Long scheduleId;
    /**
     * 医生ID
     */
    private Long userId;
    /**
     * 排班开始日期
     */
    private Date scheduleStartDate;
    /**
     * 排班结束日期
     */
    private Date scheduleEndDate;
    /**
     * 排班开始时间
     */
    private String scheduleStartTime;
    /**
     * 排班结束时间
     */
    private String scheduleEndTime;
    /**
     * 最大可预约人数
     */
    private Long max;
    
    public Long getScheduleId() {
        return scheduleId;
    }
    
    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Date getScheduleStartDate() {
        return scheduleStartDate;
    }
    
    public void setScheduleStartDate(Date scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }
    
    public Date getScheduleEndDate() {
        return scheduleEndDate;
    }
    
    public void setScheduleEndDate(Date scheduleEndDate) {
        this.scheduleEndDate = scheduleEndDate;
    }
    
    public String getScheduleStartTime() {
        return scheduleStartTime;
    }
    
    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }
    
    public String getScheduleEndTime() {
        return scheduleEndTime;
    }
    
    public void setScheduleEndTime(String scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }
    
    public Long getMax() {
        return max;
    }
    
    public void setMax(Long max) {
        this.max = max;
    }

}
