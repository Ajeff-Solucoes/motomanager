package br.com.jeffersondev.motormanager.controller;

import br.com.jeffersondev.motormanager.dto.mecanico.MecanicoRequest;
import br.com.jeffersondev.motormanager.dto.mecanico.MecanicoResponse;
import br.com.jeffersondev.motormanager.service.MecanicoService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/mecanicos")
public class MecanicoController {

	private final MecanicoService service;
	
	
	public MecanicoController(MecanicoService service) {
		this.service = service;
	}
	
	
	
	@GetMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public String status() {
		return "Mecanicos it's running";
	}
	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MecanicoResponse create(@RequestBody @Valid MecanicoRequest dto) {
		return service.create(dto);
	}
	
	
	
	@GetMapping("{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<MecanicoResponse> findAllAtivoAndOrderByNome(@PathVariable Integer pgNo, @PathVariable Integer size){
		Paginacao paginacao = new Paginacao(pgNo, size, "asc");
		return service.findAllAtivoAndOrderByNome(paginacao);
	}

	
	
	@GetMapping("/f/{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<MecanicoResponse> filter(
			@RequestParam String order,
			@RequestParam(required = false) String nome,
			@PathVariable Integer pgNo, @PathVariable Integer size
			){
		Paginacao paginacao = new Paginacao(pgNo, size, order);
		return service.filter(nome, paginacao);
	}


	
	
	@GetMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public MecanicoResponse getById(@PathVariable String uuid) {
		return service.getById(uuid);
	}
	
	
	
	@DeleteMapping("{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable String uuid) {
		service.deleteById(uuid);
	}
	
	
	
	@PutMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public MecanicoResponse update (@PathVariable String uuid, @RequestBody @Valid MecanicoRequest dto) {
		return service.update(uuid, dto);
	}
}
