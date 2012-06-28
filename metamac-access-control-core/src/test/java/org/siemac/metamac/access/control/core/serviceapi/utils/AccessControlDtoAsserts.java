package org.siemac.metamac.access.control.core.serviceapi.utils;

import static org.junit.Assert.assertEquals;

import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.common.test.utils.MetamacAsserts;


/**
 * Asserts to tests
 */
public class AccessControlDtoAsserts {

    public static void assertEqualsRoleDto(RoleDto expected, RoleDto actual) {
        assertEquals(expected.getUuid(), actual.getUuid());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    public static void assertEqualsAppDto(AppDto expected, AppDto actual) {
        assertEquals(expected.getUuid(), actual.getUuid());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    public static void assertEqualsUserDto(UserDto expected, UserDto actual) {
        assertEquals(expected.getUuid(), actual.getUuid());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getSurname(), actual.getSurname());
        assertEquals(expected.getMail(), actual.getMail());
    }

    public static void assertEqualsAccessDto(AccessDto expected, AccessDto actual) {
        assertEquals(expected.getUuid(), actual.getUuid());
        assertEquals(expected.getId(), actual.getId());
        assertEqualsRoleDto(expected.getRole(), actual.getRole());
        assertEqualsAppDto(expected.getApp(), actual.getApp());
        assertEqualsUserDto(expected.getUser(), actual.getUser());
        MetamacAsserts.assertEqualsExternalItemBtDto(expected.getOperation(), actual.getOperation());
    }

    public static void assertEqualsCreatedRoleDto(RoleDto expected, RoleDto actual) {
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    public static void assertEqualsCreatedAppDto(AppDto expected, AppDto actual) {
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    public static void assertEqualsCreatedUserDto(UserDto expected, UserDto actual) {
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getSurname(), actual.getSurname());
        assertEquals(expected.getMail(), actual.getMail());
    }

    public static void assertEqualsCreatedAccessDto(AccessDto expected, AccessDto actual) {
        assertEqualsRoleDto(expected.getRole(), actual.getRole());
        assertEqualsAppDto(expected.getApp(), actual.getApp());
        assertEqualsUserDto(expected.getUser(), actual.getUser());
        MetamacAsserts.assertEqualsExternalItemBtDto(expected.getOperation(), actual.getOperation());
    }

}
