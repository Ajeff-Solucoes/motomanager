package br.com.jeffersondev.motormanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jeffersondev.motormanager.entity.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, String>{

	Optional<Modelo> findByNomeIgnoreCase(String nome);
	
	@Query("select m from Modelo m join fetch m.marca ma where m.ativo = true order by m.nome")
	List<Modelo> findAllAtivoAndOrderByNome();

	
	default Page<Modelo> findAllByName(String nome, PageRequest pgRequest){
		Modelo modelo = new Modelo();
		modelo.setNome(nome);
		
		ExampleMatcher matcher = ExampleMatcher.matchingAny()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Modelo> example = Example.of(modelo, matcher);
		return findAll(example, pgRequest);
	}

}
