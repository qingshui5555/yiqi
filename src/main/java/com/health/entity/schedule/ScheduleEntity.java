package com.health.entity.schedule;

import com.health.entity.BaseEntity;
import java.util.Date;

/**
 * @author henry
 */
public class ScheduleEntity extends BaseEntity {
    /**
     * 主键ID
     */
    private Long scheduleId;
    /**
     * 医生ID
     */
    private Long userId;
    /**
     * 最大可预约人数
     */
    private Long max;
    /**
     * 预约日期
     */
    private Date scheduleDate;
    /**
     * 预约开始时间点hh:mm
     */
    private String scheduleStart;
    /**
     * 预约结束时间点hh:mm
     */
    private String scheduleEnd;
    
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
    
    public Long getMax() {
        return max;
    }
    
    public void setMax(Long max) {
        this.max = max;
    }
    
    public Date getScheduleDate() {
        return scheduleDate;
    }
    
    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
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
