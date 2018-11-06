package br.net.hartwig.apicarrinhocompras.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.net.hartwig.apicarrinhocompras.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instanteDoPedido);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(calendar.getTime());

	}

}
