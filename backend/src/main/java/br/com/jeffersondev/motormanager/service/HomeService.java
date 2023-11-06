package br.com.jeffersondev.motormanager.service;

import java.util.List;

import br.com.jeffersondev.motormanager.dto.home.FaturamentoTipoChartDTO;
import br.com.jeffersondev.motormanager.dto.home.HomeResponse;

public interface HomeService {

	HomeResponse getInfo();

	List<FaturamentoTipoChartDTO> getBarChars();

}
