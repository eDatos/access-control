package org.siemac.metamac.access.control.web.server.utils;

import java.util.Locale;

import org.siemac.metamac.core.common.exception.utils.TranslateExceptions;
import org.siemac.metamac.core.common.lang.LocaleUtil;
import org.springframework.stereotype.Component;

@Component
public class WebTranslateExceptions extends TranslateExceptions {

    public String getTranslatedMessage(String code, String localeString) {
        Locale locale = LocaleUtil.getLocaleFromLocaleString(localeString);
        return translateMessage(code, locale);
    }

    protected String translateMessage(String code, Locale locale) {
        return LocaleUtil.getLocalizedMessageFromBundle("i18n/messages-access_control-web", code, locale);
    }
}
