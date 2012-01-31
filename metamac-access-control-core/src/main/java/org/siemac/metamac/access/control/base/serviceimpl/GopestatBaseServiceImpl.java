package org.siemac.metamac.gopestat.base.serviceimpl;

import java.util.List;

import org.fornax.cartridges.sculptor.framework.accessapi.ConditionalCriteria;
import org.fornax.cartridges.sculptor.framework.domain.PagedResult;
import org.fornax.cartridges.sculptor.framework.domain.PagingParameter;
import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.gopestat.base.domain.Family;
import org.siemac.metamac.gopestat.base.domain.FamilyRepository;
import org.siemac.metamac.gopestat.base.domain.Instance;
import org.siemac.metamac.gopestat.base.domain.InstanceRepository;
import org.siemac.metamac.gopestat.base.domain.Operation;
import org.siemac.metamac.gopestat.base.domain.OperationRepository;
import org.siemac.metamac.gopestat.base.exception.FamilyNotFoundException;
import org.siemac.metamac.gopestat.base.exception.InstanceNotFoundException;
import org.siemac.metamac.gopestat.base.exception.OperationNotFoundException;
import org.siemac.metamac.gopestat.error.ServiceExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of GopestatBaseService.
 */
@Service("gopestatBaseService")
public class GopestatBaseServiceImpl extends GopestatBaseServiceImplBase {

    @Autowired
    private FamilyRepository    familyRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private InstanceRepository  instanceRepository;

    public GopestatBaseServiceImpl() {
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.FamilyRepository#findById}
     */
    public Family findFamilyById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return familyRepository.findById(id);
        } catch (FamilyNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.SERVICE_FAMILY_NOT_FOUND.getErrorCode(), ServiceExceptionType.SERVICE_FAMILY_NOT_FOUND.getMessageForReasonType());
        }
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.FamilyRepository#save}
     */
    public Family saveFamily(ServiceContext ctx, Family entity) throws MetamacException {
        return familyRepository.save(entity);
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.FamilyRepository#delete}
     */
    public void deleteFamily(ServiceContext ctx, Family entity) throws MetamacException {
        familyRepository.delete(entity);
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.FamilyRepository#findAll}
     */
    public List<Family> findAllFamilies(ServiceContext ctx) throws MetamacException {
        return familyRepository.findAll();
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.FamilyRepository#findByCondition}
     */
    public PagedResult<Family> findFamilyByCondition(ServiceContext ctx, List<ConditionalCriteria> condition, PagingParameter pagingParameter) {
        return familyRepository.findByCondition(condition, pagingParameter);
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.OperationRepository#findById}
     */
    public Operation findOperationById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return operationRepository.findById(id);
        } catch (OperationNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.SERVICE_OPERATION_NOT_FOUND.getErrorCode(), ServiceExceptionType.SERVICE_OPERATION_NOT_FOUND.getMessageForReasonType());
        }
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.OperationRepository#save}
     */
    public Operation saveOperation(ServiceContext ctx, Operation entity) throws MetamacException {
        return operationRepository.save(entity);
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.OperationRepository#delete}
     */
    public void deleteOperation(ServiceContext ctx, Operation entity) throws MetamacException {
        operationRepository.delete(entity);
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.OperationRepository#findAll}
     */
    public List<Operation> findAllOperations(ServiceContext ctx) throws MetamacException {
        return operationRepository.findAll();
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.OperationRepository#findByCondition}
     */
    public PagedResult<Operation> findOperationByCondition(ServiceContext ctx, List<ConditionalCriteria> condition, PagingParameter pagingParameter) throws MetamacException {
        return operationRepository.findByCondition(condition, pagingParameter);

    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.InstanceRepository#findById}
     */
    public Instance findInstanceById(ServiceContext ctx, Long id) throws MetamacException {
        try {
            return instanceRepository.findById(id);
        } catch (InstanceNotFoundException e) {
            throw new MetamacException(ServiceExceptionType.SERVICE_INSTANCE_NOT_FOUND.getErrorCode(), ServiceExceptionType.SERVICE_INSTANCE_NOT_FOUND.getMessageForReasonType());
        }
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.InstanceRepository#save}
     */
    public Instance saveInstance(ServiceContext ctx, Instance entity) throws MetamacException {
        return instanceRepository.save(entity);
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.InstanceRepository#delete}
     */
    public void deleteInstance(ServiceContext ctx, Instance entity) throws MetamacException {
        instanceRepository.delete(entity);
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.InstanceRepository#findAll}
     */
    public List<Instance> findAllInstances(ServiceContext ctx) throws MetamacException {
        return instanceRepository.findAll();
    }

    /**
     * Delegates to {@link org.siemac.metamac.gopestat.base.domain.InstanceRepository#findByCondition}
     */
    public PagedResult<Instance> findInstanceByCondition(ServiceContext ctx, List<ConditionalCriteria> condition, PagingParameter pagingParameter) throws MetamacException {
        return instanceRepository.findByCondition(condition, pagingParameter);
    }
}
