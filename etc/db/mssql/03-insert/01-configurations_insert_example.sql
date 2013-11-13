-- ###########################################
-- # Insert
-- ###########################################

-- DATASOURCE: ORACLE
insert into TB_CONFIGURATIONS (CONF_KEY,CONF_VALUE) values ('metamac.access_control.db.url','jdbc:sqlserver://FILL_ME_WITH_HOST:FILL_ME_WITH_PORT:XE;databaseName=FILL_ME_WITH_DATABASE_NAME');
insert into TB_CONFIGURATIONS (CONF_KEY,CONF_VALUE) values ('metamac.access_control.db.username','FILL_ME_WITH_USERNAME');
insert into TB_CONFIGURATIONS (CONF_KEY,CONF_VALUE) values ('metamac.access_control.db.password','FILL_ME_WITH_PASSWORD');
insert into TB_CONFIGURATIONS (CONF_KEY,CONF_VALUE) values ('metamac.access_control.db.driver_name','com.microsoft.sqlserver.jdbc.SQLServerDriver');
insert into TB_CONFIGURATIONS (CONF_KEY,CONF_VALUE) values ('metamac.access_control.db.dialect','org.hibernate.dialect.SQLServerDialect');

insert into TB_CONFIGURATIONS (CONF_KEY,CONF_VALUE) values ('metamac.access_control.user_guide.file_name','Gestor_accesos-Manual_usuario.pdf');
