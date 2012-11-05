-- ------------------------
-- TB_USERS INITIAL IMPORT
-- ------------------------

-- Inicialmente, debe crearse en BBDD un usuario de tipo ADMINISTRADOR que permita comenzar a operar en la aplicación.
-- El usuario seleccionado debe estar dado de alta en el LDAP.

-- Plantilla para la creación de un usuario: 
--    Insert into TB_USERS (ID, USERNAME, UUID, NAME, SURNAME, MAIL, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
--    (1,'<USERNAME>','<UUID>','<NAME>', '<SURNAME>', '<MAIL>', 1, 'access_control_app', convert(datetime, '2012-01-01 12:00:00', 120), 'Europe/London', 'access_control_app', convert(datetime, '2012-01-01 12:00:00', 120), 'Europe/London');

-- Ejemplo de creación de un usuario:
Insert into TB_USERS (USERNAME, UUID, NAME, SURNAME, MAIL, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
('METAMAC_ADMIN','94e714ea-d168-4d32-b3f4-f4097029a400','Administrador METAMAC', 'Administrador METAMAC', 'administrador-metamac@gobiernodecanarias.org', 1, 'access_control_app', convert(datetime, '2012-01-01 12:00:00', 120), 'Europe/London', 'access_control_app', convert(datetime, '2012-01-01 12:00:00', 120), 'Europe/London');


-- ------------------------
-- TB_ACCESS INITIAL IMPORT
-- ------------------------

-- Inicialmente, hay que dar a un usuario el rol de ADMINISTRADOR para el aplicativo de GESTOR_ACCESOS.
-- Tanto el usuario como el aplicativo deberá haberse creado con antelación.

-- Planitlla para la creación de un acceso:
-- Si se ha respetado el orden de inserción de elementos en BBDD las secuencias deberían coincidir con las del ejemplo y podría usarse directamente el ejemplo que se adjunta.
--    Insert into TB_ACCESS (ID, UUID, ROLE_FK, APP_FK, USER_FK, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
--    (1, '<UUID>','<ROLE_FK>', '<APP_FK>', '<USER_FK>', 1, 'access_control_app', convert(datetime, '2012-01-01 12:00:00', 120), 'Europe/London', 'access_control_app', convert(datetime, '2012-01-01 12:00:00', 120), 'Europe/London');

-- Ejemplo de creación de un acceso:
Insert into TB_ACCESS (UUID, ROLE_FK, APP_FK, USER_FK, VERSION, CREATED_BY, CREATED_DATE, CREATED_DATE_TZ, LAST_UPDATED_BY, LAST_UPDATED, LAST_UPDATED_TZ) values 
('1fa6695a-0fb0-40c6-bf77-b3198ca60a5f','1', '1', IDENT_CURRENT('TB_USERS'), 1, 'access_control_app', convert(datetime, '2012-01-01 12:00:00', 120), 'Europe/London', 'access_control_app', convert(datetime, '2012-01-01 12:00:00', 120), 'Europe/London');

-- ------------------------
-- NOTA IMPORTANTE: 
-- ------------------------
-- Hay que tener en cuenta que los UUID deben ser identificadores únicos.
-- Algunos UUID que se pueden usar son:
--     UUID 1: f0168295-27a6-4ef4-99b1-175a04184d7f
--     UUID 2: 0f16a06a-1086-45d7-8750-ba02d5f0ca91
--     UUID 3: 04e72af0-a327-4860-99a3-a04e2474608e
--     UUID 4: 1620c1d6-2ba0-4298-acc7-9f3c05dcf5c2
--     UUID 5: b99ea037-1bf3-4fab-b568-509c7fa68704
--     UUID 6: fc8097ab-e859-4a66-944f-6f9aa0e92585
--     UUID 7: bc1f4546-2dc1-4831-86d6-83218e6200bf
--     UUID 8: 0156f8c7-b7b0-40e6-a661-2514526e1c86
--     UUID 9: d37df296-9f73-4646-9bab-cd051983709e
--     UUID 10: b35e10a7-8fd1-4803-ad3a-8e78630e51f3
--     UUID 11: e7d4943b-f9eb-459f-a89a-4b8db23e6077
--     UUID 12: 05f75e95-b156-48fb-9daa-50bf8dc4348d
--     UUID 13: 26deb733-6c96-4b40-914e-4be2b9dc20f5
