-- ###########################################
-- # Create
-- ###########################################
-- Create pk sequence
     


-- Create normal entities
    
CREATE TABLE TB_ROLES (
  ID NUMBER(19) NOT NULL,
  CODE VARCHAR2(255 CHAR) NOT NULL,
  TITLE VARCHAR2(255 CHAR) NOT NULL,
  DESCRIPTION VARCHAR2(4000 CHAR),
  UPDATE_DATE_TZ VARCHAR2(50 CHAR),
  UPDATE_DATE TIMESTAMP ,
  UUID VARCHAR2(36 CHAR) NOT NULL,
  CREATED_DATE_TZ VARCHAR2(50 CHAR),
  CREATED_DATE TIMESTAMP,
  CREATED_BY VARCHAR2(50 CHAR),
  LAST_UPDATED_TZ VARCHAR2(50 CHAR),
  LAST_UPDATED TIMESTAMP,
  LAST_UPDATED_BY VARCHAR2(50 CHAR),
  VERSION NUMBER(19) NOT NULL
);


CREATE TABLE TB_APPS (
  ID NUMBER(19) NOT NULL,
  CODE VARCHAR2(255 CHAR) NOT NULL,
  TITLE VARCHAR2(255 CHAR) NOT NULL,
  DESCRIPTION VARCHAR2(4000 CHAR),
  UPDATE_DATE_TZ VARCHAR2(50 CHAR),
  UPDATE_DATE TIMESTAMP ,
  UUID VARCHAR2(36 CHAR) NOT NULL,
  CREATED_DATE_TZ VARCHAR2(50 CHAR),
  CREATED_DATE TIMESTAMP,
  CREATED_BY VARCHAR2(50 CHAR),
  LAST_UPDATED_TZ VARCHAR2(50 CHAR),
  LAST_UPDATED TIMESTAMP,
  LAST_UPDATED_BY VARCHAR2(50 CHAR),
  VERSION NUMBER(19) NOT NULL
);


CREATE TABLE TB_USERS (
  ID NUMBER(19) NOT NULL,
  USERNAME VARCHAR2(255 CHAR) NOT NULL,
  NAME VARCHAR2(255 CHAR) NOT NULL,
  SURNAME VARCHAR2(255 CHAR) NOT NULL,
  MAIL VARCHAR2(255 CHAR) NOT NULL,
  UPDATE_DATE_TZ VARCHAR2(50 CHAR),
  UPDATE_DATE TIMESTAMP ,
  UUID VARCHAR2(36 CHAR) NOT NULL,
  CREATED_DATE_TZ VARCHAR2(50 CHAR),
  CREATED_DATE TIMESTAMP,
  CREATED_BY VARCHAR2(50 CHAR),
  LAST_UPDATED_TZ VARCHAR2(50 CHAR),
  LAST_UPDATED TIMESTAMP,
  LAST_UPDATED_BY VARCHAR2(50 CHAR),
  VERSION NUMBER(19) NOT NULL
);


CREATE TABLE TB_INTERNATIONAL_STRINGS (
  ID NUMBER(19) NOT NULL,
  VERSION NUMBER(19) NOT NULL
);


CREATE TABLE TB_EXTERNAL_ITEMS (
  ID NUMBER(19) NOT NULL,
  CODE VARCHAR2(255 CHAR) NOT NULL,
  CODE_NESTED VARCHAR2(255 CHAR),
  URI VARCHAR2(4000 CHAR) NOT NULL,
  URN VARCHAR2(4000 CHAR),
  URN_PROVIDER VARCHAR2(4000 CHAR),
  MANAGEMENT_APP_URL VARCHAR2(4000 CHAR),
  VERSION NUMBER(19) NOT NULL,
  TITLE_FK NUMBER(19),
  TYPE VARCHAR2(255 CHAR) NOT NULL
);


CREATE TABLE TB_ACCESS (
  ID NUMBER(19) NOT NULL,
  REMOVAL_DATE_TZ VARCHAR2(50 CHAR),
  REMOVAL_DATE TIMESTAMP ,
  UPDATE_DATE_TZ VARCHAR2(50 CHAR),
  UPDATE_DATE TIMESTAMP ,
  UUID VARCHAR2(36 CHAR) NOT NULL,
  CREATED_DATE_TZ VARCHAR2(50 CHAR),
  CREATED_DATE TIMESTAMP,
  CREATED_BY VARCHAR2(50 CHAR),
  LAST_UPDATED_TZ VARCHAR2(50 CHAR),
  LAST_UPDATED TIMESTAMP,
  LAST_UPDATED_BY VARCHAR2(50 CHAR),
  VERSION NUMBER(19) NOT NULL,
  ROLE_FK NUMBER(19) NOT NULL,
  APP_FK NUMBER(19) NOT NULL,
  USER_FK NUMBER(19) NOT NULL,
  OPERATION_FK NUMBER(19)
);


