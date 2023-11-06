package br.com.jeffersondev.motormanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jeffersondev.motormanager.entity.Configuracao;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, String>{

	Optional<Configuracao> findByChave(String string);

}
