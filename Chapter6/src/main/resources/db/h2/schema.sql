CREATE table SINGER(
	ID SERIAL primary key,
	FIRST_NAME VARCHAR(60) not null,
	LAST_NAME VARCHAR(40) not null,
	BIRTH_DATE DATE
	);
CREATE table ALBUM(
	ID SERIAL primary key,
	SINGER_ID INT not null references SINGER(ID),
	TITLE VARCHAR(100) not null,
	RELEASE_DATE DATE
	);
