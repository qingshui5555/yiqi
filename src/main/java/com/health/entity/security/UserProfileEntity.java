package com.health.entity.security;

import com.health.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Henry
 * @date 2018/7/9 0:38
 */
public class UserProfileEntity extends BaseEntity {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 医院ID
     */
    private Long clinicId;
    /**
     * 科室ID
     */
    private Long departmentId;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 医生资质
     */
    private String identifier;
    /**
     *  电话咨询号码
     */
    private String telephone;
    /**
     * 职称一
     */
    private String professionFirst;
    
    /**
     * 职称二
     */
    private String professionSecond;
    /**
     * 擅长
     */
    private String skills;
    /**
     * 在线咨询付费标准
     */
    private String payOnline;
    /**
     * 电话咨询付费标准
     */
    private String payTelephone;
    /**
     * 出诊付费咨询
     */
    private String payOut;
    /**
     * 在线咨询优惠价
     */
    private String prePayOnline;
    /**
     * 电话咨询优惠价
     */
    private String prePayTelephone;
    /**
     * 预约出诊优惠价
     */
    private String prePayOut;
    /**
     * 评分等级
     */
    private BigDecimal comments;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 血型
     */
    private String blood;
    /**
     * 慢性病史
     */
    private String chronic;
    /**
     * 过敏情况
     */
    private String allergy;
    /**
     * 家庭住址
     */
    private String address;
    /**
     * 执业经历
     */
    private String expirence;
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getClinicId() {
        return clinicId;
    }
    
    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
    
    public Long getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getIdentifier() {
        return identifier;
    }
    
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getProfessionFirst() {
        return professionFirst;
    }
    
    public void setProfessionFirst(String professionFirst) {
        this.professionFirst = professionFirst;
    }
    
    public String getProfessionSecond() {
        return professionSecond;
    }
    
    public void setProfessionSecond(String professionSecond) {
        this.professionSecond = professionSecond;
    }
    
    public String getSkills() {
        return skills;
    }
    
    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    public String getPayOnline() {
        return payOnline;
    }
    
    public void setPayOnline(String payOnline) {
        this.payOnline = payOnline;
    }
    
    public String getPayTelephone() {
        return payTelephone;
    }
    
    public void setPayTelephone(String payTelephone) {
        this.payTelephone = payTelephone;
    }
    
    public String getPayOut() {
        return payOut;
    }
    
    public void setPayOut(String payOut) {
        this.payOut = payOut;
    }
    
    public String getPrePayOnline() {
        return prePayOnline;
    }
    
    public void setPrePayOnline(String prePayOnline) {
        this.prePayOnline = prePayOnline;
    }
    
    public String getPrePayTelephone() {
        return prePayTelephone;
    }
    
    public void setPrePayTelephone(String prePayTelephone) {
        this.prePayTelephone = prePayTelephone;
    }
    
    public String getPrePayOut() {
        return prePayOut;
    }
    
    public void setPrePayOut(String prePayOut) {
        this.prePayOut = prePayOut;
    }
    
    public BigDecimal getComments() {
        return comments;
    }
    
    public void setComments(BigDecimal comments) {
        this.comments = comments;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public String getBlood() {
        return blood;
    }
    
    public void setBlood(String blood) {
        this.blood = blood;
    }
    
    public String getChronic() {
        return chronic;
    }
    
    public void setChronic(String chronic) {
        this.chronic = chronic;
    }
    
    public String getAllergy() {
        return allergy;
    }
    
    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getExpirence() {
        return expirence;
    }
    
    public void setExpirence(String expirence) {
        this.expirence = expirence;
    }
}
