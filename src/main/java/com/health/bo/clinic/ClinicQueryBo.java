package com.health.bo.clinic;

import com.health.bo.BaseQueryBo;

/**
 * @author henry
 */
public class ClinicQueryBo extends BaseQueryBo {
    /**
     * 科室名称
     */
    private String departmentName;
    /**
     * 医院名称
     */
    private String clinicName;
    
    public ClinicQueryBo(int pageSize, int pageNo){
        super(pageSize, pageNo);
    }
    public String getDepartmentName() {
        return departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public String getClinicName() {
        return clinicName;
    }
    
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
    
}
