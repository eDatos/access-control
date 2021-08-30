-- --------------------------------------------------------------------------------------------------
-- EDATOS-3337 - Autenticación en aplicación interna (eUsuarios)
-- --------------------------------------------------------------------------------------------------

INSERT INTO TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) VALUES
(GET_NEXT_SEQUENCE_VALUE('ROLES'), 'GESTOR', '57b1ec2d-5f1a-4bec-8573-0a38d14c2f04', 'Gestor', 'Persona con permisos de gestión de los usuarios externos', 1, 'access_control_app', to_date('2021-06-04 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2021-06-04 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');
UPDATE TB_SEQUENCES SET SEQUENCE_NEXT_VALUE = SEQUENCE_NEXT_VALUE + 1 WHERE SEQUENCE_NAME = 'ROLES';

INSERT INTO TB_APPS (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) VALUES
(GET_NEXT_SEQUENCE_VALUE('APPS'), 'GESTOR_USUARIOS_EXTERNOS','4cb47e26-5cda-480e-9ce5-4556e78441b3','Gestor de usuarios externos', 'Aplicativo encargado de gestionar los usuarios externos', 1, 'access_control_app', to_date('2021-06-04 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2021-06-04 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');
UPDATE TB_SEQUENCES SET SEQUENCE_NEXT_VALUE = SEQUENCE_NEXT_VALUE + 1 WHERE SEQUENCE_NAME = 'APPS';

-- --------------------------------------------------------------------------------------------------
-- EDATOS-3411 - Usar autenticación del ISTAC (CoETL)
-- --------------------------------------------------------------------------------------------------

INSERT INTO TB_APPS (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) VALUES
(FILL_WITH_SCHEMA_NAME.GetSequenceNextValue('APPS'), 'GESTOR_CONSOLA_ETL','cdbb5a10-78db-4454-9a45-933c7563fc8b','Gestor de consola de ETLs', 'Aplicativo encargado de gestionar y ejecutar las ETLs', 1, 'access_control_app', to_date('2021-08-27 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2021-08-27 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');
UPDATE TB_SEQUENCES SET SEQUENCE_NEXT_VALUE = SEQUENCE_NEXT_VALUE + 1 WHERE SEQUENCE_NAME = 'APPS';

commit;
