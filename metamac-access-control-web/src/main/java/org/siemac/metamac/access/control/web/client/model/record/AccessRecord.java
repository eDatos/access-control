package org.siemac.metamac.access.control.web.client.model.record;

import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;

import com.smartgwt.client.widgets.grid.ListGridRecord;


public class AccessRecord extends ListGridRecord {

    public static final String ID = "id";
    public static final String USER = "user";
    public static final String ROLE = "role";
    public static final String APP = "app";
    public static final String OPERATION = "op";
    public static final String ACCESS_DTO = "acc-dto";
    
    
    public AccessRecord() { }
    
    public AccessRecord(Long id,
            String user,
            String role,
            String app,
            String operation,
            AccessDto accessDto) {
        setId(id);
        setUser(user);
        setRole(role);
        setApp(app);
        setOperation(operation);
        setAccessDto(accessDto);
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
    
    public void setOperation(String value) {
        setAttribute(OPERATION, value);
    }
    
    public String getOperation() {
        return getAttributeAsString(OPERATION);
    }
    
    public void setAccessDto(AccessDto accessDto) {
        setAttribute(ACCESS_DTO, accessDto);
    }
    
    public AccessDto getAccessDto() {
        return (AccessDto) getAttributeAsObject(ACCESS_DTO);
    }
    
}
