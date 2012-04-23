package org.siemac.metamac.access.control.base.serviceapi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.siemac.metamac.access.control.base.serviceapi.utils.AccessControlDtoMocks;
import org.siemac.metamac.access.control.error.ServiceExceptionType;
import org.siemac.metamac.core.common.dto.ExternalItemBtDto;
import org.siemac.metamac.core.common.enume.domain.TypeExternalArtefactsEnum;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.domain.access.control.dto.AccessDto;
import org.siemac.metamac.domain.access.control.dto.AppDto;
import org.siemac.metamac.domain.access.control.dto.RoleDto;
import org.siemac.metamac.domain.access.control.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring based transactional test with DbUnit support.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:oracle/applicationContext-test.xml"})
public class SecurityAccessControlBaseServiceFacadeTest extends AccessControlBaseTest implements AccessControlBaseServiceFacadeTestBase {

    @Autowired
    protected AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    @Test
    public void testCreateRole() throws Exception {
        accessControlBaseServiceFacade.createRole(getServiceContextAdministrador(), AccessControlDtoMocks.mockRoleDto());
        try {
            accessControlBaseServiceFacade.createRole(getServiceContextTecnicoPlanificacion(), AccessControlDtoMocks.mockRoleDto());
        } catch (MetamacException e) {
            assertEquals(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED.getCode(), e.getExceptionItems().get(0).getCode());
        }
    }

