delete from CUSTOMER;
delete from product;
delete from review;
delete from orders;
delete from users_roles;
delete from users;
delete from roles;

SET @dateFormat = 'dd/MM/yyyy';
SET @body1 = 'Its quite short and I cannot take fantastic pictures of landscapes with me. Very dissapointed';

INSERT INTO CUSTOMER (id, name, email, registered) VALUES(1, 'Robert', 'robd2000@gmail.com', PARSEDATETIME('04/07/2017', @dateFormat));
INSERT INTO customer (id, name, email, registered) VALUES(2, 'Pierre', 'pierrefontaine@yahoo.com', PARSEDATETIME('06/07/2017', @dateFormat));
INSERT INTO customer (id, name, email, registered) VALUES(3, 'Alex', 'alexander_hd@hotmail.com', PARSEDATETIME('11/07/2017', @dateFormat));
INSERT INTO customer (id, name, email, registered) VALUES(4, 'Jennifer', 'jenny01@hotmail.com', PARSEDATETIME('09/07/2017', @dateFormat));

INSERT INTO product (ID, NAME, PRICE ) VALUES(1,'King PC',1429.99);
INSERT INTO product (ID, NAME, PRICE ) VALUES(2,'Selfie stick', 7.99);
INSERT INTO product (ID, NAME, PRICE ) VALUES(3,'Samsung galaxy S7', 650);
INSERT INTO product (ID, NAME, PRICE ) VALUES(4,'Teddy bear', 21.30);

INSERT INTO PRODUCT_TAGS(TAG_ID, PRODUCT_ID, LABEL) VALUES(10, 1, 'ELECTRONICS');
INSERT INTO PRODUCT_TAGS(TAG_ID, PRODUCT_ID, LABEL) VALUES(16, 3, 'ELECTRONICS');
INSERT INTO PRODUCT_TAGS(TAG_ID, PRODUCT_ID, LABEL) VALUES(11, 1, 'COMPUTERS');
INSERT INTO PRODUCT_TAGS(TAG_ID, PRODUCT_ID, LABEL) VALUES(12, 2, 'CAMERA ACCESORIES');
INSERT INTO PRODUCT_TAGS(TAG_ID, PRODUCT_ID, LABEL) VALUES(13, 4, 'TOYS');
INSERT INTO PRODUCT_TAGS(TAG_ID, PRODUCT_ID, LABEL) VALUES(14, 4, 'KIDS');
INSERT INTO PRODUCT_TAGS(TAG_ID, PRODUCT_ID, LABEL) VALUES(15, 4, 'ENTERTAINMENT');


INSERT INTO review (ID, DATETIME ,RATE,TITLE, BODY,CUSTOMER_ID,PRODUCT_ID) VALUES(1, PARSEDATETIME('14/07/2017',@dateFormat), 5, 'King PC rocks!', 'This computer is as fast as hell',  1,1 );
INSERT INTO review (ID, DATETIME ,RATE,TITLE, BODY,CUSTOMER_ID,PRODUCT_ID) VALUES(2, PARSEDATETIME('20/07/2017',@dateFormat), 5, 'Can now play games that require loads of resources', 'Its so powerful that I finally can play games in 4K', 2, 1);
INSERT INTO review (ID, DATETIME ,RATE,TITLE, BODY,CUSTOMER_ID,PRODUCT_ID) VALUES(3, PARSEDATETIME('23/07/2017',@dateFormat), 3, 'Not very long', @body1 , 2, 2);
INSERT INTO review (ID, DATETIME ,RATE,TITLE, BODY,CUSTOMER_ID,PRODUCT_ID) VALUES(4, PARSEDATETIME('12/07/2017',@dateFormat), 1, 'Its broken', 'This is the lamest product Ive ever bought!', 3, 2);

INSERT INTO orders(ID, ORDER_ID, PRODUCT_ID, CUSTOMER_ID, DATETIME) VALUES(1, 'ABC-DEF-1230X6', 1,  1, PARSEDATETIME('11/07/2017',@dateFormat));
INSERT INTO orders(ID, ORDER_ID, PRODUCT_ID, CUSTOMER_ID, DATETIME) VALUES(2, 'GK7-009-65RT59', 1,  2, PARSEDATETIME('15/07/2017',@dateFormat));
INSERT INTO orders(ID, ORDER_ID, PRODUCT_ID, CUSTOMER_ID, DATETIME) VALUES(3, 'N20-Q64-PIRT01', 2,  3, PARSEDATETIME('16/07/2017',@dateFormat));
INSERT INTO orders(ID, ORDER_ID, PRODUCT_ID, CUSTOMER_ID, DATETIME) VALUES(4, 'BNY-Z99-O20474', 3,  2, PARSEDATETIME('20/07/2017',@dateFormat));
INSERT INTO orders(ID, ORDER_ID, PRODUCT_ID, CUSTOMER_ID, DATETIME) VALUES(5, 'N20-Q42-JOP224', 3,  4, PARSEDATETIME('21/07/2017',@dateFormat));

UPDATE product set NO_ORDERS = (SELECT count(*) from orders where PRODUCT_ID=1) WHERE ID=1;
UPDATE product set NO_ORDERS = (SELECT count(*) from orders where PRODUCT_ID=2) WHERE ID=2;
UPDATE product set NO_ORDERS = (SELECT count(*) from orders where PRODUCT_ID=3) WHERE ID=3;
UPDATE product set NO_ORDERS = (SELECT count(*) from orders where PRODUCT_ID=4) WHERE ID=4;

INSERT INTO users(ID,EMAIL, NAME, PASSWORD) VALUES(1,'r_navas@gmail.com', 'Rafael Navas', '1234');
INSERT INTO users(ID,EMAIL, NAME, PASSWORD) VALUES(2,'robd2000@gmail.com', 'Robert Dwight', 'r0000');
INSERT INTO users(ID,EMAIL, NAME, PASSWORD) VALUES(3,'alexander_hd@hotmail.com', 'Alexander Himphrey', 'al012');

INSERT INTO ROLES(ID,ROLE) VALUES(1, 'ADMIN');
INSERT INTO ROLES(ID,ROLE) VALUES(2, 'USER');

INSERT INTO users_ROLES(USERS_ID, ROLES_ID) VALUES(1, 1);
INSERT INTO users_ROLES(USERS_ID, ROLES_ID) VALUES(2, 2);
INSERT INTO users_ROLES(USERS_ID, ROLES_ID) VALUES(3, 2);