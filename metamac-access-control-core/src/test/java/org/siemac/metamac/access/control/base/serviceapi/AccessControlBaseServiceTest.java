package org.siemac.metamac.access.control.base.serviceapi;

import static org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteriaBuilder.criteriaFor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteria;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.siemac.metamac.access.control.base.domain.Access;
import org.siemac.metamac.access.control.base.domain.App;
import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.base.domain.User;
import org.siemac.metamac.access.control.base.serviceapi.utils.AccessControlDoAsserts;
import org.siemac.metamac.access.control.base.serviceapi.utils.AccessControlDoMocks;
import org.siemac.metamac.common.test.MetamacBaseTests;
import org.siemac.metamac.core.common.bt.domain.ExternalItemBt;
import org.siemac.metamac.core.common.enume.domain.TypeExternalArtefactsEnum;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring based transactional test with DbUnit support.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:oracle/applicationContext-test.xml"})
public class AccessControlBaseServiceTest extends MetamacBaseTests implements AccessControlBaseServiceTestBase {

    @Autowired
    protected AccessControlBaseService accessControlBaseService;

    private final ServiceContext       serviceContext = new ServiceContext("system", "123456", "junit");

    private static Long                ROLE_1         = Long.valueOf(1);
    private static Long                ROLE_2         = Long.valueOf(2);

    private static Long                APP_1          = Long.valueOf(1);
    private static Long                APP_2          = Long.valueOf(2);

    private static Long                USER_1         = Long.valueOf(1);
    private static Long                USER_2         = Long.valueOf(2);

    private static Long                ACCESS_1       = Long.valueOf(1);
    private static Long                ACCESS_2       = Long.valueOf(2);

    /**************************************************************************
     * SERVICE CONTEXT
     **************************************************************************/

    protected ServiceContext getServiceContext() {
        return serviceContext;
    }

    /**************************************************************************
     * ROLES
     **************************************************************************/

    @Test
    public void testFindRoleById() throws Exception {
        Role role = accessControlBaseService.findRoleById(getServiceContext(), ROLE_1);
        assertNotNull(role);
    }

    @Test
    public void testCreateRole() throws Exception {
        Role role = accessControlBaseService.createRole(getServiceContext(), AccessControlDoMocks.createRole());
        assertNotNull(role);
    }

    @Test
    public void testUpdateRole() throws Exception {
        Long id = ROLE_1;

        // Retrieve
        Role role = accessControlBaseService.findRoleById(getServiceContext(), id);
        role.setCode("newCode");
        role.setTitle("newTitle");
        role.setDescription("newDescription");

        // Update
        Role roleUpdated = accessControlBaseService.updateRole(getServiceContext(), role);

        // Validation
        assertNotNull(roleUpdated);
        AccessControlDoAsserts.assertEqualsRole(role, roleUpdated);
        assertTrue(roleUpdated.getLastUpdated().isAfter(roleUpdated.getCreatedDate()));
    }

    @Test
    public void testFindAllRoles() throws Exception {
        List<Role> roles = accessControlBaseService.findAllRoles(getServiceContext());
        assertNotNull(roles);
        assertEquals(2, roles.size());
    }

    @Test(expected = MetamacException.class)
    public void testDeleteRole() throws Exception {
        accessControlBaseService.deleteRole(getServiceContext(), ROLE_2);

        Role role = accessControlBaseService.findRoleById(getServiceContext(), ROLE_2);
        assertNull(role);
    }

    @Test
    public void testFindRoleByCondition() throws Exception {
        String code = "Admin";
        List<ConditionalCriteria> condition = criteriaFor(Role.class).withProperty(org.siemac.metamac.access.control.base.domain.RoleProperties.code()).ignoreCaseLike("%" + code + "%").build();

        List<Role> roles = accessControlBaseService.findRoleByCondition(getServiceContext(), condition);

        assertEquals(1, roles.size());
    }

    /**************************************************************************
     * APPS
     **************************************************************************/

    @Test
    public void testFindAppById() throws Exception {
        App app = accessControlBaseService.findAppById(getServiceContext(), APP_1);
        assertNotNull(app);
    }

