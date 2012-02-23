package org.siemac.metamac.access.control.base.serviceapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.siemac.metamac.access.control.base.serviceapi.utils.AccessControlDtoAsserts;
import org.siemac.metamac.access.control.base.serviceapi.utils.AccessControlDtoMocks;
import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.dto.serviceapi.AppDto;
import org.siemac.metamac.access.control.dto.serviceapi.RoleDto;
import org.siemac.metamac.access.control.dto.serviceapi.UserDto;
import org.siemac.metamac.access.control.error.ServiceExceptionType;
import org.siemac.metamac.common.test.MetamacBaseTests;
import org.siemac.metamac.common.test.utils.MetamacAsserts;
import org.siemac.metamac.common.test.utils.MetamacDtoAsserts;
import org.siemac.metamac.core.common.dto.serviceapi.ExternalItemBtDto;
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
public class AccessControlBaseServiceFacadeTest extends MetamacBaseTests implements AccessControlBaseServiceFacadeTestBase {

    @Autowired
    protected AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    private final ServiceContext             serviceContext = new ServiceContext("system", "123456", "junit");

    private static Long                      NOT_EXISTS     = Long.valueOf(-1);

    private static Long                      ROLE_1         = Long.valueOf(1);
    private static Long                      ROLE_2         = Long.valueOf(2);

    private static Long                      APP_1          = Long.valueOf(1);
    private static Long                      APP_2          = Long.valueOf(2);

    private static Long                      USER_1         = Long.valueOf(1);
    private static Long                      USER_2         = Long.valueOf(2);

