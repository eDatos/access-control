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

    public static Access createAccess(App app, Role role, User user, ExternalItem operation) {
        Access access = new Access();
        access.setApp(app);
        access.setUser(user);
        access.setRole(role);
        access.setOperation(operation);
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

    /**
     * Mock an InternationalString with one locale
     */
    public static InternationalString mockInternationalString(String locale, String label) {
        InternationalString target = new InternationalString();
        LocalisedString localisedString = new LocalisedString();
        localisedString.setLocale(locale);
        localisedString.setLabel(label);
        target.addText(localisedString);
        return target;
    }

    /**
     * Mock an InternationalString with two locales
     */
    public static InternationalString mockInternationalString(String locale01, String label01, String locale02, String label02) {
        InternationalString target = new InternationalString();
        LocalisedString localisedString01 = new LocalisedString();
        localisedString01.setLocale(locale01);
        localisedString01.setLabel(label01);
        target.addText(localisedString01);

        LocalisedString localisedString02 = new LocalisedString();
        localisedString02.setLocale(locale02);
        localisedString02.setLabel(label02);
        target.addText(localisedString02);
        return target;
    }

    // -----------------------------------------------------------------
    // EXTERNAL ITEM
    // -----------------------------------------------------------------

    public static ExternalItem mockStatisticalOperationItem() {
        String code = mockCode();
        return mockStatisticalOperationItem(code);
    }

    public static ExternalItem mockStatisticalOperationItem(String code) {
        return mockStatisticalOperationAppExternalItem(code, mockStatisticalOperationUrn(code), TypeExternalArtefactsEnum.STATISTICAL_OPERATION);
    }

    public static ExternalItem mockStatisticalOperationInstanceItem() {
        String code = mockCode();
        return mockStatisticalOperationAppExternalItem(code, mockStatisticalOperationInstanceUrn(code), TypeExternalArtefactsEnum.STATISTICAL_OPERATION_INSTANCE);
    }

    public static ExternalItem mockAgencyExternalItem() {
        String code = mockCode();
        return mockSrmAppExternalItem(code, mockAgencyUrn(code), TypeExternalArtefactsEnum.AGENCY);
    }

    public static ExternalItem mockOrganizationUnitExternalItem() {
        String code = mockCode();
        return mockSrmAppExternalItem(code, mockOrganizationUnitUrn(code), TypeExternalArtefactsEnum.ORGANISATION_UNIT);
    }

    public static ExternalItem mockConceptExternalItem() {
        String code = mockCode();
        return mockSrmAppExternalItem(code, mockConceptUrn(code), TypeExternalArtefactsEnum.CONCEPT);
    }

    public static ExternalItem mockConceptSchemeExternalItem() {
        String code = mockCode();
        return mockSrmAppExternalItem(code, mockConceptSchemeUrn(code), TypeExternalArtefactsEnum.CONCEPT_SCHEME);
    }

    public static ExternalItem mockCodeListSchemeExternalItem() {
        String code = mockCode();
        return mockSrmAppExternalItem(code, mockCodeListUrn(code), TypeExternalArtefactsEnum.CODELIST);
    }

    public static ExternalItem mockCodeExternalItem() {
        String code = mockCode();
        return mockSrmAppExternalItem(code, mockCodeUrn(code), TypeExternalArtefactsEnum.CODE);
    }

    public static ExternalItem mockDsdExternalItem() {
        String code = mockCode();
        return mockSrmAppExternalItem(code, mockDsdUrn(code), TypeExternalArtefactsEnum.DATASTRUCTURE);
    }

    public static ExternalItem mockDimensionExternalItem() {
        String code = mockCode();
        return mockSrmAppExternalItem(code, mockDimensionUrn(code), TypeExternalArtefactsEnum.DIMENSION);
    }

    public static ExternalItem mockExternalItem1(String code, String codeNested, String uri, String urn, String urnProvider, TypeExternalArtefactsEnum type) {
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

    public static ExternalItem mockExternalItem1(String code, String codeNested, String uri, String urn, String urnProvider, TypeExternalArtefactsEnum type, InternationalString title,
            String managementAppUrl) {
        ExternalItem target = mockExternalItem1(code, codeNested, uri, urn, urnProvider, type);
        target.setTitle(title);
        target.setManagementAppUrl(managementAppUrl);
        return target;
    }

    // -----------------------------------------------------------------
    // PRIVATE
    // -----------------------------------------------------------------

    private static ExternalItem mockStatisticalOperationAppExternalItem(String code, String urn, TypeExternalArtefactsEnum type) {
        return mockExternalItem1(code, null, CoreCommonConstants.API_LATEST_WITH_SLASHES + code, urn, null, type, mockInternationalString(), CoreCommonConstants.URL_SEPARATOR + code);
    }

    private static ExternalItem mockSrmAppExternalItem(String code, String urn, TypeExternalArtefactsEnum type) {
        return mockExternalItem1(code, null, CoreCommonConstants.API_LATEST_WITH_SLASHES + code, urn, urn + "-provider", type, mockInternationalString(), CoreCommonConstants.URL_SEPARATOR + code);
    }
}
