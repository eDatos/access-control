package org.siemac.metamac.access_control.rest.internal.v1_0.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

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
import org.fornax.cartridges.sculptor.framework.domain.PagedResult;
import org.fornax.cartridges.sculptor.framework.domain.PagingParameter;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.siemac.metamac.access.control.core.conf.AccessControlConfigurationService;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseService;
import org.siemac.metamac.access_control.rest.internal.v1_0.utils.RestDoMocks;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.util.ApplicationContextProvider;
import org.siemac.metamac.rest.common.test.MetamacRestBaseTest;
import org.siemac.metamac.rest.common.test.ServerResource;
import org.siemac.metamac.rest.common.test.utils.MetamacRestAsserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public abstract class AccessControlRestInternalFacadeV10BaseTest extends MetamacRestBaseTest {

    protected static Logger                              logger             = LoggerFactory.getLogger(AccessControlRestInternalFacadeV10BaseTest.class);

    private static String                                jaxrsServerAddress = "http://localhost:" + ServerResource.PORT + "/apis/access-control-internal";
    protected String                                     baseApi            = jaxrsServerAddress + "/v1.0";
    protected static ApplicationContext                  applicationContext = null;
    protected static AccessControlRestInternalFacadeV1_0 accessControlRestInternalFacadeClientXml;
    private static String                                apiEndpointv10;

    protected static RestDoMocks                         restDoMocks;

    private AccessControlBaseService                     accessControlBaseService;

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
        AccessControlConfigurationService configurationService = applicationContext.getBean(AccessControlConfigurationService.class);
        apiEndpointv10 = configurationService.retrieveAccessControlInternalApiUrlBase() + "/v1.0";

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

    protected AccessControlRestInternalFacadeV1_0 getAccessControlRestInternalFacadeClientXml() {
        WebClient.client(accessControlRestInternalFacadeClientXml).reset();
        WebClient.client(accessControlRestInternalFacadeClientXml).accept(APPLICATION_XML);
        return accessControlRestInternalFacadeClientXml;
    }

    protected String getApiEndpoint() {
        return apiEndpointv10;
    }

    @Override
    protected void incrementRequestTimeOut(WebClient create) {
        ClientConfiguration config = WebClient.getConfig(create);
        HTTPConduit conduit = config.getHttpConduit();
        conduit.getClient().setConnectionTimeout(3000000);
        conduit.getClient().setReceiveTimeout(7000000);
        conduit.getClient().setConnection(ConnectionType.CLOSE);
    }

    @Override
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

    @SuppressWarnings("unchecked")
    private void mockFindUsersByCondition() throws MetamacException {
        when(accessControlBaseService.findUserByCondition(any(ServiceContext.class), any(List.class), any(PagingParameter.class))).thenAnswer(new Answer<PagedResult<User>>() {

            @Override
            public org.fornax.cartridges.sculptor.framework.domain.PagedResult<User> answer(InvocationOnMock invocation) throws Throwable {
                List<User> mockUsers = restDoMocks.mockUsers(5);
                PagedResult<User> pagedResult = new PagedResult<User>(mockUsers, 0, mockUsers.size(), mockUsers.size(), mockUsers.size(), 0);

                return pagedResult;
            };
        });
    }

    private void mockFindRolesByCondition() throws MetamacException {
        when(accessControlBaseService.findAllRoles(any(ServiceContext.class))).then(new Answer<List<Role>>() {

            @Override
            public java.util.List<Role> answer(InvocationOnMock invocation) throws Throwable {
                return restDoMocks.mockRoles(10);
            }
        });
    }

    private void mockFindAppsByCondition() throws MetamacException {
        when(accessControlBaseService.findAllApps(any(ServiceContext.class))).then(new Answer<List<App>>() {

            @Override
            public java.util.List<App> answer(InvocationOnMock invocation) throws Throwable {
                return restDoMocks.mockApps(10);
            }
        });
    }

    private void resetMocks() throws Exception {
        accessControlBaseService = applicationContext.getBean(AccessControlBaseService.class);
        reset(accessControlBaseService);

        mockFindUsersByCondition();
        mockFindRolesByCondition();
        mockFindAppsByCondition();
    }
}
