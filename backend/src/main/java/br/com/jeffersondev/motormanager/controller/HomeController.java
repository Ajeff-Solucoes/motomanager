package br.com.jeffersondev.motormanager.controller;

import br.com.jeffersondev.motormanager.dto.home.FaturamentoTipoChartDTO;
import br.com.jeffersondev.motormanager.dto.home.HomeResponse;
import br.com.jeffersondev.motormanager.service.HomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/home")
@AllArgsConstructor
public class HomeController {
	
	private HomeService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public HomeResponse getInfo() {
		return service.getInfo();
	}
	
	@GetMapping("/bar-chart")
	@ResponseStatus(HttpStatus.OK)
	public List<FaturamentoTipoChartDTO> getInfoBarCharts() {
		return service.getBarChars();
	}

}
