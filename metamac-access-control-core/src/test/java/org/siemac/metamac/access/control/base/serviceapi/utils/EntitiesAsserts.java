package org.siemac.metamac.access.control.base.serviceapi.utils;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.joda.time.DateTime;
import org.siemac.metamac.access.control.base.domain.Access;
import org.siemac.metamac.access.control.base.domain.App;
import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.base.domain.User;
import org.siemac.metamac.core.common.bt.domain.ExternalItemBt;

/**
 * Asserts to tests
 */
public class EntitiesAsserts {
    
    public static void assertEqualsRole(Role expected, Role actual) {
        assertEquals(expected.getUuid(), actual.getUuid());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
    }
    
    public static void assertEqualsApp(App expected, App actual) {
        assertEquals(expected.getUuid(), actual.getUuid());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
    }
    
    public static void assertEqualsUser(User expected, User actual) {
        assertEquals(expected.getUuid(), actual.getUuid());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getSurname(), actual.getSurname());
        assertEquals(expected.getMail(), actual.getMail());
    }
    
    public static void assertEqualsAccess(Access expected, Access actual) {
        assertEquals(expected.getUuid(), actual.getUuid());
        assertEquals(expected.getId(), actual.getId());
        assertEqualsRole(expected.getRole(), actual.getRole());
        assertEqualsApp(expected.getApp(), actual.getApp());
        assertEqualsUser(expected.getUser(), actual.getUser());
        assertEqualsExternalItemBt(expected.getOperation(), actual.getOperation());
    }

    
    public static void assertEqualsExternalItemBt(ExternalItemBt expected, ExternalItemBt actual) {
        assertEquals(expected.getCodeId(), actual.getCodeId());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getUriInt(), actual.getUriInt());
    }
    
    public static void assertEqualsDate(String expected, Date actual) {
        assertEquals(expected, (new DateTime(actual)).toString("yyyy-MM-dd HH:mm:ss"));
    }
}
