package org.siemac.metamac.gopestat.lists.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.gopestat.dto.serviceapi.CollMethodDto;
import org.siemac.metamac.gopestat.dto.serviceapi.CostDto;
import org.siemac.metamac.gopestat.dto.serviceapi.InstanceTypeDto;
import org.siemac.metamac.gopestat.dto.serviceapi.OfficialityTypeDto;
import org.siemac.metamac.gopestat.dto.serviceapi.SurveySourceDto;
import org.siemac.metamac.gopestat.dto.serviceapi.SurveyTypeDto;
import org.siemac.metamac.gopestat.lists.domain.CollMethod;
import org.siemac.metamac.gopestat.lists.domain.Cost;
import org.siemac.metamac.gopestat.lists.domain.InstanceType;
import org.siemac.metamac.gopestat.lists.domain.OfficialityType;
import org.siemac.metamac.gopestat.lists.domain.SurveySource;
import org.siemac.metamac.gopestat.lists.domain.SurveyType;
import org.siemac.metamac.gopestat.lists.serviceapi.GopestatListsService;
import org.siemac.metamac.gopestat.service.dto.Do2DtoMapper;
import org.siemac.metamac.gopestat.service.dto.Dto2DoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of GopestatListsServiceFacade.
 */
@Service("gopestatListsServiceFacade")
public class GopestatListsServiceFacadeImpl extends GopestatListsServiceFacadeImplBase {

    public GopestatListsServiceFacadeImpl() {
    }

    /**************************************************************************
     * Dependences
     **************************************************************************/

    @Autowired
    private Do2DtoMapper         do2DtoMapper;

    @Autowired
    private Dto2DoMapper         dto2DoMapper;

    @Autowired
    private GopestatListsService gopestatListsService;

    protected Do2DtoMapper getDo2DtoMapper() {
        return do2DtoMapper;
    }

    protected Dto2DoMapper getDto2DoMapper() {
        return dto2DoMapper;
    }

    protected GopestatListsService getGopestatListsService() {
        return gopestatListsService;
    }

    // --------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- OPERATION TYPES ----------------------------------------------
    // --------------------------------------------------------------------------------------------------------------

    @Override
    public List<SurveyTypeDto> findAllSurveyTypes(ServiceContext ctx) {
        List<SurveyType> surveyTypesList = getGopestatListsService().findAllSurveyTypes(ctx);
        List<SurveyTypeDto> surveyTypesDtoList = new ArrayList<SurveyTypeDto>();
        for (SurveyType item : surveyTypesList) {
            surveyTypesDtoList.add(getDo2DtoMapper().surveyTypeToDto(item));
        }
        return surveyTypesDtoList;
    }

    @Override
    public SurveyTypeDto findSurveyTypeById(ServiceContext ctx, Long id) throws MetamacException {
        return getDo2DtoMapper().surveyTypeToDto(getGopestatListsService().findSurveyTypeById(ctx, id));
    }

    // --------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- ACTIVITY TYPES -----------------------------------------------
    // --------------------------------------------------------------------------------------------------------------

    @Override
    public List<InstanceTypeDto> findAllInstanceTypes(ServiceContext ctx) {
        List<InstanceType> instanceTypesList = getGopestatListsService().findAllInstanceTypes(ctx);
        List<InstanceTypeDto> instanceTypesDtoList = new ArrayList<InstanceTypeDto>();
        for (InstanceType item : instanceTypesList) {
            instanceTypesDtoList.add(getDo2DtoMapper().instanceTypeToDto(item));
        }
        return instanceTypesDtoList;
    }

    @Override
    public InstanceTypeDto findInstanceTypeById(ServiceContext ctx, Long id) throws MetamacException {
        return getDo2DtoMapper().instanceTypeToDto(getGopestatListsService().findInstanceTypeById(ctx, id));
    }

    // --------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------- SOURCES DATA ------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------

    @Override
    public List<SurveySourceDto> findAllSurveySources(ServiceContext ctx) {
        List<SurveySource> surveySourcesList = getGopestatListsService().findAllSurveySources(ctx);
        List<SurveySourceDto> surveySourcesDtoList = new ArrayList<SurveySourceDto>();
        for (SurveySource item : surveySourcesList) {
            surveySourcesDtoList.add(getDo2DtoMapper().surveySourceToDto(item));
        }
        return surveySourcesDtoList;
    }

    @Override
    public SurveySourceDto findSurveySourceById(ServiceContext ctx, Long id) throws MetamacException {
        return getDo2DtoMapper().surveySourceToDto(getGopestatListsService().findSurveySourceById(ctx, id));
    }

    // ----------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- OFFICIALITY TYPES ----------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------

    @Override
    public List<OfficialityTypeDto> findAllOfficialityTypes(ServiceContext ctx) {
        List<OfficialityType> officialityTypesList = getGopestatListsService().findAllOfficialityTypes(ctx);
        List<OfficialityTypeDto> officialityTypesDtoList = new ArrayList<OfficialityTypeDto>();
        for (OfficialityType item : officialityTypesList) {
            officialityTypesDtoList.add(getDo2DtoMapper().officialityTypeToDto(item));
        }
        return officialityTypesDtoList;
    }

    @Override
    public OfficialityTypeDto findOfficialityTypeById(ServiceContext ctx, Long id) throws MetamacException {
        return getDo2DtoMapper().officialityTypeToDto(getGopestatListsService().findOfficialityTypeById(ctx, id));
    }

    // ----------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- OFFICIALITY TYPES ----------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------

    @Override
    public List<CollMethodDto> findAllCollMethods(ServiceContext ctx) {
        List<CollMethod> collMethodsList = getGopestatListsService().findAllCollMethods(ctx);
        List<CollMethodDto> collMethodsDtoList = new ArrayList<CollMethodDto>();
        for (CollMethod item : collMethodsList) {
            collMethodsDtoList.add(getDo2DtoMapper().collMethodToDto(item));
        }
        return collMethodsDtoList;
    }

    @Override
    public CollMethodDto findCollMethodById(ServiceContext ctx, Long id) throws MetamacException {
        return getDo2DtoMapper().collMethodToDto(getGopestatListsService().findCollMethodById(ctx, id));
    }

    // ----------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- OFFICIALITY TYPES ----------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------

    @Override
    public List<CostDto> findAllCosts(ServiceContext ctx) {
        List<Cost> costsList = getGopestatListsService().findAllCosts(ctx);
        List<CostDto> costsDtoList = new ArrayList<CostDto>();
        for (Cost item : costsList) {
            costsDtoList.add(getDo2DtoMapper().costToDto(item));
        }
        return costsDtoList;
    }

    @Override
    public CostDto findCostById(ServiceContext ctx, Long id) throws MetamacException {
        return getDo2DtoMapper().costToDto(getGopestatListsService().findCostById(ctx, id));
    }

}
