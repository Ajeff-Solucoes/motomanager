package br.com.jeffersondev.motormanager.controller;

import br.com.jeffersondev.motormanager.dto.filtro.OrdemServicoFiltro;
import br.com.jeffersondev.motormanager.dto.os.*;
import br.com.jeffersondev.motormanager.entity.os.StatusOS;
import br.com.jeffersondev.motormanager.service.OrdemServicoService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/v1/os")
public class OrdemServicoController {

	private final OrdemServicoService service;
	
	public OrdemServicoController(OrdemServicoService service) {
		this.service = service;
	}
	
	
	@GetMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public String status() {
		return "Ordem Serviço it's running";
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OSResponse create(@RequestBody @Valid OSRequestCreate dto) {
		return service.create(dto);
	}
	
	
	@GetMapping("/{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<OSResponse> filter(
			@RequestParam String order,
			@RequestParam(required = false) String status,
			@RequestParam(required = false) String codigo,
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) String placa,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate dataInicial,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal,
			@PathVariable Integer pgNo, @PathVariable Integer size){

		if(codigo == null || codigo.isEmpty()) {
			codigo = "0";
		}
		
		OrdemServicoFiltro filtro = new OrdemServicoFiltro(Long.valueOf(codigo), dataInicial, dataFinal, StatusOS.getValue(status), nome, placa);
		Paginacao paginacao = new Paginacao(pgNo, size, order);
		return service.filter(filtro, paginacao);
	}
	
	
	@GetMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public OSResponse getById(@PathVariable String uuid) {
		OSResponse resp = service.getById(uuid);
		return resp;
	}
	
	
	@DeleteMapping("{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable String uuid) {
		service.deleteById(uuid);
	}
	
	
	@DeleteMapping("{os}/servico/{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public BigDecimal removeItemServico(@PathVariable String os, @PathVariable String uuid) {
		return service.removeItemServico(os, uuid);
	}

	
	@DeleteMapping("{os}/produto/{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeItemProduto(@PathVariable String os, @PathVariable String uuid) {
		service.removeItemProduto(os, uuid);
	}
	
	
	@PutMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public OSResponse update (@PathVariable String uuid, @RequestBody OSRequestUpdate dto) {
		return service.update(uuid, dto);
	}
	
	
	@PutMapping("{uuid}/servico")
	@ResponseStatus(HttpStatus.OK)
	public BigDecimal addItemServico(@PathVariable String uuid, @RequestBody OSItemServicoRequest servico) {
		return service.addItemServico(uuid, servico);
	}
	
	
	@PutMapping("{uuid}/produto")
	@ResponseStatus(HttpStatus.OK)
	public void addItemProduto(@PathVariable String uuid, @RequestBody OSItemProdutoRequest produto) {
		service.addItemProduto(uuid, produto);
	}
	
	
	@PatchMapping("{uuid}/status")
	@ResponseStatus(HttpStatus.OK)
	public String updateStatus(@PathVariable String uuid, @RequestBody OSUpdateStatusRequest status ) {
		return service.updateStatus(uuid, status);
	}
	
	
	@PutMapping("{uuid}/finish")
	@ResponseStatus(HttpStatus.OK)
	public void finalizarOS(@PathVariable String uuid, @RequestBody OSFinishRequest dto) {
		service.finalizarOS(uuid, dto);
	}
}
