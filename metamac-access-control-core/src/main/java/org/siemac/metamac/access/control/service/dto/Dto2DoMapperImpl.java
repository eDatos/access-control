package org.siemac.metamac.gopestat.service.dto;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.core.common.bt.domain.ExternalItemBt;
import org.siemac.metamac.core.common.dto.serviceapi.ExternalItemBtDto;
import org.siemac.metamac.core.common.dto.serviceapi.InternationalStringDto;
import org.siemac.metamac.core.common.dto.serviceapi.LocalisedStringDto;
import org.siemac.metamac.core.common.ent.domain.InternationalString;
import org.siemac.metamac.core.common.ent.domain.InternationalStringRepository;
import org.siemac.metamac.core.common.ent.domain.LocalisedString;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.vo.domain.ExternalItem;
import org.siemac.metamac.core.common.vo.domain.ExternalItemRepository;
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
import org.siemac.metamac.gopestat.error.ServiceExceptionType;
import org.siemac.metamac.gopestat.lists.domain.CollMethod;
import org.siemac.metamac.gopestat.lists.domain.Cost;
import org.siemac.metamac.gopestat.lists.domain.InstanceType;
import org.siemac.metamac.gopestat.lists.domain.OfficialityType;
import org.siemac.metamac.gopestat.lists.domain.SurveySource;
import org.siemac.metamac.gopestat.lists.domain.SurveyType;
import org.siemac.metamac.gopestat.lists.serviceapi.GopestatListsService;
import org.springframework.beans.factory.annotation.Autowired;

public class Dto2DoMapperImpl implements Dto2DoMapper {

    @Autowired
    private DozerBeanMapper               mapper;

    @Autowired
    private GopestatListsService          gopestatListsService;

    @Autowired
    private InternationalStringRepository internationalStringRepository;

    @Autowired
    private ExternalItemRepository        externalItemRepository;

    /**************************************************************************
     * PUBLIC - LISTS
     **************************************************************************/

    @Override
    public SurveyType surveyTypeDtoToEntity(SurveyTypeDto source, ServiceContext ctx) throws MetamacException {
        if (source == null) {
            return null;
        }

        SurveyType target = gopestatListsService.findSurveyTypeById(ctx, source.getId());

        return target;
    }

    @Override
    public InstanceType instanceTypeDtoToEntity(InstanceTypeDto source, ServiceContext ctx) throws MetamacException {
        if (source == null) {
            return null;
        }

        InstanceType target = gopestatListsService.findInstanceTypeById(ctx, source.getId());

        return target;
    }

    @Override
    public SurveySource surveySourceDtoToEntity(SurveySourceDto source, ServiceContext ctx) throws MetamacException {
        if (source == null) {
            return null;
        }

        SurveySource target = gopestatListsService.findSurveySourceById(ctx, source.getId());

        return target;
    }

    @Override
    public OfficialityType officialityTypeDtoToEntity(OfficialityTypeDto source, ServiceContext ctx) throws MetamacException {
        if (source == null) {
            return null;
        }

        OfficialityType target = gopestatListsService.findOfficialityTypeById(ctx, source.getId());

        return target;
    }

    @Override
    public CollMethod collMethodDtoToEntity(CollMethodDto source, ServiceContext ctx) throws MetamacException {
        if (source == null) {
            return null;
        }

        CollMethod target = gopestatListsService.findCollMethodById(ctx, source.getId());

        return target;
    }

    @Override
    public Cost costDtoToEntity(CostDto source, ServiceContext ctx) throws MetamacException {
        if (source == null) {
            return null;
        }

        Cost target = gopestatListsService.findCostById(ctx, source.getId());

        return target;
    }

    /**************************************************************************
     * PUBLIC - ENTITIES
     **************************************************************************/

    @Override
    public Family familyDtoToEntity(FamilyDto source, ServiceContext ctx) throws MetamacException {
        Family target = new Family();
        return familyDtoToEntity(source, target, ctx);
    }