    @Test
    public void testUpdateRole() throws Exception {
        Long id = ROLE_1;
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), id);
        roleDto.setCode("newCode");

        accessControlBaseServiceFacade.updateRole(getServiceContextAdministrador(), roleDto);
        try {
            accessControlBaseServiceFacade.updateRole(getServiceContextTecnicoPlanificacion(), roleDto);
        } catch (MetamacException e) {
            assertEquals(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED.getCode(), e.getExceptionItems().get(0).getCode());
        }

    }

    @Test
    public void testDeleteRole() throws Exception {
        Long id = ROLE_2;

        accessControlBaseServiceFacade.deleteRole(getServiceContextAdministrador(), id);
        try {
            accessControlBaseServiceFacade.deleteRole(getServiceContextTecnicoPlanificacion(), id);
        } catch (MetamacException e) {
            assertEquals(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED.getCode(), e.getExceptionItems().get(0).getCode());
        }
    }

    @Test
    public void testFindAllRoles() throws Exception {
        accessControlBaseServiceFacade.findAllRoles(getServiceContextAdministrador());
        accessControlBaseServiceFacade.findAllRoles(getServiceContextTecnicoPlanificacion());

    }

    @Test
    public void testFindRoleById() throws Exception {
        Long id = ROLE_1;

        accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), id);
        accessControlBaseServiceFacade.findRoleById(getServiceContextTecnicoPlanificacion(), id);

    }

    @Test
    public void testCreateApp() throws Exception {
        accessControlBaseServiceFacade.createApp(getServiceContextAdministrador(), AccessControlDtoMocks.mockAppDto());
        try {
            accessControlBaseServiceFacade.createApp(getServiceContextTecnicoPlanificacion(), AccessControlDtoMocks.mockAppDto());
        } catch (MetamacException e) {
            assertEquals(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED.getCode(), e.getExceptionItems().get(0).getCode());
        }

    }

    @Test
    public void testUpdateApp() throws Exception {
        Long id = APP_1;
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), id);
        appDto.setCode("newCode");

        accessControlBaseServiceFacade.updateApp(getServiceContextAdministrador(), appDto);
        try {
            accessControlBaseServiceFacade.updateApp(getServiceContextTecnicoPlanificacion(), appDto);
        } catch (MetamacException e) {
            assertEquals(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED.getCode(), e.getExceptionItems().get(0).getCode());
        }
    }

    @Test
    public void testDeleteApp() throws Exception {
        Long id = APP_2;

        accessControlBaseServiceFacade.deleteApp(getServiceContextAdministrador(), id);
        try {
            accessControlBaseServiceFacade.deleteApp(getServiceContextTecnicoPlanificacion(), id);
        } catch (MetamacException e) {
            assertEquals(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED.getCode(), e.getExceptionItems().get(0).getCode());
        }

    }

    @Test
    public void testFindAllApps() throws Exception {
        accessControlBaseServiceFacade.findAllApps(getServiceContextAdministrador());
        accessControlBaseServiceFacade.findAllApps(getServiceContextTecnicoPlanificacion());

    }

    @Test
    public void testFindAppById() throws Exception {
        Long id = APP_1;

        accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), id);
        accessControlBaseServiceFacade.findAppById(getServiceContextTecnicoPlanificacion(), id);
    }

    @Test
    public void testCreateUser() throws Exception {
        accessControlBaseServiceFacade.createUser(getServiceContextAdministrador(), AccessControlDtoMocks.mockUserDto());
        try {
            accessControlBaseServiceFacade.createUser(getServiceContextTecnicoPlanificacion(), AccessControlDtoMocks.mockUserDto());
        } catch (MetamacException e) {
            assertEquals(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED.getCode(), e.getExceptionItems().get(0).getCode());
        }

    }

    @Test
    public void testUpdateUser() throws Exception {
        Long id = USER_1;
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), id);
        userDto.setUsername("newUsername");

        accessControlBaseServiceFacade.updateUser(getServiceContextAdministrador(), userDto);
        try {
            accessControlBaseServiceFacade.updateUser(getServiceContextTecnicoPlanificacion(), userDto);
        } catch (MetamacException e) {
            assertEquals(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED.getCode(), e.getExceptionItems().get(0).getCode());
        }

    }

    @Test
    public void testDeleteUser() throws Exception {
        Long id = USER_2;

        accessControlBaseServiceFacade.deleteUser(getServiceContextAdministrador(), id);
        try {
            accessControlBaseServiceFacade.deleteUser(getServiceContextTecnicoPlanificacion(), id);
        } catch (MetamacException e) {
            assertEquals(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED.getCode(), e.getExceptionItems().get(0).getCode());
        }

    }

    @Test
    public void testFindAllUsers() throws Exception {
        accessControlBaseServiceFacade.findAllUsers(getServiceContextAdministrador());
        accessControlBaseServiceFacade.findAllUsers(getServiceContextTecnicoPlanificacion());

    }

    @Test
    public void testFindUserById() throws Exception {
        Long id = USER_1;

        accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), id);
        accessControlBaseServiceFacade.findUserById(getServiceContextTecnicoPlanificacion(), id);

    }

    @Test
    public void testCreateAccess() throws Exception {
        // Retrieve related entities
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), ROLE_1);
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), APP_1);
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), USER_1);
        // Mock accessDto
        AccessDto accessDto = new AccessDto();
        accessDto.setRole(roleDto);
        accessDto.setApp(appDto);
        accessDto.setUser(userDto);
        accessDto.setOperation(new ExternalItemBtDto("OPERATION:TODO:02", "OPERATION-TODO-02", TypeExternalArtefactsEnum.STATISTICAL_OPERATION));

        accessControlBaseServiceFacade.createAccess(getServiceContextAdministrador(), accessDto);
        try {
            accessControlBaseServiceFacade.createAccess(getServiceContextTecnicoPlanificacion(), accessDto);
        } catch (MetamacException e) {
            assertEquals(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED.getCode(), e.getExceptionItems().get(0).getCode());
        }
        

    }

    @Test
    public void testRemoveAccess() throws Exception {
        Long id = ACCESS_2;

        accessControlBaseServiceFacade.removeAccess(getServiceContextAdministrador(), id);
        try {
            accessControlBaseServiceFacade.removeAccess(getServiceContextTecnicoPlanificacion(), id);
        } catch (MetamacException e) {
            assertEquals(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED.getCode(), e.getExceptionItems().get(0).getCode());
        }

    }

    @Test
    public void testFindAllAccess() throws Exception {
        accessControlBaseServiceFacade.findAllAccess(getServiceContextAdministrador());
        accessControlBaseServiceFacade.findAllAccess(getServiceContextTecnicoPlanificacion());

    }

    @Test
    public void testFindAccessByCondition() throws Exception {
        String roleCode = "aDMINISTRADOR";
        String appCode = "gOPESTAT";
        String username = "arte";
        String operationCodeId = "OPERATION-TODO-01";
        
        accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, true);
        accessControlBaseServiceFacade.findAccessByCondition(getServiceContextTecnicoPlanificacion(), roleCode, appCode, username, operationCodeId, true);

    }

    @Test
    public void testFindAccessById() throws Exception {
        Long id = ACCESS_1;

        accessControlBaseServiceFacade.findAccessById(getServiceContextAdministrador(), id);
        accessControlBaseServiceFacade.findAccessById(getServiceContextTecnicoPlanificacion(), id);

    }

}
