package org.siemac.metamac.access.control.web.server.rest.utils;

import org.siemac.metamac.core.common.dto.ExternalItemDto;
import org.siemac.metamac.core.common.enume.domain.TypeExternalArtefactsEnum;
import org.siemac.metamac.rest.statistical_operations_internal.v1_0.domain.ResourceInternal;
import org.siemac.metamac.web.common.server.utils.DtoUtils;

public class ExternalItemUtils extends org.siemac.metamac.web.common.client.utils.ExternalItemUtils {

    public static ExternalItemDto getExternalItemDtoFromStatisticalOperationsResourceInternal(ResourceInternal resourceInternal) {
        ExternalItemDto externalItemDto = new ExternalItemDto();
        externalItemDto.setCode(resourceInternal.getId());
        externalItemDto.setCodeNested(resourceInternal.getNestedId());
        externalItemDto.setUri(resourceInternal.getSelfLink().getHref());
        externalItemDto.setUrn(resourceInternal.getUrn());
        externalItemDto.setType(TypeExternalArtefactsEnum.fromValue(resourceInternal.getKind()));
        externalItemDto.setTitle(DtoUtils.getInternationalStringDtoFromInternationalString(resourceInternal.getName()));
        externalItemDto.setManagementAppUrl(resourceInternal.getManagementAppLink());
        return externalItemDto;
    }
}
