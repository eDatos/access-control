-- -------------------------------------------------------------------------------------------------------------------
-- METAMAC-2137 - No funciona correctamente el link de descarga del manual de ayuda
-- -------------------------------------------------------------------------------------------------------------------

insert into TB_DATA_CONFIGURATIONS (ID,VERSION,SYSTEM_PROPERTY,CONF_KEY,CONF_VALUE) values (FILL_WITH_SCHEMA_NAME.GetSequenceNextValue('DATA_CONFIGURATIONS'),1,1,'metamac.data.docs.access_control.path','${metamac.data.path}/access-control/docs');
UPDATE TB_SEQUENCES SET SEQUENCE_NEXT_VALUE = SEQUENCE_NEXT_VALUE + 1 WHERE SEQUENCE_NAME = 'DATA_CONFIGURATIONS';
