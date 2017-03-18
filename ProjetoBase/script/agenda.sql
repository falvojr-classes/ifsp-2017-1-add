CREATE DATABASE agenda;
USE agenda;

CREATE TABLE contatos (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    email VARCHAR(50),
    telefone VARCHAR(20),
    data_nascimento DATE
);

INSERT INTO contatos (nome, email, telefone, data_nascimento)  VALUES  ('Venilton', 'falvojr@gmail.com', '999999999', '1989-07-10');

UPDATE contatos SET 
nome = 'Venilton FalvoJr',
email = '',
telefone = '',
data_nascimento = '1999-12-10'
WHERE id = 1;

DELETE FROM contatos WHERE id = 1;

SELECT * FROM contatos;