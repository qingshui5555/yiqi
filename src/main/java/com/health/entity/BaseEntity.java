package com.health.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Henry
 * @date 2018/7/8 20:47
 */
public abstract class BaseEntity implements Serializable {
    
    /**
     * 标记状态 0：未知 1：有效 2：无效
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    private Date createOn;
    
    /**
     * 创建者
     */
    private Long createBy;
    
    /**
     * 修改时间
     */
    private Date modifyOn;
    
    /**
     * 修改者
     */
    private Long modifyBy;
    
    /**
     * 版本号
     */
    private Long version;
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Date getCreateOn() {
        return createOn;
    }
    
    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    
    public Long getCreateBy() {
        return createBy;
    }
    
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
    
    public Date getModifyOn() {
        return modifyOn;
    }
    
    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
    
    public Long getModifyBy() {
        return modifyBy;
    }
    
    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }
    
    public Long getVersion() {
        return version;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }

}
