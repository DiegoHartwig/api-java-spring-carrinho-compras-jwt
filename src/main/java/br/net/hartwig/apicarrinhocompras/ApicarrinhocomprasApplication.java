package br.net.hartwig.apicarrinhocompras;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.net.hartwig.apicarrinhocompras.domain.Categoria;
import br.net.hartwig.apicarrinhocompras.domain.Cidade;
import br.net.hartwig.apicarrinhocompras.domain.Estado;
import br.net.hartwig.apicarrinhocompras.domain.Produto;
import br.net.hartwig.apicarrinhocompras.repositories.CategoriaRepository;
import br.net.hartwig.apicarrinhocompras.repositories.CidadeRepository;
import br.net.hartwig.apicarrinhocompras.repositories.EstadoRepository;
import br.net.hartwig.apicarrinhocompras.repositories.ProdutoRepository;

@SpringBootApplication
public class ApicarrinhocomprasApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApicarrinhocomprasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria1 = new Categoria(null, "Pneus");
		Categoria categoria2 = new Categoria(null, "Capacetes");
		Categoria categoria3 = new Categoria(null, "Acessórios");
		
		Produto produto1 = new Produto(null, "Pneu Metzeler", 899.00);
		Produto produto2 = new Produto(null, "Capacete Old School", 150.00);
		Produto produto3 = new Produto(null, "Comando Avançado VT 600", 650.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));
		categoria3.getProdutos().addAll(Arrays.asList(produto3));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria3));				

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3));
		
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		
		Estado estado1 = new Estado(null, "Paraná");
		Estado estado2 = new Estado(null, "Santa Catarina");
		Estado estado3 = new Estado(null, "Rio Grande do Sul");
		
		Cidade cidade1 = new Cidade(null, "Curitiba", estado1);
		Cidade cidade2 = new Cidade(null, "Florianopolis", estado2);
		Cidade cidade3 = new Cidade(null, "Porto Alegre", estado3);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2));
		estado3.getCidades().addAll(Arrays.asList(cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2, estado3));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

	}
}
