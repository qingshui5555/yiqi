package com.health.exception;

import com.health.util.i18n;
import org.apache.commons.lang.StringUtils;

/**
 * @author henry
 */
public class RequestConflictedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String messageCode = "";

    private String codedMessage = "";

    public RequestConflictedException(){

    }

    public RequestConflictedException(String message, Object... args){
        super(message);
        codedMessage = i18n.getMessage(message, args);
        if(StringUtils.isNotBlank(codedMessage)){
            messageCode = message;
        } else {
            codedMessage = message;
        }
    }

    @Override
    public String getMessage() {
        if (codedMessage.length() > 0){
            return codedMessage;
        }
        return super.getMessage();
    }

    public String getMessageCode() {
        return messageCode;
    }
    
}
