package br.com.jeffersondev.motormanager.controller;

import br.com.jeffersondev.motormanager.dto.produto.ProdutoRequest;
import br.com.jeffersondev.motormanager.dto.produto.ProdutoResponse;
import br.com.jeffersondev.motormanager.service.ProdutoService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

	private final ProdutoService service;
	
	public ProdutoController(ProdutoService service) {
		this.service = service;
	}
	
	
	@GetMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public String status() {
		return "Produto it's running";
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoResponse create(@RequestBody @Valid ProdutoRequest dto) {
		return service.create(dto);
	}
	
	
	@GetMapping("{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<ProdutoResponse> findAllAtivoAndOrderByNome(@PathVariable Integer pgNo, @PathVariable Integer size){
		Paginacao paginacao = new Paginacao(pgNo, size, "asc");
		return service.findAllAtivoAndOrderByNome(paginacao);
	}
	

	@GetMapping("/f/{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<ProdutoResponse> filter(
			@RequestParam String order,
			@RequestParam(required = false) String nome,
			@PathVariable Integer pgNo, @PathVariable Integer size
			){
		Paginacao paginacao = new Paginacao(pgNo, size, order);
		return service.filter(nome, paginacao);
	}
	
	
	
	@GetMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public ProdutoResponse getById(@PathVariable String uuid) {
		return service.getById(uuid);
	}
	
	
	@DeleteMapping("{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable String uuid) {
		service.deleteById(uuid);
	}
	
	
	@PutMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public ProdutoResponse update (@PathVariable String uuid, @RequestBody @Valid ProdutoRequest dto) {
		return service.update(uuid, dto);
	}
	
	
	@GetMapping("/name/{nome}")
	@ResponseStatus(HttpStatus.OK)
	public List<ProdutoResponse> getByNome(@PathVariable String nome) {
		return service.findByNome(nome);
	}
}
