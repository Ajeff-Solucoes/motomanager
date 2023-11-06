package br.com.jeffersondev.motormanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jeffersondev.motormanager.entity.Mecanico;

public interface MecanicoRepository extends JpaRepository<Mecanico, String>{

	Optional<Mecanico> findByNomeIgnoreCase(String nome);

	@Query("select m from Mecanico m where m.ativo = true order by m.nome")
	List<Mecanico> findAllAtivoAndOrderByNome();
	
	default Page<Mecanico> findAllByName(String nome, PageRequest pgRequest) {
		Mecanico Mecanico = new Mecanico();
		Mecanico.setNome(nome);
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Mecanico> example = Example.of(Mecanico, exampleMatcher);
		Page<Mecanico> result =  findAll(example, pgRequest);
		return result;
	}


}
