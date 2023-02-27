CREATE TABLE tb_categoria(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50) NOT NULL
);

INSERT INTO tb_categoria(descricao) VALUES
('Eletrônico'), ('Decoração'), ('Papelaria');