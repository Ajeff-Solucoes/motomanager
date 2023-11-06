package br.com.jeffersondev.motormanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jeffersondev.motormanager.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String>{

	Optional<Produto> findByNomeIgnoreCase(String nome);
	
	@Query("select p from Produto p where lower(p.nome) like %?1%")
	List<Produto> findByNameContaining(String nome);

	@Query("select m from Produto m where m.ativo = true order by m.nome")
	List<Produto> findAllAtivoAndOrderByNome();
	
	
	default Page<Produto> findAllByName(String nome, PageRequest pgRequest) {
		Produto Produto = new Produto();
		Produto.setNome(nome);
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Produto> example = Example.of(Produto, exampleMatcher);
		Page<Produto> result =  findAll(example, pgRequest);
		return result;
	}


}
