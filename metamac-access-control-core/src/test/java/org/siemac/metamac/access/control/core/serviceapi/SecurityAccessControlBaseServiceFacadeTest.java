package org.siemac.metamac.access.control.core.serviceapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.core.serviceapi.utils.AccessControlDtoMocks;
import org.siemac.metamac.access.control.error.ServiceExceptionType;
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
public class SecurityAccessControlBaseServiceFacadeTest extends AccessControlBaseTest implements AccessControlBaseServiceFacadeTestBase {

    @Autowired
    protected AccessControlBaseServiceFacade accessControlBaseServiceFacade;

    @Override
    @Test
    public void testCreateRole() throws Exception {
        accessControlBaseServiceFacade.createRole(getServiceContextAdministrador(), AccessControlDtoMocks.mockRoleDto());

        expectedMetamacException(new MetamacException(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED, getServiceContextTecnicoPlanificacion().getUserId()));
        accessControlBaseServiceFacade.createRole(getServiceContextTecnicoPlanificacion(), AccessControlDtoMocks.mockRoleDto());
    }

    @Override
    @Test
    public void testUpdateRole() throws Exception {
        Long id = ROLE_1;
        RoleDto roleDto = accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), id);
        roleDto.setCode("newCode");

        accessControlBaseServiceFacade.updateRole(getServiceContextAdministrador(), roleDto);

        expectedMetamacException(new MetamacException(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED, getServiceContextTecnicoPlanificacion().getUserId()));
        accessControlBaseServiceFacade.updateRole(getServiceContextTecnicoPlanificacion(), roleDto);
    }

    @Override
    @Test
    public void testDeleteRole() throws Exception {
        Long id = ROLE_2;
        accessControlBaseServiceFacade.deleteRole(getServiceContextAdministrador(), id);

        expectedMetamacException(new MetamacException(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED, getServiceContextTecnicoPlanificacion().getUserId()));
        accessControlBaseServiceFacade.deleteRole(getServiceContextTecnicoPlanificacion(), id);
    }

    @Override
    @Test
    public void testFindAllRoles() throws Exception {
        accessControlBaseServiceFacade.findAllRoles(getServiceContextAdministrador());
        accessControlBaseServiceFacade.findAllRoles(getServiceContextTecnicoPlanificacion());

    }

    @Override
    @Test
    public void testFindRoleById() throws Exception {
        Long id = ROLE_1;

        accessControlBaseServiceFacade.findRoleById(getServiceContextAdministrador(), id);
        accessControlBaseServiceFacade.findRoleById(getServiceContextTecnicoPlanificacion(), id);

    }

    @Override
    @Test
    public void testCreateApp() throws Exception {
        accessControlBaseServiceFacade.createApp(getServiceContextAdministrador(), AccessControlDtoMocks.mockAppDto());

        expectedMetamacException(new MetamacException(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED, getServiceContextTecnicoPlanificacion().getUserId()));
        accessControlBaseServiceFacade.createApp(getServiceContextTecnicoPlanificacion(), AccessControlDtoMocks.mockAppDto());
    }

    @Override
    @Test
    public void testUpdateApp() throws Exception {
        Long id = APP_1;
        AppDto appDto = accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), id);
        appDto.setCode("newCode");

        accessControlBaseServiceFacade.updateApp(getServiceContextAdministrador(), appDto);

        expectedMetamacException(new MetamacException(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED, getServiceContextTecnicoPlanificacion().getUserId()));
        accessControlBaseServiceFacade.updateApp(getServiceContextTecnicoPlanificacion(), appDto);
    }

    @Override
    @Test
    public void testDeleteApp() throws Exception {
        Long id = APP_2;
        accessControlBaseServiceFacade.deleteApp(getServiceContextAdministrador(), id);

        expectedMetamacException(new MetamacException(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED, getServiceContextTecnicoPlanificacion().getUserId()));
        accessControlBaseServiceFacade.deleteApp(getServiceContextTecnicoPlanificacion(), id);
    }

    @Override
    @Test
    public void testFindAllApps() throws Exception {
        accessControlBaseServiceFacade.findAllApps(getServiceContextAdministrador());
        accessControlBaseServiceFacade.findAllApps(getServiceContextTecnicoPlanificacion());

    }

    @Override
    @Test
    public void testFindAppById() throws Exception {
        Long id = APP_1;

        accessControlBaseServiceFacade.findAppById(getServiceContextAdministrador(), id);
        accessControlBaseServiceFacade.findAppById(getServiceContextTecnicoPlanificacion(), id);
    }

    @Override
    @Test
    public void testCreateUser() throws Exception {
        accessControlBaseServiceFacade.createUser(getServiceContextAdministrador(), AccessControlDtoMocks.mockUserDto());

        expectedMetamacException(new MetamacException(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED, getServiceContextTecnicoPlanificacion().getUserId()));
        accessControlBaseServiceFacade.createUser(getServiceContextTecnicoPlanificacion(), AccessControlDtoMocks.mockUserDto());
    }

    @Override
    @Test
    public void testUpdateUser() throws Exception {
        Long id = USER_1;
        UserDto userDto = accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), id);
        userDto.setUsername("newUsername");

        accessControlBaseServiceFacade.updateUser(getServiceContextAdministrador(), userDto);

        expectedMetamacException(new MetamacException(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED, getServiceContextTecnicoPlanificacion().getUserId()));
        accessControlBaseServiceFacade.updateUser(getServiceContextTecnicoPlanificacion(), userDto);
    }

    @Override
    @Test
    public void testDeleteUser() throws Exception {
        Long id = USER_2;
        accessControlBaseServiceFacade.deleteUser(getServiceContextAdministrador(), id);

        expectedMetamacException(new MetamacException(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED, getServiceContextTecnicoPlanificacion().getUserId()));
        accessControlBaseServiceFacade.deleteUser(getServiceContextTecnicoPlanificacion(), id);
    }

    @Override
    @Test
    public void testFindAllUsers() throws Exception {
        accessControlBaseServiceFacade.findAllUsers(getServiceContextAdministrador());
        accessControlBaseServiceFacade.findAllUsers(getServiceContextTecnicoPlanificacion());

    }

    @Override
    @Test
    public void testFindUserById() throws Exception {
        Long id = USER_1;

        accessControlBaseServiceFacade.findUserById(getServiceContextAdministrador(), id);
        accessControlBaseServiceFacade.findUserById(getServiceContextTecnicoPlanificacion(), id);

    }

    @Override
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
        accessDto.setOperation(new ExternalItemDto("TODO-02", "OPERATION:TODO:02", "OPERATION-TODO-02", "OPERATION-TODO-02-Internal", TypeExternalArtefactsEnum.STATISTICAL_OPERATION));

        accessControlBaseServiceFacade.createAccess(getServiceContextAdministrador(), accessDto);

        expectedMetamacException(new MetamacException(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED, getServiceContextTecnicoPlanificacion().getUserId()));
        accessControlBaseServiceFacade.createAccess(getServiceContextTecnicoPlanificacion(), accessDto);
    }

    @Override
    @Test
    public void testRemoveAccess() throws Exception {
        Long id = ACCESS_2;
        accessControlBaseServiceFacade.removeAccess(getServiceContextAdministrador(), id);

        expectedMetamacException(new MetamacException(ServiceExceptionType.SECURITY_OPERATION_NOT_ALLOWED, getServiceContextTecnicoPlanificacion().getUserId()));
        accessControlBaseServiceFacade.removeAccess(getServiceContextTecnicoPlanificacion(), id);
    }

    @Override
    @Test
    public void testFindAllAccess() throws Exception {
        accessControlBaseServiceFacade.findAllAccess(getServiceContextAdministrador());
        accessControlBaseServiceFacade.findAllAccess(getServiceContextTecnicoPlanificacion());

    }

    @Override
    @Test
    public void testFindAccessByCondition() throws Exception {
        String roleCode = "aDMINISTRADOR";
        String appCode = "gOPESTAT";
        String username = "arte";
        String operationCodeId = "OPERATION-TODO-01";

        accessControlBaseServiceFacade.findAccessByCondition(getServiceContextAdministrador(), roleCode, appCode, username, operationCodeId, true);
        accessControlBaseServiceFacade.findAccessByCondition(getServiceContextTecnicoPlanificacion(), roleCode, appCode, username, operationCodeId, true);

    }

    @Override
    @Test
    public void testFindAccessById() throws Exception {
        Long id = ACCESS_1;

        accessControlBaseServiceFacade.findAccessById(getServiceContextAdministrador(), id);
        accessControlBaseServiceFacade.findAccessById(getServiceContextTecnicoPlanificacion(), id);

    }
}
