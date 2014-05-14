package org.siemac.metamac.access.control.web.server.rest;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.core.Response;

import org.fornax.cartridges.sculptor.framework.errorhandling.ServiceContext;
import org.siemac.metamac.access.control.core.dto.AccessDto;
import org.siemac.metamac.access.control.core.notices.ServiceNoticeAction;
import org.siemac.metamac.access.control.core.notices.ServiceNoticeMessage;
import org.siemac.metamac.core.common.lang.LocaleUtil;
import org.siemac.metamac.core.common.util.ServiceContextUtils;
import org.siemac.metamac.rest.notices.v1_0.domain.Notice;
import org.siemac.metamac.rest.notices.v1_0.domain.enume.MetamacApplicationsEnum;
import org.siemac.metamac.rest.notices.v1_0.domain.utils.NoticeBuilder;
import org.siemac.metamac.web.common.server.rest.utils.RestExceptionUtils;
import org.siemac.metamac.web.common.shared.exception.MetamacWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(NoticesRestInternalService.BEAN_ID)
public class NoticesRestInternalFacadeImpl implements NoticesRestInternalService {

    @Autowired
    private RestApiLocator     restApiLocator;

    @Autowired
    private RestExceptionUtils restExceptionUtils;

    // ---------------------------------------------------------------------------------
    // NOTIFICATIONS
    // ---------------------------------------------------------------------------------

    @Override
    public void createNotificationForSaveAccessList(ServiceContext ctx, List<AccessDto> accessList) throws MetamacWebException {
        createNotification(ctx, ServiceNoticeAction.ACCESS_CREATED, ServiceNoticeMessage.ACCESS_CREATED_OK, accessList);
    }

    @Override
    public void createNotificationForDeleteAccessList(ServiceContext ctx, List<AccessDto> accessList) throws MetamacWebException {
        createNotification(ctx, ServiceNoticeAction.ACCESS_DELETED, ServiceNoticeMessage.ACCESS_DELETED_OK, accessList);
    }

    private void createNotification(ServiceContext ctx, String actionCode, String messageCode, List<AccessDto> accessList) throws MetamacWebException {
        // All access are allways for the same user
        String receiver = accessList.get(0).getUser().getUsername();

        Locale locale = ServiceContextUtils.getLocale(ctx);
        String localisedAction = LocaleUtil.getMessageForCode(actionCode, locale);

        String sendingApp = MetamacApplicationsEnum.GESTOR_ACCESOS.getName();
        String subject = "[" + sendingApp + "] " + localisedAction;

        List<String> translatedMessages = processMessages(ctx, messageCode, accessList);
        String[] messages = new String[translatedMessages.size()];
        messages = translatedMessages.toArray(messages);

        // @formatter:off
        Notice notification = NoticeBuilder.notification()
                                            .withMessagesWithoutResources(messages)
                                            .withSendingApplication(sendingApp)
                                            .withSendingUser(ctx.getUserId())
                                            .withSubject(subject)
                                            .withReceivers(receiver)
                                            .build();
        // @formatter:on

        Response response = restApiLocator.getNoticesRestInternalFacadeV10().createNotice(notification);
        restExceptionUtils.checkSendNotificationRestResponseAndThrowErrorIfApplicable(ctx, response);
    }

    private List<String> processMessages(ServiceContext ctx, String messageCode, List<AccessDto> accessDto) {
        List<String> messages = new ArrayList<String>();
        for (AccessDto access : accessDto) {
            messages.add(createTranslatedMessage(ctx, messageCode, access));
        }
        return messages;
    }

    private String createTranslatedMessage(ServiceContext ctx, String messageCode, AccessDto access) {
        Locale locale = ServiceContextUtils.getLocale(ctx);
        String localisedMessage = LocaleUtil.getMessageForCode(messageCode, locale);
        // User = {0}, Role = {1}, App = {2}, Operation = {3}
        localisedMessage = MessageFormat.format(localisedMessage, access.getUser().getUsername(), access.getRole().getTitle(), access.getApp().getTitle(), access.getOperation().getCode());
        return localisedMessage;
    }

}
