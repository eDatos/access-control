package org.siemac.metamac.access.control.service.mapper;

import java.util.HashSet;
import java.util.Set;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.joda.time.DateTime;
import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.dto.AppDto;
import org.siemac.metamac.access.control.core.dto.RoleDto;
import org.siemac.metamac.access.control.core.dto.UserDto;
import org.siemac.metamac.access.control.core.serviceapi.AccessControlBaseService;
import org.siemac.metamac.access.control.error.ServiceExceptionParameters;
import org.siemac.metamac.access.control.error.ServiceExceptionType;
import org.siemac.metamac.access.control.service.utils.AccessControlValidationUtils;
import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.core.common.dto.InternationalStringDto;
import org.siemac.metamac.core.common.dto.LocalisedStringDto;
import org.siemac.metamac.core.common.ent.domain.ExternalItem;
import org.siemac.metamac.core.common.ent.domain.ExternalItemRepository;
import org.siemac.metamac.core.common.ent.domain.InternationalString;
import org.siemac.metamac.core.common.ent.domain.InternationalStringRepository;
import org.siemac.metamac.core.common.ent.domain.LocalisedString;
import org.siemac.metamac.core.common.enume.utils.TypeExternalArtefactsEnumUtils;
import org.siemac.metamac.core.common.exception.MetamacException;
import org.siemac.metamac.core.common.mapper.BaseDto2DoMapperImpl;
import org.siemac.metamac.core.common.util.OptimisticLockingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dto2DoMapperImpl extends BaseDto2DoMapperImpl implements Dto2DoMapper {

    @Autowired
    private AccessControlBaseService      accessControlBaseService;

    @Autowired
    private InternationalStringRepository internationalStringRepository;

    @Autowired
    private ExternalItemRepository        externalItemRepository;

    @Override
    public Role roleDtoToDo(ServiceContext ctx, RoleDto source) throws MetamacException {
        if (source == null) {
            return null;
        }

        // If exists, retrieves existing entity. Otherwise, creates new entity
        Role target = new Role();
        if (source.getId() != null) {
            target = accessControlBaseService.findRoleById(ctx, source.getId());
            OptimisticLockingUtils.checkVersion(target.getVersion(), source.getOptimisticLockingVersion());
        }

        roleDtoToDo(source, target);
        return target;
    }

    @Override
    public App appDtoToDo(ServiceContext ctx, AppDto source) throws MetamacException {
        if (source == null) {
            return null;
        }

        // If exists, retrieves existing entity. Otherwise, creates new entity
        App target = new App();
        if (source.getId() != null) {
            target = accessControlBaseService.findAppById(ctx, source.getId());
            OptimisticLockingUtils.checkVersion(target.getVersion(), source.getOptimisticLockingVersion());
        }

        appDtoToDo(source, target);
        return target;
    }

    @Override
    public User userDtoToDo(ServiceContext ctx, UserDto source) throws MetamacException {
        if (source == null) {
            return null;
        }

        // If exists, retrieves existing entity. Otherwise, creates new entity
        User target = new User();
        if (source.getId() != null) {
            target = accessControlBaseService.findUserById(ctx, source.getId());
            OptimisticLockingUtils.checkVersion(target.getVersion(), source.getOptimisticLockingVersion());
        }
        userDtoToDo(source, target);
        return target;
    }

    @Override
    public Access accessDtoToDo(ServiceContext ctx, AccessDto source) throws MetamacException {
        if (source == null) {
            return null;
        }

        // If exists, retrieves existing entity. Otherwise, creates new entity
        Access target = new Access();
        if (source.getId() != null) {
            target = accessControlBaseService.findAccessById(ctx, source.getId());
            OptimisticLockingUtils.checkVersion(target.getVersion(), source.getOptimisticLockingVersion());
        }
        accessDtoToDo(ctx, source, target);
        return target;
    }

    // -------------------------------------------------------------------------------------------------
    // PRIVATE METHODS
    // -------------------------------------------------------------------------------------------------

    private Role roleDtoToDo(RoleDto source, Role target) throws MetamacException {
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, ServiceExceptionParameters.ROLE);
        }

        // Non modifiables after creation

        // Attributes modifiables
        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

        // Optimistic locking: Update "update date" attribute to force update of the root entity in order to increase attribute "version"
        target.setUpdateDate(new DateTime());

        return target;
    }

    private App appDtoToDo(AppDto source, App target) throws MetamacException {
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, ServiceExceptionParameters.APP);
        }

        // Non modifiables after creation

        // Attributes modifiables
        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

        // Optimistic locking: Update "update date" attribute to force update of the root entity in order to increase attribute "version"
        target.setUpdateDate(new DateTime());

        return target;
    }

    private User userDtoToDo(UserDto source, User target) throws MetamacException {
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, ServiceExceptionParameters.USER);
        }

        // Attributes modifiables
        target.setUsername(source.getUsername());
        target.setName(source.getName());
        target.setSurname(source.getSurname());
        target.setMail(source.getMail());

        // Optimistic locking: Update "update date" attribute to force update of the root entity in order to increase attribute "version"
        target.setUpdateDate(new DateTime());

        return target;
    }

    private Access accessDtoToDo(ServiceContext ctx, AccessDto source, Access target) throws MetamacException {
        if (target == null) {
            throw new MetamacException(ServiceExceptionType.PARAMETER_REQUIRED, ServiceExceptionParameters.ACCESS);
        }

        target.setRole(roleDtoToDo(ctx, source.getRole()));
        target.setApp(appDtoToDo(ctx, source.getApp()));
        target.setUser(userDtoToDo(ctx, source.getUser()));

        target.setOperation(externalItemDtoToDo(source.getOperation(), target.getOperation(), ServiceExceptionParameters.ACCESS_OPERATION));

        // Optimistic locking: Update "update date" attribute to force update of the root entity in order to increase attribute "version"
        target.setUpdateDate(new DateTime());

        return target;
    }

    private ExternalItem externalItemDtoToDo(ExternalItemDto source, ExternalItem target, String metadataName) throws MetamacException {
        target = externalItemWithoutUrlDtoToEntity(source, target, metadataName);

        if (target != null) {
            if (TypeExternalArtefactsEnumUtils.isExternalItemOfCommonMetadataApp(source.getType())) {
                target = commonMetadataExternalItemDtoToDo(source, target);
            } else if (TypeExternalArtefactsEnumUtils.isExternalItemOfSrmApp(source.getType())) {
                target = srmExternalItemDtoToDo(source, target);
            } else if (TypeExternalArtefactsEnumUtils.isExternalItemOfStatisticalOperationsApp(source.getType())) {
                target = statisticalOperationsExternalItemDtoToDo(source, target);
            } else {
                throw new MetamacException(ServiceExceptionType.UNKNOWN, "Type of externalItem not defined for externalItemDtoToEntity: " + source.getType());
            }
        }

        return target;
    }

    private ExternalItem externalItemWithoutUrlDtoToEntity(ExternalItemDto source, ExternalItem target, String metadataName) throws MetamacException {
        if (source == null) {
            if (target != null) {
                // delete previous entity
                externalItemRepository.delete(target);
            }
            return null;
        }

        if (target == null) {
            // We set uri because it's required but the information is incorrect because it includes the base
            target = new ExternalItem(source.getCode(), source.getUri(), source.getUrn(), source.getUrnInternal(), source.getType(), internationalStringToDo(source.getTitle(), null, metadataName));
        } else {
            target.setCode(source.getCode());
            target.setUrn(source.getUrn());
            target.setUrnInternal(source.getUrnInternal());
            target.setType(source.getType());
            target.setTitle(internationalStringToDo(source.getTitle(), target.getTitle(), metadataName));
        }

        return target;
    }

    private ExternalItem commonMetadataExternalItemDtoToDo(ExternalItemDto source, ExternalItem target) throws MetamacException {
        target.setUri(commonMetadataExternalApiUrlDtoToDo(source.getUri()));
        target.setManagementAppUrl(commonMetadataInternalWebAppUrlDtoToDo(source.getManagementAppUrl()));
        return target;
    }

    private ExternalItem srmExternalItemDtoToDo(ExternalItemDto source, ExternalItem target) throws MetamacException {
        target.setUri(srmInternalApiUrlDtoToDo(source.getUri()));
        target.setManagementAppUrl(srmInternalWebAppUrlDtoToDo(source.getManagementAppUrl()));
        return target;
    }

    private ExternalItem statisticalOperationsExternalItemDtoToDo(ExternalItemDto source, ExternalItem target) throws MetamacException {
        target.setUri(statisticalOperationsInternalApiUrlDtoToDo(source.getUri()));
        target.setManagementAppUrl(statisticalOperationsInternalWebAppUrlDtoToDo(source.getManagementAppUrl()));
        return target;
    }

    // ------------------------------------------------------------
    // INTERNATIONAL STRINGS & LOCALISED STRINGS
    // ------------------------------------------------------------

    private InternationalString internationalStringToDo(InternationalStringDto source, InternationalString target, String metadataName) throws MetamacException {
        // Check it is valid
        checkInternationalStringDtoValid(source, metadataName);

        // Transform
        if (source == null) {
            if (target != null) {
                // delete previous entity
                internationalStringRepository.delete(target);
            }
            return null;
        }

        if (AccessControlValidationUtils.isEmpty(source)) {
            throw new MetamacException(ServiceExceptionType.METADATA_REQUIRED, metadataName);
        }

        if (target == null) {
            target = new InternationalString();
        }

        Set<LocalisedString> localisedStringEntities = localisedStringDtoToDo(source.getTexts(), target.getTexts());
        target.getTexts().clear();
        target.getTexts().addAll(localisedStringEntities);

        return target;
    }

    /**
     * Transform LocalisedString, reusing existing locales
     */
    private Set<LocalisedString> localisedStringDtoToDo(Set<LocalisedStringDto> sources, Set<LocalisedString> targets) {

        Set<LocalisedString> targetsBefore = targets;
        targets = new HashSet<LocalisedString>();

        for (LocalisedStringDto source : sources) {
            boolean existsBefore = false;
            for (LocalisedString target : targetsBefore) {
                if (source.getLocale().equals(target.getLocale())) {
                    targets.add(localisedStringDtoToDo(source, target));
                    existsBefore = true;
                    break;
                }
            }
            if (!existsBefore) {
                targets.add(localisedStringDtoToDo(source));
            }
        }
        return targets;
    }

    private LocalisedString localisedStringDtoToDo(LocalisedStringDto source) {
        LocalisedString target = new LocalisedString();
        target.setLabel(source.getLabel());
        target.setLocale(source.getLocale());
        return target;
    }

    private LocalisedString localisedStringDtoToDo(LocalisedStringDto source, LocalisedString target) {
        target.setLabel(source.getLabel());
        target.setLocale(source.getLocale());
        target.setIsUnmodifiable(source.getIsUnmodifiable());
        return target;
    }

}