    @Override
    public Family familyDtoToEntity(FamilyDto source, Family target, ServiceContext ctx) throws MetamacException {
        if (source == null) {
            return null;
        }

        if (target == null) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INVALID_PARAMETER_NULL.getErrorCode(), ServiceExceptionType.SERVICE_INVALID_PARAMETER_NULL.getMessageForReasonType());
        }

        Family family = null;

        family = mapper.map(source, Family.class);

        // CODE
        // Not necessary

        // URI
        // Not necessary

        // TITLE
        family.setTitle(internationalStringToEntity(source.getTitle(), target.getTitle()));

        // TITLE_ALTERNATIVE
        family.setAcronym(internationalStringToEntity(source.getAcronym(), target.getAcronym()));

        // DESCRIPTION
        family.setDescription(internationalStringToEntity(source.getDescription(), target.getDescription()));

        // INTERNAL_INVENTORY_DATE
        // not necessary

        // PROC STATUS
        // Not necessary

        // INVENTORY_DATE
        // not necessary

        return family;
    }

    @Override
    public Operation operationDtoToEntity(OperationDto source, ServiceContext ctx) throws MetamacException {
        Operation target = new Operation();
        return operationDtoToEntity(source, target, ctx);
    }

    @Override
    public Operation operationDtoToEntity(OperationDto source, Operation target, ServiceContext ctx) throws MetamacException {
        if (source == null) {
            return null;
        }

        if (target == null) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INVALID_PARAMETER_NULL.getErrorCode(), ServiceExceptionType.SERVICE_INVALID_PARAMETER_NULL.getMessageForReasonType());
        }

        Operation operation = null;

        operation = mapper.map(source, Operation.class);

        // APP_COMMON_METADATA
        operation.setCommonMetadata(externalItemBtDtoToExternalItemBt(source.getCommonMetadata(), target.getCommonMetadata()));

        // CODE
        // Not necessary

        // URI
        // Not necessary

        // TITLE
        operation.setTitle(internationalStringToEntity(source.getTitle(), target.getTitle()));

        // TITLE_ALETERNATIVE
        operation.setAcronym(internationalStringToEntity(source.getAcronym(), target.getAcronym()));

        // SUBJECT_AREA
        operation.setSubjectArea(externalItemBtDtoToExternalItemBt(source.getSubjectArea(), target.getSubjectArea()));

        // SECONDARY_SUBJECT_AREAS
        operation.getSecondarySubjectAreas().addAll(externalItemListToEntity(source.getSecondarySubjectAreas(), target.getSecondarySubjectAreas()));

        // OBJECTIVE
        operation.setObjective(internationalStringToEntity(source.getObjective(), target.getObjective()));

        // DESCRIPTION
        operation.setDescription(internationalStringToEntity(source.getDescription(), target.getDescription()));

        // SURVEY_TYPE
        operation.setSurveyType(surveyTypeDtoToEntity(source.getSurveyType(), ctx));

        // OFFICIALITY_TYPE
        operation.setOfficialityType(officialityTypeDtoToEntity(source.getOfficialityType(), ctx));

        // PRODUCER
        operation.getProducer().addAll(externalItemListToEntity(source.getProducer(), target.getProducer()));

        // REGIONAL_RESPONSIBLE
        operation.getRegionalResponsible().addAll(externalItemListToEntity(source.getRegionalResponsible(), target.getRegionalResponsible()));

        // REGIONAL_CONTRIBUTOR
        operation.getRegionalContributor().addAll(externalItemListToEntity(source.getRegionalContributor(), target.getRegionalContributor()));

        // INTERNAL_INVENTORY_DATE
        // Not necessary

        // CURRENTLY_ACTIVE
        // Not necessary

        // INACTIVE_DATE
        // Not necessary

        // PROC_STATUS
        // Not necessary

        // STATUS
        // Not necessary

        // PUBLISHER
        operation.getPublisher().addAll(externalItemListToEntity(source.getPublisher(), target.getPublisher()));

        // REL_POL_US_AC
        operation.setRelPolUsAc(internationalStringToEntity(source.getRelPolUsAc(), target.getRelPolUsAc()));
        // REL_POL_US_AC_URL
        // Not necessary

        // RELEASE_CALENDAR
        // Not necessary
        // RELEASE_CALENDAR_ACCESS
        // Not necessary

        // UPDATE_FREQUENCY
        operation.getUpdateFrequency().addAll(externalItemListToEntity(source.getUpdateFrequency(), target.getUpdateFrequency()));

        // INVENTORY_DATE
        // Not necessary

        // REV_POLICY
        operation.setRevPolicy(internationalStringToEntity(source.getRevPolicy(), target.getRevPolicy()));
        // REV_POLICY_URL
        // Not necessary

        // REV_PRACTICE
        operation.setRevPractice(internationalStringToEntity(source.getRevPractice(), target.getRevPractice()));
        // REV_PRACTICE_URL
        // Not necessary

        // CONTACT: Extracted from AppCommonMetadata

        // LEGAL_ACTS: Extracted from AppCommonMetadata
        // DATA_SHARING: Extracted from AppCommonMetadata
        // CONFIDENCIALITY_POLICY: Extracted from AppCommonMetadata
        // CONFIDENCIALITY_DATA_TREATMENT: Extracted from AppCommonMetadata

        // COMMENT
        operation.setComment(internationalStringToEntity(source.getComment(), target.getComment()));
        // COMMENT_URL
        // Not necessary

        // NOTES
        operation.setNotes(internationalStringToEntity(source.getNotes(), target.getNotes()));
        // NOTES_URL
        // Not necessary

        return operation;
    }

    @Override
    public Instance instanceDtoToEntity(InstanceDto source, ServiceContext ctx) throws MetamacException {
        Instance target = new Instance();
        return instanceDtoToEntity(source, target, ctx);
    }

    @Override
    public Instance instanceDtoToEntity(InstanceDto source, Instance target, ServiceContext ctx) throws MetamacException {
        if (source == null) {
            return null;
        }

        if (target == null) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INVALID_PARAMETER_NULL.getErrorCode(), ServiceExceptionType.SERVICE_INVALID_PARAMETER_NULL.getMessageForReasonType());
        }

        Instance instance = null;

        instance = mapper.map(source, Instance.class);

        // ORDER
        // Not necessary

        // CODE
        // Not necessary

        // URI
        // Not necessary

        // TITLE
        instance.setTitle(internationalStringToEntity(source.getTitle(), target.getTitle()));

        // TITLE_ALETERNATIVE
        instance.setAcronym(internationalStringToEntity(source.getAcronym(), target.getAcronym()));

        // SURVEY_CODE
        // It's a relation. We do it in service

        // DATA_DESCRIPTION
        instance.setDataDescription(internationalStringToEntity(source.getDataDescription(), target.getDataDescription()));

        // STATISTICAL_POPULATION
        instance.setStatisticalPopulation(internationalStringToEntity(source.getStatisticalPopulation(), target.getStatisticalPopulation()));

        // STATISTICAL_UNIT
        instance.getStatisticalUnit().addAll(externalItemListToEntity(source.getStatisticalUnit(), target.getStatisticalUnit()));

        // GEOGRAPHIC_GRANULARITY
        instance.setGeographicGranularity(externalItemBtDtoToExternalItemBt(source.getGeographicGranularity(), target.getGeographicGranularity()));

        // GEOGRAPHIC_COMPARABILITY
        instance.setGeographicComparability(internationalStringToEntity(source.getGeographicComparability(), target.getGeographicComparability()));

        // TEMPORAL_GRANULARITY
        instance.setTemporalGranularity(externalItemBtDtoToExternalItemBt(source.getTemporalGranularity(), target.getTemporalGranularity()));

        // TEMPORAL_COMPARABILITY
        instance.setTemporalComparability(internationalStringToEntity(source.getTemporalComparability(), target.getTemporalComparability()));

        // BASE_PERIOD
        instance.setBasePeriod(externalItemBtDtoToExternalItemBt(source.getBasePeriod(), target.getBasePeriod()));

        // UNIT_MEASURE
        instance.getUnitMeasure().addAll(externalItemListToEntity(source.getUnitMeasure(), target.getUnitMeasure()));

        // STAT_CONC_DEF
        instance.setStatConcDef(internationalStringToEntity(source.getStatConcDef(), target.getStatConcDef()));

        // STAT_CONC_DEF_LIST
        instance.getStatConcDefList().addAll(externalItemListToEntity(source.getStatConcDefList(), target.getStatConcDefList()));

        // CLASS_SYSTEM
        instance.setClassSystem(internationalStringToEntity(source.getClassSystem(), target.getClassSystem()));

        // CLASS_SYSTEM_LIST
        instance.getClassSystemList().addAll(externalItemListToEntity(source.getClassSystemList(), target.getClassSystemList()));

        // INSTANCE_TYPE
        instance.setInstanceType(instanceTypeDtoToEntity(source.getInstanceType(), ctx));

        // INTERNAL_INVENTORY_DATE
        // Not necessary

        // INACTIVE_DATE
        // Not necessary

        // PROC_STATUS
        // Not necessary

        // DOC_METHOD
        instance.setDocMethod(internationalStringToEntity(source.getDocMethod(), target.getDocMethod()));

        // DOC_METHOD_URL
        // Not necessary

        // SURVEY_SOURCE
        instance.setSurveySource(surveySourceDtoToEntity(source.getSurveySource(), ctx));

        // COLL_METHOD
        instance.setCollMethod(collMethodDtoToEntity(source.getCollMethod(), ctx));

        // INFORMATION_SUPPLIERS
        instance.getInformationSuppliers().addAll(externalItemListToEntity(source.getInformationSuppliers(), target.getInformationSuppliers()));

        // FREQ_COLL
        instance.getFreqColl().addAll(externalItemListToEntity(source.getFreqColl(), target.getFreqColl()));

        // DATA_VALIDATION
        instance.setDataValidation(internationalStringToEntity(source.getDataValidation(), target.getDataValidation()));
        // DATA_VALIDATION_URL
        // Not necessary

        // DATA_COMPILATION
        instance.setDataCompilation(internationalStringToEntity(source.getDataCompilation(), target.getDataCompilation()));
        // DATA_COMPILATION_URL
        // Not necessary

        // ADJUSTMENT
        instance.setAdjustment(internationalStringToEntity(source.getAdjustment(), target.getAdjustment()));
        // ADJUSTMENT_URL
        // Not necessary

        // COST_BURDEN
        instance.setCostBurden(internationalStringToEntity(source.getCostBurden(), target.getCostBurden()));
        // COST_BURDEN_URL
        // Not necessary

        // COST
        instance.getCost().addAll(costDtoListToCostList(source.getCost(), target.getCost(), ctx));

        // INVENTORY_DATE
        // Not necessary

        // QUALITY_DOC
        instance.setQualityDoc(internationalStringToEntity(source.getQualityDoc(), target.getQualityDoc()));
        // QUALITY_DOC_URL
        // Not necessary

        // QUALITY_ASSURE
        instance.setQualityAssure(internationalStringToEntity(source.getQualityAssure(), target.getQualityAssure()));
        // QUALITY_ASSURE_URL
        // Not necessary

        // QUALITY_ASSMNT
        instance.setQualityAssmnt(internationalStringToEntity(source.getQualityAssmnt(), target.getQualityAssmnt()));
        // QUALITY_ASSMNT_URL
        // Not necessary

        // USER_NEEDS
        instance.setUserNeeds(internationalStringToEntity(source.getUserNeeds(), target.getUserNeeds()));
        // USER_NEEDS_URL
        // Not necessary

        // USER_SAT
        instance.setUserSat(internationalStringToEntity(source.getUserSat(), target.getUserSat()));
        // USER_SAT_URL
        // Not necessary

        // COMPLETENESS
        instance.setCompleteness(internationalStringToEntity(source.getCompleteness(), target.getCompleteness()));
        // COMPLETENESS_URL
        // Not necessary

        // TIMELINESS
        instance.setTimeliness(internationalStringToEntity(source.getTimeliness(), target.getTimeliness()));
        // TIMELINESS_URL
        // Not necessary

        // PUNCTUALITY
        instance.setPunctuality(internationalStringToEntity(source.getPunctuality(), target.getPunctuality()));
        // PUNCTUALITY_URL
        // Not necessary

        // ACCURACY_OVERALL
        instance.setAccuracyOverall(internationalStringToEntity(source.getAccuracyOverall(), target.getAccuracyOverall()));
        // ACCURACY_OVERALL_URL
        // Not necessary

        // SAMPLING_ERR
        instance.setSamplingErr(internationalStringToEntity(source.getSamplingErr(), target.getSamplingErr()));
        // SAMPLING_ERR_URL
        // Not necessary

        // NONSAMPLING_ERR
        instance.setNonsamplingErr(internationalStringToEntity(source.getNonsamplingErr(), target.getNonsamplingErr()));
        // NONSAMPLING_ERR_URL
        // Not necessary

        // COHER_X_DOMAIN
        instance.setCoherXDomain(internationalStringToEntity(source.getCoherXDomain(), target.getCoherXDomain()));
        // COHER_X_DOMAIN_URL
        // Not necessary

        // COHER_INTERNAL
        instance.setCoherInternal(internationalStringToEntity(source.getCoherInternal(), target.getCoherInternal()));
        // COHER_INTERNAL_URL
        // Not necessary

        // COMMENT
        instance.setComment(internationalStringToEntity(source.getComment(), target.getComment()));
        // COMMENT_URL
        // Not necessary

        // NOTES
        instance.setNotes(internationalStringToEntity(source.getNotes(), target.getNotes()));
        // NOTES_URL
        // Not necessary

        return instance;
    }

    /**************************************************************************
     * PRIVATE
     **************************************************************************/

    private Set<ExternalItem> externalItemListToEntity(Set<ExternalItemBtDto> source, Set<ExternalItem> target) {

        Set<ExternalItem> result = new HashSet<ExternalItem>();

        if (!target.isEmpty()) {
            for (ExternalItem externalItem : target) {
                externalItemRepository.delete(externalItem);
            }
        }

        if (!source.isEmpty()) {
            for (ExternalItemBtDto externalItemDto : source) {
                result.add(externalItemBtDtoToExternalItem(externalItemDto));
            }
        }

        return result;
    }

    private ExternalItem externalItemBtDtoToExternalItem(ExternalItemBtDto source) {
        if (source == null) {
            return null;
        }

        ExternalItem target = new ExternalItem(new ExternalItemBt(source.getUriInt(), source.getCodeId(), source.getType()));

        return target;
    }

    private ExternalItemBt externalItemBtDtoToExternalItemBt(ExternalItemBtDto source, ExternalItemBt target) {
        if (source == null) {
            return null;
        } else {
            target = new ExternalItemBt(source.getUriInt(), source.getCodeId(), source.getType());
        }

        return target;
    }

    private InternationalString internationalStringToEntity(InternationalStringDto source, InternationalString target) {

        if (source == null) {
            // Delete old entity
            if (target != null) {
                internationalStringRepository.delete(target);
            }

            return null;
        }

        // Avoid the appearance of trash.
        if (target != null) {
            source.setId(target.getId());
            source.setUuid(target.getUuid());
            source.setVersion(target.getVersion());
        }

        InternationalString internationalString = mapper.map(source, InternationalString.class);

        // LocalisedStringDto to LocalisedString
        for (LocalisedStringDto item : source.getTexts()) {
            if (StringUtils.isNotBlank(item.getLabel())) {
                internationalString.addText(mapper.map(item, LocalisedString.class));
            }
        }

        return internationalString;
    }

    private Set<Cost> costDtoListToCostList(Set<CostDto> source, Set<Cost> target, ServiceContext ctx) throws MetamacException {

        Set<Cost> result = new HashSet<Cost>();

        if (target != null) { // Exist
            for (CostDto itemDto : source) {
                boolean found = false;
                for (Cost itemPersisted : target) {
                    if (itemPersisted.equals(itemDto)) {
                        // target.add(itemPersisted);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    result.add(costDtoToEntity(itemDto, ctx));
                }
            }
        } else { // New
            target = new HashSet<Cost>();
            for (CostDto item : source) {
                result.add(costDtoToEntity(item, ctx));
            }
        }

        return result;
    }

}
