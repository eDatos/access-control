package org.siemac.metamac.gopestat.lists.serviceimpl;

import java.util.List;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.gopestat.error.ServiceExceptionType;
import org.siemac.metamac.gopestat.lists.domain.CollMethod;
import org.siemac.metamac.gopestat.lists.domain.CollMethodRepository;
import org.siemac.metamac.gopestat.lists.domain.Cost;
import org.siemac.metamac.gopestat.lists.domain.CostRepository;
import org.siemac.metamac.gopestat.lists.domain.InstanceType;
import org.siemac.metamac.gopestat.lists.domain.InstanceTypeRepository;
import org.siemac.metamac.gopestat.lists.domain.OfficialityType;
import org.siemac.metamac.gopestat.lists.domain.OfficialityTypeRepository;
import org.siemac.metamac.gopestat.lists.domain.SurveySource;
import org.siemac.metamac.gopestat.lists.domain.SurveySourceRepository;
import org.siemac.metamac.gopestat.lists.domain.SurveyType;
import org.siemac.metamac.gopestat.lists.domain.SurveyTypeRepository;
import org.siemac.metamac.gopestat.lists.exception.CollMethodNotFoundException;
import org.siemac.metamac.gopestat.lists.exception.CostNotFoundException;
import org.siemac.metamac.gopestat.lists.exception.InstanceTypeNotFoundException;
import org.siemac.metamac.gopestat.lists.exception.OfficialityTypeNotFoundException;
import org.siemac.metamac.gopestat.lists.exception.SurveySourceNotFoundException;
import org.siemac.metamac.gopestat.lists.exception.SurveyTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of GopestatListsService.
 */
@Service("gopestatListsService")
public class GopestatListsServiceImpl extends GopestatListsServiceImplBase {

    @Autowired
    private SurveyTypeRepository      surveyTypeRepository;
    @Autowired
    private InstanceTypeRepository    instanceTypeRepository;
    @Autowired
    private SurveySourceRepository    surveySourceRepository;
    @Autowired
    private OfficialityTypeRepository officialityTypeRepository;
    @Autowired
    private CollMethodRepository      collMethodRepository;
    @Autowired
    private CostRepository            costRepository;

    public GopestatListsServiceImpl() {
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.lists.domain.SurveyTypeRepository#findById}
     */
    public SurveyType findSurveyTypeById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return surveyTypeRepository.findById(id);
        } catch (SurveyTypeNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.SERVICE_OPERATION_TYPE_NOT_FOUND.getErrorCode(), ServiceExceptionType.SERVICE_OPERATION_TYPE_NOT_FOUND.getMessageForReasonType());
        }
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.lists.domain.SurveyTypeRepository#findAll}
     */
    public List<SurveyType> findAllSurveyTypes(ServiceContext ctx) {
        return surveyTypeRepository.findAll();
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.lists.domain.InstanceTypeRepository#findById}
     */
    public InstanceType findInstanceTypeById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return instanceTypeRepository.findById(id);
        } catch (InstanceTypeNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.SERVICE_ACTIVITY_TYPE_NOT_FOUND.getErrorCode(), ServiceExceptionType.SERVICE_ACTIVITY_TYPE_NOT_FOUND.getMessageForReasonType());
        }
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.lists.domain.InstanceTypeRepository#findAll}
     */
    public List<InstanceType> findAllInstanceTypes(ServiceContext ctx) {
        return instanceTypeRepository.findAll();

    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.lists.domain.SurveySourceRepository#findById}
     */
    public SurveySource findSurveySourceById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return surveySourceRepository.findById(id);
        } catch (SurveySourceNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.SERVICE_SOURCE_DATA_NOT_FOUND.getErrorCode(), ServiceExceptionType.SERVICE_SOURCE_DATA_NOT_FOUND.getMessageForReasonType());
        }
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.lists.domain.SurveySourceRepository#findAll}
     */
    public List<SurveySource> findAllSurveySources(ServiceContext ctx) {
        return surveySourceRepository.findAll();
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.lists.domain.OfficialityTypeRepository#findById}
     */
    public OfficialityType findOfficialityTypeById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return officialityTypeRepository.findById(id);
        } catch (OfficialityTypeNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.SERVICE_OFFICIALITY_TYPE_NOT_FOUND.getErrorCode(), ServiceExceptionType.SERVICE_OFFICIALITY_TYPE_NOT_FOUND.getMessageForReasonType());
        }
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.lists.domain.OfficialityTypeRepository#findAll}
     */
    public List<OfficialityType> findAllOfficialityTypes(ServiceContext ctx) {
        return officialityTypeRepository.findAll();
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.lists.domain.CollMethodRepository#findById}
     */
    public CollMethod findCollMethodById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return collMethodRepository.findById(id);
        } catch (CollMethodNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.SERVICE_COLL_METHOD_NOT_FOUND.getErrorCode(), ServiceExceptionType.SERVICE_COLL_METHOD_NOT_FOUND.getMessageForReasonType());
        }
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.lists.domain.CollMethodsRepository#findAll}
     */
    public List<CollMethod> findAllCollMethods(ServiceContext ctx) {
        return collMethodRepository.findAll();
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.lists.domain.CostRepository#findById}
     */
    public Cost findCostById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return costRepository.findById(id);
        } catch (CostNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.SERVICE_COST_NOT_FOUND.getErrorCode(), ServiceExceptionType.SERVICE_COST_NOT_FOUND.getMessageForReasonType());
        }
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.lists.domain.CostRepository#findAll}
     */
    public List<Cost> findAllCosts(ServiceContext ctx) {
        return costRepository.findAll();
    }
}
