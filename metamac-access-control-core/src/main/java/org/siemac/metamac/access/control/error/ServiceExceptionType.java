package org.siemac.metamac.gopestat.error;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.siemac.metamac.core.common.lang.LocaleUtil;

public enum ServiceExceptionType {

    SERVICE_INVALID_PARAMETER_COLLECTION_EMPTY("0201"),
    SERVICE_INVALID_PARAMETER_UNEXPECTED("0202"),
    SERVICE_INVALID_PARAMETER_NULL("0203"),
    SERVICE_INVALID_NOT_FOUND("0204"),
    SERVICE_INVALID_PROC_STATUS("0205"),

    SERVICE_SEARCH_NOT_FOUND("0301"),

    SERVICE_VALIDATION_CONSTRAINT_ENUMERATED("0401"),
    SERVICE_VALIDATION_CONSTRAINT_CARDINALITY_MAX("0402"),
    SERVICE_VALIDATION_COLLECTION_EMPTY("0403"),
    SERVICE_VALIDATION_METADATA_REQUIRED("0404"),

    SERVICE_FAMILY_NOT_OK("0501"),
    SERVICE_FAMILY_WITHOUT_PUBLISHED_OPERATIONS("0502"),
    SERVICE_FAMILY_NOT_FOUND("0503"),
    SERVICE_FAMILY_WITHOUT_PUBLISHED_EXTERNALLY_OPERATIONS("0504"),

    SERVICE_OPERATION_NOT_FOUND("0601"),
    SERVICE_OPERATION_PUBLISH_INTERNALLY_ERROR("0602"),

    SERVICE_INSTANCE_INCORRECT_OPERATION_ID("0701"),
    SERVICE_INSTANCE_INCORRECT_OPERATION_PROC_STATUS("0702"),
    SERVICE_INSTANCE_NOT_FOUND("0703"),
    SERVICE_INSTANCE_WITHOUT_OPERATION("0704"),
    SERVICE_INSTANCE_WITHOUT_OPERATION_PUBLISHED("0705"),
    SERVICE_INSTANCE_WITHOUT_OPERATION_PUBLISHED_EXTERNALLY("0706"),

    SERVICE_OPERATION_TYPE_NOT_FOUND("0801"),
    SERVICE_ACTIVITY_TYPE_NOT_FOUND("0802"),
    SERVICE_COLL_METHOD_NOT_FOUND("0803"),
    SERVICE_SOURCE_DATA_NOT_FOUND("0804"),
    SERVICE_OFFICIALITY_TYPE_NOT_FOUND("0805"),
    SERVICE_COST_NOT_FOUND("0806"),

    SERVICE_COMMON_METADATA_NOT_FOUND("0901");

    private String                                         errorCode;

    private static final Map<ServiceExceptionType, String> MESSAGE_MAP = new HashMap<ServiceExceptionType, String>();
    private static final Map<String, ServiceExceptionType> LOOKUP      = new HashMap<String, ServiceExceptionType>();

    static {
        // Invalid
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_INVALID_PARAMETER_COLLECTION_EMPTY, "exception.service.invalid.parameter.collection_empty");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_INVALID_PARAMETER_UNEXPECTED, "exception.service.invalid.parameter.unexpected");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_INVALID_PARAMETER_NULL, "exception.service.invalid.parameter.null");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_INVALID_NOT_FOUND, "exception.service.invalid.parameter.not_found");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_INVALID_PROC_STATUS, "exception.service.invalid.procStatus");

        // Search
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_SEARCH_NOT_FOUND, "exception.service.search.not_found");

        // Constraints
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_VALIDATION_CONSTRAINT_ENUMERATED, "exception.service.validation.constraint.enumerated");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_VALIDATION_CONSTRAINT_CARDINALITY_MAX, "exception.service.validation.constraint.cardinality_max");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_VALIDATION_COLLECTION_EMPTY, "exception.service.validation.collection_empty");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_VALIDATION_METADATA_REQUIRED, "exception.service.validation.metadata.required");

        // Family
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_FAMILY_NOT_OK, "exception.service.family.not_ok");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_FAMILY_WITHOUT_PUBLISHED_OPERATIONS, "exception.service.family.without_published_externally_operations");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_FAMILY_NOT_FOUND, "exception.service.family.not_found");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_FAMILY_WITHOUT_PUBLISHED_EXTERNALLY_OPERATIONS, "exception.service.family.without.published.externally.operations");

        // Operation
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_OPERATION_NOT_FOUND, "exception.service.operation.not_found");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_OPERATION_PUBLISH_INTERNALLY_ERROR, "exception.operation.publish_internally.error");

        // Instance
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_INSTANCE_INCORRECT_OPERATION_ID, "exception.service.instance.incorrect_operation_id");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_INSTANCE_INCORRECT_OPERATION_PROC_STATUS, "exception.service.instance.incorrect_operation_proc_status");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_INSTANCE_NOT_FOUND, "exception.service.instance.not_found");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_INSTANCE_WITHOUT_OPERATION, "exception.service.instance.without_operation");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_INSTANCE_WITHOUT_OPERATION_PUBLISHED, "exception.service.instance.without_operation_published");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_INSTANCE_WITHOUT_OPERATION_PUBLISHED_EXTERNALLY, "exception.service.instance.without_operation_published_externally");

        // Lists
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_OPERATION_TYPE_NOT_FOUND, "exception.service.list.operation_types.not_found");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_ACTIVITY_TYPE_NOT_FOUND, "exception.service.list.activity_types.not_found");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_COLL_METHOD_NOT_FOUND, "exception.service.list.coll_method.not_found");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_SOURCE_DATA_NOT_FOUND, "exception.service.list.sources_data.not_found");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_OFFICIALITY_TYPE_NOT_FOUND, "exception.service.list.officiality_types.not_found");
        MESSAGE_MAP.put(ServiceExceptionType.SERVICE_COST_NOT_FOUND, "exception.service.list.cost.not_found");

        for (ServiceExceptionType s : EnumSet.allOf(ServiceExceptionType.class)) {
            LOOKUP.put(s.getErrorCode(), s);
        }
    }

    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param number
     */
    private ServiceExceptionType(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @param exception
     * @return
     */
    public static ServiceExceptionType get(String exception) {
        return LOOKUP.get(exception);
    }

    /**
     * Returns a localized message for this reason type and locale.
     * 
     * @param locale
     *            The locale.
     * @return A localized message given a reason type and locale.
     */
    public String getMessageForReasonType(Locale locale) {
        return LocaleUtil.getLocalizedMessageFromBundle("i18n/messages-service", MESSAGE_MAP.get(this), locale);
    }

    /**
     * Returns a message for this reason type in the default locale.
     * 
     * @return A message message for this reason type in the default locale.
     */
    public String getMessageForReasonType() {
        return getMessageForReasonType(null);
    }

    /**
     * Returns a lower case string of this enum.
     * 
     * @return a lower case string of this enum
     */
    public String lowerCaseString() {
        return this.toString().toLowerCase();
    }
}
