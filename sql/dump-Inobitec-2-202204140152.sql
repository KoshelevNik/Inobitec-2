--
-- PostgreSQL database dump
--

-- Dumped from database version 10.19
-- Dumped by pg_dump version 13.3

-- Started on 2022-04-14 01:52:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2832 (class 1262 OID 16384)
-- Name: Inobitec-2; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE "Inobitec-2" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'ru';


\connect -reuse-previous=on "dbname='Inobitec-2'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- TOC entry 2833 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

--
-- TOC entry 198 (class 1259 OID 24630)
-- Name: medicineService; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."medicineService" (
    name character varying NOT NULL,
    cost integer NOT NULL,
    id integer NOT NULL,
    description text NOT NULL,
    "doctorSpecialistName" character varying NOT NULL
);


--
-- TOC entry 200 (class 1259 OID 49219)
-- Name: medicineserviceidsequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.medicineserviceidsequence
    START WITH 10007
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 196 (class 1259 OID 24619)
-- Name: order; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."order" (
    "patientId" integer NOT NULL,
    date date NOT NULL,
    id integer NOT NULL
);


--
-- TOC entry 199 (class 1259 OID 40998)
-- Name: orderItem; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."orderItem" (
    "medicineServiceId" integer NOT NULL,
    "orderId" integer NOT NULL
);


--
-- TOC entry 201 (class 1259 OID 49221)
-- Name: orderidsequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.orderidsequence
    START WITH 1002
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 197 (class 1259 OID 24622)
-- Name: patient; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.patient (
    id integer NOT NULL,
    name character varying NOT NULL,
    password character varying NOT NULL,
    mail character varying NOT NULL
);


--
-- TOC entry 202 (class 1259 OID 49223)
-- Name: patientidsequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.patientidsequence
    START WITH 10003
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2822 (class 0 OID 24630)
-- Dependencies: 198
-- Data for Name: medicineService; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public."medicineService" VALUES ('Приём врача-специалиста', 1000, 10000, 'Приём врача-специалиста', 'Карпов Анатолий Евгеньевич');
INSERT INTO public."medicineService" VALUES ('Рентген, Флюорография', 2000, 10001, 'Рентген, Флюорография', 'Николаев Николай Николаевич');
INSERT INTO public."medicineService" VALUES ('Медициниские анализы', 800, 10002, 'Медициниские анализы', 'Воробьёв Михаил Владимирович');
INSERT INTO public."medicineService" VALUES ('Осмотр врача', 200, 10003, 'Осмотр врача-специалиста', 'Игнатов Игнат Игнатович');
INSERT INTO public."medicineService" VALUES ('МРТ Головного мозга', 1200, 10004, 'МРТ Головного мозга', 'Саидов Саид Саидович');
INSERT INTO public."medicineService" VALUES ('МРТ Щитовидной железы', 1200, 10005, 'МРТ Щитовидной железы', 'Борисов Борис Борисович');
INSERT INTO public."medicineService" VALUES ('КТ Легких и органов средостения', 2300, 10006, 'КТ Легких и органов средостения', 'Гришаев Григорий Георгиевич');
INSERT INTO public."medicineService" VALUES ('УЗИ Печени и желчного пузыря', 500, 10007, 'УЗИ Печени и желчного пузыря', 'Демидов Давид Давыдов');


--
-- TOC entry 2820 (class 0 OID 24619)
-- Dependencies: 196
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public."order" VALUES (10000, '2022-03-25', 1000);
INSERT INTO public."order" VALUES (10002, '2022-07-01', 1001);


--
-- TOC entry 2823 (class 0 OID 40998)
-- Dependencies: 199
-- Data for Name: orderItem; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public."orderItem" VALUES (10003, 1000);
INSERT INTO public."orderItem" VALUES (10004, 1000);
INSERT INTO public."orderItem" VALUES (10007, 1000);
INSERT INTO public."orderItem" VALUES (10001, 1001);
INSERT INTO public."orderItem" VALUES (10002, 1001);
INSERT INTO public."orderItem" VALUES (10004, 1001);
INSERT INTO public."orderItem" VALUES (10003, 1000);
INSERT INTO public."orderItem" VALUES (10004, 1000);
INSERT INTO public."orderItem" VALUES (10007, 1000);


--
-- TOC entry 2821 (class 0 OID 24622)
-- Dependencies: 197
-- Data for Name: patient; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.patient VALUES (10000, 'Иванов Иван Иванович', '12345', 'ivanov@gmail.com');
INSERT INTO public.patient VALUES (10001, 'Алексеев Алексей Алексеевич', '54321', 'alex@yandex.ru');
INSERT INTO public.patient VALUES (10002, 'Владимиров Владимир Владимирович', '77777', 'vladimir@mail.ru');


--
-- TOC entry 2834 (class 0 OID 0)
-- Dependencies: 200
-- Name: medicineserviceidsequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.medicineserviceidsequence', 10008, true);


--
-- TOC entry 2835 (class 0 OID 0)
-- Dependencies: 201
-- Name: orderidsequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.orderidsequence', 1003, false);


--
-- TOC entry 2836 (class 0 OID 0)
-- Dependencies: 202
-- Name: patientidsequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.patientidsequence', 10004, false);


--
-- TOC entry 2693 (class 2606 OID 24629)
-- Name: patient client_id; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.patient
    ADD CONSTRAINT client_id PRIMARY KEY (id);


--
-- TOC entry 2695 (class 2606 OID 41023)
-- Name: medicineService medicineService_id; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."medicineService"
    ADD CONSTRAINT "medicineService_id" PRIMARY KEY (id);


--
-- TOC entry 2691 (class 2606 OID 41030)
-- Name: order order_id; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_id PRIMARY KEY (id);


--
-- TOC entry 2697 (class 2606 OID 41024)
-- Name: orderItem orderItem_medicineServiceId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."orderItem"
    ADD CONSTRAINT "orderItem_medicineServiceId_fkey" FOREIGN KEY ("medicineServiceId") REFERENCES public."medicineService"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2698 (class 2606 OID 41031)
-- Name: orderItem orderItem_orderId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."orderItem"
    ADD CONSTRAINT "orderItem_orderId_fkey" FOREIGN KEY ("orderId") REFERENCES public."order"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2696 (class 2606 OID 24648)
-- Name: order order_patient_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_patient_id_fkey FOREIGN KEY ("patientId") REFERENCES public.patient(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2022-04-14 01:52:41

--
-- PostgreSQL database dump complete
--

