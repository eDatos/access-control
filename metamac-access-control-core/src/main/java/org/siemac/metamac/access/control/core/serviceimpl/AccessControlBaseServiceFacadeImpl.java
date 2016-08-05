package org.siemac.metamac.access.control.core.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.core.enume.domain.AccessControlRoleEnum;
import org.siemac.metamac.access.control.security.SecurityUtils;
import org.siemac.metamac.access.control.service.mapper.Do2DtoMapper;
import org.siemac.metamac.access.control.service.mapper.Dto2DoMapper;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of AccessControlBaseServiceFacade.
 */
@Service("accessControlBaseServiceFacade")
public class AccessControlBaseServiceFacadeImpl extends AccessControlBaseServiceFacadeImplBase {

    @Autowired
    private Do2DtoMapper do2DtoMapper;

    @Autowired
    private Dto2DoMapper dto2DoMapper;

    public AccessControlBaseServiceFacadeImpl() {
    }

    public RoleDto createRole(ServiceContext ctx, RoleDto roleDto) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Transform to Entity
        Role role = dto2DoMapper.roleDtoToDo(ctx, roleDto);

        // Service call
        role = getAccessControlBaseService().createRole(ctx, role);

        // Transform to Dto
        roleDto = do2DtoMapper.roleDoToDto(role);

