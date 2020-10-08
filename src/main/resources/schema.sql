CREATE DATABASE transport;

CREATE TABLE customer(id SERIAL PRIMARY KEY, username TEXT, password TEXT);

CREATE TABLE transportTypes(id SERIAL PRIMARY KEY, title TEXT);

CREATE TABLE transport(id SERIAL PRIMARY KEY, transportTypeId INT, available BOOLEAN);

CREATE TABLE orders(id SERIAL PRIMARY KEY, customerId INT, transportId INT, transportTypeId INT, fullname TEXT, phone TEXT, status TEXT);

INSERT INTO transportTypes (title) VALUES ('type1'), ('type2'), ('type3');
INSERT INTO transport (transportTypeId, available) VALUES (1, FALSE), (1, TRUE), (1, FALSE), (1, TRUE), (1, FALSE), (1, TRUE);
INSERT INTO customer (username, password) VALUES ('user1', '12345'), ('user2', '12345'), ('user3', '12345');
INSERT INTO orders (customerId, transportTypeId, fullname, phone, status) VALUES
    (1, 1, 'Клиент 1', '89999999999', 'NEW'),
    (2, 2, 'Клиент 2', '89999959999', 'NEW'),
    (3, 3, 'Клиент 3', '89994599999', 'NEW'),
    (2, 1, 'Клиент 4', '89912399999', 'NEW'),
    (1, 2, 'Клиент 5', '89999991234', 'NEW'),
    (1, 1, 'Клиент 6', '12312312313', 'NEW'),
    (2, 2, 'Клиент 7', '32132132111', 'NEW'),
    (3, 3, 'Клиент 8', '33322211133', 'NEW'),
    (2, 1, 'Клиент 9', '12126546456', 'NEW'),
    (1, 2, 'Клиент 10', '87655344554', 'NEW');
