CREATE VIEW vw_estoque_atual AS
SELECT p.id id_produto, IFNULL(SUM(IF(m.tipo_movimentacao = 'ENTRADA', m.quantidade, -1 * m.quantidade)), 0) quantidade FROM tb_movimentacao_estoque m
RIGHT JOIN  tb_produto  p ON p.id = m.id_produto
WHERE p.deleted_at IS NULL
GROUP BY p.id