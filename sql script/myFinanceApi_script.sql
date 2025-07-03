CREATE DATABASE api_transactions;

USE api_transactions;

CREATE TABLE transaction(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	description VARCHAR(255),
    value DOUBLE NOT NULL,
    type ENUM('RECEITA', 'DESPESA') NOT NULL,
	category VARCHAR(255),
    date VARCHAR(80) NOT NULL
);

