--
-- PostgreSQL database dump
--

-- Dumped from database version 12.0
-- Dumped by pg_dump version 13.2

-- Started on 2021-05-21 21:14:07

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
-- TOC entry 2886 (class 1262 OID 80089)
-- Name: GESTION; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "GESTION" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Colombia.1252';


ALTER DATABASE "GESTION" OWNER TO postgres;

\connect "GESTION"

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 207 (class 1259 OID 80390)
-- Name: cantidad_materiales; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cantidad_materiales (
    id_cantidad character varying(255) NOT NULL,
    cantidad integer,
    nombre character varying(255) NOT NULL,
    identificador character varying(255) NOT NULL
);


ALTER TABLE public.cantidad_materiales OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 80398)
-- Name: edificaciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.edificaciones (
    id_edificacion character varying(255) NOT NULL,
    cantidad_dias integer NOT NULL,
    nombre character varying(255)
);


ALTER TABLE public.edificaciones OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 80195)
-- Name: materiales; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.materiales (
    id_material character varying(255) NOT NULL,
    identificador character varying(10),
    nombre character varying(60),
    stock integer NOT NULL
);


ALTER TABLE public.materiales OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 80436)
-- Name: ordenes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ordenes (
    id_orden character varying(255) NOT NULL,
    coordenadax double precision NOT NULL,
    coordenaday double precision NOT NULL,
    created_at timestamp without time zone,
    end_date timestamp without time zone NOT NULL,
    estado character varying(255),
    start_date timestamp without time zone NOT NULL,
    edificacion_id_edificacion character varying(255)
);


ALTER TABLE public.ordenes OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 80162)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id_rol integer NOT NULL,
    name character varying(20)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 80160)
-- Name: roles_id_rol_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_rol_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_rol_seq OWNER TO postgres;

--
-- TOC entry 2887 (class 0 OID 0)
-- Dependencies: 202
-- Name: roles_id_rol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_rol_seq OWNED BY public.roles.id_rol;


--
-- TOC entry 204 (class 1259 OID 80168)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id_usuario character varying(255) NOT NULL,
    email character varying(80),
    nombre character varying(60),
    password character varying(120),
    username character varying(50)
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 80176)
-- Name: usuarios_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios_roles (
    id_usuario character varying(255) NOT NULL,
    id_rol integer NOT NULL
);


ALTER TABLE public.usuarios_roles OWNER TO postgres;

--
-- TOC entry 2715 (class 2604 OID 80165)
-- Name: roles id_rol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id_rol SET DEFAULT nextval('public.roles_id_rol_seq'::regclass);


--
-- TOC entry 2878 (class 0 OID 80390)
-- Dependencies: 207
-- Data for Name: cantidad_materiales; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('d76796d0-ccb1-4a5e-bc86-61625df2690f', 100, 'Casa', 'Ce');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('f79de6a4-5d93-47e5-9d6e-f174b2d85d71', 50, 'Casa', 'Gr');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('3c031f3d-f59b-44b3-aa05-a42b5f7c48c4', 90, 'Casa', 'Ar');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('804a3494-2c6a-44b1-bc64-16dd33d734f2', 20, 'Casa', 'Ma');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('bb8bd6cd-b340-4e89-8c43-56b18e4dfa24', 100, 'Casa', 'Ad');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('6439a3a3-8c26-4b98-846b-d50fb29723e9', 50, 'Lago', 'Ce');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('382c12e2-0072-42db-ae52-3e0d4105504b', 60, 'Lago', 'Gr');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('76ea7c54-6ec3-4b28-8b80-839bd1281a4d', 80, 'Lago', 'Ar');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('6eec8ab1-f00f-494c-902d-035b60f91485', 10, 'Lago', 'Ma');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('fbc15812-7acd-462a-a785-e68b77bd6068', 20, 'Lago', 'Ad');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('ff171f06-daff-4546-934b-3f1f9a34de22', 20, 'Cancha', 'Ce');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('0df93932-5ca1-4051-a15a-fb339f4849cd', 20, 'Cancha', 'Ar');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('59fb626a-352f-47ac-aeba-b38d19f6c4f3', 20, 'Cancha', 'Ma');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('de0ec307-a916-404e-af6f-0857691773cb', 20, 'Cancha', 'Ad');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('039d33b3-52e7-4c4c-9b76-f71c2a16b7ea', 20, 'Cancha', 'Gr');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('32277ac4-7929-4fb9-82ac-8c84d78de2fc', 200, 'Edificio', 'Ce');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('241a10d1-0580-4e8c-864e-940e48f3e889', 100, 'Edificio', 'Gr');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('05d8151c-32c5-41a4-ac5d-ebbbda2d00f9', 180, 'Edificio', 'Ar');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('23cba562-80ef-4104-8bbd-d22d8230e99c', 40, 'Edificio', 'Ma');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('cc3111ec-868e-4db3-9856-a4bec7dd43c3', 200, 'Edificio', 'Ad');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('f7852973-fa91-499d-b43a-7e2c82c7e4df', 50, 'Gimnasio', 'Ce');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('fdbfbf2d-a259-4473-a303-3a2dc5d37602', 25, 'Gimnasio', 'Gr');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('30b6cde9-e18f-4b13-a466-2c6a687088ba', 45, 'Gimnasio', 'Ar');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('b23a00d3-3533-4121-b249-3213d847ce1c', 10, 'Gimnasio', 'Ma');
INSERT INTO public.cantidad_materiales (id_cantidad, cantidad, nombre, identificador) VALUES ('a2fa0f02-8374-4aaa-9011-e29a578d3dc2', 50, 'Gimnasio', 'Ad');


