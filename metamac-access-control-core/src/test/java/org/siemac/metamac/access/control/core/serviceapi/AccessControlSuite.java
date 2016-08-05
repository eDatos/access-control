package org.siemac.metamac.access.control.core.serviceapi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.siemac.metamac.access.control.core.error.AccessControlCheckTranslationsTest;
import org.siemac.metamac.access.control.core.mapper.ExternalItemsDo2DtoMapperTest;
import org.siemac.metamac.access.control.core.mapper.ExternalItemsDto2DoMapperTest;


/**
 * Spring based transactional test with DbUnit support.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({AccessControlCheckTranslationsTest.class,
        AccessControlBaseServiceFacadeTest.class, 
        AccessControlBaseServiceTest.class, 
        SecurityAccessControlBaseServiceFacadeTest.class,
        ExternalItemsDo2DtoMapperTest.class,
        ExternalItemsDto2DoMapperTest.class})
public class AccessControlSuite {
}
