package com.health.emrenum;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常code，key，默认消息
 * 
 * @author 黄诚<cheng.huang@linkedcare.cn>
 *
 * 2017年7月10日 下午2:54:20
 *
 */
public enum ServiceExceptionCodeEnum {
      UNDEFINED_ERROR(1000000, "undefined.error","未知异常"),
      SYSTEM_ERROR(1000001, "system.error", "系统出错了"),
      ACCESS_FORBIDDEN(1000002, "access.forbidden","没有权限"),
      NOT_IMPLEMENT(1000003, "function.not.implement", "功能未实现"),
      SYSTEM_NOT_SUPPORT(1000004, "system.not.support","系统不支持"),
      VALIDATE_ERROR(1000005, "validate.error","数据校验未通过"),
      PARAMETER_ABSENT(1000006, "parameter.absent","缺少必须的参数"),
      DATA_NOT_FOUND(1000007, "data.not.found","数据不存在"),
      DATA_HAS_EXIST(1000009, "data.has.exist","数据已存在"),
      DATA_DUPLICATION(1000010, "data.duplication","数据重复"),
      DATA_NOT_WELL(1000011, "data.not.well","数据不完整"),
      DATA_HAS_USED(1000012, "data.has.used","数据已被使用"),
      RPC_ERROR(1000013, "rpc.error","远程调用异常"),
      API_ERROR(1000014, "api.call.error","API调用异常"),
      DATA_FORBIDDEN(1000015, "data.forbidden","数据已禁用"),
      ILLEGAL_OPERATION(1000016, "illegal.operation","非法操作"),
      ;
        
        private Integer code;
        private String key;
        private String displayMessage;
      
        ServiceExceptionCodeEnum(Integer code, String key, String displayMessage){
            this.code = code;
            this.key = key;
            this.displayMessage = displayMessage;
        }

        public Integer getCode() {
            return code;
        }
        public void setCode(Integer code) {
            this.code = code;
        }
       

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getDisplayMessage() {
            return displayMessage;
        }

        public void setDisplayMessage(String displayMessage) {
            this.displayMessage = displayMessage;
        }

        public String toLog(){
            return this.name()+String.format("{%s,%s,%s}", code,key,displayMessage);
        }
}
