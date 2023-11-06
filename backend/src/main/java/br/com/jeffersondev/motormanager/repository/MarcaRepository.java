package br.com.jeffersondev.motormanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jeffersondev.motormanager.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, String>{

	Optional<Marca> findByNomeIgnoreCase(String nome);

	@Query("select m from Marca m where m.ativo = true order by m.nome")
	List<Marca> findAllAtivoAndOrderByNome();
	
	default Page<Marca> findAllByName(String nome, PageRequest pgRequest) {
		Marca marca = new Marca();
		marca.setNome(nome);
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Marca> example = Example.of(marca, exampleMatcher);
		Page<Marca> result =  findAll(example, pgRequest);
		return result;
	}


}