CREATE TABLE TB_LOCALISED_STRINGS (
  ID NUMBER(19) NOT NULL,
  LABEL VARCHAR2(4000 CHAR) NOT NULL,
  LOCALE VARCHAR2(255 CHAR) NOT NULL,
  IS_UNMODIFIABLE NUMBER(1,0),
  VERSION NUMBER(19) NOT NULL,
  INTERNATIONAL_STRING_FK NUMBER(19) NOT NULL
);



-- Create many to many relations
    

-- Primary keys
    
ALTER TABLE TB_ROLES ADD CONSTRAINT PK_TB_ROLES
	PRIMARY KEY (ID)
;

ALTER TABLE TB_APPS ADD CONSTRAINT PK_TB_APPS
	PRIMARY KEY (ID)
;

ALTER TABLE TB_USERS ADD CONSTRAINT PK_TB_USERS
	PRIMARY KEY (ID)
;

ALTER TABLE TB_INTERNATIONAL_STRINGS ADD CONSTRAINT PK_TB_INTERNATIONAL_STRINGS
	PRIMARY KEY (ID)
;

ALTER TABLE TB_EXTERNAL_ITEMS ADD CONSTRAINT PK_TB_EXTERNAL_ITEMS
	PRIMARY KEY (ID)
;

ALTER TABLE TB_ACCESS ADD CONSTRAINT PK_TB_ACCESS
	PRIMARY KEY (ID)
;

ALTER TABLE TB_LOCALISED_STRINGS ADD CONSTRAINT PK_TB_LOCALISED_STRINGS
	PRIMARY KEY (ID)
;

    

-- Unique constraints
     

ALTER TABLE TB_ROLES
    ADD CONSTRAINT UQ_TB_ROLES UNIQUE (UUID)
;

 

ALTER TABLE TB_APPS
    ADD CONSTRAINT UQ_TB_APPS UNIQUE (UUID)
;

 

ALTER TABLE TB_USERS
    ADD CONSTRAINT UQ_TB_USERS UNIQUE (UUID)
;

 
 
 

ALTER TABLE TB_ACCESS
    ADD CONSTRAINT UQ_TB_ACCESS UNIQUE (UUID)
;

 


-- Foreign key constraints
    

  
  
  
  
  
  
  
  
ALTER TABLE TB_EXTERNAL_ITEMS ADD CONSTRAINT FK_TB_EXTERNAL_ITEMS_TITLE_FK
	FOREIGN KEY (TITLE_FK) REFERENCES TB_INTERNATIONAL_STRINGS (ID)
;

  
ALTER TABLE TB_ACCESS ADD CONSTRAINT FK_TB_ACCESS_ROLE_FK
	FOREIGN KEY (ROLE_FK) REFERENCES TB_ROLES (ID) ON DELETE CASCADE
;
ALTER TABLE TB_ACCESS ADD CONSTRAINT FK_TB_ACCESS_APP_FK
	FOREIGN KEY (APP_FK) REFERENCES TB_APPS (ID) ON DELETE CASCADE
;
ALTER TABLE TB_ACCESS ADD CONSTRAINT FK_TB_ACCESS_USER_FK
	FOREIGN KEY (USER_FK) REFERENCES TB_USERS (ID) ON DELETE CASCADE
;
ALTER TABLE TB_ACCESS ADD CONSTRAINT FK_TB_ACCESS_OPERATION_FK
	FOREIGN KEY (OPERATION_FK) REFERENCES TB_EXTERNAL_ITEMS (ID)
;

  
ALTER TABLE TB_LOCALISED_STRINGS ADD CONSTRAINT FK_TB_LOCALISED_STRINGS_INTE13
	FOREIGN KEY (INTERNATIONAL_STRING_FK) REFERENCES TB_INTERNATIONAL_STRINGS (ID) ON DELETE CASCADE
;

  

    

-- Index


