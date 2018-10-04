package br.net.hartwig.apicarrinhocompras;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.net.hartwig.apicarrinhocompras.domain.Categoria;
import br.net.hartwig.apicarrinhocompras.repositories.CategoriaRepository;

@SpringBootApplication
public class ApicarrinhocomprasApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApicarrinhocomprasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria1 = new Categoria(null, "Pneus");
		Categoria categoria2 = new Categoria(null, "Capacetes");
		Categoria categoria3 = new Categoria(null, "Acessórios");

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3));

	}
}
