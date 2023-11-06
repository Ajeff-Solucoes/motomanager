package br.com.jeffersondev.motormanager.repository.helper.cliente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.jeffersondev.motormanager.dto.filtro.ClienteFiltro;
import br.com.jeffersondev.motormanager.entity.cliente.Cliente;

public class ClienteRepositoryHelperImpl implements ClienteRepositoryHelper{

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<Cliente> filter(ClienteFiltro filtro, PageRequest pgRequest) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select o from Cliente o join fetch o.veiculos v where o.ativo = true");
		
		setarQuery(sb, filtro);
		
		int offset = pgRequest.getPageNumber() * pgRequest.getPageSize();

		Query query = manager.createQuery(sb.toString())
				.setFirstResult(offset)
				.setMaxResults(20);
				
		setParameterQuery(filtro, query);
		
		List<Cliente> result = query.getResultList();
		return new PageImpl<Cliente>(result, pgRequest, result.size());
		
	}



	private void setParameterQuery(ClienteFiltro filtro, Query query) {
		
		if(!filtro.getNome().isBlank()) {
			query.setParameter("nome", "%" + filtro.getNome().toLowerCase() + "%");
		}

		if(!filtro.getPlaca().isBlank()) {
			query.setParameter("placa", "%" + filtro.getPlaca().toLowerCase() + "%");
		}
	}

	
	
	private void setarQuery(StringBuilder sb, ClienteFiltro filtro) {
		
		if(!filtro.getNome().isBlank()) {
			sb.append(" and lower(o.nome) like :nome");
		}

		if(!filtro.getPlaca().isBlank()) {
			sb.append(" and lower(v.placa) like :placa");
		}
	}

}
