package org.siemac.metamac.access.control.base.serviceapi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Spring based transactional test with DbUnit support.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({AccessControlBaseServiceFacadeTest.class, AccessControlBaseServiceTest.class, SecurityAccessControlBaseServiceFacadeTest.class})
public class AccessControlSuiteTest {
}