    private static Long                      ACCESS_1       = Long.valueOf(1);
    private static Long                      ACCESS_2       = Long.valueOf(2);
    private static Long                      ACCESS_3       = Long.valueOf(3);

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
        Long id = ROLE_1;
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), id);
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
        Long id = null;

        try {
            accessControlBaseServiceFacade.findRoleById(getServiceContext(), id);
            fail("parameter required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.PARAMETER_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("ID", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testRetrieveRoleErrorNotExists() throws Exception {

        Long id = NOT_EXISTS;

        try {
            accessControlBaseServiceFacade.findRoleById(getServiceContext(), id);
            fail("Not exists");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.ROLE_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateRole() throws Exception {
        RoleDto roleDto = AccessControlDtoMocks.mockRoleDto();

        // Create
        RoleDto roleDtoCreated = accessControlBaseServiceFacade.createRole(getServiceContext(), roleDto);

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
        assertEquals(getServiceContext().getUserId(), roleDtoCreated.getCreatedBy());
        assertTrue(DateUtils.isSameDay(new Date(), roleDtoCreated.getCreatedDate()));
        assertTrue(DateUtils.isSameDay(new Date(), roleDtoCreated.getLastUpdated()));
        assertEquals(getServiceContext().getUserId(), roleDtoCreated.getLastUpdatedBy());

    }

    @Test
    public void testCreateRoleCodeRequired() throws Exception {

        RoleDto roleDto = new RoleDto();
        roleDto.setCode(null);
        roleDto.setTitle("Title");

        try {
            accessControlBaseServiceFacade.createRole(getServiceContext(), roleDto);
            fail("code required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.METADATA_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("ROLE.CODE", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateRoleTitleRequired() throws Exception {

        RoleDto roleDto = new RoleDto();
        roleDto.setCode("CODE");
        roleDto.setTitle(null);

        try {
            accessControlBaseServiceFacade.createRole(getServiceContext(), roleDto);
            fail("title required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.METADATA_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("ROLE.TITLE", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateRoleCodeDuplicated() throws Exception {

        RoleDto roleDto = new RoleDto();
        roleDto.setCode("aDMINISTRADOR");
        roleDto.setTitle("Title");

        try {
            accessControlBaseServiceFacade.createRole(getServiceContext(), roleDto);
            fail("code duplicated");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.ROLE_ALREADY_EXIST_CODE_DUPLICATED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(roleDto.getCode(), e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateRoleCodeDuplicatedInsensitive() throws Exception {

        RoleDto roleDto = new RoleDto();
        roleDto.setCode("AdMiNISTrADOR");
        roleDto.setTitle("Title");

        try {
            accessControlBaseServiceFacade.createRole(getServiceContext(), roleDto);
            fail("code duplicated");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.ROLE_ALREADY_EXIST_CODE_DUPLICATED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(roleDto.getCode(), e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testDeleteRole() throws Exception {
        Long id = ROLE_2;

        // Delete role
        accessControlBaseServiceFacade.deleteRole(getServiceContext(), id);

        // Validation
        try {
            accessControlBaseServiceFacade.findRoleById(serviceContext, id);
            fail("role deleted");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.ROLE_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
        }

    }

    @Test
    public void testDeleteRoleWithAccess() throws Exception {
        Long id = ROLE_1;

        // Delete role
        accessControlBaseServiceFacade.deleteRole(getServiceContext(), id);

        // Validation
        try {
            accessControlBaseServiceFacade.findRoleById(serviceContext, id);
            fail("role deleted");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.ROLE_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
            assertEquals(0, accessControlBaseServiceFacade.findAllAccess(getServiceContext()).size());
        }

    }

    @Test
    public void testDeleteRoleNotExists() throws Exception {
        Long id = NOT_EXISTS;

        // Delete role
        try {
            accessControlBaseServiceFacade.deleteRole(getServiceContext(), id);
            fail("role not exists");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.ROLE_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
        }

    }

    @Test
    public void testUpdateRole() throws Exception {
        Long id = ROLE_1;

        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), id);

        roleDto.setCode("newCode");
        roleDto.setTitle("newTitle");
        roleDto.setDescription("newDescription");

        // Update
        RoleDto roleDtoUpdated = accessControlBaseServiceFacade.updateRole(getServiceContext(), roleDto);

        // Validations
        AccessControlDtoAsserts.assertEqualsRoleDto(roleDto, roleDtoUpdated);
        assertTrue(roleDtoUpdated.getLastUpdated().after(roleDtoUpdated.getCreatedDate()));
    }

    @Test
    public void testUpdateRoleNotExists() throws Exception {
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), ROLE_1);

        roleDto.setId(NOT_EXISTS);

        try {
            accessControlBaseServiceFacade.updateRole(getServiceContext(), roleDto);
            fail("role not exists");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.ROLE_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(NOT_EXISTS, e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testUpdateRoleDuplicatedCode() throws Exception {
        Long id = ROLE_1;

        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), id);

        roleDto.setCode("TEC_PLANI");

        try {
            accessControlBaseServiceFacade.updateRole(getServiceContext(), roleDto);
            fail("code duplicated");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.ROLE_ALREADY_EXIST_CODE_DUPLICATED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("TEC_PLANI", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testFindAllRoles() throws Exception {
        List<RoleDto> roles = accessControlBaseServiceFacade.findAllRoles(getServiceContext());
        assertEquals(2, roles.size());
    }

    /**************************************************************************
     * APPS
     **************************************************************************/

    @Test
    public void testCreateApp() throws Exception {
        AppDto appDto = AccessControlDtoMocks.mockAppDto();

        // Create
        AppDto appDtoCreated = accessControlBaseServiceFacade.createApp(getServiceContext(), appDto);

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
        assertEquals(getServiceContext().getUserId(), appDtoCreated.getCreatedBy());
        assertTrue(DateUtils.isSameDay(new Date(), appDtoCreated.getCreatedDate()));
        assertTrue(DateUtils.isSameDay(new Date(), appDtoCreated.getLastUpdated()));
        assertEquals(getServiceContext().getUserId(), appDtoCreated.getLastUpdatedBy());

    }

    @Test
    public void testCreateAppCodeRequired() throws Exception {

        AppDto appDto = new AppDto();
        appDto.setCode(null);
        appDto.setTitle("Title");

        try {
            accessControlBaseServiceFacade.createApp(getServiceContext(), appDto);
            fail("code required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.METADATA_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("APP.CODE", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateAppTitleRequired() throws Exception {

        AppDto appDto = new AppDto();
        appDto.setCode("CODE");
        appDto.setTitle(null);

        try {
            accessControlBaseServiceFacade.createApp(getServiceContext(), appDto);
            fail("title required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.METADATA_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("APP.TITLE", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateAppCodeDuplicated() throws Exception {

        AppDto appDto = new AppDto();
        appDto.setCode("gOPESTAT");
        appDto.setTitle("Title");

        try {
            accessControlBaseServiceFacade.createApp(getServiceContext(), appDto);
            fail("code duplicated");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.APP_ALREADY_EXIST_CODE_DUPLICATED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(appDto.getCode(), e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateAppCodeDuplicatedInsensitive() throws Exception {

        AppDto appDto = new AppDto();
        appDto.setCode("GoPeStat");
        appDto.setTitle("Title");

        try {
            accessControlBaseServiceFacade.createApp(getServiceContext(), appDto);
            fail("code duplicated");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.APP_ALREADY_EXIST_CODE_DUPLICATED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(appDto.getCode(), e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testUpdateApp() throws Exception {
        Long id = APP_1;

        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), id);

        appDto.setCode("newCode");
        appDto.setTitle("newTitle");
        appDto.setDescription("newDescription");

        // Update
        AppDto appDtoUpdated = accessControlBaseServiceFacade.updateApp(getServiceContext(), appDto);

        // Validations
        AccessControlDtoAsserts.assertEqualsAppDto(appDto, appDtoUpdated);
        assertTrue(appDtoUpdated.getLastUpdated().after(appDtoUpdated.getCreatedDate()));
    }

    @Test
    public void testUpdateAppNotExists() throws Exception {
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), APP_1);

        appDto.setId(NOT_EXISTS);

        try {
            accessControlBaseServiceFacade.updateApp(getServiceContext(), appDto);
            fail("app not exists");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.APP_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(NOT_EXISTS, e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testUpdateAppDuplicatedCode() throws Exception {
        Long id = APP_1;

        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), id);

        appDto.setCode("gPE");

        try {
            accessControlBaseServiceFacade.updateApp(getServiceContext(), appDto);
            fail("code duplicated");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.APP_ALREADY_EXIST_CODE_DUPLICATED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("gPE", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testDeleteApp() throws Exception {
        Long id = APP_2;

        // Delete app
        accessControlBaseServiceFacade.deleteApp(getServiceContext(), id);

        // Validation
        try {
            accessControlBaseServiceFacade.findAppById(serviceContext, id);
            fail("app deleted");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.APP_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
        }

    }

    @Test
    public void testDeleteAppWithAccess() throws Exception {
        Long id = APP_1;

        // Delete app
        accessControlBaseServiceFacade.deleteApp(getServiceContext(), id);

        // Validation
        try {
            accessControlBaseServiceFacade.findAppById(serviceContext, id);
            fail("app deleted");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.APP_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
            assertEquals(0, accessControlBaseServiceFacade.findAllAccess(getServiceContext()).size());
        }

    }

    @Test
    public void testDeleteAppNotExists() throws Exception {
        Long id = NOT_EXISTS;

        // Delete app
        try {
            accessControlBaseServiceFacade.deleteApp(getServiceContext(), id);
            fail("app not exists");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.APP_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
        }

    }

    @Test
    public void testFindAllApps() throws Exception {
        List<AppDto> apps = accessControlBaseServiceFacade.findAllApps(getServiceContext());
        assertEquals(2, apps.size());
    }

    @Test
    public void testFindAppById() throws Exception {
        Long id = APP_1;
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), id);
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
        Long id = null;

        try {
            accessControlBaseServiceFacade.findAppById(getServiceContext(), id);
            fail("parameter required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.PARAMETER_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("ID", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    /**************************************************************************
     * USERS
     **************************************************************************/

    @Test
    public void testFindUserById() throws Exception {
        Long id = USER_1;
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), id);
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
        Long id = null;

        try {
            accessControlBaseServiceFacade.findUserById(getServiceContext(), id);
            fail("parameter required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.PARAMETER_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("ID", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testRetrieveUserErrorNotExists() throws Exception {

        Long id = NOT_EXISTS;

        try {
            accessControlBaseServiceFacade.findUserById(getServiceContext(), id);
            fail("Not exists");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.USER_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateUser() throws Exception {
        UserDto userDto = AccessControlDtoMocks.mockUserDto();

        // Create
        UserDto userDtoCreated = accessControlBaseServiceFacade.createUser(getServiceContext(), userDto);

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
        assertEquals(getServiceContext().getUserId(), userDtoCreated.getCreatedBy());
        assertTrue(DateUtils.isSameDay(new Date(), userDtoCreated.getCreatedDate()));
        assertTrue(DateUtils.isSameDay(new Date(), userDtoCreated.getLastUpdated()));
        assertEquals(getServiceContext().getUserId(), userDtoCreated.getLastUpdatedBy());

    }

    @Test
    public void testCreateUserUsernameRequired() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setUsername(null);
        userDto.setName("Name");
        userDto.setSurname("surname");
        userDto.setMail("prueba@arte-consultores.com");

        try {
            accessControlBaseServiceFacade.createUser(getServiceContext(), userDto);
            fail("username required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.METADATA_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("USER.USERNAME", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateUserNameRequired() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        userDto.setName(null);
        userDto.setSurname("surname");
        userDto.setMail("prueba@arte-consultores.com");

        try {
            accessControlBaseServiceFacade.createUser(getServiceContext(), userDto);
            fail("name required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.METADATA_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("USER.NAME", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateUserSurnameRequired() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        userDto.setName("name");
        userDto.setSurname(null);
        userDto.setMail("prueba@arte-consultores.com");

        try {
            accessControlBaseServiceFacade.createUser(getServiceContext(), userDto);
            fail("surname required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.METADATA_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("USER.SURNAME", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateUserMailRequired() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        userDto.setName("name");
        userDto.setSurname("surname");
        userDto.setMail(null);

        try {
            accessControlBaseServiceFacade.createUser(getServiceContext(), userDto);
            fail("mail required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.METADATA_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("USER.MAIL", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateUserCodeDuplicated() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setUsername("arte");
        userDto.setName("name");
        userDto.setSurname("surname");
        userDto.setMail("prueba@arte-consultores.com");

        try {
            accessControlBaseServiceFacade.createUser(getServiceContext(), userDto);
            fail("username duplicated");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.USER_ALREADY_EXIST_CODE_DUPLICATED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(userDto.getUsername(), e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateUserCodeDuplicatedInsensitive() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setUsername("ARTe");
        userDto.setName("name");
        userDto.setSurname("surname");
        userDto.setMail("prueba@arte-consultores.com");

        try {
            accessControlBaseServiceFacade.createUser(getServiceContext(), userDto);
            fail("code duplicated");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.USER_ALREADY_EXIST_CODE_DUPLICATED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(userDto.getUsername(), e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long id = USER_2;

        // Delete user
        accessControlBaseServiceFacade.deleteUser(getServiceContext(), id);

        // Validation
        try {
            accessControlBaseServiceFacade.findUserById(serviceContext, id);
            fail("user deleted");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.USER_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
        }

    }

    @Test
    public void testDeleteUserWithAccess() throws Exception {
        Long id = USER_1;

        // Delete user
        accessControlBaseServiceFacade.deleteUser(getServiceContext(), id);

        // Validation
        try {
            accessControlBaseServiceFacade.findUserById(serviceContext, id);
            fail("user deleted");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.USER_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
            assertEquals(0, accessControlBaseServiceFacade.findAllAccess(getServiceContext()).size());
        }

    }

    @Test
    public void testDeleteUserNotExists() throws Exception {
        Long id = NOT_EXISTS;

        // Delete user
        try {
            accessControlBaseServiceFacade.deleteUser(getServiceContext(), id);
            fail("user not exists");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.USER_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
        }

    }

    @Test
    public void testUpdateUser() throws Exception {
        Long id = USER_1;

        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), id);

        userDto.setUsername("newUsername");
        userDto.setName("newName");
        userDto.setSurname("newSurname");
        userDto.setMail("newmail@arte-consultores.com");

        // Update
        UserDto userDtoUpdated = accessControlBaseServiceFacade.updateUser(getServiceContext(), userDto);

        // Validations
        AccessControlDtoAsserts.assertEqualsUserDto(userDto, userDtoUpdated);
        assertTrue(userDtoUpdated.getLastUpdated().after(userDtoUpdated.getCreatedDate()));
    }

    @Test
    public void testUpdateUserNotExists() throws Exception {
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), USER_1);

        userDto.setId(NOT_EXISTS);

        try {
            accessControlBaseServiceFacade.updateUser(getServiceContext(), userDto);
            fail("user not exists");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.USER_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(NOT_EXISTS, e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testUpdateUserDuplicatedUsername() throws Exception {
        Long id = USER_1;

        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), id);

        userDto.setUsername("prueba2");

        try {
            accessControlBaseServiceFacade.updateUser(getServiceContext(), userDto);
            fail("username duplicated");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.USER_ALREADY_EXIST_CODE_DUPLICATED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("prueba2", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testFindAllUsers() throws Exception {
        List<UserDto> users = accessControlBaseServiceFacade.findAllUsers(getServiceContext());
        assertEquals(2, users.size());
    }

    /**************************************************************************
     * ACCESS
     **************************************************************************/

    @Test
    public void testCreateAccess() throws Exception {

        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), ROLE_1);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), USER_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(appDto);
        accessDto.setUser(userDto);
        accessDto.setOperation(new ExternalItemBtDto("OPERATION:TODO:02", "OPERATION-TODO-02", TypeExternalArtefactsEnum.STATISTICAL_OPERATION));

        // Create
        AccessDto accessDtoCreated = accessControlBaseServiceFacade.createAccess(getServiceContext(), accessDto);

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
        assertEquals(getServiceContext().getUserId(), accessDtoCreated.getCreatedBy());
        assertTrue(DateUtils.isSameDay(new Date(), accessDtoCreated.getCreatedDate()));
        assertTrue(DateUtils.isSameDay(new Date(), accessDtoCreated.getLastUpdated()));
        assertEquals(getServiceContext().getUserId(), accessDtoCreated.getLastUpdatedBy());

    }

    @Test
    public void testCreateAccessWithoutOperation() throws Exception {

        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), ROLE_2);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), USER_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(appDto);
        accessDto.setUser(userDto);

        // Create
        AccessDto accessDtoCreated = accessControlBaseServiceFacade.createAccess(getServiceContext(), accessDto);

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
        assertEquals(getServiceContext().getUserId(), accessDtoCreated.getCreatedBy());
        assertTrue(DateUtils.isSameDay(new Date(), accessDtoCreated.getCreatedDate()));
        assertTrue(DateUtils.isSameDay(new Date(), accessDtoCreated.getLastUpdated()));
        assertEquals(getServiceContext().getUserId(), accessDtoCreated.getLastUpdatedBy());

    }

    @Test
    public void testCreateDuplicatedAccessWithoutOperation() throws Exception {

        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), ROLE_1);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), USER_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(appDto);
        accessDto.setUser(userDto);

        try {
            accessControlBaseServiceFacade.createAccess(getServiceContext(), accessDto);
            fail("duplicated access");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.ACCESS_ALREADY_EXIST_CODE_DUPLICATED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(4, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("aDMINISTRADOR", e.getExceptionItems().get(0).getMessageParameters()[0]);
            assertEquals("gOPESTAT", e.getExceptionItems().get(0).getMessageParameters()[1]);
            assertEquals("arte", e.getExceptionItems().get(0).getMessageParameters()[2]);
        }
    }

    @Test
    public void testCreateAccessRoleRequired() throws Exception {

        // Retrieve related entities
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), USER_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(null);
        accessDto.setApp(appDto);
        accessDto.setUser(userDto);

        try {
            accessControlBaseServiceFacade.createAccess(getServiceContext(), accessDto);
            fail("role required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.METADATA_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("ACCESS.ROLE", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateAccessAppRequired() throws Exception {

        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), ROLE_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), USER_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(null);
        accessDto.setUser(userDto);

        try {
            accessControlBaseServiceFacade.createAccess(getServiceContext(), accessDto);
            fail("app required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.METADATA_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("ACCESS.APP", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateAccessUserRequired() throws Exception {

        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), ROLE_1);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), APP_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(appDto);
        accessDto.setUser(null);

        try {
            accessControlBaseServiceFacade.createAccess(getServiceContext(), accessDto);
            fail("user required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.METADATA_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("ACCESS.USER", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
    }

    @Test
    public void testCreateDuplicatedAccess() throws Exception {

        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), ROLE_1);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), USER_1);

        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(appDto);
        accessDto.setUser(userDto);
        accessDto.setOperation(new ExternalItemBtDto("OPERATION:TODO:01", "OPERATION-TODO-01", TypeExternalArtefactsEnum.STATISTICAL_OPERATION));

        try {
            accessControlBaseServiceFacade.createAccess(getServiceContext(), accessDto);
            fail("duplicated access");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.ACCESS_ALREADY_EXIST_CODE_DUPLICATED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(4, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("aDMINISTRADOR", e.getExceptionItems().get(0).getMessageParameters()[0]);
            assertEquals("gOPESTAT", e.getExceptionItems().get(0).getMessageParameters()[1]);
            assertEquals("arte", e.getExceptionItems().get(0).getMessageParameters()[2]);
            assertEquals("OPERATION-TODO-01", e.getExceptionItems().get(0).getMessageParameters()[3]);
        }
    }

//    @Test
//    public void testUpdateAccess() throws Exception {
//        Long id = ACCESS_1;
//
//        // Retrieve new related entities
//        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), ROLE_1);
//        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), APP_1);
//        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), USER_1);
//
//        AccessDto accessDto = accessControlBaseServiceFacade.findAccessById(getServiceContext(), id);
//
//        accessDto.setRole(roleDto);
//        accessDto.setApp(appDto);
//        accessDto.setUser(userDto);
//        accessDto.setOperation(new ExternalItemBtDto("OPERATION:TODO:02", "OPERATION-TODO-02", TypeExternalArtefactsEnum.STATISTICAL_OPERATION));
//
//        // Update
//        AccessDto accessDtoUpdated = accessControlBaseServiceFacade.updateAccess(getServiceContext(), accessDto);
//
//        // Validations
//        AccessControlDtoAsserts.assertEqualsAccessDto(accessDto, accessDtoUpdated);
//        assertTrue(accessDtoUpdated.getLastUpdated().after(accessDtoUpdated.getCreatedDate()));
//    }

//    @Test
//    public void testUpdateAccessNotExists() throws Exception {
//        AccessDto accessDto = accessControlBaseServiceFacade.findAccessById(getServiceContext(), ACCESS_1);
//
//        accessDto.setId(NOT_EXISTS);
//
//        try {
//            accessControlBaseServiceFacade.updateAccess(getServiceContext(), accessDto);
//            fail("access not exists");
//        } catch (MetamacException e) {
//            assertEquals(1, e.getExceptionItems().size());
//            assertEquals(ServiceExceptionType.ACCESS_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
//            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
//            assertEquals(NOT_EXISTS, e.getExceptionItems().get(0).getMessageParameters()[0]);
//        }
//    }
    
//    @Test
//    public void testUpdateAccessDischarged() throws Exception {
//        AccessDto accessDto = accessControlBaseServiceFacade.findAccessById(getServiceContext(), ACCESS_3);
//
//        accessDto.setOperation(new ExternalItemBtDto("OPERATION:TODO:05", "OPERATION-TODO-05", TypeExternalArtefactsEnum.STATISTICAL_OPERATION));
//        
//        try {
//            accessControlBaseServiceFacade.updateAccess(getServiceContext(), accessDto);
//            fail("access discharged");
//        } catch (MetamacException e) {
//            assertEquals(1, e.getExceptionItems().size());
//            assertEquals(ServiceExceptionType.ACCESS_DISCHARGED.getCode(), e.getExceptionItems().get(0).getCode());
//            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
//            assertEquals(ACCESS_3, e.getExceptionItems().get(0).getMessageParameters()[0]);
//        }
//    }

//    @Test
//    public void testDeleteAccess() throws Exception {
//        Long id = ACCESS_1;
//
//        // Delete access
//        accessControlBaseServiceFacade.deleteAccess(getServiceContext(), id);
//
//        // Validation
//        try {
//            accessControlBaseServiceFacade.findAccessById(serviceContext, id);
//            fail("access deleted");
//        } catch (MetamacException e) {
//            assertEquals(1, e.getExceptionItems().size());
//            assertEquals(ServiceExceptionType.ACCESS_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
//            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
//            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
//
//            // Validate related entities
//            RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), ROLE_1);
//            assertNotNull(roleDto);
//            AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), APP_1);
//            assertNotNull(appDto);
//            UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), USER_1);
//            assertNotNull(userDto);
//        }
//
//    }

//    @Test
//    public void testDeleteAccessNotExists() throws Exception {
//        Long id = NOT_EXISTS;
//
//        // Delete access
//        try {
//            accessControlBaseServiceFacade.deleteAccess(getServiceContext(), id);
//            fail("access not exists");
//        } catch (MetamacException e) {
//            assertEquals(1, e.getExceptionItems().size());
//            assertEquals(ServiceExceptionType.ACCESS_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
//            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
//            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
//        }
//    }
    
//    @Test
//    public void testDeleteAccessDischarged() throws Exception {
//        Long id = ACCESS_3;
//
//        // Delete access
//        try {
//            accessControlBaseServiceFacade.deleteAccess(getServiceContext(), id);
//            fail("access discharged");
//        } catch (MetamacException e) {
//            assertEquals(1, e.getExceptionItems().size());
//            assertEquals(ServiceExceptionType.ACCESS_DISCHARGED.getCode(), e.getExceptionItems().get(0).getCode());
//            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
//            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
//        }
//    }
    
    @Test
    public void testDischargeAccess() throws Exception {
        Long id = ACCESS_1;
        
        // Access
        AccessDto access = accessControlBaseServiceFacade.findAccessById(getServiceContext(), id);
        
        // Discharge access
        accessControlBaseServiceFacade.dischargeAccess(getServiceContext(), id);
        
        // Retrieve discharged access
        AccessDto dischargedAccess = accessControlBaseServiceFacade.findAccessById(getServiceContext(), id);
        
        // Validations
        assertNotNull(dischargedAccess.getDischargeDate());
        AccessControlDtoAsserts.assertEqualsAccessDto(access, dischargedAccess);
    }
    
    @Test
    public void testDischargeAccessNotExists() throws Exception {
        Long id = NOT_EXISTS;

        // Delete access
        try {
            accessControlBaseServiceFacade.dischargeAccess(getServiceContext(), id);
            fail("access not exists");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.ACCESS_NOT_FOUND.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals(id, e.getExceptionItems().get(0).getMessageParameters()[0]);
        }

    }

    @Test
    public void testFindAllAccess() throws Exception {
        List<AccessDto> access = accessControlBaseServiceFacade.findAllAccess(getServiceContext());
        assertEquals(3, access.size());
    }

    @Test
    public void testFindAccessByCondition() throws Exception {
        // All conditions
        String roleCode = "aDMINISTRADOR";
        String appCode = "gOPESTAT";
        String username = "arte";
        String operationCodeId = "OPERATION-TODO-01";
        List<AccessDto> access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContext(), roleCode, appCode, username, operationCodeId, true);
        assertEquals(2, access.size());
        
        roleCode = "aDMINISTRADOR";
        appCode = "gOPESTAT";
        username = "arte";
        operationCodeId = "OPERATION-TODO-01";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContext(), roleCode, appCode, username, operationCodeId, false);
        assertEquals(1, access.size());
        
        // Without operation condition
        roleCode = "aDMINISTRADOR";
        appCode = "gOPESTAT";
        username = "arte";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContext(), roleCode, appCode, username, operationCodeId, true);
        assertEquals(3, access.size());
        
        roleCode = "aDMINISTRADOR";
        appCode = "gOPESTAT";
        username = "arte";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContext(), roleCode, appCode, username, operationCodeId, false);
        assertEquals(2, access.size());

        // Role condition
        roleCode = "TEC_PLANI";
        appCode = "";
        username = "";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContext(), roleCode, appCode, username, operationCodeId, true);
        assertEquals(0, access.size());
        
        roleCode = "TEC_PLANI";
        appCode = "";
        username = "";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContext(), roleCode, appCode, username, operationCodeId, false);
        assertEquals(0, access.size());

        // Role condition
        roleCode = "ADMINISTRADOR";
        appCode = "";
        username = "";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContext(), roleCode, appCode, username, operationCodeId, true);
        assertEquals(3, access.size());
        
        roleCode = "ADMINISTRADOR";
        appCode = "";
        username = "";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContext(), roleCode, appCode, username, operationCodeId, false);
        assertEquals(2, access.size());

        // Without conditions
        roleCode = "";
        appCode = "";
        username = "";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContext(), roleCode, appCode, username, operationCodeId, true);
        assertEquals(3, access.size());
        
        roleCode = "";
        appCode = "";
        username = "";
        operationCodeId = "";
        access = accessControlBaseServiceFacade.findAccessByCondition(getServiceContext(), roleCode, appCode, username, operationCodeId, false);
        assertEquals(2, access.size());
    }

    @Test
    public void testFindAccessById() throws Exception {
        Long id = APP_1;

        // Retrieve
        AccessDto accessDto = accessControlBaseServiceFacade.findAccessById(getServiceContext(), id);

        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContext(), ROLE_1);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContext(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContext(), USER_1);

        // Validations
        assertNotNull(accessDto);
        assertEquals(id, accessDto.getId());
        AccessControlDtoAsserts.assertEqualsRoleDto(roleDto, accessDto.getRole());
        AccessControlDtoAsserts.assertEqualsAppDto(appDto, accessDto.getApp());
        AccessControlDtoAsserts.assertEqualsUserDto(userDto, accessDto.getUser());
        MetamacDtoAsserts.assertEqualsExternalItemBtDto(new ExternalItemBtDto("OPERATION:TODO:01", "OPERATION-TODO-01", TypeExternalArtefactsEnum.STATISTICAL_OPERATION), accessDto.getOperation());

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

        try {
            accessControlBaseServiceFacade.findAccessById(getServiceContext(), id);
            fail("parameter required");
        } catch (MetamacException e) {
            assertEquals(1, e.getExceptionItems().size());
            assertEquals(ServiceExceptionType.PARAMETER_REQUIRED.getCode(), e.getExceptionItems().get(0).getCode());
            assertEquals(1, e.getExceptionItems().get(0).getMessageParameters().length);
            assertEquals("ID", e.getExceptionItems().get(0).getMessageParameters()[0]);
        }
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

}
