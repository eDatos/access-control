package org.siemac.metamac.access.control.core.serviceapi.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.core.common.ent.domain.ExternalItem;
import org.siemac.metamac.core.common.ent.domain.InternationalString;
import org.siemac.metamac.core.common.ent.domain.LocalisedString;

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
        assertEqualsExternalItem(expected.getOperation(), actual.getOperation());
    }

    public static void assertEqualsExternalItem(ExternalItem expected, ExternalItem actual) {

        if (actual == null && expected == null) {
            return;
        } else if ((actual != null && expected == null) || (actual == null && expected != null)) {
            fail();
        }

        assertEquals(expected.getUri(), actual.getUri());
        assertEquals(expected.getUrn(), actual.getUrn());
        assertEquals(expected.getType(), actual.getType());
        assertEqualsInternationalString(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getManagementAppUrl(), actual.getManagementAppUrl());
    }

    public static void assertEqualsInternationalString(InternationalString expected, InternationalString actual) {
        if (actual == null && expected == null) {
            return;
        } else if ((actual != null && expected == null) || (actual == null && expected != null)) {
            fail();
        }
        assertEquals(expected.getTexts().size(), actual.getTexts().size());
        for (LocalisedString localisedStringExpected : expected.getTexts()) {
            assertEquals(localisedStringExpected.getLabel(), actual.getLocalisedLabel(localisedStringExpected.getLocale()));
        }
    }

}
