package br.com.jeffersondev.motormanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jeffersondev.motormanager.entity.cliente.Cliente;
import br.com.jeffersondev.motormanager.repository.helper.cliente.ClienteRepositoryHelper;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>, ClienteRepositoryHelper{


	Optional<Cliente> findByNomeIgnoreCase(String nome);
	
	@Query("select c from Cliente c where lower(c.nome) like %?1%")
	List<Cliente> findByNomeContaining(String nome);
	
	
	@Query("select c from Cliente c left join fetch c.veiculos where c.ativo = true order by c.nome")
	List<Cliente> findAllAtivoAndOrderByNome();

}
