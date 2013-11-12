-- ###########################################
-- # Insert
-- ###########################################

insert into TB_CONFIGURATIONS (CONF_KEY,CONF_VALUE) values ('metamac.access_control.db.driver_name','oracle.jdbc.OracleDriver');
insert into TB_CONFIGURATIONS (CONF_KEY,CONF_VALUE) values ('metamac.access_control.db.username','METAMAC_ACCESS_CONTROL');
insert into TB_CONFIGURATIONS (CONF_KEY,CONF_VALUE) values ('metamac.access_control.db.url','jdbc:oracle:thin:@localhost:1521:XE');
insert into TB_CONFIGURATIONS (CONF_KEY,CONF_VALUE) values ('metamac.access_control.db.password','METAMAC_ACCESS_CONTROL');
insert into TB_CONFIGURATIONS (CONF_KEY,CONF_VALUE) values ('metamac.access_control.user_guide.file_name','Gestor_accesos-Manual_usuario.pdf');
insert into TB_CONFIGURATIONS (CONF_KEY,CONF_VALUE) values ('metamac.access_control.db.dialect','org.siemac.metamac.hibernate.dialect.Oracle10gDialectMetamac');