--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.4
-- Started on 2016-02-01 01:32:16

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 190 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2086 (class 0 OID 0)
-- Dependencies: 190
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 73975)
-- Name: account; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE account (
    account_id integer NOT NULL,
    account_guest_id integer NOT NULL,
    account_role_name character varying(255) NOT NULL,
    account_password character varying(255) NOT NULL,
    account_on_mailinglist boolean DEFAULT true
);


ALTER TABLE account OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 73981)
-- Name: account_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE account_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE account_account_id_seq OWNER TO postgres;

--
-- TOC entry 2087 (class 0 OID 0)
-- Dependencies: 173
-- Name: account_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE account_account_id_seq OWNED BY account.account_id;


--
-- TOC entry 174 (class 1259 OID 73983)
-- Name: contentfield; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contentfield (
    content_id integer NOT NULL,
    content_name character varying(255),
    content_header character varying(255),
    content_content character varying(8000)
);


ALTER TABLE contentfield OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 73989)
-- Name: contentfield_content_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contentfield_content_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contentfield_content_id_seq OWNER TO postgres;

--
-- TOC entry 2088 (class 0 OID 0)
-- Dependencies: 175
-- Name: contentfield_content_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contentfield_content_id_seq OWNED BY contentfield.content_id;


--
-- TOC entry 176 (class 1259 OID 73991)
-- Name: event; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE event (
    event_id integer NOT NULL,
    event_name character varying(255) NOT NULL,
    event_eventdate timestamp without time zone
);


ALTER TABLE event OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 73994)
-- Name: event_event_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE event_event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE event_event_id_seq OWNER TO postgres;

--
-- TOC entry 2089 (class 0 OID 0)
-- Dependencies: 177
-- Name: event_event_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE event_event_id_seq OWNED BY event.event_id;


--
-- TOC entry 178 (class 1259 OID 73996)
-- Name: eventparticipant; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE eventparticipant (
    event_id integer NOT NULL,
    guest_id integer NOT NULL
);


ALTER TABLE eventparticipant OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 73999)
-- Name: guest; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE guest (
    guest_id integer NOT NULL,
    guest_timestamp timestamp without time zone DEFAULT ('now'::text)::timestamp without time zone NOT NULL,
    guest_lastname character varying(255) NOT NULL,
    guest_infix character varying(255),
    guest_firstname character varying(255) NOT NULL,
    guest_salutation character varying(255) NOT NULL,
    guest_street character varying(255) NOT NULL,
    guest_streetnr character varying(255) NOT NULL,
    guest_zipcode character varying(255) NOT NULL,
    guest_city character varying(255) NOT NULL,
    guest_email character varying(255) NOT NULL,
    guest_phone character varying(255),
    guest_referal character varying(255) NOT NULL,
    guest_comment character varying(500),
    guest_noshow boolean DEFAULT false NOT NULL,
    guest_active boolean DEFAULT true NOT NULL
);


ALTER TABLE guest OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 74008)
-- Name: guest_guest_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE guest_guest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE guest_guest_id_seq OWNER TO postgres;

--
-- TOC entry 2090 (class 0 OID 0)
-- Dependencies: 180
-- Name: guest_guest_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE guest_guest_id_seq OWNED BY guest.guest_id;


--
-- TOC entry 181 (class 1259 OID 74010)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE orders (
    orders_id integer NOT NULL,
    orders_guest_id integer NOT NULL,
    orders_timestamp timestamp without time zone DEFAULT ('now'::text)::timestamp without time zone NOT NULL,
    orders_completed boolean DEFAULT false NOT NULL
);


ALTER TABLE orders OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 74015)
-- Name: order_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE order_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE order_order_id_seq OWNER TO postgres;

--
-- TOC entry 2091 (class 0 OID 0)
-- Dependencies: 182
-- Name: order_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE order_order_id_seq OWNED BY orders.orders_id;


--
-- TOC entry 183 (class 1259 OID 74017)
-- Name: orderline; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE orderline (
    orderline_order_id integer NOT NULL,
    orderline_wine_id integer NOT NULL,
    orderline_amount integer NOT NULL
);


