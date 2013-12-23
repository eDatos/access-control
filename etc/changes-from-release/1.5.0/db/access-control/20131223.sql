-- --------------------------------------------------------------------------------------------------------------
-- METAMAC-2018 - El control de accesos gestionará cuándo es necesario enviar e-mail a los usuarios y cuando no
-- --------------------------------------------------------------------------------------------------------------

ALTER TABLE TB_ACCESS ADD "SEND_EMAIL" NUMBER(1,0) DEFAULT 1 NOT NULL;