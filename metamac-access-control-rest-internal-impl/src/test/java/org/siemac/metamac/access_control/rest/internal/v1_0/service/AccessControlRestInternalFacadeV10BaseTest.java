package org.siemac.metamac.access_control.rest.internal.v1_0.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.ConnectionType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.siemac.metamac.access_control.rest.internal.v1_0.utils.RestDoMocks;
import org.siemac.metamac.core.common.conf.ConfigurationService;
import org.siemac.metamac.core.common.constants.shared.ConfigurationConstants;
import org.siemac.metamac.core.common.util.ApplicationContextProvider;
import org.siemac.metamac.rest.common.test.MetamacRestBaseTest;
import org.siemac.metamac.rest.common.test.ServerResource;
import org.siemac.metamac.rest.common.test.utils.MetamacRestAsserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public abstract class AccessControlRestInternalFacadeV10BaseTest extends MetamacRestBaseTest {

    protected static Logger                              logger             = LoggerFactory.getLogger(AccessControlRestInternalFacadeV10BaseTest.class);

    private static String                                jaxrsServerAddress = "http://localhost:" + ServerResource.PORT + "/apis/access-control";
    protected String                                     baseApi            = jaxrsServerAddress + "/v1.0";
    protected static ApplicationContext                  applicationContext = null;
    protected static AccessControlRestInternalFacadeV1_0 accessControlRestInternalFacadeClientXml;
    private static String                                apiEndpointv10;

    protected static RestDoMocks                         restDoMocks;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        // Start server
        assertTrue("server did not launch correctly", launchServer(ServerResource.class, true));

        // Get application context from Jetty
        applicationContext = ApplicationContextProvider.getApplicationContext();
        restDoMocks = new RestDoMocks();

        // Rest clients
        // xml
        {
            List providers = new ArrayList();
            providers.add(applicationContext.getBean("jaxbProvider", JAXBElementProvider.class));
            accessControlRestInternalFacadeClientXml = JAXRSClientFactory.create(jaxrsServerAddress, AccessControlRestInternalFacadeV1_0.class, providers, Boolean.TRUE);
        }
    }

    @Before
    public void setUp() throws Exception {
        ConfigurationService configurationService = applicationContext.getBean(ConfigurationService.class);
        apiEndpointv10 = configurationService.getProperty(ConfigurationConstants.ENDPOINT_ACCESS_CONTROL_INTERNAL_API) + "/v1.0";

        resetMocks();
    }

    @Test
    public void testErrorWithoutMatchError404() throws Exception {
        String requestUri = baseApi + "/nomatch";

        WebClient webClient = WebClient.create(requestUri).accept(APPLICATION_XML);
        Response response = webClient.get();

        assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
        InputStream responseActual = (InputStream) response.getEntity();
        assertTrue(StringUtils.isBlank(IOUtils.toString(responseActual)));
    }

    protected AccessControlRestInternalFacadeV1_0 getAccessControlRestExternalFacadeClientXml() {
        WebClient.client(accessControlRestInternalFacadeClientXml).reset();
        WebClient.client(accessControlRestInternalFacadeClientXml).accept(APPLICATION_XML);
        return accessControlRestInternalFacadeClientXml;
    }

    protected String getApiEndpoint() {
        return apiEndpointv10;
    }

    protected void incrementRequestTimeOut(WebClient create) {
        ClientConfiguration config = WebClient.getConfig(create);
        HTTPConduit conduit = config.getHttpConduit();
        conduit.getClient().setConnectionTimeout(3000000);
        conduit.getClient().setReceiveTimeout(7000000);
        conduit.getClient().setConnection(ConnectionType.CLOSE);
    }

    protected void assertInputStream(InputStream expected, InputStream actual, boolean onlyPrint) throws IOException {
        byte[] byteArray = IOUtils.toByteArray(actual);
        if (logger.isDebugEnabled()) {
            System.out.println("-------------------");
            System.out.println(IOUtils.toString(new ByteArrayInputStream(byteArray)));
            System.out.println("-------------------");
        }
        if (!onlyPrint) {
            MetamacRestAsserts.assertEqualsResponse(expected, new ByteArrayInputStream(byteArray));
        }
    }

    private void resetMocks() throws Exception {

    }

}