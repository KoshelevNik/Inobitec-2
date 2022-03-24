-- Adminer 4.8.1 PostgreSQL 10.19 dump

\connect "Inobitec-2";

CREATE TABLE "public"."medicineService" (
    "name" character varying NOT NULL,
    "cost" integer NOT NULL,
    "id" integer NOT NULL,
    "description" text NOT NULL,
    "doctorSpecialistName" character varying NOT NULL,
    CONSTRAINT "order_id" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "medicineService" ("name", "cost", "id", "description", "doctorSpecialistName") VALUES
('Приём врача-специалиста',	1000,	15021,	'Приём врача-специалиста',	'Карпов Анатолий Евгеньевич'),
('Рентген, Флюорография',	2000,	13402,	'Рентген, Флюорография',	'Николаев Николай Николаевич'),
('Медициниские анализы',	800,	64505,	'Медициниские анализы ',	'Воробьёв Михаил Владимирович');

CREATE TABLE "public"."order" (
    "patientId" integer NOT NULL,
    "serviceId" integer NOT NULL,
    "date" date NOT NULL
) WITH (oids = false);

INSERT INTO "order" ("patientId", "serviceId", "date") VALUES
(10000,	15021,	'2022-03-25'),
(10002,	64505,	'2022-07-01'),
(10001,	13402,	'2022-04-21');

CREATE TABLE "public"."patient" (
    "id" integer NOT NULL,
    "name" character varying NOT NULL,
    "password" character varying NOT NULL,
    "mail" character varying NOT NULL,
    CONSTRAINT "client_id" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "patient" ("id", "name", "password", "mail") VALUES
(10000,	'Иванов Иван Иванович',	'12345',	'ivanov@gmail.com'),
(10001,	'Алексеев Алексей Алексеевич',	'54321',	'alex@yandex.ru'),
(10002,	'Владимиров Владимир Владимирович',	'77777',	'vladimir@mail.ru');

ALTER TABLE ONLY "public"."order" ADD CONSTRAINT "order_patient_id_fkey" FOREIGN KEY ("patientId") REFERENCES patient(id) ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE ONLY "public"."order" ADD CONSTRAINT "order_service_id_fkey" FOREIGN KEY ("serviceId") REFERENCES "medicineService"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE;

-- 2022-03-24 21:04:19.105887+00
