package org.siemac.metamac.access.control.core.serviceapi.utils;

import static org.junit.Assert.assertEquals;

import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.core.common.bt.domain.ExternalItemBt;

/**
 * Asserts to tests
 */
public class AccessControlDoAsserts {

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

}
