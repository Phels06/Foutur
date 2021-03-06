PGDMP             
            x        	   videoclub    12.2    12.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                        0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            !           1262    16414 	   videoclub    DATABASE     �   CREATE DATABASE videoclub WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';
    DROP DATABASE videoclub;
                postgres    false            �            1259    16415    adherent    TABLE     �   CREATE TABLE public.adherent (
    id integer NOT NULL,
    prenom character varying(50),
    nom character varying(50),
    numero integer,
    rue character varying,
    "codePostal" character varying,
    ville character varying
);
    DROP TABLE public.adherent;
       public         heap    postgres    false            �            1259    16423    article    TABLE     y   CREATE TABLE public.article (
    id integer NOT NULL,
    "nbDisques" integer,
    bonus boolean,
    troisd boolean
);
    DROP TABLE public.article;
       public         heap    postgres    false            �            1259    16433    film    TABLE     p   CREATE TABLE public.film (
    id integer NOT NULL,
    titre character varying(50),
    "dateDeSortie" date
);
    DROP TABLE public.film;
       public         heap    postgres    false            �            1259    16428    realisateur    TABLE     ~   CREATE TABLE public.realisateur (
    id integer NOT NULL,
    prenom character varying(50),
    nom character varying(50)
);
    DROP TABLE public.realisateur;
       public         heap    postgres    false            �            1259    16438    realisation    TABLE     g   CREATE TABLE public.realisation (
    id_film integer NOT NULL,
    id_realisateur integer NOT NULL
);
    DROP TABLE public.realisation;
       public         heap    postgres    false            �
           2606    16422    adherent adresse_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.adherent
    ADD CONSTRAINT adresse_pkey PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.adherent DROP CONSTRAINT adresse_pkey;
       public            postgres    false    202            �
           2606    16427    article article_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.article DROP CONSTRAINT article_pkey;
       public            postgres    false    203            �
           2606    16442 /   realisation codebarre_id_film_id_realisateur_pk 
   CONSTRAINT     �   ALTER TABLE ONLY public.realisation
    ADD CONSTRAINT codebarre_id_film_id_realisateur_pk PRIMARY KEY (id_film, id_realisateur);
 Y   ALTER TABLE ONLY public.realisation DROP CONSTRAINT codebarre_id_film_id_realisateur_pk;
       public            postgres    false    206    206            �
           2606    16437    film film_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.film
    ADD CONSTRAINT film_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.film DROP CONSTRAINT film_pkey;
       public            postgres    false    205            �
           2606    16432    realisateur realisateur_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.realisateur
    ADD CONSTRAINT realisateur_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.realisateur DROP CONSTRAINT realisateur_pkey;
       public            postgres    false    204            �
           1259    16448    fki_codebarre_id_film_fk    INDEX     S   CREATE INDEX fki_codebarre_id_film_fk ON public.realisation USING btree (id_film);
 ,   DROP INDEX public.fki_codebarre_id_film_fk;
       public            postgres    false    206            �
           1259    16454    fki_codebarre_id_realisateur_fk    INDEX     a   CREATE INDEX fki_codebarre_id_realisateur_fk ON public.realisation USING btree (id_realisateur);
 3   DROP INDEX public.fki_codebarre_id_realisateur_fk;
       public            postgres    false    206            �
           2606    16455    article article_id_adherent_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_id_adherent_fk FOREIGN KEY (id) REFERENCES public.adherent(id) NOT VALID;
 H   ALTER TABLE ONLY public.article DROP CONSTRAINT article_id_adherent_fk;
       public          postgres    false    203    202    2703            �
           2606    16460    article article_id_film_fk    FK CONSTRAINT     }   ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_id_film_fk FOREIGN KEY (id) REFERENCES public.film(id) NOT VALID;
 D   ALTER TABLE ONLY public.article DROP CONSTRAINT article_id_film_fk;
       public          postgres    false    205    203    2709            �
           2606    16443     realisation codebarre_id_film_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.realisation
    ADD CONSTRAINT codebarre_id_film_fk FOREIGN KEY (id_film) REFERENCES public.film(id) NOT VALID;
 J   ALTER TABLE ONLY public.realisation DROP CONSTRAINT codebarre_id_film_fk;
       public          postgres    false    206    205    2709            �
           2606    16449 '   realisation codebarre_id_realisateur_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.realisation
    ADD CONSTRAINT codebarre_id_realisateur_fk FOREIGN KEY (id_realisateur) REFERENCES public.realisateur(id) NOT VALID;
 Q   ALTER TABLE ONLY public.realisation DROP CONSTRAINT codebarre_id_realisateur_fk;
       public          postgres    false    206    2707    204           