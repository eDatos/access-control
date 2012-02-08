package org.siemac.metamac.access.control.base.serviceapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.siemac.metamac.access.control.base.domain.Access;
import org.siemac.metamac.access.control.base.domain.App;
import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.base.domain.User;
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
    public void testSaveRole() throws Exception {
        Role role = accessControlBaseService.saveRole(getServiceContext(), createRole());
        assertNotNull(role);
    }

    @Test
    public void testFindAllRoles() throws Exception {
        List<Role> roles = accessControlBaseService.findAllRoles(getServiceContext());
        assertNotNull(roles);
        assertEquals(2, roles.size());
    }

    @Test(expected = MetamacException.class)
    public void testDeleteRole() throws Exception {
        Role role = accessControlBaseService.findRoleById(getServiceContext(), ROLE_2);
        assertNotNull(role);

        accessControlBaseService.deleteRole(getServiceContext(), role);

        role = accessControlBaseService.findRoleById(getServiceContext(), ROLE_2);
        assertNull(role);

    }

    @Test
    public void testFindRoleByCondition() throws Exception {
        // TODO Auto-generated method stub
        // fail("testFindRoleByCondition not implemented");
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
    public void testSaveApp() throws Exception {
        App app = accessControlBaseService.saveApp(getServiceContext(), createApp());
        assertNotNull(app);
    }

    @Test
    public void testFindAllApps() throws Exception {
        List<App> apps = accessControlBaseService.findAllApps(getServiceContext());
        assertNotNull(apps);
        assertEquals(2, apps.size());
    }

    @Test(expected = MetamacException.class)
    public void testDeleteApp() throws Exception {
        App app = accessControlBaseService.findAppById(getServiceContext(), APP_2);
        assertNotNull(app);

        accessControlBaseService.deleteApp(getServiceContext(), app);

        app = accessControlBaseService.findAppById(getServiceContext(), APP_2);
        assertNull(app);
    }

    @Test
    public void testFindAppByCondition() throws Exception {
        // TODO Auto-generated method stub
        // fail("testFindAppByCondition not implemented");
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
    public void testSaveUser() throws Exception {
        User user = accessControlBaseService.saveUser(getServiceContext(), createUser());
        assertNotNull(user);
    }

    @Test
    public void testFindAllUsers() throws Exception {
        List<User> users = accessControlBaseService.findAllUsers(getServiceContext());
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test(expected = MetamacException.class)
    public void testDeleteUser() throws Exception {
        User user = accessControlBaseService.findUserById(getServiceContext(), USER_2);
        assertNotNull(user);

        accessControlBaseService.deleteUser(getServiceContext(), user);

        user = accessControlBaseService.findUserById(getServiceContext(), USER_2);
        assertNull(user);
    }

    @Test
    public void testFindUserByCondition() throws Exception {
        // TODO Auto-generated method stub
        // fail("testFindUserByCondition not implemented");
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
    public void testSaveAccess() throws Exception {
         // Create access
         App app = accessControlBaseService.findAppById(getServiceContext(), APP_1);
         Role role = accessControlBaseService.findRoleById(getServiceContext(), ROLE_1);
         User user = accessControlBaseService.findUserById(getServiceContext(), USER_1);
         ExternalItemBt operation = new ExternalItemBt("urn:app:gopestat:StatisticalOperation=MNP", "MNP", TypeExternalArtefactsEnum.STATISTICAL_OPERATION);
        
         Access access = accessControlBaseService.saveAccess(getServiceContext(), createAccess(app, role, user, operation));
         assertNotNull(access);
    }

    @Test
    public void testFindAllAccess() throws Exception {
        List<Access> access = accessControlBaseService.findAllAccess(getServiceContext());
        assertNotNull(access);
        assertEquals(1, access.size());
    }

    @Test(expected = MetamacException.class)
    public void testDeleteAccess() throws Exception {
        Access access = accessControlBaseService.findAccessById(getServiceContext(), ACCESS_2);
        assertNotNull(access);

        accessControlBaseService.deleteAccess(getServiceContext(), access);

        access = accessControlBaseService.findAccessById(getServiceContext(), ACCESS_2);
        assertNull(access);
    }

    @Test
    public void testFindAccessByCondition() throws Exception {
        // TODO Auto-generated method stub
        // fail("testFindAccessByCondition not implemented");
    }

    /**************************************************************************
     * MOCKS
     **************************************************************************/

    private Role createRole() {
        Role role = new Role();
        role.setCode("TECNICO_DIFUSION");
        role.setTitle("Técnico de difusión");
        role.setDescription("Técnico de difusión estadística encargado de las labores de publicación");
        return role;
    }

    private App createApp() {
        App app = new App();
        app.setCode("METAMAC");
        app.setTitle("Usuario METAMAC");
        app.setDescription("Usuario de pruebas METAMAC");
        return app;
    }

    private User createUser() {
        User user = new User();
        user.setUsername("rdiaada");
        user.setName("Rita");
        user.setSurname("Díaz Adán");
        user.setMail("rdiaada@arte-consultores.com");
        return user;
    }

    private Access createAccess(App app, Role role, User user, ExternalItemBt operation) {
        Access access = new Access();
        access.setApp(app);
        access.setUser(user);
        access.setRole(role);
        access.setOperation(operation);
        return access;
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
    
//    @Override
//    public void tearDownDatabaseTester() throws Exception {
//        // NOTHING;
//    }

}
