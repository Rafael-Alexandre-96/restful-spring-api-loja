CREATE TABLE tb_venda(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_cliente BIGINT NOT NULL,
    data_venda DATETIME NOT NULL DEFAULT now(),
    data_pagamento DATETIME,
    status VARCHAR(20) NOT NULL,
    valor_desconto DOUBLE DEFAULT 0.0,
    valor_frete DOUBLE DEFAULT 0.0,
    CONSTRAINT fk_id_cliente_tb_venda FOREIGN KEY (id_cliente) REFERENCES tb_cliente(id)
)