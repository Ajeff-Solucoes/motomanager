package br.com.jeffersondev.motormanager.controller;

import br.com.jeffersondev.motormanager.dto.modelo.ModeloRequest;
import br.com.jeffersondev.motormanager.dto.modelo.ModeloResponse;
import br.com.jeffersondev.motormanager.service.ModeloService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/modelos")
public class ModeloController {

	private final ModeloService service;
	
	public ModeloController(ModeloService service) {
		this.service = service;
	}
	
	
	@GetMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public String status() {
		return "Modelos it's running";
	}
		
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ModeloResponse create(@RequestBody @Valid ModeloRequest dto) {
		return service.create(dto);
	}
		
	
	@GetMapping("{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<ModeloResponse> findAllAtivoAndOrderByNome(@PathVariable Integer pgNo, @PathVariable Integer size){
		Paginacao paginacao = new Paginacao(pgNo, size, "asc");
		return service.findAllAtivoAndOrderByNome(paginacao);
	}
		
	
	@GetMapping("/f/{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<ModeloResponse> filter(
			@RequestParam String order,
			@RequestParam(required = false) String nome,
			@PathVariable Integer pgNo, @PathVariable Integer size
			){
		Paginacao paginacao = new Paginacao(pgNo, size, order);
		return service.filter(nome, paginacao);
	}

	
	@GetMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public ModeloResponse getById(@PathVariable String uuid) {
		return service.getById(uuid);
	}
	
	
	@DeleteMapping("{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable String uuid) {
		service.deleteById(uuid);
	}
	
	
	@PutMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public ModeloResponse update (@PathVariable String uuid, @RequestBody @Valid ModeloRequest dto) {
		return service.update(uuid, dto);
	}
}
