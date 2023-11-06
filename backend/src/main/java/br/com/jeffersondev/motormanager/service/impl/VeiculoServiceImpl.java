package br.com.jeffersondev.motormanager.service.impl;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jeffersondev.motormanager.dto.cliente.VeiculoResponse;
import br.com.jeffersondev.motormanager.entity.cliente.Veiculo;
import br.com.jeffersondev.motormanager.exception.OperationNotExecutedException;
import br.com.jeffersondev.motormanager.exception.ResourceNotFoundException;
import br.com.jeffersondev.motormanager.exception.ValorInformadoInvalidoException;
import br.com.jeffersondev.motormanager.repository.VeiculoRepository;
import br.com.jeffersondev.motormanager.service.VeiculoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class VeiculoServiceImpl implements VeiculoService{
	
	private static final String MSG_NOTFOUND_ID = "Veiculo não encontrado";	
	
	private final VeiculoRepository repository;

	
	@Override
	public void deleteById(String uuid) {
		Veiculo entity = this.findById(uuid);
		try {
			repository.delete(entity);
			repository.flush();
			log.info("Veiculo com UUID {} deletado", uuid);
		} catch (DataIntegrityViolationException e) {
			log.error("Erro de integridade ao tentar deletar o registro com o UUID: {} ", uuid );
			throw new OperationNotExecutedException("O veiculo "+entity.getPlaca()+" tem relacionamento com outros registros e por isso não pode ser deletado!");
		}
	}

	
	@Override
	public VeiculoResponse getById(String uuid) {
		Veiculo entity = this.findById(uuid);
		log.info("Veiculo localizado com o UUID {}", uuid);
		return entity.fromEntityToResponse();
	}	
	
	
	private Veiculo findById(String uuid) {
		if(uuid == null) throw new ValorInformadoInvalidoException("Valor do ID não pode ser nulo");
		log.info("Tentando localizar o Servico por UUID {}", uuid);
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOTFOUND_ID));
	}	
}
