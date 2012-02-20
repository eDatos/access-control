package org.siemac.metamac.access.control.web.server.utils;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.core.common.exception.MetamacExceptionItem;
import org.siemac.metamac.web.common.shared.exception.MetamacWebExceptionItem;


public class WebExceptionUtils {

    /**
     * Returns a {@link MetamacWebExceptionItem} list from a {@link MetamacExceptionItem} list
     * 
     * @param exceptions
     * @return
     */
    public static List<MetamacWebExceptionItem> getMetamacWebExceptionItem(List<MetamacExceptionItem> exceptions) {
        List<MetamacWebExceptionItem> metamacWebExceptionItems = new ArrayList<MetamacWebExceptionItem>();
        for (MetamacExceptionItem ex : exceptions) {
            metamacWebExceptionItems.add(new MetamacWebExceptionItem(ex.getCode(), ex.getMessage(), ex.getMessageParameters()));
        }
        return metamacWebExceptionItems;
    }
    
}
