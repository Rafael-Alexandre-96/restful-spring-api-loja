CREATE TABLE tb_produto_categoria(
	id_produto BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    PRIMARY KEY(id_produto, id_categoria),
    CONSTRAINT fk_id_produto_tb_produto_categoria FOREIGN KEY (id_produto) REFERENCES tb_produto(id),
    CONSTRAINT fk_id_categoria_tb_produto_categoria FOREIGN KEY (id_categoria) REFERENCES tb_categoria(id)
);

INSERT INTO tb_produto_categoria(id_produto, id_categoria) VALUES
(1, 1), (2, 2), (3, 3), (4, 2), (4, 3);