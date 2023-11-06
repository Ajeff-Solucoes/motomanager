package br.com.jeffersondev.motormanager.controller;

import br.com.jeffersondev.motormanager.dto.cliente.*;
import br.com.jeffersondev.motormanager.dto.filtro.ClienteFiltro;
import br.com.jeffersondev.motormanager.service.ClienteService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

	private final ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	
	@GetMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public String status() {
		return "Clientes it's running";
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteResponse create(@RequestBody @Valid ClienteRequest dto) {
		return service.create(dto);
	}
	
	
	@GetMapping("{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<ClienteResponse> findAllAtivoAndOrderByNome(@PathVariable Integer pgNo, @PathVariable Integer size){
		Paginacao paginacao = new Paginacao(pgNo, size, "asc");
		return service.findAllAtivoAndOrderByNome(paginacao);
	}
	
	
	@GetMapping("/f/{pgNo}/{size}")
	@ResponseStatus(HttpStatus.OK)
	public Page<ClienteResponse> filter(
			@RequestParam String order,
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) String placa,
			@PathVariable Integer pgNo, @PathVariable Integer size			
			){
		Paginacao paginacao = new Paginacao(pgNo, size, order);
		ClienteFiltro filtro = new ClienteFiltro(nome, placa);
		return service.filter(filtro, paginacao);
	}
	
	
	
	@GetMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public ClienteResponse getById(@PathVariable String uuid) {
		return service.getById(uuid);
	}
	

	@DeleteMapping("{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable String uuid) {
		service.deleteById(uuid);
	}
	
	
	@DeleteMapping("{cliente}/veiculo/{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteVeiculoById(@PathVariable String cliente, @PathVariable String uuid) {
		service.deleteVeiculoById(cliente, uuid);
	}
	
	
	@PutMapping("{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public ClienteResponse update (@PathVariable String uuid, @RequestBody ClienteRequestUpdate dto) {
		return service.update(uuid, dto);
	}
	
	
	
	@PutMapping("{cliente}/veiculo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addVeiculo(@PathVariable String cliente, @RequestBody VeiculoAddRequest dto) {
		service.addVeiculo(cliente, dto);
	}
	
	
	@GetMapping("/nome/{nome}")
	@ResponseStatus(HttpStatus.OK)
	public List<ClienteResponseSimples> getByNome(@PathVariable String nome) {
		return service.findByNome(nome);
	}
	
	
	@GetMapping("/{uuid}/veiculos")
	@ResponseStatus(HttpStatus.OK)
	public List<VeiculoResponse> getVeiculosByCliente(@PathVariable String uuid) {
		return service.getVeiculosByCliente(uuid);
	}
}
