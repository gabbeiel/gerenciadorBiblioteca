CREATE TABLE IF NOT EXISTS usuario (
    id_usuario SERIAL PRIMARY KEY,
    nm_usuario VARCHAR(50),
    ds_email VARCHAR(100),
    ds_senha VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS autor (
    id_autor SERIAL PRIMARY KEY,
    nm_autor VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS editora (
    id_editora SERIAL PRIMARY KEY,
    nm_editora VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS livro (
    id_livro SERIAL PRIMARY KEY,
    nm_livro VARCHAR(100),
    nr_isbn VARCHAR(13),
    ds_sinopse VARCHAR(1000),
    id_autor INT,
    FOREIGN KEY (id_autor) REFERENCES autor(id_autor),
    id_editora INT,
    FOREIGN KEY (id_editora) REFERENCES editora(id_editora)
);
CREATE TABLE IF NOT EXISTS livro_autor(
    id_livro INT,
    id_autor INT,
    PRIMARY KEY (id_livro, id_autor),
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro),
    FOREIGN KEY (id_autor) REFERENCES autor(id_autor)
);
/*CREATE TABLE IF NOT EXISTS categoria (
    id_categoria SERIAL PRIMARY KEY,
    nm_categoria VARCHAR(100)
);*/