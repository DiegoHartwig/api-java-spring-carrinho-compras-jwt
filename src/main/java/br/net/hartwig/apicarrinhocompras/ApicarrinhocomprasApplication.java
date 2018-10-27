package br.net.hartwig.apicarrinhocompras;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.net.hartwig.apicarrinhocompras.domain.Categoria;
import br.net.hartwig.apicarrinhocompras.domain.Cidade;
import br.net.hartwig.apicarrinhocompras.domain.Cliente;
import br.net.hartwig.apicarrinhocompras.domain.Endereco;
import br.net.hartwig.apicarrinhocompras.domain.Estado;
import br.net.hartwig.apicarrinhocompras.domain.ItemPedido;
import br.net.hartwig.apicarrinhocompras.domain.Pagamento;
import br.net.hartwig.apicarrinhocompras.domain.PagamentoComBoleto;
import br.net.hartwig.apicarrinhocompras.domain.PagamentoComCartao;
import br.net.hartwig.apicarrinhocompras.domain.Pedido;
import br.net.hartwig.apicarrinhocompras.domain.Produto;
import br.net.hartwig.apicarrinhocompras.domain.enums.EstadoPagamento;
import br.net.hartwig.apicarrinhocompras.domain.enums.TipoCliente;
import br.net.hartwig.apicarrinhocompras.repositories.CategoriaRepository;
import br.net.hartwig.apicarrinhocompras.repositories.CidadeRepository;
import br.net.hartwig.apicarrinhocompras.repositories.ClienteRepository;
import br.net.hartwig.apicarrinhocompras.repositories.EnderecoRepository;
import br.net.hartwig.apicarrinhocompras.repositories.EstadoRepository;
import br.net.hartwig.apicarrinhocompras.repositories.ItemPedidoRepository;
import br.net.hartwig.apicarrinhocompras.repositories.PagamentoRepository;
import br.net.hartwig.apicarrinhocompras.repositories.PedidoRepository;
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

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApicarrinhocomprasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria1 = new Categoria(null, "Pneus");
		Categoria categoria2 = new Categoria(null, "Capacetes");
		Categoria categoria3 = new Categoria(null, "Baterias");
		Categoria categoria4 = new Categoria(null, "Espelhos");
		Categoria categoria5 = new Categoria(null, "Escapamento");
		Categoria categoria6 = new Categoria(null, "Kit Relação");
		Categoria categoria7 = new Categoria(null, "Aro 21");
		Categoria categoria8 = new Categoria(null, "Velas");
		Categoria categoria9 = new Categoria(null, "Cabos de Vela");
		Categoria categoria10 = new Categoria(null, "Manoplas");
		Categoria categoria11 = new Categoria(null, "Cabo de Embreagem");
		Categoria categoria12 = new Categoria(null, "Cabo de Acelerador");
		Categoria categoria13 = new Categoria(null, "Amortecedor");
		Categoria categoria14 = new Categoria(null, "Cubo");
		Categoria categoria15 = new Categoria(null, "Guidão");
		Categoria categoria16 = new Categoria(null, "Corrente");
		Categoria categoria17 = new Categoria(null, "Botas");
		Categoria categoria18 = new Categoria(null, "Roupa de Chuva");
		Categoria categoria19 = new Categoria(null, "Acessórios");
		Categoria categoria20 = new Categoria(null, "Comando Avançado");
		Categoria categoria21 = new Categoria(null, "Radiador");
		Categoria categoria22 = new Categoria(null, "Luvas");

		Produto produto1 = new Produto(null, "Pneu Metzeler", 899.00);
		Produto produto2 = new Produto(null, "Capacete Old School", 150.00);
		Produto produto3 = new Produto(null, "Comando Avançado VT 600", 650.00);

		categoria1.getProdutos().addAll(Arrays.asList(produto1));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));
		categoria3.getProdutos().addAll(Arrays.asList(produto3));

		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria3));

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5,
				categoria6, categoria7, categoria8, categoria9, categoria10, categoria11, categoria12, categoria13,
				categoria14, categoria15, categoria16, categoria17, categoria18, categoria19, categoria20, categoria21,
				categoria22));

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

		Cliente cliente1 = new Cliente(null, "Diego Hartwig", "hartwig.diego@gmail.com", "12312311231",
				TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("4130123344", "41996505412"));

		Endereco endereco1 = new Endereco(null, "Av Marechal Floriano Peixoto", "1", "", "Centro", "12312333", cliente1,
				cidade1);

		Endereco endereco2 = new Endereco(null, "Rua Isaac Ferreira da Cruz", "2", "", "Sitio Cercado", "1234423234",
				cliente1, cidade1);

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

		clienteRepository.saveAll(Arrays.asList(cliente1));

		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido pedido1 = new Pedido(null, simpleDateFormat.parse("23/10/2018 18:55"), cliente1, endereco1);

		Pedido pedido2 = new Pedido(null, simpleDateFormat.parse("22/10/2018 20:10"), cliente1, endereco2);

		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);

		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,
				simpleDateFormat.parse("23/10/2018 22:00"), null);
		pedido2.setPagamento(pagamento2);

		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));

		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));

		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 0.0, 1, 1200.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido2, produto1, 0.0, 3, 1600.00);

		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));

		produto1.getItens().addAll(Arrays.asList(itemPedido1));

		produto2.getItens().addAll(Arrays.asList(itemPedido2));

		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2));
	}
}
