CREATE TABLE tb_cliente(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(40) NOT NULL,
    celular VARCHAR(20) NOT NULL,
    email VARCHAR(40),
    endereco_logradouro VARCHAR(50) NOT NULL,
    endereco_numero VARCHAR(20) NOT NULL,
    endereco_complemento VARCHAR(20),
    endereco_bairro VARCHAR(50) NOT NULL,
    endereco_cidade VARCHAR(50) NOT NULL,
    endereco_uf VARCHAR(2) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT now(),
    updated_at DATETIME,
    deleted_at DATETIME
)