    @Test
    public void testCreateApp() throws Exception {
        App app = accessControlBaseService.createApp(getServiceContext(), AccessControlDoMocks.createApp());
        assertNotNull(app);
    }

    @Test
    public void testUpdateApp() throws Exception {
        Long id = APP_1;

        // Retrieve
        App app = accessControlBaseService.findAppById(getServiceContext(), id);
        app.setCode("newCode");
        app.setTitle("newTitle");
        app.setDescription("newDescription");

        // Update
        App appUpdated = accessControlBaseService.updateApp(getServiceContext(), app);

        // Validation
        assertNotNull(appUpdated);
        AccessControlDoAsserts.assertEqualsApp(app, appUpdated);
        assertTrue(appUpdated.getLastUpdated().isAfter(appUpdated.getCreatedDate()));
    }

    @Test
    public void testFindAllApps() throws Exception {
        List<App> apps = accessControlBaseService.findAllApps(getServiceContext());
        assertNotNull(apps);
        assertEquals(2, apps.size());
    }

    @Test(expected = MetamacException.class)
    public void testDeleteApp() throws Exception {
        accessControlBaseService.deleteApp(getServiceContext(), APP_2);

        App app = accessControlBaseService.findAppById(getServiceContext(), APP_2);
        assertNull(app);
    }

    @Test
    public void testFindAppByCondition() throws Exception {
        String code = "Gpe";
        List<ConditionalCriteria> condition = criteriaFor(App.class).withProperty(org.siemac.metamac.access.control.base.domain.AppProperties.code()).ignoreCaseLike("%" + code + "%").build();

        List<App> apps = accessControlBaseService.findAppByCondition(getServiceContext(), condition);

        assertEquals(1, apps.size());
    }

    /**************************************************************************
     * USERS
     **************************************************************************/

