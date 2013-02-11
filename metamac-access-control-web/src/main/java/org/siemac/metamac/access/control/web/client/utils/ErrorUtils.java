package org.siemac.metamac.access.control.web.client.utils;

import java.util.List;

import org.siemac.metamac.access.control.web.client.AccessControlWeb;
import org.siemac.metamac.web.common.client.utils.CommonErrorUtils;

public class ErrorUtils extends CommonErrorUtils {

    public static List<String> getErrorMessages(Throwable caught, String alternativeMessage) {
        return getErrorMessages(AccessControlWeb.getCoreMessages(), caught, alternativeMessage);
    }
}
