package org.siemac.metamac.gopestat.base.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteria;
import org.fornax.cartridges.sculptor.framework.domain.PagedResult;
import org.fornax.cartridges.sculptor.framework.domain.PagingParameter;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.gopestat.base.domain.Family;
import org.siemac.metamac.gopestat.base.domain.Instance;
import org.siemac.metamac.gopestat.base.domain.Operation;
import org.siemac.metamac.gopestat.base.serviceapi.GopestatBaseService;
import org.siemac.metamac.gopestat.dto.domain.ProcStatusEnum;
import org.siemac.metamac.gopestat.dto.domain.StatusEnum;
import org.siemac.metamac.gopestat.dto.serviceapi.FamilyBaseDto;
import org.siemac.metamac.gopestat.dto.serviceapi.FamilyDto;
import org.siemac.metamac.gopestat.dto.serviceapi.InstanceBaseDto;
import org.siemac.metamac.gopestat.dto.serviceapi.InstanceDto;
import org.siemac.metamac.gopestat.dto.serviceapi.OperationBaseDto;
import org.siemac.metamac.gopestat.dto.serviceapi.OperationDto;
import org.siemac.metamac.gopestat.service.dto.Do2DtoMapper;
import org.siemac.metamac.gopestat.service.dto.Dto2DoMapper;
import org.siemac.metamac.gopestat.service.utils.CheckMandatoryMetadataUtil;
import org.siemac.metamac.gopestat.service.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of GopestatBaseServiceFacade.
 */

@org.springframework.stereotype.Service("gopestatBaseServiceFacade")
public class GopestatBaseServiceFacadeImpl extends GopestatBaseServiceFacadeImplBase {

    public GopestatBaseServiceFacadeImpl() {
    }

    /**************************************************************************
     * Dependences
     **************************************************************************/

    @Autowired
    private Do2DtoMapper        do2DtoMapper;

    @Autowired
    private Dto2DoMapper        dto2DoMapper;

    @Autowired
    private GopestatBaseService gopestatBaseService;

    protected Do2DtoMapper getDo2DtoMapper() {
        return do2DtoMapper;
    }

    protected Dto2DoMapper getDto2DoMapper() {
        return dto2DoMapper;
    }

    protected GopestatBaseService getGopestatBaseService() {
        return gopestatBaseService;
    }

    // --------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- FAMILY SERVICES ----------------------------------------------
    // --------------------------------------------------------------------------------------------------------------

    @Override
    public FamilyDto createFamily(ServiceContext ctx, FamilyDto familyDto) throws MetamacException {
        // Fill metadata
        familyDto.setProcStatus(ProcStatusEnum.DRAFT);
        // TODO: Fill URI

        // Validation
        CheckMandatoryMetadataUtil.checkCreateFamily(familyDto);

        // Transform to Entity
        Family family = dto2DoMapper.familyDtoToEntity(familyDto, ctx);

        // Create
        family = getGopestatBaseService().saveFamily(ctx, family);

        // Transform to Dto
        familyDto = getDo2DtoMapper().familyToDto(family);

        return familyDto;
    }

    @Override
    public FamilyDto updateFamily(ServiceContext ctx, FamilyDto familyDto) throws MetamacException {
        // Check metadata
        List<OperationBaseDto> operations = findOperationsForFamily(ctx, familyDto.getId());

        if (ProcStatusEnum.PUBLISH_INTERNALLY.equals(familyDto.getProcStatus())) {
            CheckMandatoryMetadataUtil.checkFamilyForPublishInternally(familyDto);
            ValidationUtil.validateOperationsForPublishFamilyInternally(operations);
        }

        if (ProcStatusEnum.PUBLISH_EXTERNALLY.equals(familyDto.getProcStatus())) {
            CheckMandatoryMetadataUtil.checkFamilyForPublishExternally(familyDto);
            ValidationUtil.validateOperationsForPublishFamilyExternally(operations);
        }

        // Get persisted family
        Family familyPersisted = getGopestatBaseService().findFamilyById(ctx, familyDto.getId());

        // Transform to Entity
        Family family = getDto2DoMapper().familyDtoToEntity(familyDto, familyPersisted, ctx);

        // Set operations to family
        if (operations != null) {
            for (OperationBaseDto item : operations) {
                family.getOperations().add(gopestatBaseService.findOperationById(ctx, item.getId()));
            }
        }

        // Save
        family = getGopestatBaseService().saveFamily(ctx, family);

        // Transform to Dto
        familyDto = getDo2DtoMapper().familyToDto(family);

        return familyDto;
    }

