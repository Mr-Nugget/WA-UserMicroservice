CREATE TABLE public.user_wa
(
    user_id bigint NOT NULL,
    firstname character varying(255) COLLATE pg_catalog."default",
    lastname character varying(255) COLLATE pg_catalog."default",
    mail character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_wa_pkey PRIMARY KEY (user_id),
    CONSTRAINT uk_72lr4bi97ecx6p128jhceanug UNIQUE (mail)
)

TABLESPACE pg_default;

ALTER TABLE public.user_wa
    OWNER to wildadventure;