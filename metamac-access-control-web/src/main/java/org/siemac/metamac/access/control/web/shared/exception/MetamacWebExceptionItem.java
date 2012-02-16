package org.siemac.metamac.access.control.web.shared.exception;

import java.io.Serializable;


public class MetamacWebExceptionItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            code;
    private String            message;
    private Serializable[]    messageParameters = null;

    
    public MetamacWebExceptionItem() {
        
    }
    
    public MetamacWebExceptionItem(String code, String message, Serializable... messageParameters) {
        this.code = code;
        this.message = message;
        this.messageParameters = messageParameters;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }

    public Serializable[] getMessageParameters() {
        return messageParameters;
    }

}
