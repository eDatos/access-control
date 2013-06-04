-- Create sequences table

CREATE TABLE TB_SEQUENCES (
	SEQUENCE_NAME VARCHAR(255) NOT NULL,
	SEQUENCE_NEXT_VALUE BIGINT
);

ALTER TABLE TB_SEQUENCES ADD CONSTRAINT PK_TB_SEQUENCES
	PRIMARY KEY (SEQUENCE_NAME)
;

-- Initialize sequences table

Insert into TB_SEQUENCES(SEQUENCE_NAME, SEQUENCE_NEXT_VALUE) VALUES ('ROLES', 1);
Insert into TB_SEQUENCES(SEQUENCE_NAME, SEQUENCE_NEXT_VALUE) VALUES ('APPS', 1);
Insert into TB_SEQUENCES(SEQUENCE_NAME, SEQUENCE_NEXT_VALUE) VALUES ('USERS', 1);
Insert into TB_SEQUENCES(SEQUENCE_NAME, SEQUENCE_NEXT_VALUE) VALUES ('ACCESS', 1);

commit;