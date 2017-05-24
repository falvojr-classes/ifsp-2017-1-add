CREATE DATABASE agenda;
USE agenda;

CREATE TABLE pessoa (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    email VARCHAR(50),
    telefone VARCHAR(20),
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE pessoa_fisica (
    id_pf INT NOT NULL PRIMARY KEY,
    cpf VARCHAR(50) NOT NULL,
    data_nascimento DATE,
    CONSTRAINT FK_PF -- Opcional (caso desejar dar um nome especifico)
    FOREIGN KEY(id_pf) REFERENCES pessoa(id)
);

CREATE TABLE pessoa_juridica (
    id_pj INT NOT NULL PRIMARY KEY,
    cnpj VARCHAR(50) NOT NULL,
    ie VARCHAR(50),
    CONSTRAINT FK_PJ -- Opcional (caso desejar dar um nome especifico)
    FOREIGN KEY(id_pj) REFERENCES pessoa(id)
);

CREATE TABLE usuario ( 
  id int not null primary key auto_increment,
  login varchar(15) unique not null,
  senha varchar(15) not null,
  ativo boolean not null default true
);

INSERT INTO usuario (login, senha) 
VALUES ('admin', 'admin');

SELECT 
p.id, p.nome, p.email, p.telefone, p.ativo, -- pessoa
pf.cpf, pf.data_nascimento, -- pessoa_fisica
pj.cnpj, pj.ie -- pessoa_juridica
FROM pessoa p
LEFT JOIN pessoa_fisica pf ON pf.id_pf = p.id
LEFT JOIN pessoa_juridica pj ON pj.id_pj = p.id
