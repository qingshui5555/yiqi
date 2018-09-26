package com.health.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    //private int httpStatus = 200; //如果需要设置Response的status，可以设置这个值
    private Integer code;           //异常编号
    private String key;             //消息的key: xxx.yyy.zzz
    private String displayMessage;  //用于界面显示的消息
    private String debugInfo;       //用于开发人员便于调试的消息
    private long exceptionTime;     //当前时间毫秒，用于日志中查询定位异常消息

    protected ServiceException(Integer code, String key, String displayMessage, String debugInfo) {
        super(debugInfo == null ? displayMessage : displayMessage + ":" + debugInfo, null);
        this.code = code;
        this.key = key;
        this.displayMessage = displayMessage;
        this.debugInfo = debugInfo;
        this.exceptionTime = System.currentTimeMillis();
    }
    
    /**
     * 
     * @param code          Integer
     * @param key
     * @param debugInfo    debugInfo
     * @param e
     */
    protected ServiceException(Integer code, String key, String displayMessage, String debugInfo, Throwable e) {
        super(debugInfo==null ? displayMessage : displayMessage+":"+debugInfo, e);
        this.code = code;
        this.key = key;
        this.displayMessage = displayMessage;
        this.debugInfo = debugInfo;
        this.exceptionTime = System.currentTimeMillis();
    }

    public String toLogString(){
        StringBuilder sb = new StringBuilder(200);
        //if(code != null){
        //    sb.append("code=[").append(code).append("]");
        //}
        //if(key != null){
        //    sb.append(";key=[").append(key).append("]");
        //}
        if(displayMessage != null){
            sb.append("displayMessage=[").append(displayMessage).append("]");
        }
        
        if(debugInfo != null){
            sb.append(";debugInfo=[").append(debugInfo).append("]");
        }
        return sb.toString();
    }

    /**
     * 这个值不可以修改，因为程序中将getMessage()作为消息发送到前端了
     */
    @Override
    public String getMessage(){
        StringBuilder sb = new StringBuilder(200);
        //if(code != null){
        //    sb.append("code=[").append(code).append("]");
        //}
        //if(key != null){
        //    sb.append(";key=[").append(key).append("]");
        //}
        if(displayMessage != null){
            sb.append("displayMessage=[").append(displayMessage).append("]");
        }
        
        if(debugInfo != null){
            sb.append(";debugInfo=[").append(debugInfo).append("]");
        }
        sb.append("\n");
        sb.append(super.getMessage());
        return sb.toString();
    }
    
    public Integer getCode() {
        return code;
    }

    public ServiceException setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getKey() {
        return key;
    }

    public ServiceException setKey(String key) {
        this.key = key;
        return this;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public ServiceException setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
        return this;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    public ServiceException setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
        return this;
    }

    public long getExceptionTime() {
        return exceptionTime;
    }

    public void setExceptionTime(long exceptionTime) {
        this.exceptionTime = exceptionTime;
    }
}
