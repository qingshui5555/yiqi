package com.health.entity.security;

import com.health.entity.BaseEntity;

import java.util.Date;

/**
 * @author Henry
 * @date 2018/7/9 0:38
 */
public class UserEntity extends BaseEntity {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户类型
     */
    private Integer type;
    /**
     * 用户账号
     */
    private String account;
    /**
     *  手机号
     */
    private String mobile;
    /**
     * 验证码
     */
    private String vCode;
    /**
     * 验证码过期时间
     */
    private Date vExpire;
    /**
     * 密码
     */
    private String password;
    /**
     * 密码生成器
     */
    private String slat;
    /**
     * 注册状态
     */
    private Integer registerStatus;
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
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
    
    public String getvCode() {
        return vCode;
    }
    
    public void setvCode(String vCode) {
        this.vCode = vCode;
    }
    
    public Date getvExpire() {
        return vExpire;
    }
    
    public void setvExpire(Date vExpire) {
        this.vExpire = vExpire;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSlat() {
        return slat;
    }
    
    public void setSlat(String slat) {
        this.slat = slat;
    }
    
    public Integer getRegisterStatus() {
        return registerStatus;
    }
    
    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }
}
