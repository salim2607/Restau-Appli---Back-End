--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: boisson; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.boisson (
    id bigint NOT NULL,
    nom character varying(255),
    prix double precision,
    image_url character varying(255)
);


ALTER TABLE public.boisson OWNER TO postgres;

--
-- Name: boisson_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.boisson ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.boisson_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: menu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menu (
    id bigint NOT NULL,
    description character varying(255),
    nom character varying(255),
    image_url character varying(255)
);


ALTER TABLE public.menu OWNER TO postgres;

--
-- Name: menu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.menu ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.menu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: plat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plat (
    id bigint NOT NULL,
    description character varying(255),
    nom character varying(255),
    prix double precision,
    menu_id bigint,
    image_url character varying(255)
);


ALTER TABLE public.plat OWNER TO postgres;

--
-- Name: plat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.plat ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.plat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservation (
    id bigint NOT NULL,
    date_heure timestamp(6) without time zone,
    nom_client character varying(255),
    nombre_personnes integer,
    statut character varying(255),
    image_url character varying(255)
);


ALTER TABLE public.reservation OWNER TO postgres;

--
-- Name: reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.reservation ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: boisson; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.boisson (id, nom, prix, image_url) FROM stdin;
1	Coca-Cola	2.5	https://imgur.com/rmhzExz
2	Sprite	2.5	https://imgur.com/3b3NaDc
3	Jus d’Orange Frais	3.5	https://imgur.com/dnEpBd1
4	Thé Glacé Maison	4	https://imgur.com/PEvZEc5
\.


--
-- Data for Name: menu; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.menu (id, description, nom, image_url) FROM stdin;
1	Un menu équilibré pour les amateurs de pizzas traditionnelles.	Menu Pizza Classique	https://imgur.com/BBOZ7hb
2	Un menu premium avec des ingrédients raffinés et une boisson spéciale.	Menu Gourmet	https://imgur.com/a/pFlp7Y6
3	Une sélection de pizzas végétariennes accompagnées d’une boisson fraîche.	Menu Végétarien	https://imgur.com/RM4O227
4	Un menu premium avec des pizzas à la truffe et un vin raffiné.	Menu Spécial Truffe	https://imgur.com/26o9dDR
\.


--
-- Data for Name: plat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.plat (id, description, nom, prix, menu_id, image_url) FROM stdin;
1	Tomate, mozzarella, basilic frais	Pizza Margherita	8.5	1	https://imgur.com/yxbK3li
2	Mozzarella, chèvre, gorgonzola, parmesan	Pizza 4 Fromages	10.5	1	https://imgur.com/Owd4pji
3	Crème de truffe, mozzarella, champignons	Pizza Truffe	15	2	https://imgur.com/LLonzZd
4	Saumon fumé, crème fraîche, aneth	Pizza Saumon	14.5	2	https://imgur.com/undefined
5	Tomate, mozzarella, aubergines, poivrons, champignons	Pizza Végétarienne	11	3	https://imgur.com/RM4O227
6	Crème de truffe noire, parmesan, roquette	Pizza Spéciale Truffe	18	4	https://imgur.com/LLonzZd
\.


--
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservation (id, date_heure, nom_client, nombre_personnes, statut, image_url) FROM stdin;
\.


--
-- Name: boisson_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.boisson_id_seq', 4, true);


--
-- Name: menu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.menu_id_seq', 4, true);


--
-- Name: plat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.plat_id_seq', 6, true);


--
-- Name: reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservation_id_seq', 1, false);


--
-- Name: boisson boisson_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boisson
    ADD CONSTRAINT boisson_pkey PRIMARY KEY (id);


--
-- Name: menu menu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);


--
-- Name: plat plat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plat
    ADD CONSTRAINT plat_pkey PRIMARY KEY (id);


--
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);


--
-- Name: plat fk8npoc06dog4rvcaxb1ftne5uo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plat
    ADD CONSTRAINT fk8npoc06dog4rvcaxb1ftne5uo FOREIGN KEY (menu_id) REFERENCES public.menu(id);


--
-- PostgreSQL database dump complete
--

