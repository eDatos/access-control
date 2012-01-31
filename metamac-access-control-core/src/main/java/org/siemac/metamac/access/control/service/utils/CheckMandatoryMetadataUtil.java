package org.siemac.metamac.gopestat.service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.exception.MetamacExceptionItem;
import org.siemac.metamac.gopestat.dto.serviceapi.FamilyDto;
import org.siemac.metamac.gopestat.dto.serviceapi.InstanceDto;
import org.siemac.metamac.gopestat.dto.serviceapi.OperationDto;
import org.siemac.metamac.gopestat.error.ServiceExceptionType;

public class CheckMandatoryMetadataUtil {

    // ----------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- FAMILY VALIDATIONS ----------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------

    public static void checkCreateFamily(FamilyDto familyDto) throws MetamacException {
        checkCreateFamily(familyDto, null);
    }

    /**
     * Check family mandatory metadata for create it
     * 
     * @param familyDto
     * @throws MetamacException
     */
    public static void checkCreateFamily(FamilyDto familyDto, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        validateRequiredMetadata(familyDto.getCode(), exceptions, "CODE");
        validateRequiredMetadata(familyDto.getTitle(), exceptions, "TITLE");
        validateRequiredMetadata(familyDto.getProcStatus(), exceptions, "PROC_STATUS");

        if (!exceptions.isEmpty()) {
            throw new MetamacException(exceptions);
        }
    }

    /**
     * Check family mandatory metadata for publish it internally
     * 
     * @param familyDto
     * @throws MetamacException
     */

    public static void checkFamilyForPublishInternally(FamilyDto familyDto) throws MetamacException {
        checkFamilyForPublishInternally(familyDto, null);
    }

    public static void checkFamilyForPublishInternally(FamilyDto familyDto, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        validateRequiredMetadata(familyDto.getInternalInventoryDate(), exceptions, "INTERNAL_INVENTORY_DATE");

        checkCreateFamily(familyDto);
    }

    /**
     * Check family mandatory metadata for publish it externally
     * 
     * @param familyDto
     * @throws MetamacException
     */
    public static void checkFamilyForPublishExternally(FamilyDto familyDto) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        validateRequiredMetadata(familyDto.getInventoryDate(), exceptions, "INVENTORY_DATE");

