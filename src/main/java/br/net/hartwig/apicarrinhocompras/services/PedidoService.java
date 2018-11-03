package br.net.hartwig.apicarrinhocompras.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.net.hartwig.apicarrinhocompras.domain.ItemPedido;
import br.net.hartwig.apicarrinhocompras.domain.PagamentoComBoleto;
import br.net.hartwig.apicarrinhocompras.domain.Pedido;
import br.net.hartwig.apicarrinhocompras.domain.enums.EstadoPagamento;
import br.net.hartwig.apicarrinhocompras.repositories.ItemPedidoRepository;
import br.net.hartwig.apicarrinhocompras.repositories.PagamentoRepository;
import br.net.hartwig.apicarrinhocompras.repositories.PedidoRepository;
import br.net.hartwig.apicarrinhocompras.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;

	public Pedido find(Integer id) {

		Optional<Pedido> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido insert(Pedido pedido) {

		pedido.setId(null);
		pedido.setInstante(new Date());			
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);

		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());

		}
		pedido = repo.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());

		for (ItemPedido itemPedido : pedido.getItens()) {
			itemPedido.setDesconto(0.0);
			itemPedido.setPreco(produtoService.find(itemPedido.getProduto().getId()).getPreco());
			itemPedido.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		return pedido;

	}

}
