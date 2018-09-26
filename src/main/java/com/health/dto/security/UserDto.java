package com.health.dto.security;

import com.health.entity.role.RoleEntity;
import com.health.entity.security.UserProfileEntity;

import java.util.List;

/**
 * @author henry
 */
public class UserDto extends UserProfileEntity {
    
    /**
     * 注册方式 1 为验证码 2 为普通登陆
     */
    private Integer loginType;
    /**
     * 用户类型
     */
    private Integer type;
    /**
     * 登陆用户名
     */
    private String account;
    /**
     * 登陆用户名
     */
    private String mobile;
    /**
     * 登陆密码
     */
    private String password;
    /**
     * 验证码
     */
    private String vCode;
    /**
     * 角色列表
     */
    private List<RoleEntity> roleList;
    /**
     * 用户ID列表
     */
    private List<Long> userIdList;
    
    public Integer getLoginType() {
        return loginType;
    }
    
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }
    
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
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getvCode() {
        return vCode;
    }
    
    public void setvCode(String vCode) {
        this.vCode = vCode;
    }
    
    public List<RoleEntity> getRoleList() {
        return roleList;
    }
    
    public void setRoleList(List<RoleEntity> roleList) {
        this.roleList = roleList;
    }
    
    public List<Long> getUserIdList() {
        return userIdList;
    }
    
    public void setUserIdList(List<Long> userIdList) {
        this.userIdList = userIdList;
    }
}
