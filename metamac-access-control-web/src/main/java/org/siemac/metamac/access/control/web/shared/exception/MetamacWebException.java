package org.siemac.metamac.access.control.web.shared.exception;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.core.common.exception.MetamacExceptionItem;

import com.gwtplatform.dispatch.shared.ActionException;


public class MetamacWebException extends ActionException {

    private static final long serialVersionUID = -3915416260598901572L;

    private List<MetamacExceptionItem> exceptionItems;
    
    
    public MetamacWebException() {
        super();
    }
    
    public MetamacWebException(List<MetamacExceptionItem> exceptionItems) {
        super();
        this.exceptionItems = exceptionItems;
    }
   
    public List<MetamacExceptionItem> getExceptionItems() {
        if (exceptionItems == null) {
            exceptionItems = new ArrayList<MetamacExceptionItem>();
        } 
        return exceptionItems;
    }
    
}
