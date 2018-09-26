package com.health.dto.sms;

/**
 * @author Henry
 * @date 2018/7/9 0:38
 */
public class SmsDto {
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 短信验证码
     */
    private String vCode;
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getvCode() {
        return vCode;
    }
    
    public void setvCode(String vCode) {
        this.vCode = vCode;
    }
}
