package com.health.emrenum;

public enum IdentifyEnum {

    UNKNOWN(0L,""),
    IDENTIFICATION(1L,"identification"),
    PASSPORT(2L,"password");

    Long key;
    String value;

    private IdentifyEnum(Long key, String value){
        this.value = value;
        this.key = key;
    }

    public Long getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }

    public static IdentifyEnum getEnum(Long key){
        for(final IdentifyEnum sexEnum : values()){
            if(sexEnum.key == key){
                return sexEnum;
            }
        }
        throw new RuntimeException("Given key " + key + " in enum is not exist");
    }
}
