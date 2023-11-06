package br.com.jeffersondev.motormanager.service.impl;

import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.jeffersondev.motormanager.dto.marca.MarcaRequest;
import br.com.jeffersondev.motormanager.dto.marca.MarcaResponse;
import br.com.jeffersondev.motormanager.entity.Marca;
import br.com.jeffersondev.motormanager.exception.OperationNotExecutedException;
import br.com.jeffersondev.motormanager.exception.ResourceAlreadyPersistedException;
import br.com.jeffersondev.motormanager.exception.ResourceNotFoundException;
import br.com.jeffersondev.motormanager.exception.ValorInformadoInvalidoException;
import br.com.jeffersondev.motormanager.repository.MarcaRepository;
import br.com.jeffersondev.motormanager.service.MarcaService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class MarcaServiceImpl implements MarcaService{
	
	private static final String MSG_NOTFOUND_ID = "Marca não localizada ";
	
	private final MarcaRepository repository;

	@Override
	public MarcaResponse create(MarcaRequest dto) {
		Marca entity = dto.fromDtoToEntity();
		verifyIfResourceAlreadyPersisted(entity);
		entity = repository.save(entity);
		return entity.fromEntityToResponse();
	}

	
	@Override
	public Page<MarcaResponse> findAllAtivoAndOrderByNome(Paginacao paginacao) {
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize(), 
				Sort.by(Sort.Direction.fromString(paginacao.getOrder()), "nome"));
		Page<Marca> entities = repository.findAll(pgRequest);
		return convertToResponsePage(entities);
	}

	
	@Override
	public MarcaResponse getById(String uuid) {
		Marca marca = this.findById(uuid);
		log.info("Marca localizada com o UUID {}", uuid);
		return marca.fromEntityToResponse();
	}	
	
	
	
	@Override
	public void deleteById(String uuid) {
		Marca entity = this.findById(uuid);
		try {
			repository.delete(entity);
			log.info("Marca com UUID {} deletado", uuid);
		} catch (DataIntegrityViolationException e) {
			log.error("Erro de integridade ao tentar deletar o registro com o UUID: {} ", uuid );
			throw new OperationNotExecutedException("A marca "+entity.getNome()+" tem relacionamento com outros registros e por isso não pode ser deletada!");
		}
	}
	
	
	
	@Override
	public Page<MarcaResponse> filter(String nome, Paginacao paginacao) {
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize(), 
				Sort.by(Sort.Direction.fromString(paginacao.getOrder()), "nome"));
		Page<Marca> marcas = repository.findAllByName(nome == null ? "" : nome.toLowerCase(), pgRequest);
		return convertToResponsePage(marcas);
	}
	
	
	
	@Override
	public MarcaResponse update(String uuid, MarcaRequest dto) {
		Marca entity = this.findById(uuid);
		entity.setNome(dto.getNome());
		verifyIfResourceAlreadyPersisted(entity);
		entity = repository.save(entity);
		log.info("Marca com UUID {} alterado ", uuid);
		return entity.fromEntityToResponse();
	}
	

	private void verifyIfResourceAlreadyPersisted(Marca entity) {
		Optional<Marca> opt = repository.findByNomeIgnoreCase(entity.getNome());
		if(opt.isPresent() && !opt.get().equals(entity)) {
			log.error("Tentativa de cadastro de registro duplicado por nome: {}", entity.getNome());
			throw new ResourceAlreadyPersistedException("Já existe uma marca com o nome "+ entity.getNome() +" cadastrada.");
		}
	}
	
	
	private Marca findById(String uuid) {
		if(uuid == null) throw new ValorInformadoInvalidoException("Valor do ID não pode ser nulo");
		log.info("Tentando localizar a marca por UUID {}", uuid);
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOTFOUND_ID));
	}



	private Page<MarcaResponse> convertToResponsePage(Page<Marca> entities){
		return entities.map(new Function<Marca, MarcaResponse>() {
			@Override
			public MarcaResponse apply(Marca entity) {
				return entity.fromEntityToResponse();
			}
		});
	}
}
