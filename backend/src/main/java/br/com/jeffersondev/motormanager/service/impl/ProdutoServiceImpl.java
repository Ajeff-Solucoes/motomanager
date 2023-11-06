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

import br.com.jeffersondev.motormanager.dto.produto.ProdutoRequest;
import br.com.jeffersondev.motormanager.dto.produto.ProdutoResponse;
import br.com.jeffersondev.motormanager.entity.Produto;
import br.com.jeffersondev.motormanager.exception.OperationNotExecutedException;
import br.com.jeffersondev.motormanager.exception.ResourceAlreadyPersistedException;
import br.com.jeffersondev.motormanager.exception.ResourceNotFoundException;
import br.com.jeffersondev.motormanager.exception.ValorInformadoInvalidoException;
import br.com.jeffersondev.motormanager.repository.ProdutoRepository;
import br.com.jeffersondev.motormanager.service.ProdutoService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService{
	
	private static final String MSG_NOTFOUND_ID = "Produto não encontrado";
	
	private final ProdutoRepository repository;

	
	@Override
	public ProdutoResponse create(ProdutoRequest dto) {
		Produto entity = dto.fromDtoToEntity();
		verifyIfResourceAlreadyPersisted(entity);
		entity = repository.save(entity);
		return entity.fromEntityToResponse();
	}

	
	@Override
	public Page<ProdutoResponse> findAllAtivoAndOrderByNome(Paginacao paginacao) {
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize(), 
				Sort.by(Sort.Direction.fromString(paginacao.getOrder()), "nome"));
		Page<Produto> entities = repository.findAll(pgRequest);
		return convertToResponsePage(entities);
	}

	
	@Override
	public ProdutoResponse getById(String uuid) {
		Produto entity = this.findById(uuid);
		log.info("Produto localizado com o UUID {}", uuid);
		return entity.fromEntityToResponse();
	}	
	
	
	@Override
	public void deleteById(String uuid) {
		Produto entity = this.findById(uuid);
		try {
			repository.delete(entity);
			log.info("Produto com UUID {} deletado", uuid);
		} catch (DataIntegrityViolationException e) {
			log.error("Erro de integridade ao tentar deletar o registro com o UUID: {} ", uuid );
			throw new OperationNotExecutedException("O produto "+entity.getNome()+" tem relacionamento com outros registros e por isso não pode ser deletado!");
		}
	}
	
	
	
	@Override
	public ProdutoResponse update(String uuid, ProdutoRequest dto) {
		Produto entity = this.findById(uuid);
		entity.setNome(dto.getNome());
		entity.setMed(dto.getMed());
		entity.setValor(dto.getValor());
		verifyIfResourceAlreadyPersisted(entity);
		entity = repository.save(entity);
		log.info("Produto com UUID {} alterado ", uuid);
		return entity.fromEntityToResponse();
	}
	
	
	@Override
	public List<ProdutoResponse> findByNome(String nome) {
		List<Produto> produtos = repository.findByNameContaining(nome.toLowerCase());
		return produtos.stream()
				.map(Produto::fromEntityToResponse)
				.collect(Collectors.toList());
	}
	
	
	@Override
	public Page<ProdutoResponse> filter(String nome, Paginacao paginacao) {
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize(), 
				Sort.by(Sort.Direction.fromString(paginacao.getOrder()), "nome"));
		Page<Produto> entities = repository.findAllByName(nome == null ? "" : nome.toLowerCase(), pgRequest);
		return convertToResponsePage(entities);
	}
	
	

	private void verifyIfResourceAlreadyPersisted(Produto entity) {
		Optional<Produto> opt = repository.findByNomeIgnoreCase(entity.getNome());
		if(opt.isPresent() && !opt.get().equals(entity)) {
			log.error("Tentativa de cadastro de registro duplicado por nome: {}", entity.getNome());
			throw new ResourceAlreadyPersistedException("Já existe um produto com o nome "+ entity.getNome() +" cadastrado.");
		}
	}
	
	
	private Produto findById(String uuid) {
		if(uuid == null) throw new ValorInformadoInvalidoException("Valor do ID não pode ser nulo");
		log.info("Tentando localizar o produto por UUID {}", uuid);
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOTFOUND_ID));
	}



	
	private Page<ProdutoResponse> convertToResponsePage(Page<Produto> entities){
		return entities.map(new Function<Produto, ProdutoResponse>() {
			@Override
			public ProdutoResponse apply(Produto entity) {
				return entity.fromEntityToResponse();
			}
		});
	}
}
