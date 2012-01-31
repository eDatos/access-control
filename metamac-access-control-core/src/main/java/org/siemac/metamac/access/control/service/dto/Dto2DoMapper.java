package org.siemac.metamac.gopestat.service.dto;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.gopestat.base.domain.Family;
import org.siemac.metamac.gopestat.base.domain.Instance;
import org.siemac.metamac.gopestat.base.domain.Operation;
import org.siemac.metamac.gopestat.dto.serviceapi.CollMethodDto;
import org.siemac.metamac.gopestat.dto.serviceapi.CostDto;
import org.siemac.metamac.gopestat.dto.serviceapi.FamilyDto;
import org.siemac.metamac.gopestat.dto.serviceapi.InstanceDto;
import org.siemac.metamac.gopestat.dto.serviceapi.InstanceTypeDto;
import org.siemac.metamac.gopestat.dto.serviceapi.OfficialityTypeDto;
import org.siemac.metamac.gopestat.dto.serviceapi.OperationDto;
import org.siemac.metamac.gopestat.dto.serviceapi.SurveySourceDto;
import org.siemac.metamac.gopestat.dto.serviceapi.SurveyTypeDto;
import org.siemac.metamac.gopestat.lists.domain.CollMethod;
import org.siemac.metamac.gopestat.lists.domain.Cost;
import org.siemac.metamac.gopestat.lists.domain.InstanceType;
import org.siemac.metamac.gopestat.lists.domain.OfficialityType;
import org.siemac.metamac.gopestat.lists.domain.SurveySource;
import org.siemac.metamac.gopestat.lists.domain.SurveyType;

public interface Dto2DoMapper {

    // List Entities
    public SurveyType surveyTypeDtoToEntity(SurveyTypeDto source, ServiceContext ctx) throws MetamacException;
    public InstanceType instanceTypeDtoToEntity(InstanceTypeDto source, ServiceContext ctx) throws MetamacException;
    public SurveySource surveySourceDtoToEntity(SurveySourceDto source, ServiceContext ctx) throws MetamacException;
    public OfficialityType officialityTypeDtoToEntity(OfficialityTypeDto source, ServiceContext ctx) throws MetamacException;
    public CollMethod collMethodDtoToEntity(CollMethodDto source, ServiceContext ctx) throws MetamacException;
    public Cost costDtoToEntity(CostDto source, ServiceContext ctx) throws MetamacException;

    // Entities
    public Family familyDtoToEntity(FamilyDto source, Family target, ServiceContext ctx) throws MetamacException;
    public Family familyDtoToEntity(FamilyDto source, ServiceContext ctx) throws MetamacException;
    public Operation operationDtoToEntity(OperationDto source, Operation target, ServiceContext ctx) throws MetamacException;
    public Operation operationDtoToEntity(OperationDto source, ServiceContext ctx) throws MetamacException;
    public Instance instanceDtoToEntity(InstanceDto source, Instance target, ServiceContext ctx) throws MetamacException;
    public Instance instanceDtoToEntity(InstanceDto source, ServiceContext ctx) throws MetamacException;
}
