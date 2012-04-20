package org.siemac.metamac.access.control.service.ws.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.core.common.exception.MetamacExceptionItem;
import org.siemac.metamac.schema.common.v1_0.domain.MetamacExceptionItemList;


public class MetamacExceptionUtils {

    public static List<MetamacExceptionItem> getMetamacExceptionItems(MetamacExceptionItemList exceptionItemList) {
        List<MetamacExceptionItem> metamacExceptionItems = new ArrayList<MetamacExceptionItem>();
        if (exceptionItemList != null) {
            for (org.siemac.metamac.schema.common.v1_0.domain.MetamacExceptionItem item : exceptionItemList.getMetamacExceptionItem()) {
                MetamacExceptionItem metamacExceptionItem = new MetamacExceptionItem();
                metamacExceptionItem.setCode(item.getCode());
                metamacExceptionItem.setMessage(item.getMessage());
                if (item.getMessageParameters() != null) {
                    Serializable[] messageParameters = item.getMessageParameters().toArray(new Serializable[item.getMessageParameters().size()]);
                    metamacExceptionItem.setMessageParameters(messageParameters);
                }
            }
        }
        return metamacExceptionItems;
    }
    
}
