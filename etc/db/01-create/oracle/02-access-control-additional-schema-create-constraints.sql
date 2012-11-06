create table TB_SEQUENCES (
	SEQUENCE_NAME VARCHAR2(255) NOT NULL,
	SEQUENCE_NEXT_VALUE NUMBER(19)
);

ALTER TABLE TB_SEQUENCES ADD CONSTRAINT PK_TB_SEQUENCES
	PRIMARY KEY (SEQUENCE_NAME)
;

ALTER TABLE TB_ROLES ADD CONSTRAINT ROLES_CODE UNIQUE(CODE) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE TB_USERS ADD CONSTRAINT USERS_USERNAME UNIQUE(USERNAME) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE TB_APPS ADD CONSTRAINT APPS_CODE UNIQUE(CODE) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE TB_ACCESS ADD CONSTRAINT ACCESS_UNIQUE UNIQUE(ROLE_FK, APP_FK, USER_FK, OPERATION_FK, REMOVAL_DATE) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE TB_LOCALISED_STRINGS ADD CONSTRAINT LOCALE_INTERNATIONAL UNIQUE(LOCALE, INTERNATIONAL_STRING_FK) DEFERRABLE INITIALLY DEFERRED;