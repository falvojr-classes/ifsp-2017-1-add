-- Sprit da aula do dia 14/02/2017 (SQL Server)
-- Exercício 1:

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
('007', 'Ação', 120, 'Fulano', 'Bla bla bla', 2000),
('Pelé', 'Esporte', 110, 'Fulano', 'Bla bla bla', 1950),
('Hulk', 'Ação', 107, 'Fulano', 'Bla bla bla', 2005),
('Homem Aranha', 'Ação', 67, 'Siclano', 'Bla bla bla', 2009),
('O Chamado', 'Terror', 130, 'Siclano', 'Bla bla bla', 2000);

SELECT titulo, duracao, ano FROM filmes;
