package org.siemac.metamac.access.control.core.serviceapi.utils;

import org.siemac.metamac.access.control.core.domain.Access;
import org.siemac.metamac.access.control.core.domain.App;
import org.siemac.metamac.access.control.core.domain.Role;
import org.siemac.metamac.access.control.core.domain.User;
import org.siemac.metamac.common.test.utils.MetamacMocks;
import org.siemac.metamac.core.common.constants.CoreCommonConstants;
import org.siemac.metamac.core.common.ent.domain.ExternalItem;
import org.siemac.metamac.core.common.ent.domain.InternationalString;
import org.siemac.metamac.core.common.ent.domain.LocalisedString;
import org.siemac.metamac.core.common.enume.domain.TypeExternalArtefactsEnum;

/**
 * Mocks
 */
public class AccessControlDoMocks extends MetamacMocks {

    public static Role createRole() {
        Role role = new Role();
        role.setCode("TECNICO_DIFUSION");
        role.setTitle("Técnico de difusión");
        role.setDescription("Técnico de difusión estadística encargado de las labores de publicación");
        return role;
    }

    public static App createApp() {
        App app = new App();
        app.setCode("METAMAC");
        app.setTitle("Usuario METAMAC");
        app.setDescription("Usuario de pruebas METAMAC");
        return app;
    }

    public static User createUser() {
        User user = new User();
        user.setUsername("rdiaada");
        user.setName("Rita");
        user.setSurname("Díaz Adán");
        user.setMail("rdiaada@arte-consultores.com");
        return user;
    }

    public static Access createAccess(App app, Role role, User user, ExternalItem operation, Boolean sendEmail) {
        Access access = new Access();
        access.setApp(app);
        access.setUser(user);
        access.setRole(role);
        access.setOperation(operation);
        access.setSendEmail(sendEmail);
        return access;
    }

    // -----------------------------------------------------------------
    // INTERNATIONAL STRING
    // -----------------------------------------------------------------

    public static InternationalString mockInternationalString() {
        InternationalString internationalString = new InternationalString();
        LocalisedString es = new LocalisedString();
        es.setLabel(mockString(10) + " en Espanol");
        es.setLocale("es");
        es.setVersion(Long.valueOf(0));
        LocalisedString en = new LocalisedString();
        en.setLabel(mockString(10) + " in English");
        en.setLocale("en");
        en.setVersion(Long.valueOf(0));
        internationalString.addText(es);
        internationalString.addText(en);
        internationalString.setVersion(Long.valueOf(0));
        return internationalString;
    }


    // -----------------------------------------------------------------
    // EXTERNAL ITEM
    // -----------------------------------------------------------------

    public static ExternalItem mockAgencyExternalItem() {
        String code = mockCode();
        return mockSrmAppExternalItem(code, mockAgencyUrn(code), TypeExternalArtefactsEnum.AGENCY);
    }

    public static ExternalItem mockExternalItem(String code, String codeNested, String uri, String urn, String urnProvider, TypeExternalArtefactsEnum type) {
        ExternalItem target = new ExternalItem();
        target.setVersion(Long.valueOf(0));
        target.setCode(code);
        target.setCodeNested(codeNested);
        target.setUri(uri);
        target.setUrn(urn);
        target.setUrnProvider(urnProvider);
        target.setType(type);
        return target;
    }

    public static ExternalItem mockExternalItem(String code, String codeNested, String uri, String urn, String urnProvider, TypeExternalArtefactsEnum type, InternationalString title,
            String managementAppUrl) {
        ExternalItem target = mockExternalItem(code, codeNested, uri, urn, urnProvider, type);
        target.setTitle(title);
        target.setManagementAppUrl(managementAppUrl);
        return target;
    }

    private static ExternalItem mockSrmAppExternalItem(String code, String urn, TypeExternalArtefactsEnum type) {
        return mockExternalItem(code, null, CoreCommonConstants.API_LATEST_WITH_SLASHES + code, urn, urn + "-provider", type, mockInternationalString(), CoreCommonConstants.URL_SEPARATOR + code);
    }
}
