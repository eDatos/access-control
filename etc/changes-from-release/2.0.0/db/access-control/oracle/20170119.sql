-- --------------------------------------------------------------------------------------------------
-- METAMAC-2556 Añadir rol "Lector" en control de accesos
-- --------------------------------------------------------------------------------------------------

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

-- Insert new role

Insert into TB_ROLES (ID, CODE, UUID, TITLE, DESCRIPTION, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
(GET_NEXT_SEQUENCE_VALUE('ROLES'),'LECTOR','87r651f9-4190-2fgr-h789-8te0056rw412','Lector', 'Persona con permiso de lectura sobre los datos de las aplicaciones', 1, 'access_control_app', to_date('2017-01-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London', 'access_control_app', to_date('2017-01-19 09:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'Europe/London');
UPDATE TB_SEQUENCES SET SEQUENCE_NEXT_VALUE = SEQUENCE_NEXT_VALUE + 1 WHERE SEQUENCE_NAME = 'ROLES';

-- Drop function
DROP FUNCTION GET_NEXT_SEQUENCE_VALUE;


commit;