    @Test
    public void testFindUserById() throws Exception {
        User user = accessControlBaseService.findUserById(getServiceContext(), USER_1);
        assertNotNull(user);
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = accessControlBaseService.createUser(getServiceContext(), AccessControlDoMocks.createUser());
        assertNotNull(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        Long id = USER_1;

        // Retrieve
        User user = accessControlBaseService.findUserById(getServiceContext(), id);
        user.setUsername("newUsername");
        user.setName("newName");
        user.setSurname("newSurname");
        user.setMail("newMail");

        // Update
        User userUpdated = accessControlBaseService.updateUser(getServiceContext(), user);

        // Validation
        assertNotNull(userUpdated);
        AccessControlDoAsserts.assertEqualsUser(user, userUpdated);
        assertTrue(userUpdated.getLastUpdated().isAfter(userUpdated.getCreatedDate()));
    }

    @Test
    public void testFindAllUsers() throws Exception {
        List<User> users = accessControlBaseService.findAllUsers(getServiceContext());
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test(expected = MetamacException.class)
    public void testDeleteUser() throws Exception {
        accessControlBaseService.deleteUser(getServiceContext(), USER_2);

        User user = accessControlBaseService.findUserById(getServiceContext(), USER_2);
        assertNull(user);
    }

    @Test
    public void testFindUserByCondition() throws Exception {
        String username = "ARTE";
        List<ConditionalCriteria> condition = criteriaFor(User.class).withProperty(org.siemac.metamac.access.control.base.domain.UserProperties.username()).ignoreCaseLike("%" + username + "%")
                .build();

        List<User> users = accessControlBaseService.findUserByCondition(getServiceContext(), condition);

        assertEquals(1, users.size());
    }

    /**************************************************************************
     * ACCESS
     **************************************************************************/

    @Test
    public void testFindAccessById() throws Exception {
        Access access = accessControlBaseService.findAccessById(getServiceContext(), ACCESS_1);
        assertNotNull(access);
    }

    @Test
    public void testCreateAccess() throws Exception {
        // Create access
        App app = accessControlBaseService.findAppById(getServiceContext(), APP_1);
        Role role = accessControlBaseService.findRoleById(getServiceContext(), ROLE_1);
        User user = accessControlBaseService.findUserById(getServiceContext(), USER_1);
        ExternalItemBt operation = new ExternalItemBt("urn:app:gopestat:StatisticalOperation=MNP", "MNP", TypeExternalArtefactsEnum.STATISTICAL_OPERATION);

        Access access = accessControlBaseService.createAccess(getServiceContext(), AccessControlDoMocks.createAccess(app, role, user, operation));
        assertNotNull(access);
    }

    @Test
    public void testUpdateAccess() throws Exception {
        Long id = ACCESS_1;

        // Retrieve
        Access access = accessControlBaseService.findAccessById(getServiceContext(), id);

        App app = accessControlBaseService.findAppById(getServiceContext(), APP_2);
        Role role = accessControlBaseService.findRoleById(getServiceContext(), ROLE_2);
        User user = accessControlBaseService.findUserById(getServiceContext(), USER_2);
        ExternalItemBt operation = new ExternalItemBt("urn:app:gopestat:StatisticalOperation=MNP2", "MNP2", TypeExternalArtefactsEnum.STATISTICAL_OPERATION);

        access.setApp(app);
        access.setRole(role);
        access.setUser(user);
        access.setOperation(operation);

        // Update
        Access accessUpdated = accessControlBaseService.updateAccess(getServiceContext(), access);

        // Validation
        assertNotNull(accessUpdated);
        AccessControlDoAsserts.assertEqualsAccess(access, accessUpdated);
        assertTrue(accessUpdated.getLastUpdated().isAfter(accessUpdated.getCreatedDate()));
    }

    @Test
    public void testFindAllAccess() throws Exception {
        List<Access> access = accessControlBaseService.findAllAccess(getServiceContext());
        assertNotNull(access);
        assertEquals(3, access.size());
    }

    @Test(expected = MetamacException.class)
    public void testDeleteAccess() throws Exception {
        accessControlBaseService.deleteAccess(getServiceContext(), ACCESS_2);

        Access access = accessControlBaseService.findAccessById(getServiceContext(), ACCESS_2);
        assertNull(access);
    }

    @Test
    public void testRemoveAccess() throws Exception {
        Long id = ACCESS_1;

        // Retrieve access
        Access access = accessControlBaseService.findAccessById(getServiceContext(), id);

        // Remove
        accessControlBaseService.removeAccess(getServiceContext(), id);

        // Retrieve removed access
        Access removedAccess = accessControlBaseService.findAccessById(getServiceContext(), id);
        
        // Validations
        assertNotNull(removedAccess.getRemovalDate());
        AccessControlDoAsserts.assertEqualsAccess(access, removedAccess);
    }

    @Test
    public void testFindAccessByCondition() throws Exception {
        String username = "ARTE";
        List<ConditionalCriteria> condition = criteriaFor(Access.class).withProperty(org.siemac.metamac.access.control.base.domain.AccessProperties.user().username())
                .ignoreCaseLike("%" + username + "%").build();

        List<Access> access = accessControlBaseService.findAccessByCondition(getServiceContext(), condition);

        assertEquals(3, access.size());
    }

    @Test
    public void testFindAccessByConditionWithoutResults() throws Exception {
        String username = "prueba";
        List<ConditionalCriteria> condition = criteriaFor(Access.class).withProperty(org.siemac.metamac.access.control.base.domain.AccessProperties.user().username())
                .ignoreCaseLike("%" + username + "%").build();

        List<Access> access = accessControlBaseService.findAccessByCondition(getServiceContext(), condition);

        assertEquals(0, access.size());
    }

    /**************************************************************************
     * DBUNIT CONFIGURATION
     **************************************************************************/

    @Override
    protected String getDataSetFile() {
        return "dbunit/AccessControlBaseServiceTest.xml";
    }

    @Override
    protected List<String> getTablesToRemoveContent() {
        List<String> tables = new ArrayList<String>();
        tables.add("TBL_ACCESS");
        tables.add("TBL_APPS");
        tables.add("TBL_ROLES");
        tables.add("TBL_USERS");
        return tables;
    }

    @Override
    protected List<String> getSequencesToRestart() {
        List<String> sequences = new ArrayList<String>();
        sequences.add("SEQ_ROLES");
        sequences.add("SEQ_APPS");
        sequences.add("SEQ_USERS");
        sequences.add("SEQ_ACCESS");
        return sequences;
    }

    // @Override
    // public void tearDownDatabaseTester() throws Exception {
    // // NOTHING;
    // }

}
