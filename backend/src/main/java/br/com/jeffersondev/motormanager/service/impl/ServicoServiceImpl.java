package br.com.jeffersondev.motormanager.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.jeffersondev.motormanager.dto.servico.ServicoRequest;
import br.com.jeffersondev.motormanager.dto.servico.ServicoResponse;
import br.com.jeffersondev.motormanager.entity.Servico;
import br.com.jeffersondev.motormanager.exception.OperationNotExecutedException;
import br.com.jeffersondev.motormanager.exception.ResourceAlreadyPersistedException;
import br.com.jeffersondev.motormanager.exception.ResourceNotFoundException;
import br.com.jeffersondev.motormanager.exception.ValorInformadoInvalidoException;
import br.com.jeffersondev.motormanager.repository.ServicoRepository;
import br.com.jeffersondev.motormanager.service.ServicoService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ServicoServiceImpl implements ServicoService{
	
	private static final String MSG_NOTFOUND_ID = "Servico não encontrado";
	
	private final ServicoRepository repository;

	
	@Override
	public ServicoResponse create(ServicoRequest dto) {
		Servico entity = dto.fromDtoToEntity();
		verifyIfResourceAlreadyPersisted(entity);
		entity = repository.save(entity);
		return entity.fromEntityToResponse();
	}

	
	@Override
	public Page<ServicoResponse> findAllAtivoAndOrderByNome(Paginacao paginacao) {
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize(), 
				Sort.by(Sort.Direction.fromString(paginacao.getOrder()), "nome"));
		Page<Servico> entities = repository.findAll(pgRequest);
		return convertToResponsePage(entities);
	}

	
	@Override
	public ServicoResponse getById(String uuid) {
		Servico entity = this.findById(uuid);
		log.info("Servico localizado com o UUID {}", uuid);
		return entity.fromEntityToResponse();
	}	
	
	
	@Override
	public void deleteById(String uuid) {
		Servico entity = this.findById(uuid);
		try {
			repository.delete(entity);
			log.info("Servico com UUID {} deletado", uuid);
		} catch (DataIntegrityViolationException e) {
			log.error("Erro de integridade ao tentar deletar o registro com o UUID: {} ", uuid );
			throw new OperationNotExecutedException("O serviço "+entity.getNome()+" tem relacionamento com outros registros e por isso não pode ser deletado!");
		}
	}
	
	
	
	@Override
	public ServicoResponse update(String uuid, ServicoRequest dto) {
		Servico entity = this.findById(uuid);
		entity.setNome(dto.getNome());
		entity.setValor(dto.getValor());
		verifyIfResourceAlreadyPersisted(entity);
		entity = repository.save(entity);
		log.info("Servico com UUID {} alterado ", uuid);
		return entity.fromEntityToResponse();
	}
	
	
	@Override
	public List<ServicoResponse> findByNome(String nome) {
		List<Servico> servicos = repository.findByNameContaining(nome.toLowerCase());
		return servicos.stream()
				.map(Servico::fromEntityToResponse)
				.collect(Collectors.toList());
	}
	
	
	@Override
	public Page<ServicoResponse> filter(String nome, Paginacao paginacao) {
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize(), 
				Sort.by(Sort.Direction.fromString(paginacao.getOrder()), "nome"));
		Page<Servico> entities = repository.findAllByName(nome == null ? "" : nome.toLowerCase(), pgRequest);
		return convertToResponsePage(entities);
	}
	
	

	private void verifyIfResourceAlreadyPersisted(Servico entity) {
		Optional<Servico> opt = repository.findByNomeIgnoreCase(entity.getNome());
		if(opt.isPresent() && !opt.get().equals(entity)) {
			log.error("Tentativa de cadastro de registro duplicado por nome: {}", entity.getNome());
			throw new ResourceAlreadyPersistedException("Já existe um serviço com o nome "+ entity.getNome() +" cadastrado.");
		}
	}
	
	
	private Servico findById(String uuid) {
		if(uuid == null) throw new ValorInformadoInvalidoException("Valor do ID não pode ser nulo");
		log.info("Tentando localizar o Servico por UUID {}", uuid);
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOTFOUND_ID));
	}



	
	private Page<ServicoResponse> convertToResponsePage(Page<Servico> entities){
		return entities.map(new Function<Servico, ServicoResponse>() {
			@Override
			public ServicoResponse apply(Servico entity) {
				return entity.fromEntityToResponse();
			}
		});
	}
}
