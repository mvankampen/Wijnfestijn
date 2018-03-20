--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.4
-- Started on 2015-11-05 22:44:54

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2031 (class 1262 OID 24994)
-- Name: wijnfestijnDB; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "wijnfestijnDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE "wijnfestijnDB" OWNER TO postgres;


SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 179 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2034 (class 0 OID 0)
-- Dependencies: 179
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 178 (class 1259 OID 25080)
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
-- TOC entry 177 (class 1259 OID 25078)
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
-- TOC entry 2035 (class 0 OID 0)
-- Dependencies: 177
-- Name: guest_guest_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE guest_guest_id_seq OWNED BY guest.guest_id;


--
-- TOC entry 173 (class 1259 OID 24997)
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
-- TOC entry 172 (class 1259 OID 24995)
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
-- TOC entry 2036 (class 0 OID 0)
-- Dependencies: 172
-- Name: order_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE order_order_id_seq OWNED BY orders.orders_id;


--
-- TOC entry 176 (class 1259 OID 25042)
-- Name: orderline; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE orderline (
    orderline_order_id integer NOT NULL,
    orderline_wine_id integer NOT NULL,
    orderline_amount integer NOT NULL
);


ALTER TABLE orderline OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 25018)
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
-- TOC entry 174 (class 1259 OID 25016)
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
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 174
-- Name: wine_wine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wine_wine_id_seq OWNED BY wine.wine_id;


--
-- TOC entry 1904 (class 2604 OID 25083)
-- Name: guest_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY guest ALTER COLUMN guest_id SET DEFAULT nextval('guest_guest_id_seq'::regclass);


--
-- TOC entry 1899 (class 2604 OID 25000)
-- Name: orders_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orders ALTER COLUMN orders_id SET DEFAULT nextval('order_order_id_seq'::regclass);


--
-- TOC entry 1902 (class 2604 OID 25021)
-- Name: wine_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wine ALTER COLUMN wine_id SET DEFAULT nextval('wine_wine_id_seq'::regclass);


--
-- TOC entry 1915 (class 2606 OID 25091)
-- Name: guest_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY guest
    ADD CONSTRAINT guest_pk PRIMARY KEY (guest_id);


--
-- TOC entry 1909 (class 2606 OID 25003)
-- Name: order_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT order_id_pk PRIMARY KEY (orders_id);


--
-- TOC entry 1913 (class 2606 OID 25046)
-- Name: orderline_primarykey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY orderline
    ADD CONSTRAINT orderline_primarykey PRIMARY KEY (orderline_order_id, orderline_wine_id);


--
-- TOC entry 1911 (class 2606 OID 25027)
-- Name: primarykey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wine
    ADD CONSTRAINT primarykey PRIMARY KEY (wine_id);


--
-- TOC entry 1917 (class 2606 OID 25057)
-- Name: orderline_order_id_pk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orderline
    ADD CONSTRAINT orderline_order_id_pk FOREIGN KEY (orderline_order_id) REFERENCES orders(orders_id);


--
-- TOC entry 1916 (class 2606 OID 25052)
-- Name: orderline_wine_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orderline
    ADD CONSTRAINT orderline_wine_id_fk FOREIGN KEY (orderline_wine_id) REFERENCES wine(wine_id);


--
-- TOC entry 2033 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-11-05 22:44:55

--
-- PostgreSQL database dump complete
--

