package br.com.jeffersondev.motormanager.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.jeffersondev.motormanager.dto.produto.ProdutoRequest;
import br.com.jeffersondev.motormanager.dto.produto.ProdutoResponse;
import br.com.jeffersondev.motormanager.util.Paginacao;

public interface ProdutoService {

	ProdutoResponse getById(String uuid);

	void deleteById(String uuid);

	ProdutoResponse create(ProdutoRequest dto);

	Page<ProdutoResponse> findAllAtivoAndOrderByNome(Paginacao paginacao);

	ProdutoResponse update(String uuid, ProdutoRequest dto);

	Page<ProdutoResponse> filter(String nome, Paginacao paginacao);

	List<ProdutoResponse> findByNome(String nome);

}
