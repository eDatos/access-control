package org.siemac.metamac.access.control.service.dto;

import java.util.HashSet;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Do2DtoMapperImpl implements Do2DtoMapper {

    @Autowired
    private DozerBeanMapper mapper;

    /**************************************************************************
     * GETTERS
     **************************************************************************/
    protected DozerBeanMapper getMapper() {
        return mapper;
    }

    
}
