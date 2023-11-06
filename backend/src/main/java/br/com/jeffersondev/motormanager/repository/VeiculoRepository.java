package br.com.jeffersondev.motormanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jeffersondev.motormanager.entity.cliente.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, String>{

}
