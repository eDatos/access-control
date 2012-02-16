package org.siemac.metamac.access.control.web.client.utils;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.web.shared.exception.MetamacWebException;
import org.siemac.metamac.access.control.web.shared.exception.MetamacWebExceptionItem;

public class MessageUtils {

    public static List<String> getErrorMessages(Throwable caught, String alternativeMessage) {
        List<String> list = new ArrayList<String>();
        if (caught instanceof MetamacWebException) {
            List<MetamacWebExceptionItem> metamacExceptionItems = ((MetamacWebException) caught).getExceptionItems();
            for (MetamacWebExceptionItem item : metamacExceptionItems) {
                list.add(item.getMessage());
            }
        } else {
            list.add(alternativeMessage);
        }
        return list;
    }

    public static List<String> getMessageList(String ...messages) {
        List<String> messageList = new ArrayList<String>();
        for (String message : messages) {
            messageList.add(message);
        }
        return messageList;
    }

}
