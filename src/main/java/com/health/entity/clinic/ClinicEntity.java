package com.health.entity.clinic;

import com.health.entity.BaseEntity;

/**
 * @author henry
 */
public class ClinicEntity extends BaseEntity {
    /**
     * 医院ID
     */
    private Long clinicId;
    /**
     * 医院名称
     */
    private String clinicName;
    /**
     * 医院地址
     */
    private String clinicAddr;
    
    /**
     * 医院电话
     */
    private String clinicTelephone;
    
    public Long getClinicId() {
        return clinicId;
    }
    
    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
    
    public String getClinicName() {
        return clinicName;
    }
    
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
    
    public String getClinicAddr() {
        return clinicAddr;
    }
    
    public void setClinicAddr(String clinicAddr) {
        this.clinicAddr = clinicAddr;
    }
    
    public String getClinicTelephone() {
        return clinicTelephone;
    }
    
    public void setClinicTelephone(String clinicTelephone) {
        this.clinicTelephone = clinicTelephone;
    }
}
