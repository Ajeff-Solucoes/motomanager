package br.com.jeffersondev.motormanager.service.impl;

import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.jeffersondev.motormanager.dto.marca.MarcaResponse;
import br.com.jeffersondev.motormanager.dto.modelo.ModeloRequest;
import br.com.jeffersondev.motormanager.dto.modelo.ModeloResponse;
import br.com.jeffersondev.motormanager.entity.Modelo;
import br.com.jeffersondev.motormanager.exception.OperationNotExecutedException;
import br.com.jeffersondev.motormanager.exception.ResourceAlreadyPersistedException;
import br.com.jeffersondev.motormanager.exception.ResourceNotFoundException;
import br.com.jeffersondev.motormanager.exception.ValorInformadoInvalidoException;
import br.com.jeffersondev.motormanager.repository.ModeloRepository;
import br.com.jeffersondev.motormanager.service.MarcaService;
import br.com.jeffersondev.motormanager.service.ModeloService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ModeloServiceImpl implements ModeloService{
	
	private final ModeloRepository repository;
	private final MarcaService marcaService;
	
	@Override
	public ModeloResponse create(ModeloRequest dto) {
		MarcaResponse marca = marcaService.getById(dto.getMarca());
		Modelo entity = dto.fromDtoToEntity(marca.fromResponseToEntity());
		verifyIfResourceAlreadyPersisted(entity);
		entity = repository.save(entity);
		return entity.fromEntityToResponse();
	}

	
	@Override
	public Page<ModeloResponse> findAllAtivoAndOrderByNome(Paginacao paginacao) {
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize(), 
				Sort.by(Sort.Direction.fromString(paginacao.getOrder()), "nome"));
		Page<Modelo> entities = repository.findAll(pgRequest);
		return convertToResponsePage(entities);
	}

	
	@Override
	public Page<ModeloResponse> filter(String nome, Paginacao paginacao) {
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize(), 
				Sort.by(Sort.Direction.fromString(paginacao.getOrder()), "nome"));
		Page<Modelo> entities = repository.findAllByName(nome == null ? "" : nome.toLowerCase(), pgRequest);
		return convertToResponsePage(entities);
	}
	

	@Override
	public ModeloResponse getById(String uuid) {
		Modelo entity = this.findById(uuid);
		log.info("Modelo localizado com o UUID {}", uuid);
		return entity.fromEntityToResponse();
	}	
		
	
	@Override
	public void deleteById(String uuid) {
		Modelo entity = this.findById(uuid);
		try {
			repository.delete(entity);
			log.info("Modelo com UUID {} deletado", uuid);
		} catch (DataIntegrityViolationException e) {
			log.error("Erro de integridade ao tentar deletar o registro com o UUID: {} ", uuid );
			throw new OperationNotExecutedException("O modelo "+entity.getNome()+" tem relacionamento com outros registros e por isso não pode ser deletado!");
		}
	}
		
	
	@Override
	public ModeloResponse update(String uuid, ModeloRequest dto) {
		MarcaResponse marca = marcaService.getById(dto.getMarca());
		Modelo entity = findById(uuid);
		entity.setNome(dto.getNome());
		entity.setMarca(marca.fromResponseToEntity());
		verifyIfResourceAlreadyPersisted(entity);
		entity = repository.save(entity);
		log.info("Modelo com UUID {} alterado ", uuid);
		return entity.fromEntityToResponse();
	}
	
	
	private void verifyIfResourceAlreadyPersisted(Modelo entity) {
		Optional<Modelo> opt = repository.findByNomeIgnoreCase(entity.getNome());
		if(opt.isPresent() && !opt.get().equals(entity)) {
			log.error("Tentativa de cadastro de registro duplicado por nome: {}", entity.getNome());
			throw new ResourceAlreadyPersistedException("Já existe um modelo com o nome "+ entity.getNome() +" cadastrado.");
		}
	}
	
	
	private Modelo findById(String uuid) {
		if(uuid == null) throw new ValorInformadoInvalidoException("Valor do ID não pode ser nulo");
		log.info("Tentando localizar o modelo por UUID {}", uuid);
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException("Modelo não localizado"));
	}
	
	
	
	private Page<ModeloResponse> convertToResponsePage(Page<Modelo> pgResult) {
		return pgResult.map(new Function<Modelo, ModeloResponse>() {
			@Override
			public ModeloResponse apply(Modelo entity) {
				return entity.fromEntityToResponse();
			}
		});
	}
}
