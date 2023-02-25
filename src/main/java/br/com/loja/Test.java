package br.com.loja;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.loja.domain.model.Categoria;
import br.com.loja.domain.model.Cliente;
import br.com.loja.domain.model.Endereco;
import br.com.loja.domain.model.ItemVenda;
import br.com.loja.domain.model.MovimentacaoEstoque;
import br.com.loja.domain.model.Produto;
import br.com.loja.domain.model.Venda;
import br.com.loja.domain.model.enums.UF;
import br.com.loja.domain.service.CategoriaService;
import br.com.loja.domain.service.ClienteService;
import br.com.loja.domain.service.MovimentacaoEstoqueService;
import br.com.loja.domain.service.ProdutoService;
import br.com.loja.domain.service.VendaService;

@Configuration
@Profile("test")
public class Test implements CommandLineRunner {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private VendaService vendaService;
	
	@Autowired
	private MovimentacaoEstoqueService estoqueService;

	@Override
	public void run(String... args) throws Exception {
		this.createCliente();
		this.createCategoriaProduto();
		this.createMovimentacao();
		this.createVenda();
	}
	
	private void createCliente() {
		Cliente c1 = new Cliente();
		c1.setNome("Rafael");
		c1.setCelular("13981861977");
		c1.setEmail("rafael@gmail.com");
		Endereco e1 = new Endereco();
		e1.setLogradouro("Rua das Oliveiras");
		e1.setNumero("23");
		e1.setBairro("Centro");
		e1.setCidade("São Paulo");
		e1.setUf(UF.SP);
		c1.setEndereco(e1);	
		clienteService.create(c1);
	}
	
	public void createCategoriaProduto() {
		Categoria c1 = new Categoria();
		c1.setDescricao("Eletronico");
		categoriaService.create(c1);
		
		Categoria c2 = new Categoria();
		c2.setDescricao("Papelaria");
		categoriaService.create(c2);
		
		Categoria c3 = new Categoria();
		c3.setDescricao("Decoração");
		categoriaService.create(c3);
		
		Produto p1 = new Produto();
		p1.setNome("Iphone 3");
		p1.setValor(1800D);
		List<Categoria> p1c = new ArrayList<>();
		p1c.add(c1);
		p1.setCategorias(p1c);
		produtoService.create(p1);
		
		Produto p2 = new Produto();
		p2.setNome("Relogio");
		p2.setValor(20D);
		List<Categoria> p2c = new ArrayList<>();
		p2c.add(c1);
		p2c.add(c3);
		p2.setCategorias(p2c);
		produtoService.create(p2);
		
		Produto p3 = new Produto();
		p3.setNome("Samsung A50");
		p3.setValor(1300D);
		p1c.add(c1);
		p3.setCategorias(p1c);
		produtoService.create(p3);
	}
	
	public void createMovimentacao() {
		MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
		movimentacao.setProduto(produtoService.findById(1L));
		movimentacao.setTipoEntrada();
		movimentacao.setQuantidade(10);
		estoqueService.create(movimentacao);
	}
	
	public void createVenda() {
		Venda v1 = new Venda();
		v1.setCliente(clienteService.findById(1L));
		v1.setValorDesconto(0D);
		v1.setValorFrete(0D);
		List<ItemVenda> itens = new ArrayList<>();
		ItemVenda i1 = new ItemVenda();
		i1.setProduto(produtoService.findById(1L));
		i1.setQuantidade(1);
		itens.add(i1);
		ItemVenda i2 = new ItemVenda();
		i2.setProduto(produtoService.findById(2L));
		i2.setQuantidade(2);
		itens.add(i2);
		v1.setItens(itens);
		vendaService.generateNewVenda(v1);
		vendaService.finalizeVenda(1L);
	}
}