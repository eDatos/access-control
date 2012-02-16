package org.siemac.metamac.access.control.web.shared.exception;

import java.util.ArrayList;
import java.util.List;

import com.gwtplatform.dispatch.shared.ActionException;


public class MetamacWebException extends ActionException {

    private static final long serialVersionUID = -3915416260598901572L;

    private List<MetamacWebExceptionItem> exceptionItems;
    
    
    public MetamacWebException() {
        super();
    }
    
    public MetamacWebException(List<MetamacWebExceptionItem> exceptionItems) {
        super();
        this.exceptionItems = exceptionItems;
    }
   
    public List<MetamacWebExceptionItem> getExceptionItems() {
        if (exceptionItems == null) {
            exceptionItems = new ArrayList<MetamacWebExceptionItem>();
        } 
        return exceptionItems;
    }
    
}