        checkFamilyForPublishInternally(familyDto, exceptions);
    }

    // -------------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- OPERATION VALIDATIONS ----------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------

    public static void checkCreateOperation(OperationDto operationDto) throws MetamacException {
        checkCreateOperation(operationDto, null);
    }

    /**
     * Check operation mandatory metadata for create it
     * 
     * @param operationDto
     * @throws MetamacException
     */
    public static void checkCreateOperation(OperationDto operationDto, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        validateRequiredMetadata(operationDto.getCode(), exceptions, "CODE");
        validateRequiredMetadata(operationDto.getTitle(), exceptions, "TITLE");
        validateRequiredMetadata(operationDto.getProcStatus(), exceptions, "PROC_STATUS");
        validateRequiredMetadata(operationDto.getStatus(), exceptions, "STATUS");
        validateRequiredMetadata(operationDto.getSubjectArea(), exceptions, "SUBJECT_AREA");

        if (!exceptions.isEmpty()) {
            throw new MetamacException(exceptions);
        }
    }

    public static void checkOperationForPublishInternally(OperationDto operationDto) throws MetamacException {
        checkOperationForPublishInternally(operationDto, null);
    }

    /**
     * Check operation mandatory metadata for publish it internally
     * 
     * @param operationDto
     * @throws MetamacException
     */
    public static void checkOperationForPublishInternally(OperationDto operationDto, List<MetamacExceptionItem> exceptions) throws MetamacException {

        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        validateRequiredMetadata(operationDto.getCommonMetadata(), exceptions, "COMMON_METADATA");
        validateRequiredMetadata(operationDto.getObjective(), exceptions, "OBJECTIVE");
        validateRequiredMetadata(operationDto.getSurveyType(), exceptions, "SURVEY_TYPE");
        validateRequiredMetadata(operationDto.getOfficialityType(), exceptions, "OFFICIALITY_TYPE");
        validateRequiredMetadata(operationDto.getProducer(), exceptions, "PRODUCER");
        validateRequiredMetadata(operationDto.getRegionalResponsible(), exceptions, "REGIONAL_RESPONSIBLE");
        validateRequiredMetadata(operationDto.getInternalInventoryDate(), exceptions, "INTERNAL_INVEMTORY_DATE");
        validateRequiredMetadata(operationDto.getCurrentlyActive(), exceptions, "CURRENTLY_ACTIVE");
        validateRequiredMetadata(operationDto.getPublisher(), exceptions, "PUBLISHER");

        checkCreateOperation(operationDto, exceptions);
    }

    /**
     * Check operation mandatory metadata for publish it externally
     * 
     * @param operationDto
     * @throws MetamacException
     */
    public static void checkOperationForPublishExternally(OperationDto operationDto) throws MetamacException {

        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        validateRequiredMetadata(operationDto.getInventoryDate(), exceptions, "INVENTORY_DATE");

        checkOperationForPublishInternally(operationDto, exceptions);
    }

    // ------------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- INSTANCE VALIDATIONS ----------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------

    /**
     * Check instance mandatory metadata for create it
     * 
     * @param instanceDto
     * @throws MetamacException
     */
    public static void checkCreateInstance(InstanceDto instanceDto, List<MetamacExceptionItem> exceptions) throws MetamacException {
        if (exceptions == null) {
            exceptions = new ArrayList<MetamacExceptionItem>();
        }

        validateRequiredMetadata(instanceDto.getOrder(), exceptions, "ORDER");
        validateRequiredMetadata(instanceDto.getCode(), exceptions, "CODE");
        validateRequiredMetadata(instanceDto.getTitle(), exceptions, "TITLE");
        validateRequiredMetadata(instanceDto.getProcStatus(), exceptions, "PROC_STATUS");

        if (!exceptions.isEmpty()) {
            throw new MetamacException(exceptions);
        }
    }

    /**
     * Check instance mandatory metadata for publish it internally
     * 
     * @param instanceDto
     * @throws MetamacException
     */
    public static void checkInstanceForPublishInternally(InstanceDto instanceDto) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        validateRequiredMetadata(instanceDto.getInstanceType(), exceptions, "INSTANCE_TYPE");
        validateRequiredMetadata(instanceDto.getInternalInventoryDate(), exceptions, "INTERNAL_INVENTORY_DATE");

        checkCreateInstance(instanceDto, exceptions);
    }

    /**
     * Check instance mandatory metadata for publish it externally
     * 
     * @param instanceDto
     * @throws MetamacException
     */
    public static void checkInstanceForPublishExternally(InstanceDto instanceDto) throws MetamacException {
        List<MetamacExceptionItem> exceptions = new ArrayList<MetamacExceptionItem>();

        validateRequiredMetadata(instanceDto.getInventoryDate(), exceptions, "INVENTORY_DATE");

        checkInstanceForPublishInternally(instanceDto);
    }

    public static void checkCreateInstance(InstanceDto instanceDto) throws MetamacException {
        checkCreateInstance(instanceDto, null);
    }

    @SuppressWarnings("rawtypes")
    private static void validateRequiredMetadata(Object parameter, List<MetamacExceptionItem> exceptions, String parameterName) throws MetamacException {
        if (parameter == null) {
            exceptions.add(new MetamacExceptionItem(ServiceExceptionType.SERVICE_VALIDATION_METADATA_REQUIRED.getErrorCode(), ServiceExceptionType.SERVICE_VALIDATION_METADATA_REQUIRED
                    .getMessageForReasonType(), parameterName));
        } else if ((String.class.isInstance(parameter) && StringUtils.isBlank((String) parameter)) || (List.class.isInstance(parameter) && ((List) parameter).size() == 0)
                || (Set.class.isInstance(parameter) && ((Set) parameter).size() == 0)) {
            exceptions.add(new MetamacExceptionItem(ServiceExceptionType.SERVICE_VALIDATION_METADATA_REQUIRED.getErrorCode(), ServiceExceptionType.SERVICE_VALIDATION_METADATA_REQUIRED
                    .getMessageForReasonType(), parameterName));
        }
    }

}
