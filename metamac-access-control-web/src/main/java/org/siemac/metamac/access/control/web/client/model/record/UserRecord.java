package org.siemac.metamac.access.control.web.client.model.record;

import org.siemac.metamac.access.control.core.dto.UserDto;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class UserRecord extends ListGridRecord {

    public static final String ID       = "id";
    public static final String USERNAME = "username";
    public static final String NAME     = "name";
    public static final String SURNAME  = "surname";
    public static final String MAIL     = "mail";
    public static final String USER_DTO = "dto";

    public UserRecord() {
    }

    public UserRecord(Long id, String username, String name, String surname, String mail, UserDto userDto) {
        setId(id);
        setUsername(username);
        setName(name);
        setSurname(surname);
        setMail(mail);
        setUserDto(userDto);
    }

    public void setId(Long value) {
        setAttribute(ID, value);
    }

    public Long getId() {
        return getAttributeAsLong(ID);
    }

    public void setUsername(String value) {
        setAttribute(USERNAME, value);
    }

    public String getUsername() {
        return getAttributeAsString(USERNAME);
    }

    public void setName(String value) {
        setAttribute(NAME, value);
    }

    public String getName() {
        return getAttributeAsString(NAME);
    }

    public void setSurname(String value) {
        setAttribute(SURNAME, value);
    }

    public String getSurname() {
        return getAttributeAsString(SURNAME);
    }

    public void setMail(String value) {
        setAttribute(MAIL, value);
    }

    public String getMail() {
        return getAttributeAsString(MAIL);
    }

    public void setUserDto(UserDto value) {
        setAttribute(USER_DTO, value);
    }

    public UserDto getUserDto() {
        return (UserDto) getAttributeAsObject(USER_DTO);
    }

}
