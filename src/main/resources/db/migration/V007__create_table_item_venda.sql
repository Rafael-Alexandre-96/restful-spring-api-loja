CREATE TABLE tb_item_venda(
	id_venda BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    quantidade INTEGER NOT NULL DEFAULT 1,
    valor_unidade DOUBLE NOT NULL DEFAULT 0.0,
    PRIMARY KEY(id_venda, id_produto),
    CONSTRAINT fk_id_venda_tb_item_venda FOREIGN KEY (id_venda) REFERENCES tb_venda(id),
    CONSTRAINT fk_id_produto_tb_item_venda FOREIGN KEY (id_produto) REFERENCES tb_produto(id)
) 