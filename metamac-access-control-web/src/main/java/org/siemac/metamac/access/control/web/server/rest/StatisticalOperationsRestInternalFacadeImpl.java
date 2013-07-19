package org.siemac.metamac.access.control.web.server.rest;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.client.ServerWebApplicationException;
import org.apache.cxf.jaxrs.client.WebClient;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.web.server.utils.WebTranslateExceptions;
import org.siemac.metamac.access.control.web.shared.constants.WebMessageExceptionsConstants;
import org.siemac.metamac.core.common.lang.shared.LocaleConstants;
import org.siemac.metamac.rest.common.v1_0.domain.ComparisonOperator;
import org.siemac.metamac.rest.common.v1_0.domain.LogicalOperator;
import org.siemac.metamac.rest.statistical_operations_internal.v1_0.domain.Operation;
import org.siemac.metamac.rest.statistical_operations_internal.v1_0.domain.OperationCriteriaPropertyRestriction;
import org.siemac.metamac.rest.statistical_operations_internal.v1_0.domain.Operations;
import org.siemac.metamac.web.common.server.utils.WebExceptionUtils;
import org.siemac.metamac.web.common.shared.constants.CommonSharedConstants;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticalOperationsRestInternalFacadeImpl implements StatisticalOperationsRestInternalFacade {

    private static Logger          logger = Logger.getLogger(StatisticalOperationsRestInternalFacadeImpl.class.getName());

    @Autowired
    private RestApiLocator         restApiLocator;

    @Autowired
    private WebTranslateExceptions webTranslateExceptions;

    @Override
    public Operation retrieveOperation(ServiceContext serviceContext, String operationCode) throws MetamacWebException {
        try {
            return restApiLocator.getStatisticalOperationsRestFacadeV10().retrieveOperationById(operationCode); // OPERATION ID in the rest API is what we call CODE
        } catch (ServerWebApplicationException e) {
            throwMetamacWebExceptionFromServerWebApplicationException(serviceContext, e);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new MetamacWebException(CommonSharedConstants.EXCEPTION_UNKNOWN, e.getMessage());
        }
    }

    @Override
    public Operations findOperations(ServiceContext serviceContext, int firstResult, int maxResult, String operation) throws MetamacWebException {
        try {
            String query = null;
            if (!StringUtils.isBlank(operation)) {
                query = OperationCriteriaPropertyRestriction.TITLE + " " + ComparisonOperator.ILIKE.name() + " \"" + operation + "\"";
                query += " " + LogicalOperator.OR.name() + " " + OperationCriteriaPropertyRestriction.ID + " " + ComparisonOperator.ILIKE.name() + " \"" + operation + "\"";
            }

            // Pagination
            String limit = String.valueOf(maxResult);
            String offset = String.valueOf(firstResult);

            Operations findOperationsResult = restApiLocator.getStatisticalOperationsRestFacadeV10().findOperations(query, null, limit, offset);
            return findOperationsResult;
        } catch (ServerWebApplicationException e) {
            throwMetamacWebExceptionFromServerWebApplicationException(serviceContext, e);
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new MetamacWebException(CommonSharedConstants.EXCEPTION_UNKNOWN, e.getMessage());
        }
    }

    private void throwMetamacWebExceptionFromServerWebApplicationException(ServiceContext serviceContext, ServerWebApplicationException e) throws MetamacWebException {
        logger.log(Level.SEVERE, e.getMessage());

        org.siemac.metamac.rest.common.v1_0.domain.Exception exception = e.toErrorObject(WebClient.client(restApiLocator.getStatisticalOperationsRestFacadeV10()),
                org.siemac.metamac.rest.common.v1_0.domain.Exception.class);

        if (exception == null) {
            if (e.getResponse().getStatus() == 404) {
                throwMetamacWebException(serviceContext, WebMessageExceptionsConstants.REST_API_STATISTICAL_OPERATIONS_INVOCATION_ERROR_404);
            } else {
                throwMetamacWebException(serviceContext, WebMessageExceptionsConstants.REST_API_STATISTICAL_OPERATIONS_INVOCATION_ERROR_UNKNOWN);
            }
        }

        throw WebExceptionUtils.createMetamacWebException(exception);
    }

    private void throwMetamacWebException(ServiceContext serviceContext, String exceptionCode) throws MetamacWebException {
        Locale locale = (Locale) serviceContext.getProperty(LocaleConstants.locale);
        String exceptionnMessage = webTranslateExceptions.getTranslatedMessage(exceptionCode, locale);

        throw new MetamacWebException(exceptionCode, exceptionnMessage);
    }
}
