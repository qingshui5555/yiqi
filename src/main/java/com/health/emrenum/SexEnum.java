package com.health.emrenum;

/**
 * @author Henry
 * @date 2017/10/25 22:15
 */
public enum SexEnum {
    /**
     * UNKNOWN 未知性别 MALE 男性 FEMALE 女性
     */
    UNKNOWN(""),
    MALE("M"),
    FEMALE("F");
    
    private String value;
    
    SexEnum(String value){
        this.value = value;
    }
    
    public String getValue(){
        return this.value;
    }
}
