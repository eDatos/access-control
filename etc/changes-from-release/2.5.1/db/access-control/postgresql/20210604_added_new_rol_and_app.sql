-- --------------------------------------------------------------------------------------------------
-- EDATOS-3337 - Autenticación en aplicación interna (eUsuarios)
-- --------------------------------------------------------------------------------------------------

INSERT INTO TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) VALUES
((SELECT GET_NEXT_SEQUENCE_VALUE('ROLES')), 'GESTOR', '57b1ec2d-5f1a-4bec-8573-0a38d14c2f04', 'Gestor', 'Persona con permisos de gestión de los usuarios externos', 1, 'access_control_app', current_timestamp, 'Europe/London', 'access_control_app', current_timestamp, 'Europe/London');
UPDATE TB_SEQUENCES SET SEQUENCE_NEXT_VALUE = SEQUENCE_NEXT_VALUE + 1 WHERE SEQUENCE_NAME = 'ROLES';

INSERT INTO TB_APPS (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) VALUES
((SELECT GET_NEXT_SEQUENCE_VALUE('APPS')), 'GESTOR_USUARIOS_EXTERNOS','4cb47e26-5cda-480e-9ce5-4556e78441b3','Gestor de usuarios externos', 'Aplicativo encargado de gestionar los usuarios externos', 1, 'access_control_app', current_timestamp, 'Europe/London', 'access_control_app', current_timestamp, 'Europe/London');
UPDATE TB_SEQUENCES SET SEQUENCE_NEXT_VALUE = SEQUENCE_NEXT_VALUE + 1 WHERE SEQUENCE_NAME = 'APPS';

commit;
