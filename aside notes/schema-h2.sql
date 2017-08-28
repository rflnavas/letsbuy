
DROP TABLE IF EXISTS ORDERS;
DROP TABLE IF EXISTS REVIEW;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS CUSTOMER;
DROP TABLE IF EXISTS PRODUCT_TAGS;

DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ROLES;

CREATE TABLE USERS(
    ID BIGINT,
    NAME VARCHAR(32),
    PASSWORD VARCHAR(32),
    EMAIL VARCHAR(128),
    PRIMARY KEY (ID),
);   

CREATE TABLE CUSTOMER(
    ID BIGINT,
    EMAIL VARCHAR(255) NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    REGISTERED TIMESTAMP NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE PRODUCT(
    ID BIGINT,
    NAME VARCHAR(255),
    PRICE DECIMAL(19, 2),
    NO_ORDERS BIGINT,
    PRIMARY KEY (ID)
);

CREATE TABLE REVIEW(
    ID BIGINT,
    BODY TEXT,
    DATETIME TIMESTAMP,
    RATE INTEGER NOT NULL,
    TITLE VARCHAR(255),
    CUSTOMER_ID BIGINT NOT NULL,
    PRODUCT_ID BIGINT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID),
	FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID)
);

CREATE TABLE PRODUCT_TAGS(
	TAG_ID BIGINT,
	PRODUCT_ID BIGINT NOT NULL,
	LABEL VARCHAR(64),
	PRIMARY KEY(TAG_ID),
	FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID)
);


CREATE TABLE Orders(
	ID 			BIGINT,
	ORDER_ID	VARCHAR(128) NOT NULL,
	DATETIME    TIMESTAMP NOT NULL,
	CUSTOMER_ID BIGINT NOT NULL,
	PRODUCT_ID 	BIGINT NOT NULL,
 	PRIMARY KEY (ID),
	FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID),
	FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID)
);
	


	