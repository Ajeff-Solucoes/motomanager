package br.com.jeffersondev.motormanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jeffersondev.motormanager.entity.os.OSItemProduto;

@Repository
public interface ItemProdutoRepository extends JpaRepository<OSItemProduto, String>{

}
