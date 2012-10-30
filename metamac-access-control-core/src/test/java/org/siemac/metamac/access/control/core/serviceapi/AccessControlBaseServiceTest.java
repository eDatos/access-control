package org.siemac.metamac.access.control.core.serviceapi;

import static org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteriaBuilder.criteriaFor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access.control.core.serviceapi.utils.AccessControlDoAsserts;
import org.siemac.metamac.access.control.core.serviceapi.utils.AccessControlDoMocks;
import org.siemac.metamac.core.common.ent.domain.ExternalItem;
import org.siemac.metamac.core.common.enume.domain.TypeExternalArtefactsEnum;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring based transactional test with DbUnit support.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/access-control/applicationContext-test.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class AccessControlBaseServiceTest extends AccessControlBaseTest implements AccessControlBaseServiceTestBase {

    @Autowired
    protected AccessControlBaseService accessControlBaseService;

    /**************************************************************************
     * ROLES
     **************************************************************************/

    @Test
    public void testFindRoleById() throws Exception {
        Role role = accessControlBaseService.findRoleById(getServiceContextAdministrador(), ROLE_1);
        assertNotNull(role);
    }

    @Test
    public void testCreateRole() throws Exception {
        Role role = accessControlBaseService.createRole(getServiceContextAdministrador(), AccessControlDoMocks.createRole());
        assertNotNull(role);
    }

    @Test
    public void testUpdateRole() throws Exception {
        Long id = ROLE_1;

        // Retrieve
        Role role = accessControlBaseService.findRoleById(getServiceContextAdministrador(), id);
        role.setCode("newCode");
        role.setTitle("newTitle");
        role.setDescription("newDescription");

        // Update
        Role roleUpdated = accessControlBaseService.updateRole(getServiceContextAdministrador(), role);

        // Validation
        assertNotNull(roleUpdated);
        AccessControlDoAsserts.assertEqualsRole(role, roleUpdated);
        assertTrue(roleUpdated.getLastUpdated().isAfter(roleUpdated.getCreatedDate()));
    }

    @Test
    public void testFindAllRoles() throws Exception {
        List<Role> roles = accessControlBaseService.findAllRoles(getServiceContextAdministrador());
        assertNotNull(roles);
        assertEquals(2, roles.size());
    }

    @Test(expected = MetamacException.class)
    public void testDeleteRole() throws Exception {
        accessControlBaseService.deleteRole(getServiceContextAdministrador(), ROLE_2);

        Role role = accessControlBaseService.findRoleById(getServiceContextAdministrador(), ROLE_2);
        assertNull(role);
    }

    @Test
    public void testFindRoleByCondition() throws Exception {
        String code = "Admin";
        List<ConditionalCriteria> condition = criteriaFor(Role.class).withProperty(org.siemac.metamac.access.control.core.domain.RoleProperties.code()).ignoreCaseLike("%" + code + "%").build();

        List<Role> roles = accessControlBaseService.findRoleByCondition(getServiceContextAdministrador(), condition);

        assertEquals(1, roles.size());
    }

    /**************************************************************************
     * APPS
     **************************************************************************/

    @Test
    public void testFindAppById() throws Exception {
        App app = accessControlBaseService.findAppById(getServiceContextAdministrador(), APP_1);
        assertNotNull(app);
    }

    @Test
    public void testCreateApp() throws Exception {
        App app = accessControlBaseService.createApp(getServiceContextAdministrador(), AccessControlDoMocks.createApp());
        assertNotNull(app);
    }

    @Test
    public void testUpdateApp() throws Exception {
        Long id = APP_1;

        // Retrieve
        App app = accessControlBaseService.findAppById(getServiceContextAdministrador(), id);
        app.setCode("newCode");
        app.setTitle("newTitle");
        app.setDescription("newDescription");

        // Update
        App appUpdated = accessControlBaseService.updateApp(getServiceContextAdministrador(), app);

        // Validation
        assertNotNull(appUpdated);
        AccessControlDoAsserts.assertEqualsApp(app, appUpdated);
        assertTrue(appUpdated.getLastUpdated().isAfter(appUpdated.getCreatedDate()));
    }

    @Test
    public void testFindAllApps() throws Exception {
        List<App> apps = accessControlBaseService.findAllApps(getServiceContextAdministrador());
        assertNotNull(apps);
        assertEquals(2, apps.size());
    }

    @Test(expected = MetamacException.class)
    public void testDeleteApp() throws Exception {
        accessControlBaseService.deleteApp(getServiceContextAdministrador(), APP_2);

        App app = accessControlBaseService.findAppById(getServiceContextAdministrador(), APP_2);
        assertNull(app);
    }

    @Test
    public void testFindAppByCondition() throws Exception {
        String code = "Gpe";
        List<ConditionalCriteria> condition = criteriaFor(App.class).withProperty(org.siemac.metamac.access.control.core.domain.AppProperties.code()).ignoreCaseLike("%" + code + "%").build();

        List<App> apps = accessControlBaseService.findAppByCondition(getServiceContextAdministrador(), condition);

        assertEquals(1, apps.size());
    }

    /**************************************************************************
     * USERS
     **************************************************************************/

    @Test
    public void testFindUserById() throws Exception {
        User user = accessControlBaseService.findUserById(getServiceContextAdministrador(), USER_1);
        assertNotNull(user);
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = accessControlBaseService.createUser(getServiceContextAdministrador(), AccessControlDoMocks.createUser());
        assertNotNull(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        Long id = USER_1;

        // Retrieve
        User user = accessControlBaseService.findUserById(getServiceContextAdministrador(), id);
        user.setUsername("newUsername");
        user.setName("newName");
        user.setSurname("newSurname");
        user.setMail("newMail");

        // Update
        User userUpdated = accessControlBaseService.updateUser(getServiceContextAdministrador(), user);

        // Validation
        assertNotNull(userUpdated);
        AccessControlDoAsserts.assertEqualsUser(user, userUpdated);
        assertTrue(userUpdated.getLastUpdated().isAfter(userUpdated.getCreatedDate()));
    }

    @Test
    public void testFindAllUsers() throws Exception {
        List<User> users = accessControlBaseService.findAllUsers(getServiceContextAdministrador());
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test(expected = MetamacException.class)
    public void testDeleteUser() throws Exception {
        accessControlBaseService.deleteUser(getServiceContextAdministrador(), USER_2);

        User user = accessControlBaseService.findUserById(getServiceContextAdministrador(), USER_2);
        assertNull(user);
    }

    @Test
    public void testFindUserByCondition() throws Exception {
        String username = "ARTE";
        List<ConditionalCriteria> condition = criteriaFor(User.class).withProperty(org.siemac.metamac.access.control.core.domain.UserProperties.username()).ignoreCaseLike("%" + username + "%")
                .build();

        List<User> users = accessControlBaseService.findUserByCondition(getServiceContextAdministrador(), condition);

        assertEquals(1, users.size());
    }

    /**************************************************************************
     * ACCESS
     **************************************************************************/

    @Test
    public void testFindAccessById() throws Exception {
        Access access = accessControlBaseService.findAccessById(getServiceContextAdministrador(), ACCESS_1);
        assertNotNull(access);
    }

    @Test
    public void testCreateAccess() throws Exception {
        // Create access
        App app = accessControlBaseService.findAppById(getServiceContextAdministrador(), APP_1);
        Role role = accessControlBaseService.findRoleById(getServiceContextAdministrador(), ROLE_1);
        User user = accessControlBaseService.findUserById(getServiceContextAdministrador(), USER_1);
        ExternalItem operation = new ExternalItem("MNP", "urn:app:gopestat:StatisticalOperation=MNP", "MNP", TypeExternalArtefactsEnum.STATISTICAL_OPERATION);

        Access access = accessControlBaseService.createAccess(getServiceContextAdministrador(), AccessControlDoMocks.createAccess(app, role, user, operation));
        assertNotNull(access);
    }

    @Test
    public void testUpdateAccess() throws Exception {
        Long id = ACCESS_1;

        // Retrieve
        Access access = accessControlBaseService.findAccessById(getServiceContextAdministrador(), id);

        App app = accessControlBaseService.findAppById(getServiceContextAdministrador(), APP_2);
        Role role = accessControlBaseService.findRoleById(getServiceContextAdministrador(), ROLE_2);
        User user = accessControlBaseService.findUserById(getServiceContextAdministrador(), USER_2);
        ExternalItem operation = new ExternalItem("MNP2", "urn:app:gopestat:StatisticalOperation=MNP2", "MNP2", TypeExternalArtefactsEnum.STATISTICAL_OPERATION);

        access.setApp(app);
        access.setRole(role);
        access.setUser(user);
        access.setOperation(operation);

        // Update
        Access accessUpdated = accessControlBaseService.updateAccess(getServiceContextAdministrador(), access);

        // Validation
        assertNotNull(accessUpdated);
        AccessControlDoAsserts.assertEqualsAccess(access, accessUpdated);
        assertTrue(accessUpdated.getLastUpdated().isAfter(accessUpdated.getCreatedDate()));
    }

    @Test
    public void testFindAllAccess() throws Exception {
        List<Access> access = accessControlBaseService.findAllAccess(getServiceContextAdministrador());
        assertNotNull(access);
        assertEquals(3, access.size());
    }

    @Test(expected = MetamacException.class)
    public void testDeleteAccess() throws Exception {
        accessControlBaseService.deleteAccess(getServiceContextAdministrador(), ACCESS_2);

        Access access = accessControlBaseService.findAccessById(getServiceContextAdministrador(), ACCESS_2);
        assertNull(access);
    }

    @Test
    public void testRemoveAccess() throws Exception {
        Long id = ACCESS_1;

        // Retrieve access
        Access access = accessControlBaseService.findAccessById(getServiceContextAdministrador(), id);

        // Remove
        accessControlBaseService.removeAccess(getServiceContextAdministrador(), id);

        // Retrieve removed access
        Access removedAccess = accessControlBaseService.findAccessById(getServiceContextAdministrador(), id);

        // Validations
        assertNotNull(removedAccess.getRemovalDate());
        AccessControlDoAsserts.assertEqualsAccess(access, removedAccess);
    }

    @Test
    public void testFindAccessByCondition() throws Exception {
        String username = "ARTE";
        List<ConditionalCriteria> condition = criteriaFor(Access.class).withProperty(org.siemac.metamac.access.control.core.domain.AccessProperties.user().username())
                .ignoreCaseLike("%" + username + "%").build();

        List<Access> access = accessControlBaseService.findAccessByCondition(getServiceContextAdministrador(), condition);

        assertEquals(3, access.size());
    }

    @Test
    public void testFindAccessByConditionWithoutResults() throws Exception {
        String username = "prueba";
        List<ConditionalCriteria> condition = criteriaFor(Access.class).withProperty(org.siemac.metamac.access.control.core.domain.AccessProperties.user().username())
                .ignoreCaseLike("%" + username + "%").build();

        List<Access> access = accessControlBaseService.findAccessByCondition(getServiceContextAdministrador(), condition);

        assertEquals(0, access.size());
    }
}