ALTER TABLE orderline OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 74020)
-- Name: promotion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE promotion (
    prm_promotion_id integer NOT NULL,
    prm_promotion_name character varying(255),
    prm_begin timestamp without time zone,
    prm_end timestamp without time zone
);


ALTER TABLE promotion OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 74023)
-- Name: promotion_prm_promotion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE promotion_prm_promotion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE promotion_prm_promotion_id_seq OWNER TO postgres;

--
-- TOC entry 2092 (class 0 OID 0)
-- Dependencies: 185
-- Name: promotion_prm_promotion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE promotion_prm_promotion_id_seq OWNED BY promotion.prm_promotion_id;


--
-- TOC entry 186 (class 1259 OID 74025)
-- Name: promotionwine; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE promotionwine (
    pw_promotion_id integer NOT NULL,
    pw_wine_id integer NOT NULL
);


ALTER TABLE promotionwine OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 74028)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE roles (
    roles_name character varying NOT NULL
);


ALTER TABLE roles OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 74034)
-- Name: wine; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wine (
    wine_id integer NOT NULL,
    wine_name character varying(255) NOT NULL,
    wine_publisher character varying(255) NOT NULL,
    wine_year character varying(255),
    wine_price numeric(4,2) NOT NULL,
    wine_rank character varying(255),
    wine_active boolean DEFAULT true NOT NULL,
    wine_category character varying(255) NOT NULL,
    wine_type character varying(255) NOT NULL,
    wine_costprice character varying(255) NOT NULL,
    wine_margin double precision
);


ALTER TABLE wine OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 74041)
-- Name: wine_wine_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wine_wine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wine_wine_id_seq OWNER TO postgres;

--
-- TOC entry 2093 (class 0 OID 0)
-- Dependencies: 189
-- Name: wine_wine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wine_wine_id_seq OWNED BY wine.wine_id;


--
-- TOC entry 1938 (class 2604 OID 74043)
-- Name: account_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account ALTER COLUMN account_id SET DEFAULT nextval('account_account_id_seq'::regclass);


--
-- TOC entry 1940 (class 2604 OID 74044)
-- Name: content_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contentfield ALTER COLUMN content_id SET DEFAULT nextval('contentfield_content_id_seq'::regclass);


--
-- TOC entry 1941 (class 2604 OID 74045)
-- Name: event_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY event ALTER COLUMN event_id SET DEFAULT nextval('event_event_id_seq'::regclass);


--
-- TOC entry 1945 (class 2604 OID 74046)
-- Name: guest_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY guest ALTER COLUMN guest_id SET DEFAULT nextval('guest_guest_id_seq'::regclass);


--
-- TOC entry 1948 (class 2604 OID 74047)
-- Name: orders_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orders ALTER COLUMN orders_id SET DEFAULT nextval('order_order_id_seq'::regclass);


--
-- TOC entry 1949 (class 2604 OID 74048)
-- Name: prm_promotion_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promotion ALTER COLUMN prm_promotion_id SET DEFAULT nextval('promotion_prm_promotion_id_seq'::regclass);


--
-- TOC entry 1951 (class 2604 OID 74049)
-- Name: wine_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wine ALTER COLUMN wine_id SET DEFAULT nextval('wine_wine_id_seq'::regclass);


