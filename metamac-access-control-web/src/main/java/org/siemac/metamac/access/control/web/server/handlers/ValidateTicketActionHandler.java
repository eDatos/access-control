package org.siemac.metamac.access.control.web.server.handlers;

import javax.net.ssl.HostnameVerifier;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Saml11TicketValidator;
import org.jasig.cas.client.validation.TicketValidationException;
import org.siemac.metamac.access.control.constants.AccessControlConstants;
import org.siemac.metamac.access.control.web.server.ServiceContextHolder;
import org.siemac.metamac.access.control.web.shared.ValidateTicketAction;
import org.siemac.metamac.access.control.web.shared.ValidateTicketResult;
import org.siemac.metamac.core.common.conf.ConfigurationService;
import org.siemac.metamac.domain.access.control.enume.domain.AccessControlRoleEnum;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.sso.client.MetamacPrincipalAccess;
import org.siemac.metamac.sso.client.SsoClientConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class ValidateTicketActionHandler extends AbstractActionHandler<ValidateTicketAction, ValidateTicketResult> {

    private static Logger        log                        = LoggerFactory.getLogger(ValidateTicketActionHandler.class);

    private static String        PROP_CAS_SERVER_URL_PREFIX = "metamac.security.casServerUrlPrefix";
    private static String        PROP_TOLERANCE             = "metamac.security.tolerance";

    @Autowired
    private ConfigurationService configurationService       = null;

    public ValidateTicketActionHandler() {
        super(ValidateTicketAction.class);
    }

    @Override
    public ValidateTicketResult execute(ValidateTicketAction action, ExecutionContext context) throws ActionException {
        String casServerUrlPrefix = configurationService.getConfig().getString(PROP_CAS_SERVER_URL_PREFIX);
        String ticket = action.getTicket();
        String service = action.getServiceUrl();
        String tolerance = configurationService.getConfig().getString(PROP_TOLERANCE); // ms
        String renew = "false";
        String encoding = null;
        // String disableXmlSchemaValidation = "false";
        HostnameVerifier hostnameVerifier = null; // getHostnameVerifier(filterConfig)

        final Saml11TicketValidator ticketValidator = new Saml11TicketValidator(casServerUrlPrefix);
        ticketValidator.setTolerance(Long.parseLong(tolerance));
        ticketValidator.setRenew(parseBoolean(renew));
        ticketValidator.setHostnameVerifier(hostnameVerifier);
        ticketValidator.setEncoding(encoding);
        // validator.setDisableXmlSchemaValidation(parseBoolean(disableXmlSchemaValidation));

        try {
            final Assertion assertion = ticketValidator.validate(ticket, service);

            if (log.isDebugEnabled()) {
                log.debug("Successfully authenticated user: " + assertion.getPrincipal().getName());
            }

            AttributePrincipal attributePrincipal = assertion.getPrincipal();
            ServiceContext serviceContext = new ServiceContext(attributePrincipal.getName(), ticket, AccessControlConstants.SECURITY_APPLICATION_ID);
            MetamacPrincipal metamacPrincipal = new MetamacPrincipal();
            metamacPrincipal.setUserId(serviceContext.getUserId());
            metamacPrincipal.getAccesses().add(new MetamacPrincipalAccess(AccessControlRoleEnum.ADMINISTRADOR.getName(), AccessControlConstants.SECURITY_APPLICATION_ID, null));
            serviceContext.setProperty(SsoClientConstants.PRINCIPAL_ATTRIBUTE, metamacPrincipal);

            ServiceContextHolder.putCurrentServiceContext(serviceContext);

            return new ValidateTicketResult(metamacPrincipal);
        } catch (final TicketValidationException e) {
            throw new ActionException(e);
        }

    }
    @Override
    public void undo(ValidateTicketAction arg0, ValidateTicketResult arg1, ExecutionContext arg2) throws ActionException {

    }

    protected final boolean parseBoolean(final String value) {
        return ((value != null) && value.equalsIgnoreCase("true"));
    }
}
