package com.health.dto.clinic;

/**
 * @author henry
 */
public class ClinicDto {
  
    /**
     * 医院名称
     */
    private String clinicName;
    
    /**
     * 科室ID
     */
    private Long departmentId;
    
    public String getClinicName() {
        return clinicName;
    }
    
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
    
    public Long getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
