package br.com.jeffersondev.motormanager.repository.helper.os;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.jeffersondev.motormanager.dto.filtro.OrdemServicoFiltro;
import br.com.jeffersondev.motormanager.entity.os.OrdemServico;
import br.com.jeffersondev.motormanager.entity.os.StatusOS;

public class OrdemServicoRepositoryHelperImpl implements OrdemServicoRepositoryHelper{

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<OrdemServico> filter(OrdemServicoFiltro filtro, String order, PageRequest pgRequest) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select o from OrdemServico o where o.ativo = true");
		
		setarQuery(sb, filtro);
		
		int offset = pgRequest.getPageNumber() * pgRequest.getPageSize();
		
		sb.append(" order by codigo " + order);

		Query query = manager.createQuery(sb.toString())
				.setFirstResult(offset)
				.setMaxResults(20);
				
		setParameterQuery(filtro, query);
		
		List<OrdemServico> result = query.getResultList();
		return new PageImpl<OrdemServico>(result, pgRequest, result.size());
		
	}


	private void setParameterQuery(OrdemServicoFiltro filtro, Query query) {
		if(filtro.getCodigo() > 0) {
			query.setParameter("codigo", filtro.getCodigo());
		}

		if(filtro.getDataInicial() != null) {
			query.setParameter("datainicio", filtro.getDataInicial());
		}

		if(filtro.getDataFinal() != null) {
			query.setParameter("datafinal", filtro.getDataFinal());
		}
		
		if(!filtro.getNome().isBlank()) {
			query.setParameter("nome", "%" + filtro.getNome().toLowerCase() + "%");
		}

		if(!filtro.getStatus().equals(StatusOS.TODOS)) {
			query.setParameter("status", filtro.getStatus());
		}

		if(!filtro.getPlaca().isBlank()) {
			query.setParameter("placa", "%" + filtro.getPlaca().toLowerCase() + "%");
		}
	}

	
	
	private void setarQuery(StringBuilder sb, OrdemServicoFiltro filtro) {
		if(filtro.getCodigo() > 0) {
			sb.append(" and o.codigo = :codigo");
		}
		
		if(filtro.getDataInicial() != null) {
			sb.append(" and o.data >= :datainicio");
		}
		
		if(filtro.getDataFinal() != null) {
			sb.append(" and o.data <= :datafinal");
		}
		
		if(!filtro.getNome().isBlank()) {
			sb.append(" and lower(o.cliente.nome) like :nome");
		}

		if(!filtro.getStatus().equals(StatusOS.TODOS)) {
			sb.append(" and o.status = :status");
		}

		if(!filtro.getPlaca().isBlank()) {
			sb.append(" and lower(o.veiculo.placa) like :placa");
		}
	}


}
