package org.siemac.metamac.access.control.base.serviceapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.siemac.metamac.access.control.base.serviceapi.utils.AccessControlDtoAsserts;
import org.siemac.metamac.access.control.base.serviceapi.utils.AccessControlDtoMocks;
import org.siemac.metamac.access.control.error.ServiceExceptionType;
import org.siemac.metamac.access.control.service.ws.StatisticalOperationsInternalWebServiceFacade;
import org.siemac.metamac.common.test.MetamacBaseTests;
import org.siemac.metamac.common.test.utils.MetamacAsserts;
import org.siemac.metamac.common.test.utils.MetamacDtoAsserts;
import org.siemac.metamac.core.common.dto.ExternalItemBtDto;
import org.siemac.metamac.core.common.enume.domain.TypeExternalArtefactsEnum;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.domain.access.control.dto.AccessDto;
import org.siemac.metamac.domain.access.control.dto.AppDto;
import org.siemac.metamac.domain.access.control.dto.RoleDto;
import org.siemac.metamac.domain.access.control.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring based transactional test with DbUnit support.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:oracle/applicationContext-test.xml"})
public class StatisticalOperationsInternalWebServiceFacadeTest extends MetamacBaseTests {

    @Autowired
    protected StatisticalOperationsInternalWebServiceFacade statisticalOperationsInternalWebServiceFacade;

    private final ServiceContext             serviceContext = new ServiceContext("system", "123456", "junit");


    
    @Test
    public void testFindOperations() throws MetamacException {
        statisticalOperationsInternalWebServiceFacade.findOperations();
    }
    
    
    /**************************************************************************
     * SERVICE CONTEXT
     **************************************************************************/

    protected ServiceContext getServiceContext() {
        return serviceContext;
    }


    @Override
    protected String getDataSetFile() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    protected List<String> getTablesToRemoveContent() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    protected List<String> getSequencesToRestart() {
        // TODO Auto-generated method stub
        return null;
    }


}
