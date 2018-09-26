package com.health.emrenum;

/**
 * @author Henry
 * @date 2017/10/25 22:15
 */
public enum StatusEnum {
    UNKNOWN(0L), VALID(1L), INVALID(2L);
    
    Long value;
    
    private StatusEnum(Long value){
        this.value = value;
    }
    
    public Long getValue(){
        return this.value;
    }
}
