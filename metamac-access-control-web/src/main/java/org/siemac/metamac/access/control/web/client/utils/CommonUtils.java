package org.siemac.metamac.access.control.web.client.utils;

import java.util.LinkedHashMap;
import java.util.List;

import org.siemac.metamac.access.control.core.dto.RoleDto;

public class CommonUtils {

    public static LinkedHashMap<String, String> getRolesHashMap(List<RoleDto> roleDtos) {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
        valueMap.put(new String(), new String());
        for (RoleDto role : roleDtos) {
            valueMap.put(role.getId().toString(), role.getTitle());
        }
        return valueMap;
    }
}
