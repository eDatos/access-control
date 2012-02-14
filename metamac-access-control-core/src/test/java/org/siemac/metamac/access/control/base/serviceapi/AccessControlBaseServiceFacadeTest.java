package org.siemac.metamac.access.control.base.serviceapi;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.siemac.metamac.common.test.MetamacBaseTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring based transactional test with DbUnit support.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:oracle/applicationContext-test.xml"})
public class AccessControlBaseServiceFacadeTest extends MetamacBaseTests implements AccessControlBaseServiceFacadeTestBase {

    @Autowired
    protected AccessControlBaseServiceFacade accessControlBaseServiceFacade;

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
    public void testCreateRole() throws Exception {
        // TODO Auto-generated method stub
        fail("testCreateRole not implemented");
    }

    @Test
    public void testUpdateRole() throws Exception {
        // TODO Auto-generated method stub
        fail("testUpdateRole not implemented");
    }

    @Test
    public void testDeleteRole() throws Exception {
        // TODO Auto-generated method stub
        fail("testDeleteRole not implemented");
    }

    @Test
    public void testFindAllRoles() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindAllRoles not implemented");
    }

    @Test
    public void testFindRoleByCondition() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindRoleByCondition not implemented");
    }

    @Test
    public void testFindRoleById() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindRoleById not implemented");
    }

    
    /**************************************************************************
     * APPS
     **************************************************************************/
    
    @Test
    public void testCreateApp() throws Exception {
        // TODO Auto-generated method stub
        fail("testCreateApp not implemented");
    }

    @Test
    public void testUpdateApp() throws Exception {
        // TODO Auto-generated method stub
        fail("testUpdateApp not implemented");
    }

    @Test
    public void testDeleteApp() throws Exception {
        // TODO Auto-generated method stub
        fail("testDeleteApp not implemented");
    }

    @Test
    public void testFindAllApps() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindAllApps not implemented");
    }

    @Test
    public void testFindAppByCondition() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindAppByCondition not implemented");
    }

    @Test
    public void testFindAppById() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindAppById not implemented");
    }

    /**************************************************************************
     * USERS
     **************************************************************************/
    
    @Test
    public void testCreateUser() throws Exception {
        // TODO Auto-generated method stub
        fail("testCreateUser not implemented");
    }

    @Test
    public void testUpdateUser() throws Exception {
        // TODO Auto-generated method stub
        fail("testUpdateUser not implemented");
    }

    @Test
    public void testDeleteUser() throws Exception {
        // TODO Auto-generated method stub
        fail("testDeleteUser not implemented");
    }

    @Test
    public void testFindAllUsers() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindAllUsers not implemented");
    }

    @Test
    public void testFindUserByCondition() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindUserByCondition not implemented");
    }

    @Test
    public void testFindUserById() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindUserById not implemented");
    }

    /**************************************************************************
     * ACCESS
     **************************************************************************/
    
    @Test
    public void testCreateAccess() throws Exception {
        // TODO Auto-generated method stub
        fail("testCreateAccess not implemented");
    }

    @Test
    public void testUpdateAccess() throws Exception {
        // TODO Auto-generated method stub
        fail("testUpdateAccess not implemented");
    }

    @Test
    public void testDeleteAccess() throws Exception {
        // TODO Auto-generated method stub
        fail("testDeleteAccess not implemented");
    }

    @Test
    public void testFindAllAccess() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindAllAccess not implemented");
    }

    @Test
    public void testFindAccessByCondition() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindAccessByCondition not implemented");
    }

    @Test
    public void testFindAccessById() throws Exception {
        // TODO Auto-generated method stub
        fail("testFindAccessById not implemented");
    }

    
    
    /**************************************************************************
     * MOCKS
     **************************************************************************/
    
    
    
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
}
