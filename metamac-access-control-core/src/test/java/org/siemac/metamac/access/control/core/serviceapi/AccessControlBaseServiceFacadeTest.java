package org.siemac.metamac.access.control.core.serviceapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.core.serviceapi.utils.AccessControlDtoAsserts;
import org.siemac.metamac.access.control.core.serviceapi.utils.AccessControlDtoMocks;
import org.siemac.metamac.access.control.error.ServiceExceptionParameters;
import org.siemac.metamac.access.control.error.ServiceExceptionType;
import org.siemac.metamac.common.test.utils.MetamacAsserts;
import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.core.common.enume.domain.TypeExternalArtefactsEnum;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
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
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class AccessControlBaseServiceFacadeTest extends AccessControlBaseTest implements AccessControlBaseServiceFacadeTestBase {

    @Autowired
    protected AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    /**************************************************************************
     * ROLES
     **************************************************************************/

    @Override
    @Test
    public void testFindRoleById() throws Exception {
        Long id = ROLE_1;
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), id);
        assertNotNull(roleDto);
        assertEquals(id, roleDto.getId());
        assertEquals("aDMINISTRADOR", roleDto.getCode());
        assertEquals("R-1", roleDto.getUuid());
        assertEquals("Administrador", roleDto.getTitle());
        assertEquals("Administrador del sistema", roleDto.getDescription());
        assertEquals(Long.valueOf(1), roleDto.getVersion());
        assertEquals("user1", roleDto.getCreatedBy());
        MetamacAsserts.assertEqualsDate("2010-07-22 12:47:22", roleDto.getCreatedDate());
        assertEquals("user2", roleDto.getLastUpdatedBy());
        MetamacAsserts.assertEqualsDate("2011-08-24 01:02:03", roleDto.getLastUpdated());
    }

    @Test
    public void testFindRoleByIdErrorParameterRequired() throws Exception {
        expectedMetamacException(new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, ServiceExceptionParameters.ID));
        accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), null);
    }

    @Test
    public void testRetrieveRoleErrorNotExists() throws Exception {
        Long id = NOT_EXISTS;

        expectedMetamacException(new MetamacException(ServiceExceptionType.ROLE_NOT_FOUND, id));
        accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), id);
    }

    @Override
    @Test
    public void testCreateRole() throws Exception {
        RoleDto roleDto = AccessControlDtoMocks.mockRoleDto();

        // Create
        RoleDto roleDtoCreated = accessControlBaseServiceFacade.createRole(getServiceContextAdministrador(), roleDto);

        // Validate
        assertNotNull(roleDtoCreated);
        assertNotNull(roleDtoCreated.getId());
        assertNotNull(roleDtoCreated.getUuid());
        assertNotNull(roleDtoCreated.getVersion());

        AccessControlDtoAsserts.assertEqualsCreatedRoleDto(roleDto, roleDtoCreated);

        // Audit validations
        assertNotNull(roleDtoCreated.getCreatedBy());
        assertNotNull(roleDtoCreated.getCreatedDate());
        assertNotNull(roleDtoCreated.getLastUpdated());
        assertNotNull(roleDtoCreated.getLastUpdatedBy());
        assertEquals(getServiceContextAdministrador().getUserId(), roleDtoCreated.getCreatedBy());
        assertTrue(DateUtils.isSameDay(new Date(), roleDtoCreated.getCreatedDate()));
        assertTrue(DateUtils.isSameDay(new Date(), roleDtoCreated.getLastUpdated()));
        assertEquals(getServiceContextAdministrador().getUserId(), roleDtoCreated.getLastUpdatedBy());

    }

    @Test
    public void testCreateRoleCodeRequired() throws Exception {
        expectedMetamacException(new MetamacException(ServiceExceptionType.METADATA_REQUIRED, ServiceExceptionParameters.ROLE_CODE));

        RoleDto roleDto = new RoleDto();
        roleDto.setCode(null);
        roleDto.setTitle("Title");
        accessControlBaseServiceFacade.createRole(getServiceContextAdministrador(), roleDto);
    }

    @Test
    public void testCreateRoleTitleRequired() throws Exception {
        expectedMetamacException(new MetamacException(ServiceExceptionType.METADATA_REQUIRED, ServiceExceptionParameters.ROLE_TITLE));

        RoleDto roleDto = new RoleDto();
        roleDto.setCode("CODE");
        roleDto.setTitle(null);
        accessControlBaseServiceFacade.createRole(getServiceContextAdministrador(), roleDto);
    }

    @Test
    public void testCreateRoleCodeDuplicated() throws Exception {

        RoleDto roleDto = new RoleDto();
        roleDto.setCode("aDMINISTRADOR");
        roleDto.setTitle("Title");

        expectedMetamacException(new MetamacException(ServiceExceptionType.ROLE_ALREADY_EXIST_CODE_DUPLICATED, roleDto.getCode()));
        accessControlBaseServiceFacade.createRole(getServiceContextAdministrador(), roleDto);
    }

    @Test
    public void testCreateRoleCodeDuplicatedInsensitive() throws Exception {

        RoleDto roleDto = new RoleDto();
        roleDto.setCode("AdMiNISTrADOR");
        roleDto.setTitle("Title");

        expectedMetamacException(new MetamacException(ServiceExceptionType.ROLE_ALREADY_EXIST_CODE_DUPLICATED, roleDto.getCode()));
        accessControlBaseServiceFacade.createRole(getServiceContextAdministrador(), roleDto);
    }

    @Override
    @Test
    public void testDeleteRole() throws Exception {
        Long id = ROLE_2;
        expectedMetamacException(new MetamacException(ServiceExceptionType.ROLE_NOT_FOUND, id));

        // Delete role
        accessControlBaseServiceFacade.deleteRole(getServiceContextAdministrador(), id);

        // Validation
        accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), id);
    }

    @Test
    public void testDeleteRoleWithAccess() throws Exception {
        Long id = ROLE_1;
        expectedMetamacException(new MetamacException(ServiceExceptionType.ROLE_NOT_FOUND, id));

        // Delete role
        accessControlBaseServiceFacade.deleteRole(getServiceContextAdministrador(), id);

        // Validation
        accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), id);
    }

    @Test
    public void testDeleteRoleNotExists() throws Exception {
        Long id = NOT_EXISTS;

        expectedMetamacException(new MetamacException(ServiceExceptionType.ROLE_NOT_FOUND, id));
        accessControlBaseServiceFacade.deleteRole(getServiceContextAdministrador(), id);
    }

    @Override
    @Test
    public void testUpdateRole() throws Exception {
        Long id = ROLE_1;

        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), id);

        roleDto.setCode("newCode");
        roleDto.setTitle("newTitle");
        roleDto.setDescription("newDescription");

        // Update
        RoleDto roleDtoUpdated = accessControlBaseServiceFacade.updateRole(getServiceContextAdministrador(), roleDto);

        // Validations
        AccessControlDtoAsserts.assertEqualsRoleDto(roleDto, roleDtoUpdated);
        assertTrue(roleDtoUpdated.getLastUpdated().after(roleDtoUpdated.getCreatedDate()));
    }

    @Test
    public void testUpdateRoleNotExists() throws Exception {
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), ROLE_1);
        roleDto.setId(NOT_EXISTS);

        expectedMetamacException(new MetamacException(ServiceExceptionType.ROLE_NOT_FOUND, roleDto.getId()));
        accessControlBaseServiceFacade.updateRole(getServiceContextAdministrador(), roleDto);
    }

    @Test
    public void testUpdateRoleDuplicatedCode() throws Exception {
        Long id = ROLE_1;
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), id);
        roleDto.setCode("TEC_PLANI");

        expectedMetamacException(new MetamacException(ServiceExceptionType.ROLE_ALREADY_EXIST_CODE_DUPLICATED, roleDto.getCode()));
        accessControlBaseServiceFacade.updateRole(getServiceContextAdministrador(), roleDto);
    }

    @Test
    public void testUpdateRoleOptimisticLockingError() throws Exception {
        Long id = ROLE_1;

        // Retrieve role - session 1
        RoleDto roleDtoSession1 = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), id);
        assertEquals(Long.valueOf(1), roleDtoSession1.getOptimisticLockingVersion());
        roleDtoSession1.setTitle("newTitle");

        // Retrieve role - session 2
        RoleDto roleDtoSession2 = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), id);
        assertEquals(Long.valueOf(1), roleDtoSession1.getOptimisticLockingVersion());
        roleDtoSession2.setTitle("newTitle2");

        // Update role - session 1
        RoleDto roleDtoSession1AfterUpdate = accessControlBaseServiceFacade.updateRole(getServiceContextAdministrador(), roleDtoSession1);
        AccessControlDtoAsserts.assertEqualsRoleDto(roleDtoSession1, roleDtoSession1AfterUpdate);
        assertEquals(Long.valueOf(2), roleDtoSession1AfterUpdate.getOptimisticLockingVersion());

        // Update role - session 2
        try {
            accessControlBaseServiceFacade.updateRole(getServiceContextAdministrador(), roleDtoSession2);
            fail("Optimistic locking");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.OPTIMISTIC_LOCKING.getCode(), e.getExceptionItems().get(0).getCode());
            assertNull(e.getExceptionItems().get(0).getMessageParameters());
        }

        // Update role - session 1
        roleDtoSession1AfterUpdate.setTitle("newTitle - secondUpdate");
        RoleDto roleDtoSession1AfterUpdate2 = accessControlBaseServiceFacade.updateRole(getServiceContextAdministrador(), roleDtoSession1AfterUpdate);
        AccessControlDtoAsserts.assertEqualsRoleDto(roleDtoSession1AfterUpdate, roleDtoSession1AfterUpdate2);
        assertEquals(Long.valueOf(3), roleDtoSession1AfterUpdate2.getOptimisticLockingVersion());
    }

    @Override
    @Test
    public void testFindAllRoles() throws Exception {
        List<RoleDto> roles = accessControlBaseServiceFacade.findAllRoles(getServiceContextAdministrador());
        assertEquals(2, roles.size());
    }

    /**************************************************************************
     * APPS
     **************************************************************************/

    @Override
    @Test
    public void testCreateApp() throws Exception {
        AppDto appDto = AccessControlDtoMocks.mockAppDto();

        // Create
        AppDto appDtoCreated = accessControlBaseServiceFacade.createApp(getServiceContextAdministrador(), appDto);

        // Validate
        assertNotNull(appDtoCreated);
        assertNotNull(appDtoCreated.getId());
        assertNotNull(appDtoCreated.getUuid());
        assertNotNull(appDtoCreated.getVersion());

        AccessControlDtoAsserts.assertEqualsCreatedAppDto(appDto, appDtoCreated);

        // Audit validations
        assertNotNull(appDtoCreated.getCreatedBy());
        assertNotNull(appDtoCreated.getCreatedDate());
        assertNotNull(appDtoCreated.getLastUpdated());
        assertNotNull(appDtoCreated.getLastUpdatedBy());
        assertEquals(getServiceContextAdministrador().getUserId(), appDtoCreated.getCreatedBy());
        assertTrue(DateUtils.isSameDay(new Date(), appDtoCreated.getCreatedDate()));
        assertTrue(DateUtils.isSameDay(new Date(), appDtoCreated.getLastUpdated()));
        assertEquals(getServiceContextAdministrador().getUserId(), appDtoCreated.getLastUpdatedBy());

    }

    @Test
    public void testCreateAppCodeRequired() throws Exception {
        AppDto appDto = new AppDto();
        appDto.setCode(null);
        appDto.setTitle("Title");

        expectedMetamacException(new MetamacException(ServiceExceptionType.METADATA_REQUIRED, ServiceExceptionParameters.APP_CODE));
        accessControlBaseServiceFacade.createApp(getServiceContextAdministrador(), appDto);
    }

    @Test
    public void testCreateAppTitleRequired() throws Exception {
        AppDto appDto = new AppDto();
        appDto.setCode("CODE");
        appDto.setTitle(null);

        expectedMetamacException(new MetamacException(ServiceExceptionType.METADATA_REQUIRED, ServiceExceptionParameters.APP_TITLE));
        accessControlBaseServiceFacade.createApp(getServiceContextAdministrador(), appDto);
    }

    @Test
    public void testCreateAppCodeDuplicated() throws Exception {
        AppDto appDto = new AppDto();
        appDto.setCode("gOPESTAT");
        appDto.setTitle("Title");

        expectedMetamacException(new MetamacException(ServiceExceptionType.APP_ALREADY_EXIST_CODE_DUPLICATED, appDto.getCode()));
        accessControlBaseServiceFacade.createApp(getServiceContextAdministrador(), appDto);
    }

    @Test
    public void testCreateAppCodeDuplicatedInsensitive() throws Exception {
        AppDto appDto = new AppDto();
        appDto.setCode("GoPeStat");
        appDto.setTitle("Title");

        expectedMetamacException(new MetamacException(ServiceExceptionType.APP_ALREADY_EXIST_CODE_DUPLICATED, appDto.getCode()));
        accessControlBaseServiceFacade.createApp(getServiceContextAdministrador(), appDto);
    }

    @Override
    @Test
    public void testUpdateApp() throws Exception {
        Long id = APP_1;

        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), id);

        appDto.setCode("newCode");
        appDto.setTitle("newTitle");
        appDto.setDescription("newDescription");

        // Update
        AppDto appDtoUpdated = accessControlBaseServiceFacade.updateApp(getServiceContextAdministrador(), appDto);

        // Validations
        AccessControlDtoAsserts.assertEqualsAppDto(appDto, appDtoUpdated);
        assertTrue(appDtoUpdated.getLastUpdated().after(appDtoUpdated.getCreatedDate()));
    }

    @Test
    public void testUpdateAppNotExists() throws Exception {
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), APP_1);
        appDto.setId(NOT_EXISTS);

        expectedMetamacException(new MetamacException(ServiceExceptionType.APP_NOT_FOUND, appDto.getId()));
        accessControlBaseServiceFacade.updateApp(getServiceContextAdministrador(), appDto);
    }

    @Test
    public void testUpdateAppDuplicatedCode() throws Exception {
        Long id = APP_1;
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), id);
        appDto.setCode("gPE");

        expectedMetamacException(new MetamacException(ServiceExceptionType.APP_ALREADY_EXIST_CODE_DUPLICATED, appDto.getCode()));
        accessControlBaseServiceFacade.updateApp(getServiceContextAdministrador(), appDto);
    }

    @Test
    public void testUpdateAppOptimisticLockingError() throws Exception {
        Long id = APP_1;

        // Retrieve app - session 1
        AppDto appDtoSession1 = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), id);
        assertEquals(Long.valueOf(1), appDtoSession1.getOptimisticLockingVersion());
        appDtoSession1.setTitle("newTitle");

        // Retrieve app - session 2
        AppDto appDtoSession2 = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), id);
        assertEquals(Long.valueOf(1), appDtoSession1.getOptimisticLockingVersion());
        appDtoSession2.setTitle("newTitle2");

        // Update app - session 1
        AppDto appDtoSession1AfterUpdate = accessControlBaseServiceFacade.updateApp(getServiceContextAdministrador(), appDtoSession1);
        AccessControlDtoAsserts.assertEqualsAppDto(appDtoSession1, appDtoSession1AfterUpdate);
        assertEquals(Long.valueOf(2), appDtoSession1AfterUpdate.getOptimisticLockingVersion());

        // Update app - session 2
        try {
            accessControlBaseServiceFacade.updateApp(getServiceContextAdministrador(), appDtoSession2);
            fail("Optimistic locking");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.OPTIMISTIC_LOCKING.getCode(), e.getExceptionItems().get(0).getCode());
            assertNull(e.getExceptionItems().get(0).getMessageParameters());
        }

        // Update app - session 1
        appDtoSession1AfterUpdate.setTitle("newTitle - secondUpdate");
        AppDto appDtoSession1AfterUpdate2 = accessControlBaseServiceFacade.updateApp(getServiceContextAdministrador(), appDtoSession1AfterUpdate);
        AccessControlDtoAsserts.assertEqualsAppDto(appDtoSession1AfterUpdate, appDtoSession1AfterUpdate2);
        assertEquals(Long.valueOf(3), appDtoSession1AfterUpdate2.getOptimisticLockingVersion());
    }

    @Override
    @Test
    public void testDeleteApp() throws Exception {
        Long id = APP_2;

        expectedMetamacException(new MetamacException(ServiceExceptionType.APP_NOT_FOUND, id));

        // Delete app
        accessControlBaseServiceFacade.deleteApp(getServiceContextAdministrador(), id);

        // Validation
        accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), id);
    }

    @Test
    public void testDeleteAppWithAccess() throws Exception {
        Long id = APP_1;

        expectedMetamacException(new MetamacException(ServiceExceptionType.APP_NOT_FOUND, id));

        // Delete app
        accessControlBaseServiceFacade.deleteApp(getServiceContextAdministrador(), id);

        // Validation
        accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), id);
    }

    @Test
    public void testDeleteAppNotExists() throws Exception {
        Long id = NOT_EXISTS;

        expectedMetamacException(new MetamacException(ServiceExceptionType.APP_NOT_FOUND, id));
        accessControlBaseServiceFacade.deleteApp(getServiceContextAdministrador(), id);
    }

    @Override
    @Test
    public void testFindAllApps() throws Exception {
        List<AppDto> apps = accessControlBaseServiceFacade.findAllApps(getServiceContextAdministrador());
        assertEquals(2, apps.size());
    }

    @Override
    @Test
    public void testFindAppById() throws Exception {
        Long id = APP_1;
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), id);
        assertNotNull(appDto);
        assertEquals(id, appDto.getId());
        assertEquals("gOPESTAT", appDto.getCode());
        assertEquals("A-1", appDto.getUuid());
        assertEquals("Gestor de Operaciones", appDto.getTitle());
        assertEquals("Gestor de operaciones estadísticas del ISTAC", appDto.getDescription());
        assertEquals(Long.valueOf(1), appDto.getVersion());
        assertEquals("user1", appDto.getCreatedBy());
        MetamacAsserts.assertEqualsDate("2010-07-22 12:47:22", appDto.getCreatedDate());
        assertEquals("user2", appDto.getLastUpdatedBy());
        MetamacAsserts.assertEqualsDate("2011-08-24 01:02:03", appDto.getLastUpdated());
    }

    @Test
    public void testFindAppByIdErrorParameterRequired() throws Exception {
        expectedMetamacException(new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, ServiceExceptionParameters.ID));
        accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), null);
    }

    /**************************************************************************
     * USERS
     **************************************************************************/

    @Override
    @Test
    public void testFindUserById() throws Exception {
        Long id = USER_1;
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), id);
        assertNotNull(userDto);
        assertEquals(id, userDto.getId());
        assertEquals("arte", userDto.getUsername());
        assertEquals("USER-1", userDto.getUuid());
        assertEquals("Pruebas", userDto.getName());
        assertEquals("Arte Consultores", userDto.getSurname());
        assertEquals("pruebas@arte-consultores.com", userDto.getMail());
        assertEquals(Long.valueOf(1), userDto.getVersion());
        assertEquals("user1", userDto.getCreatedBy());
        MetamacAsserts.assertEqualsDate("2010-07-22 12:47:22", userDto.getCreatedDate());
        assertEquals("user2", userDto.getLastUpdatedBy());
        MetamacAsserts.assertEqualsDate("2011-08-24 01:02:03", userDto.getLastUpdated());
    }

    @Test
    public void testFindUserByIdErrorParameterRequired() throws Exception {
        expectedMetamacException(new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, ServiceExceptionParameters.ID));
        accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), null);
    }

    @Test
    public void testRetrieveUserErrorNotExists() throws Exception {
        Long id = NOT_EXISTS;

        expectedMetamacException(new MetamacException(ServiceExceptionType.USER_NOT_FOUND, id));
        accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), id);
    }

    @Override
    @Test
    public void testCreateUser() throws Exception {
        UserDto userDto = AccessControlDtoMocks.mockUserDto();

        // Create
        UserDto userDtoCreated = accessControlBaseServiceFacade.createUser(getServiceContextAdministrador(), userDto);

        // Validate
        assertNotNull(userDtoCreated);
        assertNotNull(userDtoCreated.getId());
        assertNotNull(userDtoCreated.getUuid());
        assertNotNull(userDtoCreated.getVersion());

        AccessControlDtoAsserts.assertEqualsCreatedUserDto(userDto, userDtoCreated);

        // Audit validations
        assertNotNull(userDtoCreated.getCreatedBy());
        assertNotNull(userDtoCreated.getCreatedDate());
        assertNotNull(userDtoCreated.getLastUpdated());
        assertNotNull(userDtoCreated.getLastUpdatedBy());
        assertEquals(getServiceContextAdministrador().getUserId(), userDtoCreated.getCreatedBy());
        assertTrue(DateUtils.isSameDay(new Date(), userDtoCreated.getCreatedDate()));
        assertTrue(DateUtils.isSameDay(new Date(), userDtoCreated.getLastUpdated()));
        assertEquals(getServiceContextAdministrador().getUserId(), userDtoCreated.getLastUpdatedBy());

    }

    @Test
    public void testCreateUserUsernameRequired() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername(null);
        userDto.setName("Name");
        userDto.setSurname("surname");
        userDto.setMail("prueba@arte-consultores.com");

        expectedMetamacException(new MetamacException(ServiceExceptionType.METADATA_REQUIRED, ServiceExceptionParameters.USER_USERNAME));
        accessControlBaseServiceFacade.createUser(getServiceContextAdministrador(), userDto);
    }

    @Test
    public void testCreateUserNameRequired() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        userDto.setName(null);
        userDto.setSurname("surname");
        userDto.setMail("prueba@arte-consultores.com");

        expectedMetamacException(new MetamacException(ServiceExceptionType.METADATA_REQUIRED, ServiceExceptionParameters.USER_NAME));
        accessControlBaseServiceFacade.createUser(getServiceContextAdministrador(), userDto);
    }

    @Test
    public void testCreateUserSurnameRequired() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        userDto.setName("name");
        userDto.setSurname(null);
        userDto.setMail("prueba@arte-consultores.com");

        expectedMetamacException(new MetamacException(ServiceExceptionType.METADATA_REQUIRED, ServiceExceptionParameters.USER_SURNAME));
        accessControlBaseServiceFacade.createUser(getServiceContextAdministrador(), userDto);
    }

    @Test
    public void testCreateUserMailRequired() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        userDto.setName("name");
        userDto.setSurname("surname");
        userDto.setMail(null);

        expectedMetamacException(new MetamacException(ServiceExceptionType.METADATA_REQUIRED, ServiceExceptionParameters.USER_MAIL));
        accessControlBaseServiceFacade.createUser(getServiceContextAdministrador(), userDto);
    }

    @Test
    public void testCreateUserCodeDuplicated() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("arte");
        userDto.setName("name");
        userDto.setSurname("surname");
        userDto.setMail("prueba@arte-consultores.com");

        expectedMetamacException(new MetamacException(ServiceExceptionType.USER_ALREADY_EXIST_CODE_DUPLICATED, userDto.getUsername()));
        accessControlBaseServiceFacade.createUser(getServiceContextAdministrador(), userDto);
    }

    @Test
    public void testCreateUserCodeDuplicatedInsensitive() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("ARTe");
        userDto.setName("name");
        userDto.setSurname("surname");
        userDto.setMail("prueba@arte-consultores.com");

        expectedMetamacException(new MetamacException(ServiceExceptionType.USER_ALREADY_EXIST_CODE_DUPLICATED, userDto.getUsername().toLowerCase()));
        accessControlBaseServiceFacade.createUser(getServiceContextAdministrador(), userDto);
    }

    @Override
    @Test
    public void testDeleteUser() throws Exception {
        Long id = USER_2;
        expectedMetamacException(new MetamacException(ServiceExceptionType.USER_NOT_FOUND, id));

        // Delete user
        accessControlBaseServiceFacade.deleteUser(getServiceContextAdministrador(), id);

        // Validation
        accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), id);
    }

    @Test
    public void testDeleteUserWithAccess() throws Exception {
        Long id = USER_1;
        expectedMetamacException(new MetamacException(ServiceExceptionType.USER_NOT_FOUND, id));

        // Delete user
        accessControlBaseServiceFacade.deleteUser(getServiceContextAdministrador(), id);

        // Validation
        accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), id);
    }

    @Test
    public void testDeleteUserNotExists() throws Exception {
        Long id = NOT_EXISTS;

        expectedMetamacException(new MetamacException(ServiceExceptionType.USER_NOT_FOUND, id));
        accessControlBaseServiceFacade.deleteUser(getServiceContextAdministrador(), id);
    }

    @Override
    @Test
    public void testUpdateUser() throws Exception {
        Long id = USER_1;

        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), id);

        userDto.setUsername("newUsername");
        userDto.setName("newName");
        userDto.setSurname("newSurname");
        userDto.setMail("newmail@arte-consultores.com");

        // Update
        UserDto userDtoUpdated = accessControlBaseServiceFacade.updateUser(getServiceContextAdministrador(), userDto);

        // Validations
        AccessControlDtoAsserts.assertEqualsUserDto(userDto, userDtoUpdated);
        assertTrue(userDtoUpdated.getLastUpdated().after(userDtoUpdated.getCreatedDate()));
    }

    @Test
    public void testUpdateUserNotExists() throws Exception {
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), USER_1);
        userDto.setId(NOT_EXISTS);

        expectedMetamacException(new MetamacException(ServiceExceptionType.USER_NOT_FOUND, userDto.getId()));
        accessControlBaseServiceFacade.updateUser(getServiceContextAdministrador(), userDto);
    }

    @Test
    public void testUpdateUserDuplicatedUsername() throws Exception {
        Long id = USER_1;
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), id);
        userDto.setUsername("prueba2");

        expectedMetamacException(new MetamacException(ServiceExceptionType.USER_ALREADY_EXIST_CODE_DUPLICATED, userDto.getUsername()));
        accessControlBaseServiceFacade.updateUser(getServiceContextAdministrador(), userDto);
    }

    @Test
    public void testUpdateUserOptimisticLockingError() throws Exception {
        Long id = APP_1;

        // Retrieve user - session 1
        UserDto userDtoSession1 = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), id);
        assertEquals(Long.valueOf(1), userDtoSession1.getOptimisticLockingVersion());
        userDtoSession1.setName("newName");

        // Retrieve user - session 2
        UserDto userDtoSession2 = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), id);
        assertEquals(Long.valueOf(1), userDtoSession1.getOptimisticLockingVersion());
        userDtoSession2.setName("newName2");

        // Update user - session 1
        UserDto userDtoSession1AfterUpdate = accessControlBaseServiceFacade.updateUser(getServiceContextAdministrador(), userDtoSession1);
        AccessControlDtoAsserts.assertEqualsUserDto(userDtoSession1, userDtoSession1AfterUpdate);
        assertEquals(Long.valueOf(2), userDtoSession1AfterUpdate.getOptimisticLockingVersion());

        // Update user - session 2
        try {
            accessControlBaseServiceFacade.updateUser(getServiceContextAdministrador(), userDtoSession2);
            fail("Optimistic locking");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.OPTIMISTIC_LOCKING.getCode(), e.getExceptionItems().get(0).getCode());
            assertNull(e.getExceptionItems().get(0).getMessageParameters());
        }

        // Update user - session 1
        userDtoSession1AfterUpdate.setName("newName - secondUpdate");
        UserDto userDtoSession1AfterUpdate2 = accessControlBaseServiceFacade.updateUser(getServiceContextAdministrador(), userDtoSession1AfterUpdate);
        AccessControlDtoAsserts.assertEqualsUserDto(userDtoSession1AfterUpdate, userDtoSession1AfterUpdate2);
        assertEquals(Long.valueOf(3), userDtoSession1AfterUpdate2.getOptimisticLockingVersion());
    }

    @Override
    @Test
    public void testFindAllUsers() throws Exception {
        List<UserDto> users = accessControlBaseServiceFacade.findAllUsers(getServiceContextAdministrador());
        assertEquals(2, users.size());
    }

    /**************************************************************************
     * ACCESS
     **************************************************************************/

    @Override
    @Test
    public void testCreateAccess() throws Exception {

        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), ROLE_1);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), USER_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(appDto);
        accessDto.setUser(userDto);
        accessDto.setOperation(new ExternalItemDto("TODO-02", "apis.metamac.org/statistical-operations-internal/OPERATION:TODO:02", "OPERATION-TODO-02", null,
                TypeExternalArtefactsEnum.STATISTICAL_OPERATION));

        // Create
        AccessDto accessDtoCreated = accessControlBaseServiceFacade.createAccess(getServiceContextAdministrador(), accessDto);

        // Validate
        assertNotNull(accessDtoCreated);
        assertNotNull(accessDtoCreated.getId());
        assertNotNull(accessDtoCreated.getUuid());
        assertNotNull(accessDtoCreated.getVersion());

        AccessControlDtoAsserts.assertEqualsCreatedAccessDto(accessDto, accessDtoCreated);

        // Audit validations
        assertNotNull(accessDtoCreated.getCreatedBy());
        assertNotNull(accessDtoCreated.getCreatedDate());
        assertNotNull(accessDtoCreated.getLastUpdated());
        assertNotNull(accessDtoCreated.getLastUpdatedBy());
        assertEquals(getServiceContextAdministrador().getUserId(), accessDtoCreated.getCreatedBy());
        assertTrue(DateUtils.isSameDay(new Date(), accessDtoCreated.getCreatedDate()));
        assertTrue(DateUtils.isSameDay(new Date(), accessDtoCreated.getLastUpdated()));
        assertEquals(getServiceContextAdministrador().getUserId(), accessDtoCreated.getLastUpdatedBy());

    }

    @Test
    public void testCreateAccessWithoutOperation() throws Exception {

        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), ROLE_2);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), USER_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(appDto);
        accessDto.setUser(userDto);

        // Create
        AccessDto accessDtoCreated = accessControlBaseServiceFacade.createAccess(getServiceContextAdministrador(), accessDto);

        // Validate
        assertNotNull(accessDtoCreated);
        assertNotNull(accessDtoCreated.getId());
        assertNotNull(accessDtoCreated.getUuid());
        assertNotNull(accessDtoCreated.getVersion());

        AccessControlDtoAsserts.assertEqualsCreatedAccessDto(accessDto, accessDtoCreated);

        // Audit validations
        assertNotNull(accessDtoCreated.getCreatedBy());
        assertNotNull(accessDtoCreated.getCreatedDate());
        assertNotNull(accessDtoCreated.getLastUpdated());
        assertNotNull(accessDtoCreated.getLastUpdatedBy());
        assertEquals(getServiceContextAdministrador().getUserId(), accessDtoCreated.getCreatedBy());
        assertTrue(DateUtils.isSameDay(new Date(), accessDtoCreated.getCreatedDate()));
        assertTrue(DateUtils.isSameDay(new Date(), accessDtoCreated.getLastUpdated()));
        assertEquals(getServiceContextAdministrador().getUserId(), accessDtoCreated.getLastUpdatedBy());

    }

    @Test
    public void testCreateDuplicatedAccessWithoutOperation() throws Exception {
        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), ROLE_1);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), USER_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(appDto);
        accessDto.setUser(userDto);

        expectedMetamacException(new MetamacException(ServiceExceptionType.ACCESS_ALREADY_EXIST_WITHOUT_OPERATION, accessDto.getRole().getCode(), accessDto.getApp().getCode(), accessDto.getUser()
                .getUsername()));
        accessControlBaseServiceFacade.createAccess(getServiceContextAdministrador(), accessDto);
    }

    @Test
    public void testCreateAccessRoleRequired() throws Exception {
        // Retrieve related entities
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), USER_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(null);
        accessDto.setApp(appDto);
        accessDto.setUser(userDto);

        expectedMetamacException(new MetamacException(ServiceExceptionType.METADATA_REQUIRED, ServiceExceptionParameters.ACCESS_ROLE));
        accessControlBaseServiceFacade.createAccess(getServiceContextAdministrador(), accessDto);
    }

    @Test
    public void testCreateAccessAppRequired() throws Exception {
        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), ROLE_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), USER_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(null);
        accessDto.setUser(userDto);

        expectedMetamacException(new MetamacException(ServiceExceptionType.METADATA_REQUIRED, ServiceExceptionParameters.ACCESS_APP));
        accessControlBaseServiceFacade.createAccess(getServiceContextAdministrador(), accessDto);
    }

    @Test
    public void testCreateAccessUserRequired() throws Exception {
        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), ROLE_1);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), APP_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(appDto);
        accessDto.setUser(null);

        expectedMetamacException(new MetamacException(ServiceExceptionType.METADATA_REQUIRED, ServiceExceptionParameters.ACCESS_USER));
        accessControlBaseServiceFacade.createAccess(getServiceContextAdministrador(), accessDto);
    }

    @Test
    public void testCreateDuplicatedAccess() throws Exception {

        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), ROLE_1);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), USER_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(appDto);
        accessDto.setUser(userDto);
        accessDto.setOperation(new ExternalItemDto("TODO-01", "OPERATION:TODO:01", "OPERATION-TODO-01", null, TypeExternalArtefactsEnum.STATISTICAL_OPERATION));

        expectedMetamacException(new MetamacException(ServiceExceptionType.ACCESS_ALREADY_EXIST_WITH_OPERATION, accessDto.getRole().getCode(), accessDto.getApp().getCode(), accessDto.getUser()
                .getUsername(), accessDto.getOperation().getUrn()));
        accessControlBaseServiceFacade.createAccess(getServiceContextAdministrador(), accessDto);
    }

    @Override
    @Test
    public void testRemoveAccess() throws Exception {
        Long id = ACCESS_1;

        // Access
        AccessDto access = accessControlBaseServiceFacade.findAccessById(getServiceContextAdministrador(), id);

        // Remove access
        accessControlBaseServiceFacade.removeAccess(getServiceContextAdministrador(), id);

        // Retrieve removed access
        AccessDto removedAccess = accessControlBaseServiceFacade.findAccessById(getServiceContextAdministrador(), id);

        // Validations
        assertNotNull(removedAccess.getRemovalDate());
        AccessControlDtoAsserts.assertEqualsAccessDto(access, removedAccess);
    }

    @Test
    public void testRemoveAccessNotExists() throws Exception {
        Long id = NOT_EXISTS;

        expectedMetamacException(new MetamacException(ServiceExceptionType.ACCESS_NOT_FOUND, id));
        accessControlBaseServiceFacade.removeAccess(getServiceContextAdministrador(), id);
    }

    @Override
    @Test
    public void testFindAllAccess() throws Exception {
        List<AccessDto> access = accessControlBaseServiceFacade.findAllAccess(getServiceContextAdministrador());
        assertEquals(3, access.size());
    }

    @Override
    @Test
    public void testFindAccessByCondition() throws Exception {
        // All conditions
        String roleCode = "aDMINISTRADOR";
        String appCode = "gOPESTAT";
        String username = "arte";
        String operationCodeId = "OPERATION-TODO-01";
        List<AccessDto> access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, true);
        assertEquals(1, access.size());

        roleCode = "aDMINISTRADOR";
        appCode = "gOPESTAT";
        username = "arte";
        operationCodeId = "OPERATION-TODO-01";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, false);
        assertEquals(1, access.size());

        roleCode = "aDMINISTRADOR";
        appCode = "gOPESTAT";
        username = "arte";
        operationCodeId = "OPERATION-TODO-01";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, null);
        assertEquals(2, access.size());

        // Without operation condition
        roleCode = "aDMINISTRADOR";
        appCode = "gOPESTAT";
        username = "arte";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, true);
        assertEquals(1, access.size());

        roleCode = "aDMINISTRADOR";
        appCode = "gOPESTAT";
        username = "arte";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, false);
        assertEquals(2, access.size());

        // Role condition
        roleCode = "TEC_PLANI";
        appCode = "";
        username = "";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, true);
        assertEquals(0, access.size());

        roleCode = "TEC_PLANI";
        appCode = "";
        username = "";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, false);
        assertEquals(0, access.size());

        // Role condition
        roleCode = "ADMINISTRADOR";
        appCode = "";
        username = "";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, true);
        assertEquals(1, access.size());

        roleCode = "ADMINISTRADOR";
        appCode = "";
        username = "";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, false);
        assertEquals(2, access.size());

        // Without conditions
        roleCode = "";
        appCode = "";
        username = "";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, true);
        assertEquals(1, access.size());

        roleCode = "";
        appCode = "";
        username = "";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, false);
        assertEquals(2, access.size());
    }

    @Override
    @Test
    public void testFindAccessById() throws Exception {
        Long id = APP_1;

        // Retrieve
        AccessDto accessDto = accessControlBaseServiceFacade.findAccessById(getServiceContextAdministrador(), id);

        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), ROLE_1);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), USER_1);

        // Validations
        assertNotNull(accessDto);
        assertEquals(id, accessDto.getId());
        AccessControlDtoAsserts.assertEqualsRoleDto(roleDto, accessDto.getRole());
        AccessControlDtoAsserts.assertEqualsAppDto(appDto, accessDto.getApp());
        AccessControlDtoAsserts.assertEqualsUserDto(userDto, accessDto.getUser());
        MetamacAsserts.assertEqualsExternalItemDto(new ExternalItemDto("TODO-01", "apis.metamac.org/statistical-operations-internal/OPERATION:TODO:01", "OPERATION-TODO-01",
                null, TypeExternalArtefactsEnum.STATISTICAL_OPERATION, null, "http://localhost:8080/metamac-statistical-operations-web/OPERATION:TODO:01"), accessDto
                .getOperation());

        assertEquals("A-1", accessDto.getUuid());
        assertEquals(Long.valueOf(1), accessDto.getVersion());
        assertEquals("user1", accessDto.getCreatedBy());
        MetamacAsserts.assertEqualsDate("2010-07-22 12:47:22", accessDto.getCreatedDate());
        assertEquals("user2", accessDto.getLastUpdatedBy());
        MetamacAsserts.assertEqualsDate("2011-08-24 01:02:03", accessDto.getLastUpdated());
    }

    @Test
    public void testFindAccessByIdErrorParameterRequired() throws Exception {
        Long id = null;

        expectedMetamacException(new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, ServiceExceptionParameters.ID));
        accessControlBaseServiceFacade.findAccessById(getServiceContextAdministrador(), id);
    }
}