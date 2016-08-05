package org.siemac.metamac.access.control.web.client.model.record;

import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.web.common.client.widgets.NavigableListGridRecord;

public class AccessRecord extends NavigableListGridRecord {

    public static final String ID           = "id";
    public static final String USER         = "user";
    public static final String ROLE         = "role";
    public static final String APP          = "app";
    public static final String OPERATION    = "op";
    public static final String SEND_EMAIL   = "send-email";
    public static final String REMOVAL_DATE = "date";

    public static final String ACCESS_DTO   = "acc-dto";

    public AccessRecord() {
    }

    public AccessRecord(Long id, String user, String role, String app, ExternalItemDto operation, String sendEmail, String dischargedDate, AccessDto accessDto) {
        setId(id);
        setUser(user);
        setRole(role);
        setApp(app);
        setOperation(operation);
        setRemovalDate(dischargedDate);
        setAccessDto(accessDto);
        setSendEmail(sendEmail);
    }

    public void setId(Long value) {
        setAttribute(ID, value);
    }

    public Long getId() {
        return getAttributeAsLong(ID);
    }

    public void setUser(String value) {
        setAttribute(USER, value);
    }

    public String getUser() {
        return getAttributeAsString(USER);
    }

    public void setRole(String value) {
        setAttribute(ROLE, value);
    }

    public String getRole() {
        return getAttributeAsString(ROLE);
    }

    public void setApp(String value) {
        setAttribute(APP, value);
    }

    public String getApp() {
        return getAttributeAsString(APP);
    }

    public void setOperation(ExternalItemDto operation) {
        setExternalItem(OPERATION, operation);
    }

    public void setSendEmail(String sendEmail) {
        setAttribute(SEND_EMAIL, sendEmail);
    }

    public void setAccessDto(AccessDto accessDto) {
        setAttribute(ACCESS_DTO, accessDto);
    }

    public AccessDto getAccessDto() {
        return (AccessDto) getAttributeAsObject(ACCESS_DTO);
    }

    public void setRemovalDate(String value) {
        setAttribute(REMOVAL_DATE, value);
    }

    public String getRemovalDate() {
        return getAttributeAsString(REMOVAL_DATE);
    }

}