--
-- TOC entry 2061 (class 0 OID 73975)
-- Dependencies: 172
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (45, 125, 'guest', 'wachtwoord', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (49, 129, 'guest', 'test', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (50, 130, 'guest', 'wachtwoord', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (51, 131, 'guest', 'wachtwoord', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (52, 132, 'guest', 'test1', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (53, 133, 'guest', 'test', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (54, 134, 'guest', 'test', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (55, 137, 'guest', 'test', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (56, 138, 'guest', 'test', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (57, 139, 'guest', 'test', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (58, 141, 'guest', 'test', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (59, 142, 'guest', 'ja1', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (60, 143, 'admin', 'test1', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (61, 144, 'admin', 'test1', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (62, 145, 'admin', 'test', NULL);
INSERT INTO account (account_id, account_guest_id, account_role_name, account_password, account_on_mailinglist) VALUES (63, 146, 'admin', 'test1', false);


--
-- TOC entry 2094 (class 0 OID 0)
-- Dependencies: 173
-- Name: account_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('account_account_id_seq', 63, true);


--
-- TOC entry 2063 (class 0 OID 73983)
-- Dependencies: 174
-- Data for Name: contentfield; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contentfield (content_id, content_name, content_header, content_content) VALUES (2, 'inlog', 'Hey daar!', NULL);
INSERT INTO contentfield (content_id, content_name, content_header, content_content) VALUES (1, 'start', 'Welkom!', 'Welkom op onze pagina! Wij hopen dat je veel plezier hebt');


--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 175
-- Name: contentfield_content_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contentfield_content_id_seq', 2, true);


--
-- TOC entry 2065 (class 0 OID 73991)
-- Dependencies: 176
-- Data for Name: event; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO event (event_id, event_name, event_eventdate) VALUES (2, 'Drink wijn', '2016-03-03 00:00:00');


--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 177
-- Name: event_event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('event_event_id_seq', 2, true);


--
-- TOC entry 2067 (class 0 OID 73996)
-- Dependencies: 178
-- Data for Name: eventparticipant; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO eventparticipant (event_id, guest_id) VALUES (1, 112);
INSERT INTO eventparticipant (event_id, guest_id) VALUES (1, 113);
INSERT INTO eventparticipant (event_id, guest_id) VALUES (2, 146);


--
-- TOC entry 2068 (class 0 OID 73999)
-- Dependencies: 179
-- Data for Name: guest; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (130, '2016-01-25 14:25:28.719823', 'sloove', NULL, 'dennis', 'Meneer', 'test', '14', 'test21', 'vifjhuizen', 'dennistest2@hotmail.nl', '0654124812', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (131, '2016-01-25 14:26:57.462594', 'sloove', NULL, 'dennis', 'Meneer', 'mient', '14', '2145tg', 'vijfhuizen', 'dennistest3@hotmail.nl', '0654125487', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (135, '2016-01-26 14:56:24.84066', 'test', 'test', 'test', 'Meneer', 'test', '1', '1234AA', 'test', 'testets@test.nl', '238472934', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (136, '2016-01-26 14:58:00.768805', 'teset', 'teset', 'test', 'Meneer', 'test', '1', '1243DN', 'tst', 'teststaasljdfa@test.nl', '2384729347239', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (137, '2016-01-26 15:00:55.078835', 'test', 'test', 'test', 'Meneer', 'test', '123', '1234AA', 'test', 'sander@test.nl', '38723987492', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (138, '2016-01-26 15:04:01.370019', 'test', 'test', 'teset', 'Meneer', 'test', '12', '2222AA', 'test', 'testesttest@test.nl', '238472934', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (139, '2016-01-26 15:14:42.959991', 'test', 'test', 'test', 'Meneer', 'test', '1', '1234AA', 'test', 'johnnytest@test.nl', '324879374', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (141, '2016-01-26 16:54:14.10058', 'tes', 'test', 'test', 'Meneer', 'test', '3', '1433NR', 'test', 'asdjflkasldf@test.nl', '2348273947', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (112, '2016-01-21 14:55:17.914872', 'testalalal', 'test', 'test', 'Meneer', 'test', '3', '20354ER', 'test', 'test@domein.nl', '23492359', 'Lions lid persoonlijk', 'daf', false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (132, '2016-01-26 12:27:02.112844', 'Jong', 'de', 'Sander', 'Meneer', 'Eenstraat', '123', '1234AA', 'Amsterdam', 'testsander@test.nl', '0987654321', 'Lions mailing', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (133, '2016-01-26 13:29:05.907131', 'test', 'test', 'test', 'Meneer', 'test', '123', '1234AA', 'test', 'tes21t@test.nl', '23847298347209', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (134, '2016-01-26 13:33:43.118517', 'test', 'test', 'test', 'Meneer', 'test', '12', '1234AA', 'test', 'test@testttt.nl', '932482438', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (140, '2016-01-26 15:51:40.336706', 'test', 'test', 'test', 'Meneer', 'test', '1', '1234AA', 'test', 'testtttt@test.nl', '3284729347', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (142, '2016-01-27 12:22:07.532316', 'straat', 'van', 'jahooo', 'Meneer', 'ASSD', '12', '1234ER', 'Heertmen', 'roelvink@nee.nl', '20592819', 'Lions mailing', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (113, '2016-01-21 14:55:18.055947', 'test', 'test', 'test', 'Meneer', 'test', '3', '20354ER', 'test', 'test@domein.nl', '23492359', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (114, '2016-01-21 14:55:18.209881', 'test', 'test', 'test', 'Meneer', 'test', '3', '20354ER', 'test', 'test@domein.nl', '23492359', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (115, '2016-01-21 14:55:18.391413', 'test', 'test', 'test', 'Meneer', 'test', '3', '20354ER', 'test', 'test@domein.nl', '23492359', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (116, '2016-01-21 14:55:18.557228', 'test', 'test', 'test', 'Meneer', 'test', '3', '20354ER', 'test', 'test@domein.nl', '23492359', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (117, '2016-01-21 14:55:18.740347', 'test', 'test', 'test', 'Meneer', 'test', '3', '20354ER', 'test', 'test@domein.nl', '23492359', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (118, '2016-01-21 14:55:18.919972', 'test', 'test', 'test', 'Meneer', 'test', '3', '20354ER', 'test', 'test@domein.nl', '23492359', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (119, '2016-01-21 14:55:19.08622', 'test', 'test', 'test', 'Meneer', 'test', '3', '20354ER', 'test', 'test@domein.nl', '23492359', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (120, '2016-01-21 14:55:19.270324', 'test', 'test', 'test', 'Meneer', 'test', '3', '20354ER', 'test', 'test@domein.nl', '23492359', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (121, '2016-01-21 14:55:19.438427', 'test', 'test', 'test', 'Meneer', 'test', '3', '20354ER', 'test', 'test@domein.nl', '23492359', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (122, '2016-01-21 14:55:19.644552', 'test', 'test', 'test', 'Meneer', 'test', '3', '20354ER', 'test', 'test@domein.nl', '23492359', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (123, '2016-01-21 15:24:13.761252', 'test', 'test', 'test', 'Mevrouw', 'test', '13413', '1234AA', 'test', 'test@test.nl', '2349873049', 'Oegeester Courant', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (125, '2016-01-25 11:26:21.509758', 'sloove', '', 'dennis', 'Meneer', 'mient', '14', '2141tc', 'vijfhuizen', 'dennistest1@hotmail.nl', '0654214875', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (129, '2016-01-25 12:15:05.35423', 'Plas', 'van der', 'Patrick', 'Meneer', 'Teststraat', '1', '1974AG', 'IJmuiden1', 'patricktest@hotmail.com', '0636115045', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (143, '2016-01-30 20:34:33.95', 'test', 'test', 'test', 'Meneer', 'test', '3', '1234ER', 'werrew', 'admin@test.nl', '06893482', 'Affiche', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (144, '2016-01-31 12:45:39.577', 'test', 'test', 'test', 'Meneer', 'test', '0', '2222ER', 'test', 'alexadmin@alex.nl', '0235984', 'Lions lid persoonlijk', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (145, '2016-01-31 15:34:52.691', 'etst', 'etst', 'etst', 'Meneer', 'etst', '3', 'test', 'test', 'test@test', 'test', 'Affiche', NULL, false, true);
INSERT INTO guest (guest_id, guest_timestamp, guest_lastname, guest_infix, guest_firstname, guest_salutation, guest_street, guest_streetnr, guest_zipcode, guest_city, guest_email, guest_phone, guest_referal, guest_comment, guest_noshow, guest_active) VALUES (146, '2016-01-31 15:38:24.411', 'wal', 'van der', 'Alex', 'Meneer', 'waddenstraat', '793', '2036lx', 'haarlem', 'amvvanderwal@live.nl', '0694359234', 'Oegeester Courant', NULL, false, true);


--
-- TOC entry 2097 (class 0 OID 0)
-- Dependencies: 180
-- Name: guest_guest_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('guest_guest_id_seq', 146, true);


--
-- TOC entry 2098 (class 0 OID 0)
-- Dependencies: 182
-- Name: order_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('order_order_id_seq', 4, true);


--
-- TOC entry 2072 (class 0 OID 74017)
-- Dependencies: 183
-- Data for Name: orderline; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO orderline (orderline_order_id, orderline_wine_id, orderline_amount) VALUES (1, 1, 1);
INSERT INTO orderline (orderline_order_id, orderline_wine_id, orderline_amount) VALUES (1, 2, 2);
INSERT INTO orderline (orderline_order_id, orderline_wine_id, orderline_amount) VALUES (4, 1, 3);
INSERT INTO orderline (orderline_order_id, orderline_wine_id, orderline_amount) VALUES (4, 1, 3);
INSERT INTO orderline (orderline_order_id, orderline_wine_id, orderline_amount) VALUES (4, 1, 3);
INSERT INTO orderline (orderline_order_id, orderline_wine_id, orderline_amount) VALUES (4, 2, 3);
INSERT INTO orderline (orderline_order_id, orderline_wine_id, orderline_amount) VALUES (4, 2, 3);
INSERT INTO orderline (orderline_order_id, orderline_wine_id, orderline_amount) VALUES (4, 20, 3);


--
-- TOC entry 2070 (class 0 OID 74010)
-- Dependencies: 181
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO orders (orders_id, orders_guest_id, orders_timestamp, orders_completed) VALUES (1, 1, '2016-01-07 13:49:45.865636', false);
INSERT INTO orders (orders_id, orders_guest_id, orders_timestamp, orders_completed) VALUES (2, 1, '2016-01-14 08:29:03.602609', false);
INSERT INTO orders (orders_id, orders_guest_id, orders_timestamp, orders_completed) VALUES (3, 1, '2016-01-14 09:27:35.16367', false);
INSERT INTO orders (orders_id, orders_guest_id, orders_timestamp, orders_completed) VALUES (4, 143, '2016-01-30 21:05:01.539', false);


--
-- TOC entry 2073 (class 0 OID 74020)
-- Dependencies: 184
-- Data for Name: promotion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO promotion (prm_promotion_id, prm_promotion_name, prm_begin, prm_end) VALUES (37, 'yes', '2016-01-01 00:00:00', '2016-12-12 00:00:00');


--
-- TOC entry 2099 (class 0 OID 0)
-- Dependencies: 185
-- Name: promotion_prm_promotion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('promotion_prm_promotion_id_seq', 37, true);


--
-- TOC entry 2075 (class 0 OID 74025)
-- Dependencies: 186
-- Data for Name: promotionwine; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (2, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (2, 2);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (3, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (4, 2);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (5, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (6, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (7, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (8, 2);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (9, 2);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (10, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (11, 2);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (15, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (16, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (17, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (18, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (19, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (32, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (32, 2);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (33, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (33, 2);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (34, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (34, 20);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (35, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (35, 2);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (35, 20);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (36, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (36, 2);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (36, 20);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (37, 1);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (37, 2);
INSERT INTO promotionwine (pw_promotion_id, pw_wine_id) VALUES (37, 20);


--
-- TOC entry 2076 (class 0 OID 74028)
-- Dependencies: 187
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO roles (roles_name) VALUES ('guest');
INSERT INTO roles (roles_name) VALUES ('admin');
INSERT INTO roles (roles_name) VALUES ('member');
INSERT INTO roles (roles_name) VALUES ('manager');


--
-- TOC entry 2077 (class 0 OID 74034)
-- Dependencies: 188
-- Data for Name: wine; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO wine (wine_id, wine_name, wine_publisher, wine_year, wine_price, wine_rank, wine_active, wine_category, wine_type, wine_costprice, wine_margin) VALUES (2, 'Grüner Veltliner Löss', 'Hiedlee', '2012', 12.10, NULL, true, 'Oostenrijk', 'Wit', '10.5', 0);
INSERT INTO wine (wine_id, wine_name, wine_publisher, wine_year, wine_price, wine_rank, wine_active, wine_category, wine_type, wine_costprice, wine_margin) VALUES (20, 'Herwe', 'nee', '2012', 1.00, '12', true, 'niet', 'jatochneetoch', '2', 2);


--
-- TOC entry 2100 (class 0 OID 0)
-- Dependencies: 189
-- Name: wine_wine_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('wine_wine_id_seq', 20, true);


--
-- TOC entry 2085 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-02-01 01:32:16

--
-- PostgreSQL database dump complete
--

