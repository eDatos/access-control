
    
-- ###########################################
-- # Drop
-- ###########################################
-- Drop index


-- Drop many to many relations
    
-- Drop normal entities
    
DROP TABLE TB_LOCALISED_STRINGS CASCADE;

DROP TABLE TB_ACCESS CASCADE;

DROP TABLE TB_EXTERNAL_ITEMS CASCADE;

DROP TABLE TB_INTERNATIONAL_STRINGS CASCADE;

DROP TABLE TB_USERS CASCADE;

DROP TABLE TB_APPS CASCADE;

DROP TABLE TB_ROLES CASCADE;


-- Drop sequences

drop sequence SEQ_EXTERNAL_ITEMS;
drop sequence SEQ_L10NSTRS;
drop sequence SEQ_I18NSTRS;
drop sequence SEQ_ROLES;
drop sequence SEQ_APPS;
drop sequence SEQ_USERS;
drop sequence SEQ_ACCESS;