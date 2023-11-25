CREATE TABLE SINGER (
    ID INT NOT NULL AUTO_INCREMENT
    , FIRST_NAME VARCHAR(60) NOT NULL
    , LAST_NAME VARCHAR(40) NOT NULL
    , BIRTH_DATE DATE
    , VERSION INT NOT NULL DEFAULT 0
    , UNIQUE UQ_SINGER_1 (FIRST_NAME, LAST_NAME)
    , PRIMARY KEY(ID)
);

CREATE TABLE ALBUM (
    ID INT NOT NULL AUTO_INCREMENT
    , SINGER_ID INT NOT NULL
    , TITLE VARCHAR(100) NOT NULL
    , RELEASE_DATE DATE
    , VERSION INT NOT NULL DEFAULT 0
    , UNIQUE UQ_SINGER_ALBUM_1 (SINGER_ID, TITLE)
    , PRIMARY KEY (ID)
    , CONSTRAINT FK_ALBUM_SINGER FOREIGN KEY (SINGER_ID)
    REFERENCES SINGER (ID)
);

CREATE TABLE INSTRUMENT (
    INSTRUMENT_ID VARCHAR(20) NOT NULL
    , PRIMARY KEY (INSTRUMENT_ID)
);

CREATE TABLE SINGER_INSTRUMENT (
    SINGER_ID INT NOT NULL
    , INSTRUMENT_ID VARCHAR(20) NOT NULL
    , PRIMARY KEY (SINGER_ID, INSTRUMENT_ID)
    , CONSTRAINT FK_SINGER_INSTRUMENT_1 FOREIGN KEY (SINGER_ID)
    REFERENCES SINGER (ID) ON DELETE CASCADE
    , CONSTRAINT FK_SINGER_INSTRUMENT_2 FOREIGN KEY (INSTRUMENT_ID)
    REFERENCES INSTRUMENT (INSTRUMENT_ID)
);

--CREATE table SINGER(
--	ID SERIAL primary key,
--	FIRST_NAME VARCHAR(60) not null,
--	LAST_NAME VARCHAR(40) not null,
--	BIRTH_DATE DATE,
--	VERSION INT not null DEFAULT 0
--	);
--
--CREATE table ALBUM(
--	ID SERIAL primary key,
--	SINGER_ID INT not null references SINGER(ID),
--	TITLE VARCHAR(100) not null,
--	RELEASE_DATE DATE,
--	VERSION INT not null DEFAULT 0
--	);
--
--CREATE table INSTRUMENT(
--	INSTRUMENT_ID VARCHAR(20) primary key
--	);
--
--CREATE table SINGER_INSTRUMENT(
--	SINGER_ID INT not null references SINGER(ID) ON DELETE CASCADE,
--	INSTRUMENT_ID VARCHAR(20) not null references INSTRUMENT(INSTRUMENT_ID)
--	);

--CREATE OR REPLACE FUNCTION getFirstNameById(in_id INT)
--	RETURNS VARCHAR(60) AS $$
--	BEGIN
--  		RETURN ( SELECT first_name FROM singer WHERE id = in_id);
--	END
--$$ LANGUAGE plpgsql;
--
--SELECT * FROM getFirstNameById(1);