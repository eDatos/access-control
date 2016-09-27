-- ------------------------------------------------------------------------------------------------------
-- METAMAC-2124 - Cuando se realiza una búsqueda por username los valores deben convertirse a lowercase
-- ------------------------------------------------------------------------------------------------------
update TB_USERS set username = lower(username);
commit;