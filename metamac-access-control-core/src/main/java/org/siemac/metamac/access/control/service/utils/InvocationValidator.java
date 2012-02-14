package org.siemac.metamac.access.control.service.utils;

import java.util.ArrayList;
import java.util.List;

import org.siemac.metamac.access.control.base.domain.Role;
import org.siemac.metamac.access.control.dto.serviceapi.RoleDto;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.exception.MetamacExceptionItem;
import org.siemac.metamac.core.common.exception.utils.ExceptionUtils;
import org.siemac.metamac.core.common.serviceimpl.utils.ValidationUtils;

public class InvocationValidator {
    
    public static void checkCreateRole(RoleDto roleDto, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }
        
        checkRole(roleDto, exceptions);
        ValidationUtils.checkMetadataEmpty(roleDto.getUuid(), "ROLE.UUID", exceptions);
        
        ExceptionUtils.throwIfException(exceptions);
    }
    
    public static void checkUpdateRole(RoleDto roleDto, Role roleInProduction, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }
        
        checkRole(roleDto, exceptions);
        ValidationUtils.checkMetadataRequired(roleDto.getUuid(), "ROLE.UUID", exceptions);
        
        ExceptionUtils.throwIfException(exceptions);
    }
    
    public static void checkRetrieveRole(String uuid, String version, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }
        
        ValidationUtils.checkParameterRequired(uuid, "UUID", exceptions);
        // version is optional
        
        ExceptionUtils.throwIfException(exceptions);
    }
    
    public static void checkDeleteRole(String uuid, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }
        
        ValidationUtils.checkParameterRequired(uuid, "UUID", exceptions);
        
        ExceptionUtils.throwIfException(exceptions);
    }

    
    public static void checkFindRole(List<MetamacExceptionItem> exceptions) throws MetamacException {
        
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }
        
        // nothing to validate
        
        ExceptionUtils.throwIfException(exceptions);        
    }
    
    
//    public static void checkCreateDimension(String indicatorsSystemUuid, DimensionDto dimensionDto, List<MetamacExceptionItem> exceptions) throws MetamacException {
//        if (exceptions == null) {
//            exceptions = new ArrayList<MetamacExceptionItem>();
//        }
//        
//        checkParameterRequired(indicatorsSystemUuid, "INDICATORS_SYSTEM_UUID", exceptions);
//        checkDimension(dimensionDto, exceptions);
//        checkMetadataEmpty(dimensionDto.getUuid(), "DIMENSION.UUID", exceptions);
//        checkMetadataEmpty(dimensionDto.getSubdimensions(), "DIMENSION.SUBDIMENSIONS", exceptions);
//
//        throwIfException(exceptions);
//    }

//    public static void checkRetrieveDimension(String uuid, List<MetamacExceptionItem> exceptions) throws MetamacException {
//        
//        if (exceptions == null) {
//            exceptions = new ArrayList<MetamacExceptionItem>();
//        }
//        
//        checkParameterRequired(uuid, "UUID", exceptions);
//
//        throwIfException(exceptions);
//    }
    
    
    private static void checkRole(RoleDto roleDto, List<MetamacExceptionItem> exceptions) {
        ValidationUtils.checkParameterRequired(roleDto, "ROLE", exceptions);
        ValidationUtils.checkMetadataRequired(roleDto.getCode(), "ROLE.CODE", exceptions);
        ValidationUtils.checkMetadataRequired(roleDto.getTitle(), "ROLE.TITLE", exceptions);
    }
}
