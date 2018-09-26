package com.health.common;

import com.health.exception.ServiceException;
import org.apache.commons.lang.StringUtils;

/**
 * @author Henry
 * @date 2018/7/8 20:47
 */
public class ResponseData {

    /**
     * api版本
     */
    private String apiVersion = "1.0.17";

    /**
     * 服务器消耗时间毫秒数
     */
    private long consumeTime;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 结果调用状态
     */
    private String status;

    /**
     * 接口返回值班
     */
    private Object values;

    /**
     * 分页对象
     */
    private Pager pager;
    
    /**
     * 调用成功，返回对象
     * @param values
     */
    public ResponseData(final Object values) {
        setMessage(Constant.STR_EMPTY);
        setStatus(Constant.STR_SUCCESS);
        setValues(values);
    }
    
    /**
     * 调用失败，抛出异常
     * @param isError
     * @param exception
     */
    public ResponseData(final Boolean isError, final Throwable exception) {
        setStatus(!isError ? Constant.STR_SUCCESS : Constant.STR_ERROR);
        if(exception instanceof ServiceException){
            ServiceException se = (ServiceException)exception;
            message = se.getDisplayMessage();
        } else if (exception instanceof RuntimeException) {
            message = exception.getMessage();
        } else {
            message = exception.getMessage();
            if(StringUtils.isBlank(message) || message.indexOf(Constant.STR_EXCEPTION) > 0 || message.indexOf(Constant.STR_NEW_LINE) > 0){
                message =  Constant.ERROR_SYSTEM;
            }
        }
    }
    
    /**
     * 调用结果，返回信息
     * @param isError
     * @param message
     */
    public ResponseData(final Boolean isError, final String message) {
        setStatus(!isError ? Constant.STR_SUCCESS : Constant.STR_ERROR);
        setMessage(message);
    }
    
    /**
     * 调用结果，返回信息和对象
     * @param isError
     * @param message
     * @param values
     */
    public ResponseData(final Boolean isError, final String message, final Object values) {
        setStatus(!isError ? Constant.STR_SUCCESS : Constant.STR_ERROR);
        setMessage(message);
        setValues(values);
    }
    
    /**
     * 调用结果，返回信息、对象和分页
     * @param isError
     * @param message
     * @param values
     * @param pager
     */
    public ResponseData(final Boolean isError, final String message, final Object values, final Pager pager) {
        setStatus(!isError ? Constant.STR_SUCCESS : Constant.STR_ERROR);
        setMessage(message);
        setValues(values);
        setPager(pager);
    }
    
    /**
     * 调用成功，返回对象和分页
     * @param values
     * @param pager
     */
    public ResponseData(final Object values, final Pager pager) {
        setMessage(Constant.STR_EMPTY);
        setStatus(Constant.STR_SUCCESS);
        setValues(values);
        setPager(pager);
    }
    
    /**
     * 设置开始时间
     * @param startTime
     * @return
     */
    public ResponseData setStartTime(long startTime){
        consumeTime = System.currentTimeMillis() - startTime;
        return this;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getValues() {
        return values;
    }

    public void setValues(Object values) {
        this.values = values;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

}
