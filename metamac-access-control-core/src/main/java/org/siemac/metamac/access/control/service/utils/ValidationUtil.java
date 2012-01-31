package org.siemac.metamac.gopestat.service.utils;

import java.util.List;

import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.gopestat.dto.domain.ProcStatusEnum;
import org.siemac.metamac.gopestat.dto.serviceapi.FamilyDto;
import org.siemac.metamac.gopestat.dto.serviceapi.InstanceDto;
import org.siemac.metamac.gopestat.dto.serviceapi.OperationBaseDto;
import org.siemac.metamac.gopestat.dto.serviceapi.OperationDto;
import org.siemac.metamac.gopestat.error.ServiceExceptionType;

public class ValidationUtil {

    // -----------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- GENERIC VALIDATIONS ----------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Check if an expectedProcStatus is equals to a realProcStatus
     * 
     * @param expectedProcStatus
     * @param realProcStatus
     * @throws MetamacException
     */
    public static void validateProcStatus(ProcStatusEnum expectedProcStatus, ProcStatusEnum realProcStatus) throws MetamacException {
        if (!expectedProcStatus.equals(realProcStatus)) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INVALID_PROC_STATUS.getErrorCode(), ServiceExceptionType.SERVICE_INVALID_PROC_STATUS.getMessageForReasonType(), expectedProcStatus);
        }
    }

    // ----------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- FAMILY VALIDATIONS ----------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------

    /**
     * Check if a family is related with some operations
     * 
     * @param familyDto
     * @param operationsForFamily
     * @throws MetamacException
     */
    public static void validateIfFamilyRelatedWithOperations(List<OperationBaseDto> operationsForFamily) throws MetamacException {
        if (operationsForFamily.isEmpty()) {
            throw new MetamacException(ServiceExceptionType.SERVICE_VALIDATION_COLLECTION_EMPTY.getErrorCode(), ServiceExceptionType.SERVICE_VALIDATION_COLLECTION_EMPTY.getMessageForReasonType(),
                    "familyDto.getOperations");
        }
    }

    /**
     * Check if a family is related with any operation with PUBLISH_INTERNALLY or PUBLISH_EXTERNALLY ProcStatus
     * 
     * @param operations
     * @throws MetamacException
     */
    public static void validateOperationsForPublishFamilyInternally(List<OperationBaseDto> operations) throws MetamacException {

        validateIfFamilyRelatedWithOperations(operations);

        for (OperationBaseDto operationDto : operations) {
            if (!ProcStatusEnum.DRAFT.equals(operationDto)) {
                return;
            }
        }

        throw new MetamacException(ServiceExceptionType.SERVICE_FAMILY_WITHOUT_PUBLISHED_OPERATIONS.getErrorCode(),
                ServiceExceptionType.SERVICE_FAMILY_WITHOUT_PUBLISHED_OPERATIONS.getMessageForReasonType());
    }

    /**
     * Check if a family is related with any operation with PUBLISH_EXTERNALLY ProcStatus
     * 
     * @param operations
     * @throws MetamacException
     */
    public static void validateOperationsForPublishFamilyExternally(List<OperationBaseDto> operations) throws MetamacException {
        for (OperationBaseDto operationDto : operations) {
            if (ProcStatusEnum.PUBLISH_EXTERNALLY.equals(operationDto.getProcStatus())) {
                return;
            }
        }

        throw new MetamacException(ServiceExceptionType.SERVICE_FAMILY_WITHOUT_PUBLISHED_EXTERNALLY_OPERATIONS.getErrorCode(),
                ServiceExceptionType.SERVICE_FAMILY_WITHOUT_PUBLISHED_EXTERNALLY_OPERATIONS.getMessageForReasonType());
    }

    /**
     * Check if a family is in a correct procStatus for publish internally
     * 
     * @param familyDto
     * @throws MetamacException
     */
    public static void validateFamilyProcStatusForPublishInternally(FamilyDto familyDto) throws MetamacException {
        if (!ProcStatusEnum.DRAFT.equals(familyDto.getProcStatus())) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INVALID_PROC_STATUS.getErrorCode(), ServiceExceptionType.SERVICE_INVALID_PROC_STATUS.getMessageForReasonType(),
                    ProcStatusEnum.DRAFT);
        }
    }

    // -------------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- OPERATION VALIDATIONS ----------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------

    /**
     * Check if an operation is in a correct procStatus for publish internally
     * 
     * @param operationDto
     * @throws MetamacException
     */
    public static void validateOperationProcStatusForPublishInternally(OperationDto operationDto) throws MetamacException {
        if (!ProcStatusEnum.DRAFT.equals(operationDto.getProcStatus())) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INVALID_PROC_STATUS.getErrorCode(), ServiceExceptionType.SERVICE_INVALID_PROC_STATUS.getMessageForReasonType(),
                    ProcStatusEnum.DRAFT);
        }
    }

    // ------------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- INSTANCE VALIDATIONS ----------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------

    /**
     * Check if an instance is in a correct procStatus for publish internally
     * 
     * @param instanceDto
     * @throws MetamacException
     */
    public static void validateInstanceProcStatusForPublishInternally(InstanceDto instanceDto) throws MetamacException {
        if (!ProcStatusEnum.DRAFT.equals(instanceDto.getProcStatus())) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INVALID_PROC_STATUS.getErrorCode(), ServiceExceptionType.SERVICE_INVALID_PROC_STATUS.getMessageForReasonType(),
                    ProcStatusEnum.DRAFT);
        }
    }

    /**
     * Check if the operation id is the same that was persisted
     * 
     * @param operationIdPersisted
     * @param operationIdForPersist
     * @throws MetamacException
     */
    public static void validateOperationForInstance(Long operationIdPersisted, Long operationIdForPersist) throws MetamacException {
        if (operationIdForPersist.compareTo(operationIdPersisted) != 0) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INSTANCE_INCORRECT_OPERATION_ID.getErrorCode(),
                    ServiceExceptionType.SERVICE_INSTANCE_INCORRECT_OPERATION_ID.getMessageForReasonType());
        }

    }

    /**
     * Check if the operation proc_status isn't DRAFT
     * 
     * @param operationDto
     * @throws MetamacException
     */
    public static void validateOperationProcStatusForSaveInstance(OperationDto operationDto) throws MetamacException {
        if (ProcStatusEnum.DRAFT.equals(operationDto.getProcStatus())) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INSTANCE_INCORRECT_OPERATION_PROC_STATUS.getErrorCode(),
                    ServiceExceptionType.SERVICE_INSTANCE_INCORRECT_OPERATION_PROC_STATUS.getMessageForReasonType());
        }
    }

    /**
     * Check if the instance is related at least with one published internally or externally operation
     * 
     * @param operationDto
     * @throws MetamacException
     */
    public static void validateOperationForPublishInstanceInternally(OperationDto operationDto) throws MetamacException {
        if (operationDto == null) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INSTANCE_WITHOUT_OPERATION.getErrorCode(), ServiceExceptionType.SERVICE_INSTANCE_WITHOUT_OPERATION.getMessageForReasonType());
        }

        if (ProcStatusEnum.DRAFT.equals(operationDto.getProcStatus())) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INSTANCE_WITHOUT_OPERATION_PUBLISHED.getErrorCode(),
                    ServiceExceptionType.SERVICE_INSTANCE_WITHOUT_OPERATION_PUBLISHED.getMessageForReasonType());
        }
    }

    /**
     * Check if the instance is related at least with one published externally operation
     * 
     * @param operationDto
     * @throws MetamacException
     */
    public static void validateOperationForPublishInstanceExternally(OperationDto operationDto) throws MetamacException {
        if (operationDto == null) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INSTANCE_WITHOUT_OPERATION.getErrorCode(), ServiceExceptionType.SERVICE_INSTANCE_WITHOUT_OPERATION.getMessageForReasonType());
        }

        if (!ProcStatusEnum.PUBLISH_EXTERNALLY.equals(operationDto.getProcStatus())) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INSTANCE_WITHOUT_OPERATION_PUBLISHED_EXTERNALLY.getErrorCode(),
                    ServiceExceptionType.SERVICE_INSTANCE_WITHOUT_OPERATION_PUBLISHED_EXTERNALLY.getMessageForReasonType());
        }

    }

}