--
-- TOC entry 2879 (class 0 OID 80398)
-- Dependencies: 208
-- Data for Name: edificaciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.edificaciones (id_edificacion, cantidad_dias, nombre) VALUES ('7cd06110-b65e-4ba9-bfa9-bf254e76713e', 3, 'Casa');
INSERT INTO public.edificaciones (id_edificacion, cantidad_dias, nombre) VALUES ('e5647586-f139-45c1-b4db-395a3d43af9b', 2, 'Lago');
INSERT INTO public.edificaciones (id_edificacion, cantidad_dias, nombre) VALUES ('a8e0bb4c-449b-41ee-8da4-bf7aa63335a4', 1, 'Cancha');
INSERT INTO public.edificaciones (id_edificacion, cantidad_dias, nombre) VALUES ('18ab08ac-7813-43a8-9f18-da20630b3555', 6, 'Edificio');
INSERT INTO public.edificaciones (id_edificacion, cantidad_dias, nombre) VALUES ('ea835f0c-cc0f-4ac2-89ff-161ff2c09d9d', 2, 'Gimnasio');


--
-- TOC entry 2877 (class 0 OID 80195)
-- Dependencies: 206
-- Data for Name: materiales; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.materiales (id_material, identificador, nombre, stock) VALUES ('01d5f672-531c-4a4f-befe-9b3cf6de66ea', 'Ce', 'Cemento', 1000);
INSERT INTO public.materiales (id_material, identificador, nombre, stock) VALUES ('ac1e739f-6773-43f0-b6ba-d507c21b8ce8', 'Gr', 'Grava', 1000);
INSERT INTO public.materiales (id_material, identificador, nombre, stock) VALUES ('1577c666-34b8-421b-aaf8-b1d155fc5ac2', 'Ar', 'Arena', 1000);
INSERT INTO public.materiales (id_material, identificador, nombre, stock) VALUES ('0de7d81e-9b08-49cd-bcf4-c1d35060047d', 'Ma', 'Madera', 1000);
INSERT INTO public.materiales (id_material, identificador, nombre, stock) VALUES ('fccf7c6c-2f5e-407c-ac95-d31d24c5f059', 'Ad', 'Adobe', 1000);


--
-- TOC entry 2880 (class 0 OID 80436)
-- Dependencies: 209
-- Data for Name: ordenes; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2874 (class 0 OID 80162)
-- Dependencies: 203
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.roles (id_rol, name) VALUES (1, 'ROLE_USER');
INSERT INTO public.roles (id_rol, name) VALUES (2, 'ROLE_SUPERVISOR');
INSERT INTO public.roles (id_rol, name) VALUES (3, 'ROLE_ADMIN');


--
-- TOC entry 2875 (class 0 OID 80168)
-- Dependencies: 204
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2876 (class 0 OID 80176)
-- Dependencies: 205
-- Data for Name: usuarios_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2888 (class 0 OID 0)
-- Dependencies: 202
-- Name: roles_id_rol_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_rol_seq', 3, true);


--
-- TOC entry 2731 (class 2606 OID 80397)
-- Name: cantidad_materiales cantidad_materiales_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cantidad_materiales
    ADD CONSTRAINT cantidad_materiales_pkey PRIMARY KEY (id_cantidad);


