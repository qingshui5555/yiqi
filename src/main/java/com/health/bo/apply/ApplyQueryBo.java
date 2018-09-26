package com.health.bo.apply;

import com.health.bo.BaseQueryBo;

import java.util.Date;

/**
 * @author henry
 */
public class ApplyQueryBo extends BaseQueryBo {
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
    /**
     * 审核状态 0 待审核 1 已批准 2 已拒绝
     */
    private Integer auditStatus;
    /**
     * 排班ID
     */
    private Long scheduleId;
    
    public ApplyQueryBo(){
        super();
    }
    
    public ApplyQueryBo(int pageSize, int pageNo){
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
    
    public Integer getAuditStatus() {
        return auditStatus;
    }
    
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
    
    public Long getScheduleId() {
        return scheduleId;
    }
    
    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
    
}
