-- --------------------------------------------------------------------------------------------------
-- METAMAC-2556 Añadir rol "Lector" en control de accesos
-- --------------------------------------------------------------------------------------------------

Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(FILL_WITH_SCHEMA_NAME.GetSequenceNextValue('ROLES'), 'LECTOR','87r651f9-4190-2fgr-h789-8te0056rw412','Lector', 'Persona con permiso de lectura sobre los datos de las aplicaciones', 1, 'access_control_app', convert(datetime, '2017-01-19 09:00:00', 120), 'Europe/London', 'access_control_app', convert(datetime, '2017-01-19 09:00:00', 120), 'Europe/London');
UPDATE TB_SEQUENCES SET SEQUENCE_NEXT_VALUE = SEQUENCE_NEXT_VALUE + 1 WHERE SEQUENCE_NAME = 'ROLES';
