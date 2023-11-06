package br.com.jeffersondev.motormanager.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.jeffersondev.motormanager.dto.cliente.ClienteRequest;
import br.com.jeffersondev.motormanager.dto.cliente.ClienteRequestUpdate;
import br.com.jeffersondev.motormanager.dto.cliente.ClienteResponse;
import br.com.jeffersondev.motormanager.dto.cliente.ClienteResponseSimples;
import br.com.jeffersondev.motormanager.dto.cliente.VeiculoAddRequest;
import br.com.jeffersondev.motormanager.dto.cliente.VeiculoResponse;
import br.com.jeffersondev.motormanager.dto.filtro.ClienteFiltro;
import br.com.jeffersondev.motormanager.dto.modelo.ModeloResponse;
import br.com.jeffersondev.motormanager.entity.cliente.Cliente;
import br.com.jeffersondev.motormanager.entity.cliente.Veiculo;
import br.com.jeffersondev.motormanager.exception.OperationNotExecutedException;
import br.com.jeffersondev.motormanager.exception.ResourceAlreadyPersistedException;
import br.com.jeffersondev.motormanager.exception.ResourceNotFoundException;
import br.com.jeffersondev.motormanager.exception.ValorInformadoInvalidoException;
import br.com.jeffersondev.motormanager.repository.ClienteRepository;
import br.com.jeffersondev.motormanager.service.ClienteService;
import br.com.jeffersondev.motormanager.service.ModeloService;
import br.com.jeffersondev.motormanager.service.VeiculoService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService{
	
	private static final String MSG_NOTFOUND_ID = "Cliente não encontrado";
	
	private final ClienteRepository repository;
	private final VeiculoService veiculoService;
	private final ModeloService modeloService;


	@Override
	public ClienteResponse create(ClienteRequest dto) {
		Set<Veiculo> veiculos = getVeiculosEntity(dto.getVeiculos());
		Cliente entity = dto.fromDtoToEntity(veiculos);
		verifyIfResourceAlreadyPersisted(entity);
		entity = repository.save(entity);
		return entity.fromEntityToResponse();
	}
	

	@Override
	public Page<ClienteResponse> findAllAtivoAndOrderByNome(Paginacao paginacao) {
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize(), 
				Sort.by(Sort.Direction.fromString(paginacao.getOrder()), "nome"));
		Page<Cliente> entities = repository.findAll(pgRequest);
		return convertToResponsePage(entities);
	}


	@Override
	public ClienteResponse getById(String uuid) {
		Cliente entity = this.findById(uuid);
		log.info("Cliente localizado com o UUID {}", uuid);
		return entity.fromEntityToResponse();
	}	
	
	
	
	@Override
	public void deleteById(String uuid) {
		Cliente entity = this.findById(uuid);
		try {
			repository.delete(entity);
			log.info("Cliente com UUID {} deletado", uuid);
		} catch (DataIntegrityViolationException e) {
			log.error("Erro de integridade ao tentar deletar o registro com o UUID: {} ", uuid );
			throw new OperationNotExecutedException("O cliente "+entity.getNome()+" tem relacionamento com outros registros e por isso não pode ser deletado!");
		}
	}
	
	
	
	@Override
	public ClienteResponse update(String uuid, ClienteRequestUpdate dto) {
		Cliente entity = this.findById(uuid);
		updateRegister(dto, entity);
		verifyIfResourceAlreadyPersisted(entity);
		entity = repository.save(entity);
		log.info("Cliente com UUID {} alterado ", uuid);
		return entity.fromEntityToResponse();
	}
	
	
	
	@Override
	public List<ClienteResponseSimples> findByNome(String nome) {
		List<Cliente> entities = repository.findByNomeContaining(nome.toLowerCase());
		return entities.stream()
				.map(Cliente::fromEntityToResponseSimples)
				.collect(Collectors.toList());
	}	


	
	@Override
	public Page<ClienteResponse> filter(ClienteFiltro filtro, Paginacao paginacao) {
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize(), 
				Sort.by(Sort.Direction.fromString(paginacao.getOrder()), "nome"));
		Page<Cliente> entities = repository.filter(filtro, pgRequest);
		return convertToResponsePage(entities);
	}
	
	
	@Override
	public void deleteVeiculoById(String cliente, String uuid) {
		Cliente entity = this.findById(cliente);
		
		List<Veiculo> veiculo = entity.getVeiculos().stream()
				.filter(v -> v.getUuid().equals(uuid)).collect(Collectors.toList());
		
		if(veiculo.isEmpty()) {
			log.info("Não foi encontrado o veiculo com uuid {} cadastrado no cliente {} para ser deletado", uuid, entity.getNome());
			throw new ResourceNotFoundException("Não foi encontrado o veiculo do cliente para ser deletado");
		}else {
			
			if(entity.getVeiculos().size() == 1) {
				log.info("Tentativa de deletar o veiculo {} mas foi negado devido o cliente {} possuir somente este veiculo", uuid, entity.getNome());
				throw new OperationNotExecutedException("O veiculo não pode ser deletado pois é o único que o cliente possui e o cliente tem que possuir no minimo um veiculo");
			}
			entity.getVeiculos().remove(veiculo.get(0));
			veiculoService.deleteById(uuid);
		}
		
	}


	@Override
	public void addVeiculo(String cliente, VeiculoAddRequest dto) {
		Cliente entity = this.findById(cliente);
		Optional<Veiculo> opt = entity.getVeiculos().stream().filter(v -> v.getPlaca().equals(dto.getPlaca())).findFirst();
		if(opt.isPresent()) {
			throw new OperationNotExecutedException("Já existe um veiculo com a placa " + opt.get().getPlaca() + " cadastrada para este cliente");
		}
		Veiculo veiculo = createVeiculoByDto(dto);
		entity.getVeiculos().add(veiculo);
	}
	
	
	@Override
	public List<VeiculoResponse> getVeiculosByCliente(String uuid) {
		Cliente entity = this.findById(uuid);
		log.info("Encontrado o cliente {} com {} veiculos cadastrados", entity.getNome(), entity.getVeiculos().size());
		return entity.getVeiculos().stream().map(Veiculo::fromEntityToResponse).collect(Collectors.toList());
	}
	
	
	@Override
	public Integer getQtdAll() {
		return repository.findAll().size();
	}

	
	private void verifyIfResourceAlreadyPersisted(Cliente entity) {
		Optional<Cliente> opt = repository.findByNomeIgnoreCase(entity.getNome());
		if(opt.isPresent() && !opt.get().equals(entity)) {
			log.error("Tentativa de cadastro de registro duplicado por nome: {}", entity.getNome());
			throw new ResourceAlreadyPersistedException("Já existe um cliente com o nome "+ entity.getNome() +" cadastrado.");
		}
	}
	
	
	private Cliente findById(String uuid) {
		if(uuid == null) throw new ValorInformadoInvalidoException("Valor do ID não pode ser nulo");
		log.info("Tentando localizar o Cliente por UUID {}", uuid);
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOTFOUND_ID));
	}
	

	private void updateRegister(ClienteRequestUpdate dto, Cliente entity) {
		entity.setNome(dto.getNome());
		entity.setCelular(dto.getCelular());
		entity.setTelefone(dto.getTelefone());
		entity.setDataNascimento(dto.getData_nascimento());
		entity.setEmail(dto.getEmail());
		entity.setLogradouro(dto.getLogradouro());
		entity.setBairro(dto.getBairro());
		entity.setCidade(dto.getCidade());
		entity.setCep(dto.getCep());
	}
	
	
	private Page<ClienteResponse> convertToResponsePage(Page<Cliente> pagedResult) {
		return pagedResult.map(new Function<Cliente, ClienteResponse>() {
			@Override
			public ClienteResponse apply(Cliente entity) {
				return entity.fromEntityToResponse();
			}
		});
	}
	
	
	
	private Set<Veiculo> getVeiculosEntity(Set<VeiculoAddRequest> veiculos) {
		Set<Veiculo> entities = new HashSet<>();
		for(VeiculoAddRequest v : veiculos) {
			Veiculo veiculo = createVeiculoByDto(v);
			entities.add(veiculo);
		}
		return entities;
	}


	private Veiculo createVeiculoByDto(VeiculoAddRequest dto) {
		ModeloResponse modelo = modeloService.getById(dto.getModelo());
		Veiculo veiculo = dto.fromDtoToEntity(modelo.fromResponseToEntity());
		return veiculo;
	}


}
