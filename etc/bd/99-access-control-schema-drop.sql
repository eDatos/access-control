
    
-- ###########################################
-- # Drop
-- ###########################################
-- Drop index
  


-- Drop many to many relations
    
-- Drop normal entities
    
DROP TABLE TBL_ACCESS CASCADE CONSTRAINTS PURGE;

DROP TABLE TBL_USERS CASCADE CONSTRAINTS PURGE;

DROP TABLE TBL_APPS CASCADE CONSTRAINTS PURGE;

DROP TABLE TBL_ROLES CASCADE CONSTRAINTS PURGE;


-- Drop sequences
    
drop sequence SEQ_ROLES;
drop sequence SEQ_APPS;
drop sequence SEQ_USERS;
drop sequence SEQ_ACCESS;