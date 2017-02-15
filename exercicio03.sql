-- Sprit da aula do dia 14/02/2017 (SQL Server)
-- Exerc�cio32:

-- Busque todos os filmes lan�ados at� o ano 2000:

SELECT * FROM filmes WHERE ano <= 2000;

-- Busque todos os filmes que sejam da categoria �Terror�:

SELECT * FROM filmes WHERE categoria = 'Terror';

-- Busque todos os filmes que contenham �as� em seu t�tulo:

INSERT INTO filmes
(titulo, categoria, duracao, diretor, sinopse, ano)
VALUES ('As branquelas', 'Com�dia', 60, 'Fulano', 'Minha sinopse...', 2013);

SELECT * FROM filmes WHERE titulo LIKE '%as%';

-- Busque os 5 primeiros filmes considerando uma ordena��o crescente em seu t�tulo:

SELECT TOP 5 * FROM filmes ORDER BY titulo ASC;

-- Busque os 5 primeiros filmes considerando uma ordena��o decrescente em sua dura��o:

SELECT TOP 5 * FROM filmes ORDER BY duracao DESC;

-- Busque todos os filmes la�ados em 2016 que tenham mais de 1 hora de dura��o:

SELECT * FROM filmes WHERE ano = 2016 AND duracao > 60;

-- Busque apenas os t�tulos e anos de lan�amento dos filmes cujo diretor come�e com a letra �a�:

SELECT titulo, ano FROM filmes WHERE diretor LIKE 'a%';

-- Exclua todos os filmes com categoria igual a �Romance�:

DELETE FROM filmes WHERE categoria = 'Romance';

-- Atualize todos os filmes com sinopse nula para que passem a ser vazias:

UPDATE filmes SET sinopse = '' WHERE sinopse IS NULL;

-- Busque todos os filmes lan�ados entre os anos 2000 e 2010:

SELECT * FROM filmes WHERE ano >= 2000 AND ano <= 2010;

SELECT * FROM filmes WHERE ano BETWEEN 2000 AND 2010;