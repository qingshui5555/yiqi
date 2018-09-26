package com.health.bo.security;

import com.health.bo.BaseQueryBo;

import java.util.Date;

/**
 * @author henry
 */
public class UserQueryBo extends BaseQueryBo {
    /**
     * 用户类型
     */
    private int type;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 用户名
     */
    private String name;
    /**
     * 医院ID
     */
    private Long clinicId;
    /**
     * 科室ID
     */
    private Long departmentId;
    /**
     * 科室名称
     */
    private String departmentName;
    /**
     * 注册状态
     */
    private Integer registerStatus;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    
    public UserQueryBo(int pageSize, int pageNo){
        super(pageSize, pageNo);
    }
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getClinicId() {
        return clinicId;
    }
    
    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
    
    public Long getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public Integer getRegisterStatus() {
        return registerStatus;
    }
    
    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
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
