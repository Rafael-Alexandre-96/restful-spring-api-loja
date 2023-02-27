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
);

INSERT INTO tb_cliente (nome, celular, email, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade, endereco_uf) VALUES
('Solange', '11998874433', 'solange0@gmail.com', 'Rua das Oliveiras', '87', 'Centro', 'São Paulo', 'SP'),
('Marcos', '43998874433', 'marcos@ig.com.br', 'Rua das Azeitonas', '97', 'Centro', 'Curitiba', 'PR'),
('Felipe', '38998874433', 'felipe@gmail.com', 'Rua dos Tomates', '62', 'Centro', 'Belo Horizonte', 'MG'),
('Maria', '73998874433', 'maria@outlook.com', 'Rua das Batatas', '81', 'Campos', 'Salvador', 'BA'),
('Augusto', '11998873344', 'augusto@gmail.com', 'Rua dos Palamares', '55', 'Vila São Jorge', 'São Paulo', 'SP');