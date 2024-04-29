CREATE TABLE IF NOT EXISTS tb_pessoa (
    id SERIAL PRIMARY KEY,
    nome_completo VARCHAR(100),
    data_nascimento TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_endereco (
    id SERIAL PRIMARY KEY,
    logradouro VARCHAR(255),
    cep VARCHAR(20),
    estado VARCHAR(50),
    cidade VARCHAR(100),
    numero VARCHAR(10),
    id_pessoa BIGINT,
    CONSTRAINT fk_pessoa FOREIGN KEY (id_pessoa) REFERENCES tb_pessoa(id)
);
