-- Create normal entities
    
CREATE TABLE TB_ROLES (
  ID NUMBER(19) NOT NULL,
  CODE VARCHAR2(100) NOT NULL,
  TITLE VARCHAR2(100) NOT NULL,
  DESCRIPTION VARCHAR2(100),
  UUID VARCHAR2(36) NOT NULL,
  CREATED_DATE_TZ VARCHAR2(50)
  ,
  CREATED_DATE TIMESTAMP,
  CREATED_BY VARCHAR2(50),
  LAST_UPDATED_TZ VARCHAR2(50)
  ,
  LAST_UPDATED TIMESTAMP,
  LAST_UPDATED_BY VARCHAR2(50),
  VERSION NUMBER(19) NOT NULL
);


CREATE TABLE TB_APPS (
  ID NUMBER(19) NOT NULL,
  CODE VARCHAR2(100) NOT NULL,
  TITLE VARCHAR2(100) NOT NULL,
  DESCRIPTION VARCHAR2(100),
  UUID VARCHAR2(36) NOT NULL,
  CREATED_DATE_TZ VARCHAR2(50)
  ,
  CREATED_DATE TIMESTAMP,
  CREATED_BY VARCHAR2(50),
  LAST_UPDATED_TZ VARCHAR2(50)
  ,
  LAST_UPDATED TIMESTAMP,
  LAST_UPDATED_BY VARCHAR2(50),
  VERSION NUMBER(19) NOT NULL
);


CREATE TABLE TB_USERS (
  ID NUMBER(19) NOT NULL,
  USERNAME VARCHAR2(100) NOT NULL,
  NAME VARCHAR2(100) NOT NULL,
  SURNAME VARCHAR2(100) NOT NULL,
  MAIL VARCHAR2(100) NOT NULL,
  UUID VARCHAR2(36) NOT NULL,
  CREATED_DATE_TZ VARCHAR2(50)
  ,
  CREATED_DATE TIMESTAMP,
  CREATED_BY VARCHAR2(50),
  LAST_UPDATED_TZ VARCHAR2(50)
  ,
  LAST_UPDATED TIMESTAMP,
  LAST_UPDATED_BY VARCHAR2(50),
  VERSION NUMBER(19) NOT NULL
);


CREATE TABLE TB_ACCESS (
  ID NUMBER(19) NOT NULL,
  REMOVAL_DATE_TZ VARCHAR2(50)
  ,
  REMOVAL_DATE TIMESTAMP ,
  UUID VARCHAR2(36) NOT NULL,
  CREATED_DATE_TZ VARCHAR2(50)
  ,
  CREATED_DATE TIMESTAMP,
  CREATED_BY VARCHAR2(50),
  LAST_UPDATED_TZ VARCHAR2(50)
  ,
  LAST_UPDATED TIMESTAMP,
  LAST_UPDATED_BY VARCHAR2(50),
  VERSION NUMBER(19) NOT NULL,
  ROLE_FK NUMBER(19) NOT NULL,
  APP_FK NUMBER(19) NOT NULL,
  USER_FK NUMBER(19) NOT NULL,
  OPERATION_URI_INT VARCHAR2(255),
  OPERATION_CODE_ID VARCHAR2(255),
  OPERATION_TYPE VARCHAR2(40)
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

ALTER TABLE TB_ACCESS ADD CONSTRAINT PK_TB_ACCESS
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
    

  
  
  
  
  
  
ALTER TABLE TB_ACCESS ADD CONSTRAINT FK_TB_ACCESS_ROLE_FK
	FOREIGN KEY (ROLE_FK) REFERENCES TB_ROLES (ID) ON DELETE CASCADE
;
ALTER TABLE TB_ACCESS ADD CONSTRAINT FK_TB_ACCESS_APP_FK
	FOREIGN KEY (APP_FK) REFERENCES TB_APPS (ID) ON DELETE CASCADE
;
ALTER TABLE TB_ACCESS ADD CONSTRAINT FK_TB_ACCESS_USER_FK
	FOREIGN KEY (USER_FK) REFERENCES TB_USERS (ID) ON DELETE CASCADE
;

  

    

-- Index
  


 