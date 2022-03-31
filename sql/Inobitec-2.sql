-- Adminer 4.8.1 PostgreSQL 10.19 dump

\connect "Inobitec-2";

CREATE TABLE "public"."medicineService" (
    "name" character varying NOT NULL,
    "cost" integer NOT NULL,
    "id" integer NOT NULL,
    "description" text NOT NULL,
    "doctorSpecialistName" character varying NOT NULL,
    CONSTRAINT "medicineService_id" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "medicineService" ("name", "cost", "id", "description", "doctorSpecialistName") VALUES
('Приём врача-специалиста',	1000,	15021,	'Приём врача-специалиста',	'Карпов Анатолий Евгеньевич'),
('Рентген, Флюорография',	2000,	13402,	'Рентген, Флюорография',	'Николаев Николай Николаевич'),
('Медициниские анализы',	800,	64505,	'Медициниские анализы',	'Воробьёв Михаил Владимирович'),
('Осмотр врача',	200,	78943,	'Осмотр врача-специалиста',	'Игнатов Игнат Игнатович'),
('МРТ Головного мозга',	1200,	16557,	'МРТ Головного мозга',	'Саидов Саид Саидович'),
('МРТ Щитовидной железы',	1200,	12345,	'МРТ Щитовидной железы',	'Борисов Борис Борисович'),
('КТ Легких и органов средостения',	2300,	59731,	'КТ Легких и органов средостения',	'Гришаев Григорий Георгиевич'),
('УЗИ Печени и желчного пузыря',	500,	94316,	'УЗИ Печени и желчного пузыря',	'Демидов Давид Давыдов');

CREATE TABLE "public"."order" (
    "patientId" integer NOT NULL,
    "date" date NOT NULL,
    "id" integer NOT NULL,
    CONSTRAINT "order_id" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "order" ("patientId", "date", "id") VALUES
(10000,	'2022-03-25',	666),
(10002,	'2022-07-01',	888);

CREATE TABLE "public"."orderItem" (
    "medicineServiceId" integer NOT NULL,
    "orderId" integer NOT NULL
) WITH (oids = false);

INSERT INTO "orderItem" ("medicineServiceId", "orderId") VALUES
(16557,	666),
(78943,	666),
(94316,	666),
(64505,	888),
(13402,	888),
(16557,	888);

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

ALTER TABLE ONLY "public"."orderItem" ADD CONSTRAINT "orderItem_medicineServiceId_fkey" FOREIGN KEY ("medicineServiceId") REFERENCES "medicineService"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE;
ALTER TABLE ONLY "public"."orderItem" ADD CONSTRAINT "orderItem_orderId_fkey" FOREIGN KEY ("orderId") REFERENCES "order"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE;

-- 2022-03-31 21:23:57.180302+00
