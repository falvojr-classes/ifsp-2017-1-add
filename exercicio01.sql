-- Sprit da aula do dia 14/02/2017 (SQL Server)
-- Exerc�cio 1:

CREATE DATABASE cinema;

USE cinema;

CREATE TABLE filmes (
	id INT IDENTITY(1,1) PRIMARY KEY,
	titulo VARCHAR(50) NOT NULL,
	categoria VARCHAR(50) NOT NULL,
	duracao INT NOT NULL,
	diretor VARCHAR(50) NOT NULL,
	sinopse TEXT NOT NULL,
	ano SMALLINT NOT NULL
);

INSERT INTO filmes VALUES
('007', 'A��o', 120, 'Fulano', 'Bla bla bla', 2000),
('Pel�', 'Esporte', 110, 'Fulano', 'Bla bla bla', 1950),
('Hulk', 'A��o', 107, 'Fulano', 'Bla bla bla', 2005),
('Homem Aranha', 'A��o', 67, 'Siclano', 'Bla bla bla', 2009),
('O Chamado', 'Terror', 130, 'Siclano', 'Bla bla bla', 2000);

SELECT titulo, duracao, ano FROM filmes;
