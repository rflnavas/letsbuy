
/*delete from CUSTOMER;
delete from product;
delete from review;
*/

INSERT INTO customer (id, name, email, registered) VALUES(1, 'Robert', 'robd2000@gmail.com', PARSEDATETIME('04/07/2017', 'dd/MM/yyyy'));
INSERT INTO customer (id, name, email, registered) VALUES(2, 'Pierre', 'pierrefontaine@yahoo.com', PARSEDATETIME('06/07/2017', 'dd/MM/yyyy'));
INSERT INTO customer (id, name, email, registered) VALUES(3, 'Alex', 'robd2000@hotmail.com', PARSEDATETIME('11/07/2017', 'dd/MM/yyyy'));

INSERT INTO product (ID, NAME, PRICE ) VALUES(1,'King PC',1429.99);
INSERT INTO product (ID, NAME, PRICE ) VALUES(2,'Selfie stick', 7.99);

INSERT INTO review (ID, DATE,RATE,TITLE, BODY,CUSTOMER_ID,PRODUCT_ID) VALUES(1, PARSEDATETIME('14/07/2017','dd/MM/yyyy'), 5, 'King PC rocks!', 'This computer is as fast as hell',  1,1 );

INSERT INTO review (ID, DATE,RATE,TITLE, BODY,CUSTOMER_ID,PRODUCT_ID) VALUES(2, PARSEDATETIME('20/07/2017','dd/MM/yyyy'), 5, 'Can now play games that require loads of resources', 'Its so powerful that I finally can play games in 4K', 2, 1);
INSERT INTO review (ID, DATE,RATE,TITLE, BODY,CUSTOMER_ID,PRODUCT_ID) VALUES(3, PARSEDATETIME('23/07/2017','dd/MM/yyyy'), 3, 'Not very long', '', 2, 2);
INSERT INTO review (ID, DATE,RATE,TITLE, BODY,CUSTOMER_ID,PRODUCT_ID) VALUES(4, PARSEDATETIME('12/07/2017','dd/MM/yyyy'), 1, 'Its broken', 'Very disappointed...', 3, 2);

