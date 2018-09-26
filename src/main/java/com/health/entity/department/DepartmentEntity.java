package com.health.entity.department;

import com.health.entity.BaseEntity;

/**
 * @author henry
 */
public class DepartmentEntity extends BaseEntity {
    /**
     * 科室ID
     */
    private Long departmentId;
    /**
     * 医院ID
     */
    private Long clinicId;
    /**
     * 医院名称
     */
    private String departmentName;
    /**
     * 是否可以出诊
     */
    private Integer isOut;
    /**
     * 是否可以在线咨询
     */
    private Integer isOnline;
    /**
     * 是否可以电话咨询
     */
    private Integer isTelephone;
    /**
     * 是否可以预约咨询
     */
    private Integer isAppointment;
    
    public Long getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    
    public Long getClinicId() {
        return clinicId;
    }
    
    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public Integer getIsOut() {
        return isOut;
    }
    
    public void setIsOut(Integer isOut) {
        this.isOut = isOut;
    }
    
    public Integer getIsOnline() {
        return isOnline;
    }
    
    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }
    
    public Integer getIsTelephone() {
        return isTelephone;
    }
    
    public void setIsTelephone(Integer isTelephone) {
        this.isTelephone = isTelephone;
    }
    
    public Integer getIsAppointment() {
        return isAppointment;
    }
    
    public void setIsAppointment(Integer isAppointment) {
        this.isAppointment = isAppointment;
    }
}
