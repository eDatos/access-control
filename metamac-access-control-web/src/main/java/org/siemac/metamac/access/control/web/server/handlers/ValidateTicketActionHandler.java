package org.siemac.metamac.access.control.web.server.handlers;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequestWrapper;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.constants.AccessControlConstants;
import org.siemac.metamac.access.control.web.server.ServiceContextHolder;
import org.siemac.metamac.access.control.web.server.session.SingleSignOutFilter;
import org.siemac.metamac.access.control.web.shared.ValidateTicketAction;
import org.siemac.metamac.access.control.web.shared.ValidateTicketResult;
import org.siemac.metamac.core.common.conf.ConfigurationService;
import org.siemac.metamac.sso.client.MetamacPrincipal;
import org.siemac.metamac.sso.client.SsoClientConstants;
import org.siemac.metamac.sso.validation.ValidateTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

@Component
public class ValidateTicketActionHandler extends AbstractActionHandler<ValidateTicketAction, ValidateTicketResult> {

    protected static Logger      log                        = LoggerFactory.getLogger(ValidateTicketActionHandler.class);

    private static String        PROP_CAS_SERVER_URL_PREFIX = "metamac.security.casServerUrlPrefix";
    private static String        PROP_TOLERANCE             = "metamac.security.tolerance";

    @Autowired
    private ConfigurationService configurationService       = null;

    private ValidateTicket       validateTicket             = null;

    public ValidateTicketActionHandler() {
        super(ValidateTicketAction.class);
    }

    @PostConstruct
    public void initActionHandler() {
        String casServerUrlPrefix = configurationService.getConfig().getString(PROP_CAS_SERVER_URL_PREFIX);
        String tolerance = configurationService.getConfig().getString(PROP_TOLERANCE); // ms

        validateTicket = new ValidateTicket(casServerUrlPrefix);
        validateTicket.setTolerance(Long.valueOf(tolerance));
    }

    @Override
    public ValidateTicketResult execute(ValidateTicketAction action, ExecutionContext context) throws ActionException {

        final String ticket = action.getTicket();
        final String service = action.getServiceUrl();

        try {
            MetamacPrincipal metamacPrincipal = validateTicket.validateTicket(ticket, service);
            ServiceContext serviceContext = new ServiceContext(metamacPrincipal.getUserId(), ticket, AccessControlConstants.SECURITY_APPLICATION_ID);
            serviceContext.setProperty(SsoClientConstants.PRINCIPAL_ATTRIBUTE, metamacPrincipal);
            ServiceContextHolder.putCurrentServiceContext(serviceContext);
            
            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(ServiceContextHolder.getCurrentRequest()) {
                public String getParameter(String name) {
                    if ("ticket".equals(name)) {
                        return ticket;
                    }
                    return super.getParameter(name);
                }
                @Override
                public String getQueryString() {
                    return super.getQueryString() + "&ticket=" + ticket;
                }
            };
            SingleSignOutFilter.getSingleSignOutHandler().recordSession(requestWrapper);
            
            return new ValidateTicketResult(metamacPrincipal);
        } catch (final org.siemac.metamac.sso.exception.TicketValidationException e) {
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
