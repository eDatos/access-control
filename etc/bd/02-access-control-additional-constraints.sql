ALTER TABLE TBL_ROLES ADD CONSTRAINT ROLES_CODE UNIQUE(CODE) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE TBL_USERS ADD CONSTRAINT USERS_USERNAME UNIQUE(USERNAME) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE TBL_APPS ADD CONSTRAINT APPS_CODE UNIQUE(CODE) DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE TBL_ACCESS ADD CONSTRAINT ACCESS_UNIQUE UNIQUE(ROLE_FK, APP_FK, USER_FK, OPERATION_CODE_ID) DEFERRABLE INITIALLY DEFERRED;