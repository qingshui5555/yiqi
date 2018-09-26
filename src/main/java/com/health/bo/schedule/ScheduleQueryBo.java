package com.health.bo.schedule;

import com.health.bo.BaseQueryBo;

import java.util.Date;

/**
 * @author henry
 */
public class ScheduleQueryBo extends BaseQueryBo {
    /**
     * 医生ID
     */
    private Long userId;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    
    public ScheduleQueryBo(int pageSize, int pageNo){
        super(pageSize, pageNo);
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
