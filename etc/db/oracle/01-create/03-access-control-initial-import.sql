-- TB_ROLES INITIAL IMPORT
Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(1,'ADMINISTRADOR','dfd78658-165a-4f15-9aff-a250f622c616','Administrador', 'Persona que puede realizar cualquier acción en los aplicativos', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(2,'TECNICO_SISTEMA_INDICADORES','e76789b0-4e01-49fd-8c6e-de2c84d01de2','Técnico de sistema de indicadores', 'Persona responsable de la producción de sistemas de indicadores', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(3,'TECNICO_APOYO_PRODUCCION','56b631f9-4099-4fae-b50d-82600ca20412','Técnico de apoyo a la producción', 'Persona que produce datos y metadatos: puede realizar tareas en las fases de  depuración, imputación, elevación y análisis de los datos recibidos, comprueba las validaciones automáticas, genera informes y genera la publicación', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(4,'TECNICO_PRODUCCION','92603846-d1d1-43f8-9471-1a1cd5a9cd6e','Técnico de producción', 'Persona que es responsable de la producción de la operación estadística: supervisa la publicación, administra instancias, envía a validación manual, envía a difundir, determina hasta cuándo estará disponible una publicación, además de todo lo que hace el técnico de apoyo a la producción estadística', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(5,'TECNICO_APOYO_DIFUSION','0b117203-6cb5-4f41-8a79-5e051ea1d3f5','Técnico de apoyo a la difusión', 'Persona que apoya al técnico de difusión', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(6,'TECNICO_DIFUSION','d8ae8da2-a366-4fd3-9b27-e231c8788fdc','Técnico de difusión', 'Persona que es responsable de validar, aceptar, generar metadatos de la publicación y publicarla', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(7,'TECNICO_PLANIFICACION','0ea8f762-ad62-4b99-8a03-eb01c6b27646','Técnico de planificación', 'Persona que es responsable de la planificación estadística', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(8,'TECNICO_APOYO_PLANIFICACION','a864b578-04e5-435f-9228-9dde6495463a','Técnico de apoyo a la planificación', 'Persona que colabora en la planificación estadística', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(9,'JEFE_NORMALIZACION','7d63c0a3-cf69-4caa-a894-0054d2d0850e','Jefe de Normalización', 'Máximo responsable de normalización estadística', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(10,'TECNICO_NORMALIZACION','32226551-ddc7-4464-90a5-93eb1685920c','Técnico de normalización', 'Persona que desarrolla labores de normalización estadística', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(11,'TECNICO_APOYO_NORMALIZACION','97e471ea-a599-4d3e-8417-ae0cae8399fa','Técnico de apoyo a la normalización', 'Persona que colabora en las labores de normalización estadística', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');


-- TB_APPS INITIAL IMPORT
Insert into TB_APPS (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(1,'GESTOR_ACCESOS','9a1c9e0d-4515-4792-b730-e200b090d90b','Gestor de control de accesos', 'Aplicativo encargado de gestionar los accesos a los diferentes aplicativos del proyecto Metamac', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_APPS (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(2,'GESTOR_INDICADORES','06986a76-ef9e-4f52-b017-1e3ffac7137a','Gestor de indicadores', 'Aplicativo encargado de gestionar sistemas de indicadores e indicadores', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_APPS (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(3,'GESTOR_OPERACIONES','21418d27-2edb-4216-9fb6-3c4d14548591','Gestor de operaciones estadísticas', 'Aplicativo encargado de gestionar familias, operaciones e instancias estadísticas', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_APPS (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(4,'GESTOR_METADATOS_COMUNES','babf9cad-399a-4221-bedf-1f43f79fc03a','Gestor de metadatos comunes', 'Aplicativo encargado de aquellos metadatos que siempre toman el mismo valor dentro de una organización', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');

Insert into TB_APPS (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(5,'GESTOR_RECURSOS_ESTRUCTURALES','6eb8c7c5-d839-40e2-bd89-a4863e402fd0','Gestor de recursos estructurales', 'Aplicativo encargado de gestionar los recursos estructurales', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');