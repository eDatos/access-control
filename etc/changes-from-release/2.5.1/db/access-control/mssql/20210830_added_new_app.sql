-- --------------------------------------------------------------------------------------------------
-- EDATOS-3411 - Usar autenticación del ISTAC (CoETL)
-- --------------------------------------------------------------------------------------------------

INSERT INTO TB_APPS (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) VALUES
(FILL_WITH_SCHEMA_NAME.GetSequenceNextValue('APPS'), 'GESTOR_CONSOLA_ETL','cdbb5a10-78db-4454-9a45-933c7563fc8b','Gestor de consola de ETLs', 'Aplicativo encargado de gestionar y ejecutar las ETLs', 1, 'access_control_app', convert(datetime, '2021-08-27 09:00:00', 120), 'Europe/London', 'access_control_app', convert(datetime, '2021-08-27 09:00:00', 120), 'Europe/London');
UPDATE TB_SEQUENCES SET SEQUENCE_NEXT_VALUE = SEQUENCE_NEXT_VALUE + 1 WHERE SEQUENCE_NAME = 'APPS';

commit;
