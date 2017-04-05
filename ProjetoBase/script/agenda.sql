CREATE DATABASE agenda;
USE agenda;

CREATE TABLE contatos (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    email VARCHAR(50),
    telefone VARCHAR(20),
    data_nascimento DATE
);

-- INSERT INTO contatos (nome, email, telefone, data_nascimento)  VALUES  ('Venilton', 'falvojr@gmail.com', '999999999', '1989-07-10');
-- 
-- UPDATE contatos SET 
-- nome = 'Venilton FalvoJr',
-- email = '',
-- telefone = '',
-- data_nascimento = '1999-12-10'
-- WHERE id = 1;
-- 
-- DELETE FROM contatos WHERE id = 1;
-- 
-- SELECT * FROM contatos;

CREATE TABLE usuarios ( 
  id int not null primary key auto_increment,
  login varchar(15) unique not null,
  senha varchar(15) not null,
  ativo boolean not null default true
);

INSERT INTO usuarios (login, senha) 
VALUES ('admin', 'admin');

SELECT id FROM usuarios 
WHERE login = 'admin' 
AND senha = 'admin'
AND ativo = true;