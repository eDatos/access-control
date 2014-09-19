package org.siemac.metamac.access_control.rest.internal.v1_0.service;

import org.junit.Test;
import org.siemac.metamac.access_control.rest.internal.AccessControlRestInternalConstants;
import org.siemac.metamac.rest.access_control.v1_0.domain.Apps;
import org.siemac.metamac.rest.access_control.v1_0.domain.Roles;
import org.siemac.metamac.rest.access_control.v1_0.domain.Users;

public class AccessControlRestInternalFacadeV10UsersTest extends AccessControlRestInternalFacadeV10BaseTest {

    @Test
    public void testFindUsers() throws Exception {
        Users users = accessControlRestInternalFacadeClientXml.findUsers(null, null, null);

        assertEquals(5, users.getUsers().size());
        assertEquals(AccessControlRestInternalConstants.KIND_USERS, users.getKind());
    }

    @Test
    public void testFindRoles() throws Exception {
        Roles roles = accessControlRestInternalFacadeClientXml.findRoles();

        assertEquals(10, roles.getRoles().size());
        assertEquals(AccessControlRestInternalConstants.KIND_ROLES, roles.getKind());
    }

    @Test
    public void testFindApps() throws Exception {
        Apps apps = accessControlRestInternalFacadeClientXml.findApps();

        assertEquals(10, apps.getApps().size());
        assertEquals(AccessControlRestInternalConstants.KIND_APPS, apps.getKind());
    }
}