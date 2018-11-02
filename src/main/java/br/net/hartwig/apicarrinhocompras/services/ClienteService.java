package br.net.hartwig.apicarrinhocompras.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.net.hartwig.apicarrinhocompras.domain.Cidade;
import br.net.hartwig.apicarrinhocompras.domain.Cliente;
import br.net.hartwig.apicarrinhocompras.domain.Endereco;
import br.net.hartwig.apicarrinhocompras.domain.enums.TipoCliente;
import br.net.hartwig.apicarrinhocompras.dto.ClienteDTO;
import br.net.hartwig.apicarrinhocompras.dto.NovoClienteDTO;
import br.net.hartwig.apicarrinhocompras.repositories.ClienteRepository;
import br.net.hartwig.apicarrinhocompras.repositories.EnderecoRepository;
import br.net.hartwig.apicarrinhocompras.services.exceptions.DataIntegrityException;
import br.net.hartwig.apicarrinhocompras.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {

		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = repo.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	public Cliente update(Cliente cliente) {
		Cliente novoCliente = find(cliente.getId());
		updateData(novoCliente, cliente);
		return repo.save(novoCliente);
	}

	public void delete(Integer id) {
		find(id);

		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não foi possivel excluir este cliente, pois ele possui produtos associados!");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO clienteDto) {
		return new Cliente(null, clienteDto.getNome(), clienteDto.getEmail(), null, null);
	}

	public Cliente fromDTO(NovoClienteDTO novoClienteDto) {
		Cliente clie = new Cliente(null, novoClienteDto.getNome(), novoClienteDto.getEmail(),
				novoClienteDto.getCpfCnpj(), TipoCliente.toEnum(novoClienteDto.getTipoCliente()));

		Cidade cid = new Cidade(novoClienteDto.getCidadeId(), null, null);

		Endereco ende = new Endereco(null, novoClienteDto.getLogradouro(), novoClienteDto.getNumero(),
				novoClienteDto.getComplemento(), novoClienteDto.getBairro(), novoClienteDto.getCep(), clie, cid);

		clie.getEnderecos().add(ende);

		clie.getTelefones().add(novoClienteDto.getTelefone1());

		if (novoClienteDto.getTelefone2() != null) {
			clie.getTelefones().add(novoClienteDto.getTelefone2());
		}
		if (novoClienteDto.getTelefone3() != null) {
			clie.getTelefones().add(novoClienteDto.getTelefone3());
		}
		return clie;
	}

	private void updateData(Cliente novoCliente, Cliente cliente) {
		novoCliente.setNome(cliente.getNome());
		novoCliente.setEmail(cliente.getEmail());
	}

}
