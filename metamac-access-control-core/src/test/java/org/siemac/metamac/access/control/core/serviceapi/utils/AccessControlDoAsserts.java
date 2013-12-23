package org.siemac.metamac.access.control.core.serviceapi.utils;

import static org.junit.Assert.assertEquals;

import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;

/**
 * Asserts to tests
 */
public class AccessControlDoAsserts extends AccessControlBaseAsserts {

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
        assertEqualsExternalItem(expected.getOperation(), actual.getOperation());
        assertEquals(expected.getSendEmail(), actual.getSendEmail());
    }
}