    @Override
    public void deleteFamily(ServiceContext ctx, Long familyId) throws MetamacException {
        // Retrieve
        Family family = getGopestatBaseService().findFamilyById(ctx, familyId);

        // Check if ProcStatus is DRAFT
        ValidationUtil.validateProcStatus(ProcStatusEnum.DRAFT, family.getProcStatus());

        // Remove related operations
        if (!family.getOperations().isEmpty()) {
            family.removeAllOperations();
        }

        // Remove family
        getGopestatBaseService().deleteFamily(ctx, family);
    }

    @Override
    public List<FamilyBaseDto> findAllFamilies(ServiceContext ctx) throws MetamacException {
        List<Family> familiesList = getGopestatBaseService().findAllFamilies(ctx);
        List<FamilyBaseDto> familiesDtoList = new ArrayList<FamilyBaseDto>();
        for (Family family : familiesList) {
            familiesDtoList.add(getDo2DtoMapper().familyToBaseDto(family));
        }
        return familiesDtoList;
    }

    @Override
    public PagedResult<FamilyBaseDto> findFamilyByCondition(ServiceContext ctx, List<ConditionalCriteria> condition, PagingParameter pagingParameter) throws MetamacException {
        // Search
        PagedResult<Family> familiesPagedList = getGopestatBaseService().findFamilyByCondition(ctx, condition, pagingParameter);

        // Conversion
        List<FamilyBaseDto> familiesDtoList = new ArrayList<FamilyBaseDto>();
        for (Family family : familiesPagedList.getValues()) {
            familiesDtoList.add(getDo2DtoMapper().familyToBaseDto(family));
        }

        // Prepare for return
        PagedResult<FamilyBaseDto> familiesDtoPagedList = new PagedResult<FamilyBaseDto>(familiesDtoList, familiesPagedList.getStartRow(), familiesPagedList.getRowCount(),
                familiesPagedList.getPageSize(), familiesPagedList.getTotalRows(), familiesPagedList.getAdditionalResultRows());

        return familiesDtoPagedList;
    }

    @Override
    public FamilyDto findFamilyById(ServiceContext ctx, Long identifier) throws MetamacException {
        return getDo2DtoMapper().familyToDto(getGopestatBaseService().findFamilyById(ctx, identifier));
    }

    @Override
    public FamilyDto publishInternallyFamily(ServiceContext ctx, Long familyId) throws MetamacException {

        // Retrive
        FamilyDto familyDto = findFamilyById(ctx, familyId);

        // Check ProcStatus
        ValidationUtil.validateFamilyProcStatusForPublishInternally(familyDto);

        // Change state
        familyDto.setProcStatus(ProcStatusEnum.PUBLISH_INTERNALLY);

        // Fill metadata
        familyDto.setInternalInventoryDate(new Date());

        // Save
        familyDto = updateFamily(ctx, familyDto);

        return familyDto;
    }

    @Override
    public FamilyDto publishExternallyFamily(ServiceContext ctx, Long familyId) throws MetamacException {
        // Retrive
        FamilyDto familyDto = findFamilyById(ctx, familyId);

        // Check ProcStatus
        ValidationUtil.validateProcStatus(ProcStatusEnum.PUBLISH_INTERNALLY, familyDto.getProcStatus());

        // Change state
        familyDto.setProcStatus(ProcStatusEnum.PUBLISH_EXTERNALLY);

        // Fill metadata
        familyDto.setInventoryDate(new Date());

        // Save
        familyDto = updateFamily(ctx, familyDto);

        return familyDto;
    }

