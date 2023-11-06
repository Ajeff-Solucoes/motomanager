package br.com.jeffersondev.motormanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jeffersondev.motormanager.entity.os.OrdemServico;
import br.com.jeffersondev.motormanager.entity.os.StatusOS;
import br.com.jeffersondev.motormanager.repository.helper.os.OrdemServicoRepositoryHelper;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, String>, OrdemServicoRepositoryHelper{

	List<OrdemServico> findAllByStatus(StatusOS status);


}
