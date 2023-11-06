package br.com.jeffersondev.motormanager.controller;

import br.com.jeffersondev.motormanager.dto.servico.ServicoRequest;
import br.com.jeffersondev.motormanager.dto.servico.ServicoResponse;
import br.com.jeffersondev.motormanager.service.ServicoService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/servicos")
public class ServicoController {

	private final ServicoService service;
	
	
	@GetMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public String status() {
		return "Servico it's running";
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoResponse create(@RequestBody @Valid ServicoRequest dto) {
		return service.create(dto);
	}
	
	
	@GetMapping("{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<ServicoResponse> findAllAtivoAndOrderByNome(@PathVariable Integer pgNo, @PathVariable Integer size){
		Paginacao paginacao = new Paginacao(pgNo, size, "asc");
		return service.findAllAtivoAndOrderByNome(paginacao);
	}
	

	@GetMapping("/f/{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<ServicoResponse> filter(
			@RequestParam String order,
			@RequestParam(required = false) String nome,
			@PathVariable Integer pgNo, @PathVariable Integer size
			){
		Paginacao paginacao = new Paginacao(pgNo, size, order);
		return service.filter(nome, paginacao);
	}
	
	
	
	@GetMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public ServicoResponse getById(@PathVariable String uuid) {
		return service.getById(uuid);
	}
	
	
	@DeleteMapping("{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RolesAllowed({"admin","moto-admin"})
	public void deleteById(@PathVariable String uuid) {
		service.deleteById(uuid);
	}
	
	
	@PutMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public ServicoResponse update (@PathVariable String uuid, @RequestBody @Valid ServicoRequest dto) {
		return service.update(uuid, dto);
	}
	
	
	@GetMapping("/name")
	@ResponseStatus(HttpStatus.OK)	
	public List<ServicoResponse> getByNome(@RequestParam(name = "name", required = true) String nome) {
		return service.findByNome(nome.trim());
	}
}
