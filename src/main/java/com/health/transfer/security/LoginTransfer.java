package com.health.transfer.security;

import com.health.entity.role.RoleEntity;
import com.health.entity.security.UserProfileEntity;

import java.util.List;

/**
 * @author henry
 */
public class LoginTransfer extends UserProfileEntity {
    
    private String account;
    
    private String mobile;

    private String token;
    
    private Boolean needChangePassword = false;
    
    private String departmentName;
    
    private List<RoleEntity> roleList;
    
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
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public Boolean getNeedChangePassword() {
        return needChangePassword;
    }
    
    public void setNeedChangePassword(Boolean needChangePassword) {
        this.needChangePassword = needChangePassword;
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
}
