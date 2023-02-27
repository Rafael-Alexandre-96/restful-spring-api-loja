CREATE TABLE tb_movimentacao_estoque(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_produto BIGINT NOT NULL,
    data_movimentacao DATETIME NOT NULL DEFAULT now(),
    quantidade INTEGER NOT NULL DEFAULT 1,
    tipo_movimentacao VARCHAR(20) NOT NULL,
    observacao VARCHAR(255),
    CONSTRAINT fk_id_produto_tb_movimentacao_estoque FOREIGN KEY (id_produto) REFERENCES tb_produto(id)
);

INSERT INTO tb_movimentacao_estoque(id_produto, quantidade, tipo_movimentacao) VALUES
(1, 10, 'ENTRADA'),
(2, 20, 'ENTRADA'),
(3, 30, 'ENTRADA'),
(4, 40, 'ENTRADA');