--
-- TOC entry 2735 (class 2606 OID 80405)
-- Name: edificaciones edificaciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.edificaciones
    ADD CONSTRAINT edificaciones_pkey PRIMARY KEY (id_edificacion);


--
-- TOC entry 2727 (class 2606 OID 80199)
-- Name: materiales materiales_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.materiales
    ADD CONSTRAINT materiales_pkey PRIMARY KEY (id_material);


--
-- TOC entry 2739 (class 2606 OID 80443)
-- Name: ordenes ordenes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordenes
    ADD CONSTRAINT ordenes_pkey PRIMARY KEY (id_orden);


--
-- TOC entry 2717 (class 2606 OID 80167)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id_rol);


--
-- TOC entry 2737 (class 2606 OID 80417)
-- Name: edificaciones uk57n2fpgck924gqy17jkns16rv; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.edificaciones
    ADD CONSTRAINT uk57n2fpgck924gqy17jkns16rv UNIQUE (nombre);


--
-- TOC entry 2733 (class 2606 OID 80415)
-- Name: cantidad_materiales ukbvbma0cwvnpfbtcrusaddxdpo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cantidad_materiales
    ADD CONSTRAINT ukbvbma0cwvnpfbtcrusaddxdpo UNIQUE (identificador, nombre);


--
-- TOC entry 2719 (class 2606 OID 80184)
-- Name: usuarios ukkfsp0s1tflm1cwlj8idhqsad0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT ukkfsp0s1tflm1cwlj8idhqsad0 UNIQUE (email);


--
-- TOC entry 2721 (class 2606 OID 80182)
-- Name: usuarios ukm2dvbwfge291euvmk6vkkocao; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT ukm2dvbwfge291euvmk6vkkocao UNIQUE (username);


--
-- TOC entry 2729 (class 2606 OID 80201)
-- Name: materiales ukode1jb0h7r5s0fd8rt2td1imj; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.materiales
    ADD CONSTRAINT ukode1jb0h7r5s0fd8rt2td1imj UNIQUE (identificador);


--
-- TOC entry 2741 (class 2606 OID 80445)
-- Name: ordenes ukpv5swmketdj8samubjfa8ntkg; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordenes
    ADD CONSTRAINT ukpv5swmketdj8samubjfa8ntkg UNIQUE (coordenadax, coordenaday);


--
-- TOC entry 2723 (class 2606 OID 80175)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 2725 (class 2606 OID 80180)
-- Name: usuarios_roles usuarios_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_roles
    ADD CONSTRAINT usuarios_roles_pkey PRIMARY KEY (id_usuario, id_rol);


--
-- TOC entry 2746 (class 2606 OID 80446)
-- Name: ordenes fkgsifvpd81a51y0rx61jqmhxyp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordenes
    ADD CONSTRAINT fkgsifvpd81a51y0rx61jqmhxyp FOREIGN KEY (edificacion_id_edificacion) REFERENCES public.edificaciones(id_edificacion);


--
-- TOC entry 2742 (class 2606 OID 80185)
-- Name: usuarios_roles fkhcbndx0dnk4c3ww8jfg98k7el; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_roles
    ADD CONSTRAINT fkhcbndx0dnk4c3ww8jfg98k7el FOREIGN KEY (id_rol) REFERENCES public.roles(id_rol);


--
-- TOC entry 2744 (class 2606 OID 80418)
-- Name: cantidad_materiales fknjocvjwtftmu6q21n3es77yfm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cantidad_materiales
    ADD CONSTRAINT fknjocvjwtftmu6q21n3es77yfm FOREIGN KEY (nombre) REFERENCES public.edificaciones(nombre);


--
-- TOC entry 2745 (class 2606 OID 80423)
-- Name: cantidad_materiales fkoh30hifw58lh25jq39heedx4p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cantidad_materiales
    ADD CONSTRAINT fkoh30hifw58lh25jq39heedx4p FOREIGN KEY (identificador) REFERENCES public.materiales(identificador);


--
-- TOC entry 2743 (class 2606 OID 80190)
-- Name: usuarios_roles fkt5th9sao5wjukq9ij7154ktuw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_roles
    ADD CONSTRAINT fkt5th9sao5wjukq9ij7154ktuw FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario);


-- Completed on 2021-05-21 21:14:07

--
-- PostgreSQL database dump complete
--

