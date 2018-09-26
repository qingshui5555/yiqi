package com.health.transfer.security;

import com.health.entity.role.RoleEntity;
import com.health.entity.security.UserProfileEntity;

import java.util.List;

/**
 * @author henry
 */
public class UserTransfer extends UserProfileEntity{
    /**
     * 用户类型
     */
    private Integer type;
    /**
     * 用户名
     */
    private String account;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 注册状态
     */
    private Integer registerStatus;
    /**
     * 医院名称
     */
    private String clinicName;
    /**
     * 科室名称
     */
    private String departmentName;
    /**
     * 角色列表
     */
    private List<RoleEntity> roleList;
    /**
     * 是否在线
     */
    private Boolean isOnline;
    
    private Long roleId;
    
    private String roleName;
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public String getAccount() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public Integer getRegisterStatus() {
        return registerStatus;
    }
    
    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }
    
    public String getClinicName() {
        return clinicName;
    }
    
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public List<RoleEntity> getRoleList() {
        return roleList;
    }
    
    public void setRoleList(List<RoleEntity> roleList) {
        this.roleList = roleList;
    }
    
    public Boolean getOnline() {
        return isOnline;
    }
    
    public void setOnline(Boolean online) {
        isOnline = online;
    }
    
    public Long getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
