package org.siemac.metamac.access_control.rest.internal.v1_0.service;

import org.junit.Test;
import org.siemac.metamac.rest.access_control.v1_0.domain.Users;

public class AccessControlRestInternalFacadeV10UsersTest extends AccessControlRestInternalFacadeV10BaseTest {

    @Test
    public void testFindUsers() throws Exception {
        Users users = accessControlRestInternalFacadeClientXml.findUsers(null, null, null);

        int kaka = 2;
        //
        // { // All data: specific with general format (StructureSpecificData)
        // WebClient create = WebClient.create(baseApi);
        // incrementRequestTimeOut(create); // Timeout
        // create.path("notifications/{0}", NOTIFICATION_URN);
        // Response response = create.get();
        //
        // InputStream responseExpected = NotificationsRestExternalFacadeV10NotificationsTest.class.getResourceAsStream("/responses/notifications/retrieveNotification.xml");
        // assertEquals(200, response.getStatus());
        // assertInputStream(responseExpected, (InputStream) response.getEntity(), false);
        // }
    }
}