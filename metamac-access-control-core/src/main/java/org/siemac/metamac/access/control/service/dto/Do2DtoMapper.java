package org.siemac.metamac.gopestat.service.dto;

import org.siemac.metamac.gopestat.base.domain.Family;
import org.siemac.metamac.gopestat.base.domain.Instance;
import org.siemac.metamac.gopestat.base.domain.Operation;
import org.siemac.metamac.gopestat.dto.serviceapi.CollMethodDto;
import org.siemac.metamac.gopestat.dto.serviceapi.CostDto;
import org.siemac.metamac.gopestat.dto.serviceapi.FamilyBaseDto;
import org.siemac.metamac.gopestat.dto.serviceapi.FamilyDto;
import org.siemac.metamac.gopestat.dto.serviceapi.InstanceBaseDto;
import org.siemac.metamac.gopestat.dto.serviceapi.InstanceDto;
import org.siemac.metamac.gopestat.dto.serviceapi.InstanceTypeDto;
import org.siemac.metamac.gopestat.dto.serviceapi.OfficialityTypeDto;
import org.siemac.metamac.gopestat.dto.serviceapi.OperationBaseDto;
import org.siemac.metamac.gopestat.dto.serviceapi.OperationDto;
import org.siemac.metamac.gopestat.dto.serviceapi.SurveySourceDto;
import org.siemac.metamac.gopestat.dto.serviceapi.SurveyTypeDto;
import org.siemac.metamac.gopestat.lists.domain.CollMethod;
import org.siemac.metamac.gopestat.lists.domain.Cost;
import org.siemac.metamac.gopestat.lists.domain.InstanceType;
import org.siemac.metamac.gopestat.lists.domain.OfficialityType;
import org.siemac.metamac.gopestat.lists.domain.SurveySource;
import org.siemac.metamac.gopestat.lists.domain.SurveyType;

public interface Do2DtoMapper {

    // Lists
    public SurveyTypeDto surveyTypeToDto(SurveyType surveyType);
    public InstanceTypeDto instanceTypeToDto(InstanceType instanceType);
    public SurveySourceDto surveySourceToDto(SurveySource sourceData);
    public OfficialityTypeDto officialityTypeToDto(OfficialityType officialityType);
    public CollMethodDto collMethodToDto(CollMethod collMethod);
    public CostDto costToDto(Cost cost);

    // Entities
    public FamilyDto familyToDto(Family family);
    // public OperationDto operationToDto(Operation operation) throws GopestatException;
    public OperationDto operationToDto(Operation operation);
    public InstanceDto instanceToDto(Instance instance);

    public FamilyBaseDto familyToBaseDto(Family family);
    public OperationBaseDto operationToBaseDto(Operation operation);
    public InstanceBaseDto instanceToBaseDto(Instance instance);
}
