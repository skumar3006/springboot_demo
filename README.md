# springboot_demo
Springboot with multiple database and spring batch
create db  user_db and excecute below query to create table:
-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    department_id bigint NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;
    
-----------------------------------------------------
create another db learn and excecute below query to add tables


-- Table: public.address

-- DROP TABLE IF EXISTS public.address;
-- Table: public.student

-- DROP TABLE IF EXISTS public.student;

CREATE TABLE IF NOT EXISTS public.student
(
    id integer NOT NULL DEFAULT nextval('student_id_seq'::regclass),
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    created_on bigint NOT NULL,
    CONSTRAINT student_pkey PRIMARY KEY (id),
    CONSTRAINT student_email_key UNIQUE (email)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.student
    OWNER to postgres;
    
CREATE TABLE IF NOT EXISTS public.address
(
    id integer NOT NULL DEFAULT nextval('address_id_seq'::regclass),
    student_id integer,
    address1 character varying(20) COLLATE pg_catalog."default",
    address2 character varying(20) COLLATE pg_catalog."default",
    state character varying(50) COLLATE pg_catalog."default",
    city character varying(50) COLLATE pg_catalog."default",
    created_on bigint,
    zip character varying(6) COLLATE pg_catalog."default",
    CONSTRAINT address_pkey PRIMARY KEY (id),
    CONSTRAINT fk_student_address FOREIGN KEY (student_id)
        REFERENCES public.student (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.address
    OWNER to postgres;
    
-- Table: public.shedlock

-- DROP TABLE IF EXISTS public.shedlock;

CREATE TABLE IF NOT EXISTS public.shedlock
(
    name character varying(64) COLLATE pg_catalog."default" NOT NULL,
    lock_until timestamp(3) without time zone,
    locked_at timestamp(3) without time zone,
    locked_by character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT shedlock_pkey PRIMARY KEY (name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.shedlock
    OWNER to postgres;
    
