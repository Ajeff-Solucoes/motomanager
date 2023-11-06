package br.com.jeffersondev.motormanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jeffersondev.motormanager.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, String>{

	Optional<Servico> findByNomeIgnoreCase(String nome);

	@Query("select p from Servico p where lower(p.nome) like %?1%")
	List<Servico> findByNameContaining(String nome);

	@Query("select m from Servico m where m.ativo = true order by m.nome")
	List<Servico> findAllAtivoAndOrderByNome();
	
	
	default Page<Servico> findAllByName(String nome, PageRequest pgRequest) {
		Servico Servico = new Servico();
		Servico.setNome(nome);
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Servico> example = Example.of(Servico, exampleMatcher);
		Page<Servico> result =  findAll(example, pgRequest);
		return result;
	}

}
