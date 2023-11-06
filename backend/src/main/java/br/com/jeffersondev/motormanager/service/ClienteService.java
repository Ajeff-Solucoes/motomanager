package br.com.jeffersondev.motormanager.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.jeffersondev.motormanager.dto.cliente.ClienteRequest;
import br.com.jeffersondev.motormanager.dto.cliente.ClienteRequestUpdate;
import br.com.jeffersondev.motormanager.dto.cliente.ClienteResponse;
import br.com.jeffersondev.motormanager.dto.cliente.ClienteResponseSimples;
import br.com.jeffersondev.motormanager.dto.cliente.VeiculoAddRequest;
import br.com.jeffersondev.motormanager.dto.cliente.VeiculoResponse;
import br.com.jeffersondev.motormanager.dto.filtro.ClienteFiltro;
import br.com.jeffersondev.motormanager.util.Paginacao;


public interface ClienteService {

	ClienteResponse getById(String uuid);

	void deleteById(String uuid);

	ClienteResponse create(ClienteRequest dto);

	Page<ClienteResponse> findAllAtivoAndOrderByNome(Paginacao paginacao);

	ClienteResponse update(String uuid, ClienteRequestUpdate dto);

	Page<ClienteResponse> filter(ClienteFiltro filtro, Paginacao paginacao);

	List<ClienteResponseSimples> findByNome(String nome);

	void deleteVeiculoById(String cliente, String uuid);

	void addVeiculo(String cliente, VeiculoAddRequest dto);

	List<VeiculoResponse> getVeiculosByCliente(String uuid);

	Integer getQtdAll();

}