        // Return
        return roleDto;
    }

    public RoleDto updateRole(ServiceContext ctx, RoleDto roleDto) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Transform to Entity
        Role role = dto2DoMapper.roleDtoToDo(ctx, roleDto);

        // Service call
        role = getAccessControlBaseService().updateRole(ctx, role);

        // Transform to Dto
        roleDto = do2DtoMapper.roleDoToDto(role);

        // Return
        return roleDto;
    }

    public void deleteRole(ServiceContext ctx, Long roleId) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Service call
        getAccessControlBaseService().deleteRole(ctx, roleId);
    }

    public List<RoleDto> findAllRoles(ServiceContext ctx) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ANY_ROLE_ALLOWED);

        // Service call
        List<Role> roles = getAccessControlBaseService().findAllRoles(ctx);

        // Transform to Dto
        List<RoleDto> rolesDto = rolesListDo2Dto(roles);

        return rolesDto;
    }

    public RoleDto findRoleById(ServiceContext ctx, Long id) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ANY_ROLE_ALLOWED);

        // Service call
        Role role = getAccessControlBaseService().findRoleById(ctx, id);

        // Transform to Dto
        RoleDto roleDto = do2DtoMapper.roleDoToDto(role);

        return roleDto;
    }

    public AppDto createApp(ServiceContext ctx, AppDto appDto) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Transform to Entity
        App app = dto2DoMapper.appDtoToDo(ctx, appDto);

        // Service call
        app = getAccessControlBaseService().createApp(ctx, app);

        // Transform to Dto
        appDto = do2DtoMapper.appDoToDto(app);

        // Return
        return appDto;
    }

    public AppDto updateApp(ServiceContext ctx, AppDto appDto) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Transform to Entity
        App app = dto2DoMapper.appDtoToDo(ctx, appDto);

        // Service call
        app = getAccessControlBaseService().updateApp(ctx, app);

        // Transform to Dto
        appDto = do2DtoMapper.appDoToDto(app);

        // Return
        return appDto;
    }

    public void deleteApp(ServiceContext ctx, Long appId) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Service call
        getAccessControlBaseService().deleteApp(ctx, appId);
    }

    public List<AppDto> findAllApps(ServiceContext ctx) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ANY_ROLE_ALLOWED);

        // Service call
        List<App> apps = getAccessControlBaseService().findAllApps(ctx);

        // Transform to Dto
        List<AppDto> appsDto = appsListDo2Dto(apps);

        return appsDto;
    }

    public AppDto findAppById(ServiceContext ctx, Long id) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ANY_ROLE_ALLOWED);

        // Service call
        App app = getAccessControlBaseService().findAppById(ctx, id);

        // Transform to Dto
        AppDto appDto = do2DtoMapper.appDoToDto(app);

        return appDto;

    }

    public UserDto createUser(ServiceContext ctx, UserDto userDto) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Transform to Entity
        User user = dto2DoMapper.userDtoToDo(ctx, userDto);

        // Service call
        user = getAccessControlBaseService().createUser(ctx, user);

        // Transform to Dto
        userDto = do2DtoMapper.userDoToDto(user);

        // Return
        return userDto;
    }

    public UserDto updateUser(ServiceContext ctx, UserDto userDto) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Transform to Entity
        User user = dto2DoMapper.userDtoToDo(ctx, userDto);

        // Service call
        user = getAccessControlBaseService().updateUser(ctx, user);

        // Transform to Dto
        userDto = do2DtoMapper.userDoToDto(user);

        // Return
        return userDto;
    }

    public void deleteUser(ServiceContext ctx, Long userId) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Service call
        getAccessControlBaseService().deleteUser(ctx, userId);
    }

    public List<UserDto> findAllUsers(ServiceContext ctx) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ANY_ROLE_ALLOWED);

        // Service call
        List<User> users = getAccessControlBaseService().findAllUsers(ctx);

        // Transform to Dto
        List<UserDto> usersDto = usersListDo2Dto(users);

        return usersDto;
    }

    public UserDto findUserById(ServiceContext ctx, Long id) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ANY_ROLE_ALLOWED);

        // Service call
        User user = getAccessControlBaseService().findUserById(ctx, id);

        // Transform to Dto
        UserDto userDto = do2DtoMapper.userDoToDto(user);

        return userDto;
    }

    public AccessDto createAccess(ServiceContext ctx, AccessDto accessDto) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Transform to Entity
        Access access = dto2DoMapper.accessDtoToDo(ctx, accessDto);

        // Service call
        access = getAccessControlBaseService().createAccess(ctx, access);

        // Transform to Dto
        accessDto = do2DtoMapper.accessDoToDto(access);

        // Return
        return accessDto;

    }

    public AccessDto updateAccess(ServiceContext ctx, AccessDto accessDto) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Transform to Entity
        Access access = dto2DoMapper.accessDtoToDo(ctx, accessDto);

        // Service call
        access = getAccessControlBaseService().updateAccess(ctx, access);

        // Transform to Dto
        accessDto = do2DtoMapper.accessDoToDto(access);

        // Return
        return accessDto;

    }

    public void deleteAccess(ServiceContext ctx, Long accessId) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Service call
        getAccessControlBaseService().deleteAccess(ctx, accessId);
    }

    public void removeAccess(ServiceContext ctx, Long accessId) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ADMINISTRADOR);

        // Service call
        getAccessControlBaseService().removeAccess(ctx, accessId);
    }

    public List<AccessDto> findAllAccess(ServiceContext ctx) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ANY_ROLE_ALLOWED);

        // Service call
        List<Access> access = getAccessControlBaseService().findAllAccess(ctx);

        // Transform to Dto
        List<AccessDto> accessDto = accessListDo2Dto(access);

        return accessDto;
    }

    public AccessDto findAccessById(ServiceContext ctx, Long id) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ANY_ROLE_ALLOWED);

        // Service call
        Access access = getAccessControlBaseService().findAccessById(ctx, id);

        // Transform to Dto
        AccessDto accessDto = do2DtoMapper.accessDoToDto(access);

        return accessDto;
    }

    public List<AccessDto> findAccessByCondition(ServiceContext ctx, String roleCode, String appCode, String username, String operationCodeId, Boolean removalAccess) throws MetamacException {
        // Security
        SecurityUtils.checkServiceOperationAllowed(ctx, AccessControlRoleEnum.ANY_ROLE_ALLOWED);

        // Service call
        List<Access> access = getAccessControlBaseService().findAccessByCondition(ctx, roleCode, appCode, username, operationCodeId, removalAccess);

        // Transform to Dto
        List<AccessDto> accessDto = accessListDo2Dto(access);

        return accessDto;
    }

    // --------------------------------------------------------------------------------
    // TRANSFORM LISTS
    // --------------------------------------------------------------------------------

    private List<RoleDto> rolesListDo2Dto(List<Role> rolesList) {
        List<RoleDto> rolesDto = new ArrayList<RoleDto>();
        for (Role role : rolesList) {
            rolesDto.add(do2DtoMapper.roleDoToDto(role));
        }
        return rolesDto;
    }

    private List<AppDto> appsListDo2Dto(List<App> appsList) {
        List<AppDto> appsDto = new ArrayList<AppDto>();
        for (App app : appsList) {
            appsDto.add(do2DtoMapper.appDoToDto(app));
        }
        return appsDto;
    }

    private List<UserDto> usersListDo2Dto(List<User> usersList) {
        List<UserDto> usersDto = new ArrayList<UserDto>();
        for (User user : usersList) {
            usersDto.add(do2DtoMapper.userDoToDto(user));
        }
        return usersDto;
    }

    private List<AccessDto> accessListDo2Dto(List<Access> accessList) throws MetamacException {
        List<AccessDto> accessDto = new ArrayList<AccessDto>();
        for (Access access : accessList) {
            accessDto.add(do2DtoMapper.accessDoToDto(access));
        }
        return accessDto;
    }

}
