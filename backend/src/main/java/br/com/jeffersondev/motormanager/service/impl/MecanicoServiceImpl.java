package br.com.jeffersondev.motormanager.service.impl;

import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.jeffersondev.motormanager.dto.mecanico.MecanicoRequest;
import br.com.jeffersondev.motormanager.dto.mecanico.MecanicoResponse;
import br.com.jeffersondev.motormanager.entity.Mecanico;
import br.com.jeffersondev.motormanager.exception.OperationNotExecutedException;
import br.com.jeffersondev.motormanager.exception.ResourceAlreadyPersistedException;
import br.com.jeffersondev.motormanager.exception.ResourceNotFoundException;
import br.com.jeffersondev.motormanager.exception.ValorInformadoInvalidoException;
import br.com.jeffersondev.motormanager.repository.MecanicoRepository;
import br.com.jeffersondev.motormanager.service.MecanicoService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class MecanicoServiceImpl implements MecanicoService{
	
	private static final String MSG_NOTFOUND_ID = "Mecanico não localizado ";
	
	private final MecanicoRepository repository;

	@Override
	public MecanicoResponse create(MecanicoRequest dto) {
		Mecanico entity = dto.fromDtoToEntity();
		verifyIfResourceAlreadyPersisted(entity);
		entity = repository.save(entity);
		return entity.fromEntityToResponse();
	}

	
	@Override
	public Page<MecanicoResponse> findAllAtivoAndOrderByNome(Paginacao paginacao) {
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize(), 
				Sort.by(Sort.Direction.fromString(paginacao.getOrder()), "nome"));
		Page<Mecanico> entities = repository.findAll(pgRequest);
		return convertToResponsePage(entities);
	}

	
	@Override
	public MecanicoResponse getById(String uuid) {
		Mecanico entity = this.findById(uuid);
		log.info("Marca localizada com o UUID {}", uuid);
		return entity.fromEntityToResponse();
	}	
	
	
	@Override
	public void deleteById(String uuid) {
		Mecanico entity = this.findById(uuid);
		try {
			repository.delete(entity);
			log.info("Mecanico com UUID {} deletado", uuid);
		} catch (DataIntegrityViolationException e) {
			log.error("Erro de integridade ao tentar deletar o registro com o UUID: {} ", uuid );
			throw new OperationNotExecutedException("O mecânico "+entity.getNome()+" tem relacionamento com outros registros e por isso não pode ser deletado!");
		}
	}
	
	
	@Override
	public MecanicoResponse update(String uuid, MecanicoRequest dto) {
		Mecanico entity = this.findById(uuid);
		entity.setNome(dto.getNome());
		verifyIfResourceAlreadyPersisted(entity);
		entity = repository.save(entity);
		log.info("Mecanico com UUID {} alterado ", uuid);
		return entity.fromEntityToResponse();
	}
	

	
	@Override
	public Page<MecanicoResponse> filter(String nome, Paginacao paginacao) {
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize(), 
				Sort.by(Sort.Direction.fromString(paginacao.getOrder()), "nome"));
		Page<Mecanico> entities = repository.findAllByName(nome == null ? "" : nome.toLowerCase(), pgRequest);
		return convertToResponsePage(entities);
	}
	
	
	
	private void verifyIfResourceAlreadyPersisted(Mecanico entity) {
		Optional<Mecanico> opt = repository.findByNomeIgnoreCase(entity.getNome());
		if(opt.isPresent() && !opt.get().equals(entity)) {
			log.error("Tentativa de cadastro de registro duplicado por nome: {}", entity.getNome());
			throw new ResourceAlreadyPersistedException("Já existe um mecanico com o nome "+ entity.getNome() +" cadastrado.");
		}
	}
	
	
	private Mecanico findById(String uuid) {
		if(uuid == null) throw new ValorInformadoInvalidoException("Valor do ID não pode ser nulo");
		log.info("Tentando localizar o mecanico por UUID {}", uuid);
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOTFOUND_ID));
	}

	
	
	private Page<MecanicoResponse> convertToResponsePage(Page<Mecanico> entities){
		return entities.map(new Function<Mecanico, MecanicoResponse>() {
			@Override
			public MecanicoResponse apply(Mecanico entity) {
				return entity.fromEntityToResponse();
			}
		});
	}
}
