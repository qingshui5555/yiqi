package com.health.dto.security;

import com.health.dto.BaseDto;

/**
 * @author henry
 */
public class LoginDto extends BaseDto{
    
    /**
     * 登陆方式 1 为验证码 2 为普通登陆
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
}