    @Override
    public List<OperationBaseDto> findOperationsForFamily(ServiceContext ctx, Long familyId) throws MetamacException {
        Set<Operation> operations;
        operations = getGopestatBaseService().findFamilyById(ctx, familyId).getOperations();
        List<OperationBaseDto> operationsDtos = new ArrayList<OperationBaseDto>();

        if (!operations.isEmpty()) {
            for (Operation operation : operations) {
                operationsDtos.add(getDo2DtoMapper().operationToBaseDto(operation));
            }
        }

        return operationsDtos;
    }

    @Override
    public List<OperationBaseDto> addOperationForFamily(ServiceContext ctx, Long familyId, Long operationId) throws MetamacException {
        addOperationFamilyAssociation(ctx, familyId, operationId);

        return findOperationsForFamily(ctx, familyId);
    }

    @Override
    public List<OperationBaseDto> removeOperationForFamily(ServiceContext ctx, Long familyId, Long operationId) throws MetamacException {
        removeOperationFamilyAssociation(ctx, familyId, operationId);
        return findOperationsForFamily(ctx, familyId);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- OPERATION SERVICES ----------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public OperationDto createOperation(ServiceContext ctx, OperationDto operationDto) throws MetamacException {
        // Fill metadata
        operationDto.setProcStatus(ProcStatusEnum.DRAFT);
        operationDto.setStatus(StatusEnum.PLANNING);
        operationDto.setCurrentlyActive(Boolean.FALSE);
        // TODO: Fill URI

        // Validation
        CheckMandatoryMetadataUtil.checkCreateOperation(operationDto);

        // Transform to Entity
        Operation operation = dto2DoMapper.operationDtoToEntity(operationDto, ctx);

        // Create
        operation = getGopestatBaseService().saveOperation(ctx, operation);

        // Transform to Dto
        operationDto = operationToDto(ctx, operation);

        return operationDto;
    }


    @Override
    public OperationDto updateOperation(ServiceContext ctx, OperationDto operationDto) throws MetamacException {

        // Check metadata
        if (ProcStatusEnum.PUBLISH_INTERNALLY.equals(operationDto.getProcStatus())) {
            CheckMandatoryMetadataUtil.checkOperationForPublishInternally(operationDto);
        }

        if (ProcStatusEnum.PUBLISH_EXTERNALLY.equals(operationDto.getProcStatus())) {
            CheckMandatoryMetadataUtil.checkOperationForPublishExternally(operationDto);
        }

        // Get persisted operation
        Operation operationPersisted = getGopestatBaseService().findOperationById(ctx, operationDto.getId());

        // Transform Dto to Entity
        Operation operation = getDto2DoMapper().operationDtoToEntity(operationDto, operationPersisted, ctx);

        // Set families to operation (it's necessary because Dto dosen't have families)
        List<FamilyBaseDto> familiesDtoForOperation = findFamiliesForOperation(ctx, operationDto.getId());
        if (familiesDtoForOperation != null) {
            for (FamilyBaseDto item : familiesDtoForOperation) {
                Family family = gopestatBaseService.findFamilyById(ctx, item.getId());
                operation.getFamilies().add(family);
            }
        }

        // Set instances to operation (it's necessary because Dto dosen't have instances)
        List<InstanceBaseDto> instancesDtoForOperation = findInstancesForOperation(ctx, operationDto.getId());
        if (instancesDtoForOperation != null) {
            for (InstanceBaseDto item : instancesDtoForOperation) {
                Instance instance = gopestatBaseService.findInstanceById(ctx, item.getId());
                operation.getInstances().add(instance);
            }
        }

        // Save
        operation = getGopestatBaseService().saveOperation(ctx, operation);

        // Transform to Dto
        operationDto = operationToDto(ctx, operation);

        return operationDto;
    }

    @Override
    public void deleteOperation(ServiceContext ctx, Long operationId) throws MetamacException {
        // Retrieve
        Operation operation = getGopestatBaseService().findOperationById(ctx, operationId);

        // Check if ProcStatus is DRAFT
        ValidationUtil.validateProcStatus(ProcStatusEnum.DRAFT, operation.getProcStatus());

        // Remove related families
        if (!operation.getFamilies().isEmpty()) {
            operation.removeAllFamilies();
        }

        // Remove operation
        getGopestatBaseService().deleteOperation(ctx, operation);

        // Remove no-cascade associations
        // Not necessary
    }

    @Override
    public List<OperationBaseDto> findAllOperations(ServiceContext ctx) throws MetamacException {
        List<Operation> operationsList = getGopestatBaseService().findAllOperations(ctx);
        List<OperationBaseDto> operationsDtoList = new ArrayList<OperationBaseDto>();
        for (Operation operation : operationsList) {
            operationsDtoList.add(getDo2DtoMapper().operationToBaseDto(operation));
        }
        return operationsDtoList;
    }

    @Override
    public PagedResult<OperationBaseDto> findOperationsByCondition(ServiceContext ctx, List<ConditionalCriteria> condition, PagingParameter pagingParameter) throws MetamacException {
        // Search
        PagedResult<Operation> operationsPagedList = getGopestatBaseService().findOperationByCondition(ctx, condition, pagingParameter);

        // Conversion
        List<OperationBaseDto> operationsDtoList = new ArrayList<OperationBaseDto>();
        for (Operation operation : operationsPagedList.getValues()) {
            operationsDtoList.add(getDo2DtoMapper().operationToBaseDto(operation));
        }

        // Prepare for return
        PagedResult<OperationBaseDto> operationsDtoPagedList = new PagedResult<OperationBaseDto>(operationsDtoList, operationsPagedList.getStartRow(), operationsPagedList.getRowCount(),
                operationsPagedList.getPageSize(), operationsPagedList.getTotalRows(), operationsPagedList.getAdditionalResultRows());

        return operationsDtoPagedList;
    }

    @Override
    public OperationDto findOperationById(ServiceContext ctx, Long identifier) throws MetamacException {
        Operation operation = getGopestatBaseService().findOperationById(ctx, identifier);
        return operationToDto(ctx, operation);
    }

    @Override
    public OperationDto publishInternallyOperation(ServiceContext ctx, Long operationId) throws MetamacException {
        // Retrive
        OperationDto operationDto = findOperationById(ctx, operationId);

        // Check ProcStatus
        ValidationUtil.validateOperationProcStatusForPublishInternally(operationDto);

        // Change ProcStatus
        operationDto.setProcStatus(ProcStatusEnum.PUBLISH_INTERNALLY);

        // Fill metadata
        operationDto.setInternalInventoryDate(new Date());

        // Save
        operationDto = updateOperation(ctx, operationDto);

        return operationDto;
    }

    @Override
    public OperationDto publishExternallyOperation(ServiceContext ctx, Long operationId) throws MetamacException {
        // Retrive
        OperationDto operationDto = findOperationById(ctx, operationId);

        // Check ProcStatus
        ValidationUtil.validateProcStatus(ProcStatusEnum.PUBLISH_INTERNALLY, operationDto.getProcStatus());

        // Change ProcStatus
        operationDto.setProcStatus(ProcStatusEnum.PUBLISH_EXTERNALLY);

        // Fill metadata
        operationDto.setInventoryDate(new Date());

        // Save
        operationDto = updateOperation(ctx, operationDto);

        return operationDto;
    }

    @Override
    public List<FamilyBaseDto> findFamiliesForOperation(ServiceContext ctx, Long operationId) throws MetamacException {
        Set<Family> families = getGopestatBaseService().findOperationById(ctx, operationId).getFamilies();
        List<FamilyBaseDto> familiesDto = new ArrayList<FamilyBaseDto>();

        if (!families.isEmpty()) {
            for (Family item : families) {
                familiesDto.add(getDo2DtoMapper().familyToBaseDto(item));
            }
        }

        return familiesDto;
    }

    /**
     * Retrive the instances associated with an operationId. Instances are returned in order of newest to oldest
     */
    @Override
    public List<InstanceBaseDto> findInstancesForOperation(ServiceContext ctx, Long operationId) throws MetamacException {
        Operation operation = getGopestatBaseService().findOperationById(ctx, operationId);
        List<Instance> instances = operation.getInstances();
        List<InstanceBaseDto> instancesDto = new ArrayList<InstanceBaseDto>();

        if (!instances.isEmpty()) {
            for (Instance item : instances) {
                instancesDto.add(getDo2DtoMapper().instanceToBaseDto(item));
            }
        }

        return instancesDto;
    }

    @Override
    public List<FamilyBaseDto> addFamilyForOperation(ServiceContext ctx, Long operationId, Long familyId) throws MetamacException {
        addOperationFamilyAssociation(ctx, familyId, operationId);

        return findFamiliesForOperation(ctx, operationId);
    }

    @Override
    public List<FamilyBaseDto> removeFamilyForOperation(ServiceContext ctx, Long operationId, Long familyId) throws MetamacException {
        removeOperationFamilyAssociation(ctx, familyId, operationId);

        return findFamiliesForOperation(ctx, operationId);
    }

    // ----------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- INSTANCE SERVICES ----------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------

    @Override
    public InstanceDto createInstance(ServiceContext ctx, Long operationId, InstanceDto instanceDto) throws MetamacException {
        // Obtain number of instances
        Integer order = findInstancesForOperation(ctx, operationId).size();

        // Fill metadata
        instanceDto.setOrder(order);
        instanceDto.setProcStatus(ProcStatusEnum.DRAFT);

        // Validation
        CheckMandatoryMetadataUtil.checkCreateInstance(instanceDto);

        // Transform to Entity
        Instance instance = dto2DoMapper.instanceDtoToEntity(instanceDto, ctx);

        // Set operation to instance
        Operation operation = gopestatBaseService.findOperationById(ctx, operationId);
        instance.setOperation(operation);

        // Create
        instance = getGopestatBaseService().saveInstance(ctx, instance);

        // Transform to Dto
        instanceDto = getDo2DtoMapper().instanceToDto(instance);

        // Save operation
        operation.addInstance(instance);
        gopestatBaseService.saveOperation(ctx, operation);

        return instanceDto;
    }

    @Override
    public InstanceDto updateInstance(ServiceContext ctx, Long operationId, InstanceDto instanceDto) throws MetamacException {

        // Check metadata
        OperationDto operationDto = findOperationById(ctx, operationId);
        if (ProcStatusEnum.PUBLISH_INTERNALLY.equals(instanceDto.getProcStatus())) {
            CheckMandatoryMetadataUtil.checkInstanceForPublishInternally(instanceDto);
            ValidationUtil.validateOperationForPublishInstanceInternally(operationDto);
        }

        if (ProcStatusEnum.PUBLISH_EXTERNALLY.equals(instanceDto.getProcStatus())) {
            CheckMandatoryMetadataUtil.checkInstanceForPublishExternally(instanceDto);
            ValidationUtil.validateOperationForPublishInstanceExternally(operationDto);
        }

        // Check operation PROC_STATUS (it can't be draft)
        ValidationUtil.validateOperationProcStatusForSaveInstance(operationDto);

        // Get persisted instance
        Instance instancePersisted = getGopestatBaseService().findInstanceById(ctx, instanceDto.getId());

        // Validate operation for instance
        ValidationUtil.validateOperationForInstance(instancePersisted.getOperation().getId(), operationId);

        // Transform to entity
        Instance instance = getDto2DoMapper().instanceDtoToEntity(instanceDto, instancePersisted, ctx);

        // Set operation to instance
        Operation operation = gopestatBaseService.findOperationById(ctx, operationId);
        instance.setOperation(operation);

        // Save instances
        instance = getGopestatBaseService().saveInstance(ctx, instance);

        return getDo2DtoMapper().instanceToDto(instance);
    }

    /**
     * Upgrade the order of the instances of an operation. You must provide a list of id sorted oldest to newest
     */
    public List<InstanceBaseDto> updateInstancesOrder(ServiceContext ctx, Long operationId, List<Long> instancesIdList) throws MetamacException {

        List<Instance> instancesList = new ArrayList<Instance>();

        Integer order = Integer.valueOf(0);

        for (Long instanceId : instancesIdList) {
            // Get instance
            Instance instance = gopestatBaseService.findInstanceById(ctx, instanceId);

            // Set order
            instance.setOrder(order);

            // Add
            instancesList.add(instance);

            order++;
        }

        // Save operation
        Operation operation = gopestatBaseService.findOperationById(ctx, operationId);
        operation.getInstances().clear();
        operation.getInstances().addAll(instancesList);

        operation = gopestatBaseService.saveOperation(ctx, operation);

        return findInstancesForOperation(ctx, operationId);
    }

    @Override
    public void deleteInstance(ServiceContext ctx, Long instanceId) throws MetamacException {

        // Retrieve
        Instance instance = getGopestatBaseService().findInstanceById(ctx, instanceId);

        Operation operation = gopestatBaseService.findOperationById(ctx, instance.getOperation().getId());

        // Check if ProcStatus is DRAFT
        ValidationUtil.validateProcStatus(ProcStatusEnum.DRAFT, instance.getProcStatus());

        // Remove instance
        getGopestatBaseService().deleteInstance(ctx, instance);
        // operation.removeInstance(instance);

        // Reset order of instances
        List<InstanceBaseDto> instances = findInstancesForOperation(ctx, operation.getId());
        Collections.reverse(instances);
        
        List<Long> instancesIdList = new ArrayList<Long>();
        
        for (InstanceBaseDto instanceDto : instances) {
            instancesIdList.add(instanceDto.getId());
        }
        updateInstancesOrder(ctx, operation.getId(), instancesIdList);
    }

    @Override
    public List<InstanceBaseDto> findAllInstances(ServiceContext ctx) throws MetamacException {
        List<Instance> instancesList = getGopestatBaseService().findAllInstances(ctx);
        List<InstanceBaseDto> instancesDtoList = new ArrayList<InstanceBaseDto>();
        for (Instance instance : instancesList) {
            instancesDtoList.add(getDo2DtoMapper().instanceToBaseDto(instance));
        }
        return instancesDtoList;
    }

    @Override
    public PagedResult<InstanceBaseDto> findInstanceByCondition(ServiceContext ctx, List<ConditionalCriteria> condition, PagingParameter pagingParameter) throws MetamacException {
        // Search
        PagedResult<Instance> instancesPagedList = getGopestatBaseService().findInstanceByCondition(ctx, condition, pagingParameter);

        // Conversion
        List<InstanceBaseDto> instancesDtoList = new ArrayList<InstanceBaseDto>();
        for (Instance instance : instancesPagedList.getValues()) {
            instancesDtoList.add(getDo2DtoMapper().instanceToBaseDto(instance));
        }

        // Prepare for return
        PagedResult<InstanceBaseDto> instancesDtoPagedList = new PagedResult<InstanceBaseDto>(instancesDtoList, instancesPagedList.getStartRow(), instancesPagedList.getRowCount(),
                instancesPagedList.getPageSize(), instancesPagedList.getTotalRows(), instancesPagedList.getAdditionalResultRows());

        return instancesDtoPagedList;
    }

    @Override
    public InstanceDto findInstanceById(ServiceContext ctx, Long identifier) throws MetamacException {
        return getDo2DtoMapper().instanceToDto(getGopestatBaseService().findInstanceById(ctx, identifier));
    }

    @Override
    public InstanceDto publishInternallyInstance(ServiceContext ctx, Long instanceId) throws MetamacException {
        // Retrive
        InstanceDto instanceDto = findInstanceById(ctx, instanceId);

        // Check procStatus
        ValidationUtil.validateInstanceProcStatusForPublishInternally(instanceDto);

        // Change procStatus
        instanceDto.setProcStatus(ProcStatusEnum.PUBLISH_INTERNALLY);

        // Get operation
        OperationBaseDto operationDto = findOperationForInstance(ctx, instanceDto.getId());

        // Fill metadata
        instanceDto.setInternalInventoryDate(new Date());

        // Save instance
        instanceDto = updateInstance(ctx, operationDto.getId(), instanceDto);

        return instanceDto;
    }

    @Override
    public InstanceDto publishExternallyInstance(ServiceContext ctx, Long instanceId) throws MetamacException {
        // Retrive
        InstanceDto instanceDto = findInstanceById(ctx, instanceId);

        // Check procStatus
        ValidationUtil.validateProcStatus(ProcStatusEnum.PUBLISH_INTERNALLY, instanceDto.getProcStatus());

        // Change procStatus
        instanceDto.setProcStatus(ProcStatusEnum.PUBLISH_EXTERNALLY);

        // Fill metadata
        instanceDto.setInventoryDate(new Date());

        // Get operation
        Long operationId = findOperationForInstance(ctx, instanceDto.getId()).getId();

        instanceDto = updateInstance(ctx, operationId, instanceDto);

        return instanceDto;
    }

    @Override
    public OperationBaseDto findOperationForInstance(ServiceContext ctx, Long instanceId) throws MetamacException {
        Operation operation = getGopestatBaseService().findInstanceById(ctx, instanceId).getOperation();
        return getDo2DtoMapper().operationToBaseDto(operation);
    }

    @Override
    public InstanceBaseDto findInstanceBaseById(ServiceContext ctx, Long id) throws MetamacException {
        return getDo2DtoMapper().instanceToBaseDto(getGopestatBaseService().findInstanceById(ctx, id));
    }

    // --------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------- PRIVATE METHODS ----------------------------------------------
    // --------------------------------------------------------------------------------------------------------------

    private void addOperationFamilyAssociation(ServiceContext ctx, Long familyId, Long operationId) throws MetamacException {
        // Get operation
        Operation operation = getGopestatBaseService().findOperationById(ctx, operationId);

        // Get family
        Family family = getGopestatBaseService().findFamilyById(ctx, familyId);

        // Associate
        family.addOperation(operation);

        // Save family
        getGopestatBaseService().saveFamily(ctx, family);
    }

    private void removeOperationFamilyAssociation(ServiceContext ctx, Long familyId, Long operationId) throws MetamacException {
        // Get operation
        Operation operation = getGopestatBaseService().findOperationById(ctx, operationId);

        // Get family
        Family family = getGopestatBaseService().findFamilyById(ctx, familyId);

        // Remove association
        family.removeOperation(operation);

        // Save
        getGopestatBaseService().saveFamily(ctx, family);
    }
    
    private OperationDto operationToDto(ServiceContext ctx, Operation operation) throws MetamacException {
        OperationDto operationDto = getDo2DtoMapper().operationToDto(operation);
        
        List<InstanceBaseDto> instances = findInstancesForOperation(ctx, operationDto.getId());
        
        InstanceBaseDto currentInternalInstance = null;
        InstanceBaseDto currentInstance = null;
        
        for (InstanceBaseDto instanceBaseDto : instances) {
            if (currentInstance == null && ProcStatusEnum.PUBLISH_EXTERNALLY.equals(instanceBaseDto.getProcStatus())) {
                currentInstance = instanceBaseDto;
            }
            
            if (currentInternalInstance == null && ProcStatusEnum.PUBLISH_INTERNALLY.equals(instanceBaseDto.getProcStatus())) {
                currentInternalInstance = instanceBaseDto;
            }
            
            if (currentInstance != null && currentInternalInstance != null) {
                break;
            }
        }
        
        operationDto.setCurrentInstance(currentInstance);
        operationDto.setCurrentInternalInstance(currentInternalInstance);
        
        return operationDto;
    }

}
