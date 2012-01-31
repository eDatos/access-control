package org.siemac.metamac.access.control.service.dto;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;

import org.springframework.beans.factory.annotation.Autowired;

public class Dto2DoMapperImpl implements Dto2DoMapper {

    @Autowired
    private DozerBeanMapper               mapper;


}
