package org.siemac.metamac.access.control.web.server.rest;

import javax.annotation.PostConstruct;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.siemac.metamac.access.control.core.conf.AccessControlConfigurationService;
import org.siemac.metamac.notices.rest.internal.v1_0.service.NoticesV1_0;
import org.siemac.metamac.statistical_operations.rest.internal.v1_0.service.StatisticalOperationsRestInternalFacadeV10;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestApiLocator {

    @Autowired
    private AccessControlConfigurationService          configurationService;

    private StatisticalOperationsRestInternalFacadeV10 statisticalOperationsRestInternalFacadeV10 = null;
    private NoticesV1_0                                noticesRestInternalFacadeV10               = null;

    @PostConstruct
    public void initService() throws Exception {
        String statisticalOperationsBaseApi = configurationService.retrieveStatisticalOperationsInternalApiUrlBase();
        // true to do thread safe
        statisticalOperationsRestInternalFacadeV10 = JAXRSClientFactory.create(statisticalOperationsBaseApi, StatisticalOperationsRestInternalFacadeV10.class, null, true);

        String notificationsBaseApi = configurationService.retrieveNoticesInternalApiUrlBase();
        noticesRestInternalFacadeV10 = JAXRSClientFactory.create(notificationsBaseApi, NoticesV1_0.class, null, true);
    }

    public StatisticalOperationsRestInternalFacadeV10 getStatisticalOperationsRestFacadeV10() {
        // reset thread context
        WebClient.client(statisticalOperationsRestInternalFacadeV10).reset();
        WebClient.client(statisticalOperationsRestInternalFacadeV10).accept("application/xml");

        return statisticalOperationsRestInternalFacadeV10;
    }

    public NoticesV1_0 getNoticesRestInternalFacadeV10() {
        // reset thread context
        WebClient.client(noticesRestInternalFacadeV10).reset();
        WebClient.client(noticesRestInternalFacadeV10).accept("application/xml");

        return noticesRestInternalFacadeV10;
    }
}
