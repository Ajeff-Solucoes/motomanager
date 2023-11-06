package br.com.jeffersondev.motormanager.controller;

import br.com.jeffersondev.motormanager.dto.marca.MarcaRequest;
import br.com.jeffersondev.motormanager.dto.marca.MarcaResponse;
import br.com.jeffersondev.motormanager.service.MarcaService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/marcas")
public class MarcaController {

	private final MarcaService service;
	
	
	public MarcaController(MarcaService service) {
		this.service = service;
	}
	
	
	
	@GetMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public String status() {
		return "Marcas it's running";
	}
	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MarcaResponse create(@RequestBody @Valid MarcaRequest dto) {
		return service.create(dto);
	}
	
	
	
	@GetMapping("{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<MarcaResponse> findAllAtivoAndOrderByNome(@PathVariable Integer pgNo, @PathVariable Integer size){
		Paginacao paginacao = new Paginacao(pgNo, size, "asc");
		return service.findAllAtivoAndOrderByNome(paginacao);
	}

	
	
	@GetMapping("/f/{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<MarcaResponse> filter(
			@RequestParam String order,
			@RequestParam(required = false) String nome,
			@PathVariable Integer pgNo, @PathVariable Integer size
			){
		Paginacao paginacao = new Paginacao(pgNo, size, order);
		return service.filter(nome, paginacao);
	}

	
	
	@GetMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public MarcaResponse getById(@PathVariable String uuid) {
		return service.getById(uuid);
	}
	
	
	
	@DeleteMapping("{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable String uuid) {
		service.deleteById(uuid);
	}
	
	
	
	@PutMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public MarcaResponse update (@PathVariable String uuid, @RequestBody @Valid MarcaRequest dto) {
		return service.update(uuid, dto);
	}
}
