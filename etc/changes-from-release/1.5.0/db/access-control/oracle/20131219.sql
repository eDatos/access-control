-- ------------------------------------------------------------------------------------------------------------
-- METAMAC-2009 - [BBDD] Añadir a los scripts de import del access-control los aplicativos y roles que faltan
-- ------------------------------------------------------------------------------------------------------------

-- Create GET_NEXT_SEQUENCE_VALUE function

CREATE OR REPLACE FUNCTION GET_NEXT_SEQUENCE_VALUE(sequence_name_in IN VARCHAR2)
  RETURN NUMBER
  IS sequence_next_value_out NUMBER(19,0);
  BEGIN
    SELECT SEQUENCE_NEXT_VALUE INTO sequence_next_value_out
    FROM TB_SEQUENCES 
    WHERE SEQUENCE_NAME = sequence_name_in;
    RETURN (sequence_next_value_out);
  END;
/


-- Inserts
Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(GET_NEXT_SEQUENCE_VALUE('ROLES'),'JEFE_PRODUCCION','97e471ea-a599-4d3e-8517-ae5cae53995a','Jefe de Producción', 'Persona que supervisa todas las labores de producción estadística', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');
UPDATE TB_SEQUENCES SET SEQUENCE_NEXT_VALUE = SEQUENCE_NEXT_VALUE + 1 WHERE SEQUENCE_NAME = 'ROLES';

Insert Into Tb_Apps (Id, Code, Uuid, Title, Description, Version, Created_By, Created_Date, Created_Date_Tz, Last_Updated_By, Last_Updated, Last_Updated_Tz) Values 
(Get_Next_Sequence_Value('APPS'),'GESTOR_NOTIFICACIONES','ga232c30-d8dc-11e2-a28f-0800200c9a66','Gestor de notificaciones', 'Aplicativo encargado de gestionar las notificaciones que se emiten desde diferentes aplicaciones del organismo', 1, 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2012-04-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');
UPDATE TB_SEQUENCES SET SEQUENCE_NEXT_VALUE = SEQUENCE_NEXT_VALUE + 1 WHERE SEQUENCE_NAME = 'APPS';

-- Drop function
DROP FUNCTION GET_NEXT_SEQUENCE_VALUE;

commit;