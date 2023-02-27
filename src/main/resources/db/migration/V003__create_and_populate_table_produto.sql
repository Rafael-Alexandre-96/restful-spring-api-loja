CREATE TABLE tb_produto(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    valor DOUBLE NOT NULL DEFAULT 0.0,
    created_at DATETIME NOT NULL DEFAULT now(),
    updated_at DATETIME,
    deleted_at DATETIME
);

INSERT INTO tb_produto(nome, valor) VALUES
('Samsung A50', 1500),
('Poster', 20),
('Lápis', 1),
('Porta-Caneta', 5.5);