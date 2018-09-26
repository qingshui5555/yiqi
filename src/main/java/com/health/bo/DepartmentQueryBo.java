package com.health.bo;

/**
 * @author henry
 */
public class DepartmentQueryBo extends BaseQueryBo {
    
    /**
	  * 医院ID
     */
    private Long clinicId;
    
    /**
     * 科室名称
     */
    private String departmentName;
    
    public DepartmentQueryBo(int pageSize, int pageNo){
        super(pageSize, pageNo);
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
}
