-- Exercício 05: Utilizando um banco de dados em nuvem, realize as seguintes consultas:

-- Busque o nome de todas as cidades que começam com 'Ara';
SELECT nome FROM tb_cidades
WHERE nome LIKE 'ARA%';

-- Busque o nome de todas as cidades do estado de São Paulo;
SELECT c.nome FROM tb_cidades c
INNER JOIN tb_estados e ON c.id_estado = e.id
WHERE e.nome = 'Sao Paulo';

-- Indice criado para aumento de performance da query a seguir
CREATE INDEX idx_uf ON tb_estados(uf);

SELECT c.nome FROM tb_cidades c
INNER JOIN tb_estados e ON c.id_estado = e.id
WHERE e.uf = 'SP';

SELECT c.nome FROM tb_cidades c
INNER JOIN tb_estados e ON c.id_estado = e.id
WHERE e.id = 26;

-- Busque o nome e UF de todas as cidades da região sudeste (Espírito Santo, Minas Gerais, Rio de Janeiro e São Paulo);
SELECT c.nome, e.uf FROM tb_cidades c
INNER JOIN tb_estados e ON c.id_estado = e.id
WHERE e.uf IN ('ES', 'MG', 'RJ', 'SP');

SELECT c.nome, e.uf FROM tb_cidades c
INNER JOIN tb_estados e ON c.id_estado = e.id
WHERE e.id IN (8, 11, 19, 26);

-- Busque a quantidade de cidades considerando todos os estados;
SELECT COUNT(*) FROM tb_cidades;

-- Busque a quantidade de cidades da região sudeste;
SELECT COUNT(*) FROM tb_cidades c
INNER JOIN tb_estados e ON c.id_estado = e.id
WHERE e.uf IN ('ES', 'MG', 'RJ', 'SP');

-- Busque todas as cidades com nome composto. Por exemplo: Santa Lúcia, Nova Europa, Américo Brasiliense etc.
SELECT * FROM tb_cidades
WHERE nome LIKE '% %'