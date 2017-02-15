-- Sprit da aula do dia 14/02/2017 (SQL Server)
-- Exercício 4:

CREATE TABLE categorias (
	id INT IDENTITY(1,1) PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
);

CREATE TABLE diretores (
	id INT IDENTITY(1,1) PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
);


--ALTER TABLE filmes DROP COLUMN categoria, diretor;
ALTER TABLE filmes DROP COLUMN categoria;
ALTER TABLE filmes DROP COLUMN diretor;

ALTER TABLE filmes 
ADD id_categoria INT
FOREIGN KEY(id_categoria) REFERENCES categorias(id);

ALTER TABLE filmes 
ADD id_diretor INT
FOREIGN KEY(id_diretor) REFERENCES diretores(id);

INSERT INTO diretores VALUES ('Eu');
INSERT INTO categorias VALUES ('Terror');

select * from filmes;

UPDATE filmes SET id_categoria = 1, id_diretor = 1;

SELECT d.nome FROM filmes AS f
JOIN diretores AS d ON d.id = f.id_diretor
WHERE f.titulo LIKE '%a%';