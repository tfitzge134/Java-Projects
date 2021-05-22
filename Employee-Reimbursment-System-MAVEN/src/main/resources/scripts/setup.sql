--- SEQUENCES----------------------------

-- SEQUENCE: ers.ERS_REIMBURSEMENT_REIMB_ID_seq

-- DROP SEQUENCE ers."ERS_REIMBURSEMENT_REIMB_ID_seq";

CREATE SEQUENCE ers."ERS_REIMBURSEMENT_REIMB_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE ers."ERS_REIMBURSEMENT_REIMB_ID_seq"
    OWNER TO postgres;
    
-- SEQUENCE: ers.ERS_REIMBURSEMENT_TYPE_REIMB_TYPE_ID_seq

-- DROP SEQUENCE ers."ERS_REIMBURSEMENT_TYPE_REIMB_TYPE_ID_seq";

CREATE SEQUENCE ers."ERS_REIMBURSEMENT_TYPE_REIMB_TYPE_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE ers."ERS_REIMBURSEMENT_TYPE_REIMB_TYPE_ID_seq"
    OWNER TO postgres;
    
-- SEQUENCE: ers.ERS_REIMBURSEMENT_TYPE_REIMB_TYPE_ID_seq

-- DROP SEQUENCE ers."ERS_REIMBURSEMENT_TYPE_REIMB_TYPE_ID_seq";

CREATE SEQUENCE ers."ERS_REIMBURSEMENT_TYPE_REIMB_TYPE_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE ers."ERS_REIMBURSEMENT_TYPE_REIMB_TYPE_ID_seq"
    OWNER TO postgres;
    
-- SEQUENCE: ers.ERS_USER_ROLES_ERS_USER_ROLE_ID_seq

-- DROP SEQUENCE ers."ERS_USER_ROLES_ERS_USER_ROLE_ID_seq";

CREATE SEQUENCE ers."ERS_USER_ROLES_ERS_USER_ROLE_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE ers."ERS_USER_ROLES_ERS_USER_ROLE_ID_seq"
    OWNER TO postgres;
    
-- SEQUENCE: ers.REIMB_STATUS_REIMB_STATUS_ID_seq

-- DROP SEQUENCE ers."REIMB_STATUS_REIMB_STATUS_ID_seq";

CREATE SEQUENCE ers."REIMB_STATUS_REIMB_STATUS_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE ers."REIMB_STATUS_REIMB_STATUS_ID_seq"
    OWNER TO postgres;
    
--- TABLES----------------------------
-- Table: ers.ers_user_roles

-- DROP TABLE ers.ers_user_roles;

CREATE TABLE ers.ers_user_roles
(
    ers_users_role_id integer NOT NULL DEFAULT nextval('ers."ERS_USER_ROLES_ERS_USER_ROLE_ID_seq"'::regclass),
    user_role character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT "ERS_USER_ROLES_pkey" PRIMARY KEY (ers_users_role_id)
)

TABLESPACE pg_default;

ALTER TABLE ers.ers_user_roles
    OWNER to postgres;

INSERT INTO ers.ers_user_roles(
	ers_users_role_id, user_role)
	VALUES (1, 'Employee');

INSERT INTO ers.ers_user_roles(
	ers_users_role_id, user_role)
	VALUES (2, 'Manager');
	
-- Table: ers.ers_users

-- DROP TABLE ers.ers_users;

CREATE TABLE ers.ers_users
(
    ers_users_id integer NOT NULL DEFAULT nextval('ers."ERS_USERS_ERS_USERS_ID_seq"'::regclass),
    ers_password character varying(50) COLLATE pg_catalog."default" NOT NULL,
    user_first_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    user_last_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    user_email character varying(150) COLLATE pg_catalog."default" NOT NULL,
    user_role_id integer NOT NULL,
    ers_username character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT "ERS_USERS_pkey" PRIMARY KEY (ers_users_id)
)

TABLESPACE pg_default;

ALTER TABLE ers.ers_users
    OWNER to postgres;
    
-- Table: ers.ers_reimbursement_type

-- DROP TABLE ers.ers_reimbursement_type;

CREATE TABLE ers.ers_reimbursement_type
(
    reimb_type_id integer NOT NULL DEFAULT nextval('ers."ERS_REIMBURSEMENT_TYPE_REIMB_TYPE_ID_seq"'::regclass),
    reimb_type character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT "ERS_REIMBURSEMENT_TYPE_pkey" PRIMARY KEY (reimb_type_id)
)

TABLESPACE pg_default;

ALTER TABLE ers.ers_reimbursement_type
    OWNER to postgres;
    
INSERT INTO ers.ers_reimbursement_type(
	reimb_type_id, reimb_type)
	VALUES (1, 'Travel');
INSERT INTO ers.ers_reimbursement_type(
	reimb_type_id, reimb_type)
	VALUES (2, 'Food');
INSERT INTO ers.ers_reimbursement_type(
	reimb_type_id, reimb_type)
	VALUES (3, 'Internet');

	-- Table: ers.ers_reimbursement_status

-- DROP TABLE ers.ers_reimbursement_status;

CREATE TABLE ers.ers_reimbursement_status
(
    reimb_status_id integer NOT NULL DEFAULT nextval('ers."REIMB_STATUS_REIMB_STATUS_ID_seq"'::regclass),
    reimb_status character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT "REIMB_STATUS_pkey" PRIMARY KEY (reimb_status_id)
)

TABLESPACE pg_default;

ALTER TABLE ers.ers_reimbursement_status
    OWNER to postgres;
    
    
INSERT INTO ers.ers_reimbursement_status(
	reimb_status_id, reimb_status)
	VALUES (1, 'Pending');
INSERT INTO ers.ers_reimbursement_status(
	reimb_status_id, reimb_status)
	VALUES (2, 'Approved');
INSERT INTO ers.ers_reimbursement_status(
	reimb_status_id, reimb_status)
	VALUES (3, 'Rejected');
	
-- Table: ers.ers_reimbursement

-- DROP TABLE ers.ers_reimbursement;

CREATE TABLE ers.ers_reimbursement
(
    reimb_id integer NOT NULL DEFAULT nextval('ers."ERS_REIMBURSEMENT_REIMB_ID_seq"'::regclass),
    reimb_amount numeric NOT NULL,
    reimb_submitted timestamp without time zone NOT NULL,
    reimb_resolved timestamp without time zone,
    reimb_receipt bytea,
    reimb_author integer NOT NULL,
    reimb_resolver integer,
    reimb_status_id integer NOT NULL,
    reimb_type_id integer NOT NULL,
    reimb_description character varying(250) COLLATE pg_catalog."default",
    CONSTRAINT "ERS_REIMBURSEMENT_pkey" PRIMARY KEY (reimb_id),
    CONSTRAINT "ERS_REIMBURSEMENT_STATUS_FK" FOREIGN KEY (reimb_status_id)
        REFERENCES ers.ers_reimbursement_status (reimb_status_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "ERS_REIMBURSEMENT_TYPE_FK" FOREIGN KEY (reimb_type_id)
        REFERENCES ers.ers_reimbursement_type (reimb_type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "ERS_USERS_FK_AUTH" FOREIGN KEY (reimb_author)
        REFERENCES ers.ers_users (ers_users_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "ERS_USERS_FK_RESLVR" FOREIGN KEY (reimb_resolver)
        REFERENCES ers.ers_users (ers_users_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE ers.ers_reimbursement
    OWNER to postgres;