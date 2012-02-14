package org.siemac.metamac.access.control.base.serviceimpl;

import java.util.List;

import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteria;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.dto.serviceapi.AccessDto;
import org.siemac.metamac.access.control.dto.serviceapi.AppDto;
import org.siemac.metamac.access.control.dto.serviceapi.RoleDto;
import org.siemac.metamac.access.control.dto.serviceapi.UserDto;
import org.siemac.metamac.access.control.service.dto.Do2DtoMapper;
import org.siemac.metamac.access.control.service.dto.Dto2DoMapper;
import org.siemac.metamac.access.control.service.utils.InvocationValidator;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of AccessControlBaseServiceFacade.
 */
@Service("accessControlBaseServiceFacade")
public class AccessControlBaseServiceFacadeImpl extends AccessControlBaseServiceFacadeImplBase {

    @Autowired
    private Do2DtoMapper        do2DtoMapper;

    @Autowired
    private Dto2DoMapper        dto2DoMapper;
    
    
    public AccessControlBaseServiceFacadeImpl() {
    }

    
    public RoleDto createRole(ServiceContext ctx, RoleDto roleDto) throws MetamacException {

        // Validation of parameters
        InvocationValidator.checkCreateRole(roleDto, null);
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("createRole not implemented");
    }

    public RoleDto updateRole(ServiceContext ctx, RoleDto roleDto) throws MetamacException {

        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("updateRole not implemented");

    }

    public void deleteRole(ServiceContext ctx, Long roleId) throws MetamacException {

        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("deleteRole not implemented");

    }

    public List<RoleDto> findAllRoles(ServiceContext ctx) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findAllRoles not implemented");

    }

    public List<RoleDto> findRoleByCondition(ServiceContext ctx, List<ConditionalCriteria> condition) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findRoleByCondition not implemented");

    }

    public RoleDto findRoleById(ServiceContext ctx, Long id) throws MetamacException {

        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findRoleById not implemented");

    }

    public AppDto createApp(ServiceContext ctx, AppDto appDto) throws MetamacException {

        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("createApp not implemented");

    }

    public AppDto updateApp(ServiceContext ctx, AppDto appDto) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("updateApp not implemented");

    }

    public void deleteApp(ServiceContext ctx, Long appId) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("deleteApp not implemented");

    }

    public List<AppDto> findAllApps(ServiceContext ctx) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findAllApps not implemented");

    }

    public List<AppDto> findAppByCondition(ServiceContext ctx, List<ConditionalCriteria> condition) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findAppByCondition not implemented");

    }

    public AppDto findAppById(ServiceContext ctx, Long id) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findAppById not implemented");

    }

    public UserDto createUser(ServiceContext ctx, UserDto userDto) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("createUser not implemented");

    }

    public UserDto updateUser(ServiceContext ctx, UserDto userDto) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("updateUser not implemented");

    }

    public void deleteUser(ServiceContext ctx, Long userId) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("deleteUser not implemented");

    }

    public List<UserDto> findAllUsers(ServiceContext ctx) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findAllUsers not implemented");

    }

    public List<UserDto> findUserByCondition(ServiceContext ctx, List<ConditionalCriteria> condition) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findUserByCondition not implemented");

    }

    public UserDto findUserById(ServiceContext ctx, Long id) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findUserById not implemented");

    }

    public AccessDto createAccess(ServiceContext ctx, AccessDto accessDto) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("createAccess not implemented");

    }

    public AccessDto updateAccess(ServiceContext ctx, AccessDto accessDto) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("updateAccess not implemented");

    }

    public void deleteAccess(ServiceContext ctx, Long accessId) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("deleteAccess not implemented");

    }

    public List<AccessDto> findAllAccess(ServiceContext ctx) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findAllAccess not implemented");

    }

    public List<AccessDto> findAccessByCondition(ServiceContext ctx, List<ConditionalCriteria> condition) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findAccessByCondition not implemented");

    }

    public AccessDto findAccessById(ServiceContext ctx, Long id) throws MetamacException {
        // Validation of parameters
        
        // Transform to Entity
        
        // Service call
        
        // Transform to Dto
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("findAccessById not implemented");

    }
}
