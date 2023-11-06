package br.com.jeffersondev.motormanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jeffersondev.motormanager.entity.os.OSItemServico;

@Repository
public interface ItemServicoRepository extends JpaRepository<OSItemServico, String>{


}
