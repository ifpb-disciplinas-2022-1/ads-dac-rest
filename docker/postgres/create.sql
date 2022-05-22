CREATE TABLE livros(
    id serial PRIMARY KEY, 
    titulo VARCHAR(80), 
    url_capa VARCHAR(200),
    preco FLOAT,
    dataDeLancamento DATE
);
CREATE TABLE editoras(
    codigo SERIAL PRIMARY KEY, 
    localDeOrigem VARCHAR(100), 
    nomeFantasia VARCHAR(100)
);