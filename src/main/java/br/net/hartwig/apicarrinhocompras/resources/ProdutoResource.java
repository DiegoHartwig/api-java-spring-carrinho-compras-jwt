package br.net.hartwig.apicarrinhocompras.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.net.hartwig.apicarrinhocompras.domain.Produto;
import br.net.hartwig.apicarrinhocompras.dto.ProdutoDTO;
import br.net.hartwig.apicarrinhocompras.resources.utils.URL;
import br.net.hartwig.apicarrinhocompras.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {

		Produto produto = service.find(id);

		return ResponseEntity.ok().body(produto);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "linePerPage", defaultValue = "20") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		String nomeDecoded = URL.decodeParam(nome);

		List<Integer> ids = URL.decodeIntList(categorias);

		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		
		Page<ProdutoDTO> listDTO = list.map(categoria -> new ProdutoDTO(categoria));

		return ResponseEntity.ok().body(listDTO);

	}

}
