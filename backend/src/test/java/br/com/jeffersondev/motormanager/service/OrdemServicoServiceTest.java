package br.com.jeffersondev.motormanager.service;

//@ActiveProfiles("tst")
//@ExtendWith(SpringExtension.class)
public class OrdemServicoServiceTest {
//	
//	OrdemServicoService service;
//	
//	@MockBean OrdemServicoRepository repository;
//	@MockBean VeiculoRepository veiculoRepository;
//	@MockBean ClienteRepository clienteRepository;
//	@MockBean MecanicoRepository mecanicoRepository;
//	@MockBean ConfiguracaoRepository configRepository;
//	@MockBean ServicoRepository servicoRepository;
//	@MockBean ItemServicoRepository itemRepository;
//	@MockBean ItemProdutoRepository itemProdutoRepository;
//	@MockBean ProdutoRepository produtoRepository;
//	
//	@BeforeEach
//	public void setUp() {
//		this.service = new OrdemServicoServiceImpl(repository, veiculoRepository, clienteRepository, 
//				mecanicoRepository, configRepository, servicoRepository, itemRepository, itemProdutoRepository, produtoRepository);
//	}
//	
//	@Test
//	@DisplayName("Deve criar um registro com sucesso")
//	public void create() {
//		final var uuid = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		final var configuracao = new Configuracao("Codigo_OS", "0");
//		
//		OSRequestCreate req = new OSRequestCreate(data, defeito, observacao, uuid, uuid, uuid);
//		OSRequestCreate dto = dtoCreate(data, defeito, observacao, mecanico, cliente, veiculo);
//		OrdemServico entity = OrdemServico.createEntityByDTO(dto);
//		entity.setCodigo(Long.valueOf(1));
//		
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(entity);
//		Mockito.when(veiculoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(veiculo));
//		Mockito.when(clienteRepository.findById(Mockito.anyString())).thenReturn(Optional.of(cliente));
//		Mockito.when(mecanicoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(mecanico));
//		Mockito.when(configRepository.findByChave(Mockito.anyString())).thenReturn(Optional.of(configuracao));
//		
//		OrdemServicoSimpleResponse result = service.create(req);
//		
//		assertThat(result).isNotNull();
//		assertThat(result.getUuid()).isNotBlank();
//		assertThat(result.getStatus()).isEqualTo(StatusOS.ABERTO);
//		assertThat(result.getData()).isEqualTo(dto.getData());
//		assertThat(result.getCodigo()).isEqualTo(1);
//		assertThat(result.getDefeito()).isEqualTo(dto.getDefeito());
//		assertThat(result.getObservacao()).isEqualTo(dto.getObservacao());
//		assertThat(result.getCliente()).isEqualTo(dto.getCliente());
//		assertThat(result.getVeiculo()).isEqualTo(dto.getVeiculo());
//		assertThat(result.getCodigo()).isNotNull();
//		assertThat(result.getTotal()).isNotNull();
//		
//		Mockito.verify(repository, times(1)).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve criar um registro com sucesso sem passar a data que será a data atual")
//	public void createWithInformDate() {
//		final var uuid = UUID.randomUUID().toString();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		final var configuracao = new Configuracao("Codigo_OS", "0");
//		
//		OSRequestCreate req = new OSRequestCreate(null, defeito, observacao, uuid, uuid, uuid);
//		OSRequestCreate dto = new OSRequestCreate(defeito, observacao,veiculo , cliente, mecanico);
//		OrdemServico entity = OrdemServico.createEntityByDTO(dto);
//		entity.setCodigo(Long.valueOf(1));
//		entity.setData(LocalDate.now());
//		
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(entity);
//		Mockito.when(veiculoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(veiculo));
//		Mockito.when(clienteRepository.findById(Mockito.anyString())).thenReturn(Optional.of(cliente));
//		Mockito.when(mecanicoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(mecanico));
//		Mockito.when(configRepository.findByChave(Mockito.anyString())).thenReturn(Optional.of(configuracao));
//
//		OrdemServicoSimpleResponse result = service.create(req);
//		
//		assertThat(result).isNotNull();
//		assertThat(result.getUuid()).isNotBlank();
//		assertThat(result.getStatus()).isEqualTo(StatusOS.ABERTO);
//		assertThat(result.getCodigo()).isEqualTo(1);
//		assertThat(result.getData()).isEqualTo(LocalDate.now());
//		assertThat(result.getDefeito()).isEqualTo(dto.getDefeito());
//		assertThat(result.getObservacao()).isEqualTo(dto.getObservacao());
//		assertThat(result.getCliente()).isEqualTo(dto.getCliente());
//		assertThat(result.getVeiculo()).isEqualTo(dto.getVeiculo());
//		assertThat(result.getCodigo()).isNotNull();
//		assertThat(result.getTotal()).isNotNull();
//		
//		Mockito.verify(repository, times(1)).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve dar erro quando não encontrar a numeração inicial em configurações")
//	public void erroCreateWhenConfigNumberNotFound() {
//		final var uuid = UUID.randomUUID().toString();
//		final var observacao = "";
//		final var defeito = "Teste";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		
//		final var msgError = "Não existe código inicial configurado com a chave 'Codigo_OS'";
//		
//		OSRequestCreate req = new OSRequestCreate(null, defeito, observacao, uuid, uuid, uuid);
//		OSRequestCreate dto = new OSRequestCreate(defeito, observacao, veiculo, cliente, mecanico);
//		OrdemServico entity = OrdemServico.createEntityByDTO(dto);
//		entity.setCodigo(Long.valueOf(1));
//		
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(entity);
//		Mockito.when(veiculoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(veiculo));
//		Mockito.when(clienteRepository.findById(Mockito.anyString())).thenReturn(Optional.of(cliente));
//		Mockito.when(mecanicoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(mecanico));
//		Mockito.when(configRepository.findByChave(Mockito.anyString())).thenReturn(Optional.empty());
//		
//		Throwable result = catchThrowable(() -> service.create(req));
//		
//		assertThat(result).isInstanceOf(ResourceNotFoundException.class)
//			.hasMessage(msgError);
//		
//		Mockito.verify(repository, never()).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve dar erro quando não encontrar o veiculo")
//	public void erroCreateWhenVeiculoNotFound() {
//		final var uuid = UUID.randomUUID().toString();
//		final var observacao = "";
//		final var defeito = "Teste";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		
//		final var msgError = "Registro não encontrado com o UUID: " + uuid;
//		
//		OSRequestCreate req = new OSRequestCreate(null, defeito, observacao, uuid, uuid, uuid);
//		OSRequestCreate dto = new OSRequestCreate(defeito, observacao, veiculo, cliente, mecanico);
//		OrdemServico entity = OrdemServico.createEntityByDTO(dto);
//		entity.setCodigo(Long.valueOf(1));
//		
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(entity);
//		Mockito.when(veiculoRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
//		Mockito.when(clienteRepository.findById(Mockito.anyString())).thenReturn(Optional.of(cliente));
//		Mockito.when(mecanicoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(mecanico));
//		
//		Throwable result = catchThrowable(() -> service.create(req));
//		
//		assertThat(result).isInstanceOf(ResourceNotFoundException.class)
//			.hasMessage(msgError);
//		
//		Mockito.verify(repository, never()).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve dar erro quando não encontrar o cliente")
//	public void erroCreateWhenClienteNotFound() {
//		final var uuid = UUID.randomUUID().toString();
//		final var observacao = "";
//		final var defeito = "Teste";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		
//		final var msgError = "Registro não encontrado com o UUID: " + uuid;
//		
//		OSRequestCreate req = new OSRequestCreate(null, defeito, observacao, uuid, uuid, uuid);
//		OSRequestCreate dto = new OSRequestCreate(defeito, observacao, veiculo, cliente, mecanico);
//		OrdemServico entity = OrdemServico.createEntityByDTO(dto);
//		entity.setCodigo(Long.valueOf(1));
//		
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(entity);
//		Mockito.when(veiculoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(veiculo));
//		Mockito.when(clienteRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
//		Mockito.when(mecanicoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(mecanico));
//		
//		Throwable result = catchThrowable(() -> service.create(req));
//		
//		assertThat(result).isInstanceOf(ResourceNotFoundException.class)
//			.hasMessage(msgError);
//		
//		Mockito.verify(repository, never()).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve dar erro quando não encontrar o mecanico")
//	public void erroCreateWhenMecanicoNotFound() {
//		final var uuid = UUID.randomUUID().toString();
//		final var observacao = "";
//		final var defeito = "Teste";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		
//		final var msgError = "Registro não encontrado com o UUID: " + uuid;
//		
//		OSRequestCreate req = new OSRequestCreate(null, defeito, observacao, uuid, uuid, uuid);
//		OSRequestCreate dto = new OSRequestCreate(defeito, observacao, veiculo, cliente, mecanico);
//		OrdemServico entity = OrdemServico.createEntityByDTO(dto);
//		entity.setCodigo(Long.valueOf(1));
//		
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(entity);
//		Mockito.when(veiculoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(veiculo));
//		Mockito.when(clienteRepository.findById(Mockito.anyString())).thenReturn(Optional.of(cliente));
//		Mockito.when(mecanicoRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
//		
//		Throwable result = catchThrowable(() -> service.create(req));
//		
//		assertThat(result).isInstanceOf(ResourceNotFoundException.class)
//			.hasMessage(msgError);
//		
//		Mockito.verify(repository, never()).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	
//	@Test
//	@DisplayName("Deve retornar erro quando o DTO for null")
//	public void errorCreateDtoNullTest() {
//		final var msgError = "O recurso não pode ser nulo: OrdemServicoCreateDTO";
//		OSRequestCreate dto = null;
//		
//			Throwable result = catchThrowable( () -> service.create(dto));
//		
//		assertThat(result).isInstanceOf(ResourceShouldNotBeNullException.class)
//			.hasMessage(msgError);
//		
//		Mockito.verify(repository, never()).save(Mockito.any(OrdemServico.class));
//	}
//	
//
//	
//	@Test
//	@DisplayName("Deve procurar e retornar um registro por UUID")
//	public void simpleFindByIdTest() {
//		final var uuid = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		
//		OrdemServico entity = OrdemServico.createEntity(data, defeito, observacao, cliente, veiculo, mecanico);
//		
//		Mockito.when(repository.findById(uuid)).thenReturn(Optional.of(entity));
//		
//		OrdemServicoSimpleResponse result = service.simpleFindById(uuid);
//		
//		assertThat(result).isNotNull();
//		assertThat(result.getUuid()).isNotBlank();
//		assertThat(result.getUuid()).isEqualTo(entity.getUuid());
//		assertThat(result.getStatus()).isNotNull();
//		assertThat(result.getData()).isEqualTo(data);
//		assertThat(result.getDefeito()).isEqualTo(defeito);
//		assertThat(result.getObservacao()).isEqualTo(observacao);
//		assertThat(result.getCliente()).isEqualTo(cliente);
//		assertThat(result.getVeiculo()).isEqualTo(veiculo);
//		assertThat(result.getTotal()).isNotNull();
//		
//		Mockito.verify(repository, times(1)).findById(uuid);
//	}
//	
//	
//	@Test
//	@DisplayName("Deve procurar e retornar um registro COMPLETO por UUID")
//	public void completeFindByIdTest() {
//		final var uuid = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		final var servico = Mockito.mock(Servico.class);
//		final var pendencia = "";
//		final var totalDesconto = BigDecimal.ZERO;
//		final var totalAcrescimo = BigDecimal.ZERO;
//		
//		OrdemServico entity = OrdemServico.createEntity(data, defeito, observacao, cliente, veiculo, mecanico);
//		OSItemServico item = new OSItemServico(uuid, 1.0, BigDecimal.TEN, entity, servico);
//		entity.getServicos().add(item);
//		entity.calculaServicos();
//		entity.calculaProdutos();
//		
//		Mockito.when(repository.findById(uuid)).thenReturn(Optional.of(entity));
//		
//		OSResponse result = service.findById(uuid);
//		
//		assertThat(result).isNotNull();
//		assertThat(result.getUuid()).isNotBlank();
//		assertThat(result.getUuid()).isEqualTo(entity.getUuid());
//		assertThat(result.getStatus()).isNotNull();
//		assertThat(result.getData()).isEqualTo(data);
//		assertThat(result.getDefeito()).isEqualTo(defeito);
//		assertThat(result.getObservacao()).isEqualTo(observacao);
//		assertThat(result.getCliente()).isEqualTo(cliente);
//		assertThat(result.getVeiculo()).isEqualTo(veiculo);
//		assertThat(result.getMecanico()).isEqualTo(mecanico);
//		assertThat(result.getPendencias()).isEqualTo(pendencia);
//		assertThat(result.getServicos()).isNotNull();
//		assertThat(result.getProdutos()).isNotNull();
//		assertThat(result.getProdutos()).isEmpty();
//		assertThat(result.getTotal()).isEqualTo(BigDecimal.TEN.setScale(2));
//		assertThat(result.getTotalServico()).isEqualTo(BigDecimal.TEN.setScale(2));
//		assertThat(result.getTotalProduto()).isEqualTo(BigDecimal.ZERO.setScale(2));
//		assertThat(result.getTotalDescontos()).isEqualTo(totalDesconto);
//		assertThat(result.getTotalAcrescimos()).isEqualTo(totalAcrescimo);
//		
//		Mockito.verify(repository, times(1)).findById(uuid);
//	}
//	
//	
//	@Test
//	@DisplayName("Deve retornar erro quando o UUID for blank")
//	public void errorSimpleFindByIdBlank() {
//		final var msgError = "UUID não pode estar em branco na pesquisa";
//		final String uuid = "";
//		
//		Throwable result = catchThrowable( () -> service.simpleFindById(uuid));
//		
//		assertThat(result).isInstanceOf(ResourceShouldNotBeNullException.class)
//		.hasMessage(msgError);
//	}
//
//	
//	@Test
//	@DisplayName("Deve retornar erro quando não encontrar registro por UUID")
//	public void errorSimpleFindByIdNotFound() {
//		final var uuid = UUID.randomUUID().toString();
//		final var msgError = "Registro não encontrado com o UUID: " + uuid;
//		
//		Mockito.when(repository.findById(uuid)).thenReturn(Optional.empty());
//		
//		Throwable result = catchThrowable( () -> service.simpleFindById(uuid));
//		
//		assertThat(result).isInstanceOf(ResourceNotFoundException.class)
//		.hasMessage(msgError);
//	}
//	
//
//	@Test
//	@DisplayName("Deve retornar erro quando o UUID for null")
//	public void errorSimpleFindByIdNull() {
//		final String uuid = null;
//		
//		Throwable result = catchThrowable( () -> service.simpleFindById(uuid));
//		
//		assertThat(result).isInstanceOf(NullPointerException.class);
//	}
//	
//	
//	@Test
//	@DisplayName("Deve deletar um registro por UUID")
//	public void deleteTest() {
//		final var uuid = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		
//		OrdemServico entity = OrdemServico.createEntity(data, defeito, observacao, cliente, veiculo, mecanico);
//		
//		Mockito.when(repository.findById(uuid)).thenReturn(Optional.of(entity));
//		Mockito.doNothing().when(repository).delete(entity);
//		
//		service.deleteById(uuid);
//		
//		Mockito.verify(repository, times(1)).findById(uuid);
//		Mockito.verify(repository, times(1)).delete(entity);
//	}
//	
//	
//	@Test
//	@DisplayName("Deve retornar erro ao tentar excluir um registro com relacionamento outros registros")
//	public void errorDeleteDateIntegrityViolation() {
//		final var uuid = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		final var codigo = 1L;
//		
//		final var msgError = "A Ordem de Serviço nº " + codigo + " não pode ser excluída.";
//
//		OrdemServico entity = OrdemServico.createEntity(data, defeito, observacao, cliente, veiculo, mecanico);
//		entity.setCodigo(1L);
//		
//		Mockito.when(repository.findById(uuid)).thenReturn(Optional.of(entity));
//		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).delete(entity);
//		
//		Throwable error = catchThrowable( () -> service.deleteById(uuid));
//		
//		assertThat(error).isInstanceOf(OperationNotExecutedException.class)
//		.hasMessage(msgError);
//		
//		Mockito.verify(repository, times(1)).findById(uuid);
//		Mockito.verify(repository, times(1)).delete(entity);
//	}
//	
//	
//	@Test
//	@DisplayName("Deve retornar erro quando não encontrar registro por UUID para deletar")
//	public void errorDeleteIdNotFound() {
//		final var uuid = UUID.randomUUID().toString();
//		final var msgError = "Registro não encontrado com o UUID: " + uuid;
//		
//		Mockito.when(repository.findById(uuid)).thenReturn(Optional.empty());
//		
//		Throwable result = catchThrowable( () -> service.deleteById(uuid));
//		
//		assertThat(result).isInstanceOf(ResourceNotFoundException.class)
//		.hasMessage(msgError);
//	}
//	
//
//	@Test
//	@DisplayName("Deve retornar erro quando o UUID for null para deletar")
//	public void errorDeleteIdNull() {
//		final String uuid = null;
//		
//		Throwable result = catchThrowable( () -> service.deleteById(uuid));
//		
//		assertThat(result).isInstanceOf(NullPointerException.class);
//	}
//	
//	
//	@Test
//	@DisplayName("Deve retornar erro quando o UUID for blank para deletar")
//	public void errorDeleteIdBlank() {
//		final var msgError = "UUID não pode estar em branco na pesquisa";
//		final String uuid = "";
//		
//		Throwable result = catchThrowable( () -> service.deleteById(uuid));
//		
//		assertThat(result).isInstanceOf(ResourceShouldNotBeNullException.class)
//		.hasMessage(msgError);
//	}
//
//	
//	@Test
//	@DisplayName("Deve alterar um registro com sucesso")
//	public void updateTest() {
//		final var uuid = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		
//		final var updateDefeito = "Não Funciona - Updated";
//		final var updateObs = "Updated";
//		
//		OSRequestUpdate dto = new OSRequestUpdate(data, defeito, observacao, uuid, uuid, uuid);
//		dto.setStatus("Aberto");
//		dto.setDefeito(updateDefeito);
//		dto.setObservacao(updateObs);
//		
//		OrdemServico entity = OrdemServico.createEntity(data, defeito, observacao, cliente, veiculo, mecanico);
//		entity.setUuid(uuid);
//		
//		Mockito.when(repository.findById(uuid)).thenReturn(Optional.of(entity));
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(entity);
//		Mockito.when(veiculoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(veiculo));
//		Mockito.when(clienteRepository.findById(Mockito.anyString())).thenReturn(Optional.of(cliente));
//		Mockito.when(mecanicoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(mecanico));
//		
//		assertThat(entity.getDefeito()).isEqualTo(defeito);
//		assertThat(entity.getObservacao()).isEqualTo(observacao);
//		
//		OSResponse result = service.update(uuid, dto);
//		
//		assertThat(result).isNotNull();
//		assertThat(result.getUuid()).isNotBlank();
//		assertThat(result.getStatus()).isEqualTo(StatusOS.ABERTO);
//		assertThat(result.getData()).isEqualTo(dto.getData());
//		assertThat(result.getDefeito()).isEqualTo(updateDefeito);
//		assertThat(result.getObservacao()).isEqualTo(updateObs);
//		
//		Mockito.verify(repository, times(1)).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve dar erro quando não encontrar o mecanico em update")
//	public void erroUpdateWhenMecanicoNotFound() {
//		final var uuid = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var observacao = "";
//		final var defeito = "Teste";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		
//		final var msgError = "Registro não encontrado com o UUID: " + uuid;
//		
//		OSRequestUpdate dto = new OSRequestUpdate(data, defeito, observacao, uuid, uuid, uuid);
//		dto.setStatus("Aberto");
//		
//		OrdemServico entity = OrdemServico.createEntity(data, defeito, observacao, cliente, veiculo, mecanico);
//		entity.setUuid(uuid);
//		
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(entity);
//		Mockito.when(veiculoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(veiculo));
//		Mockito.when(clienteRepository.findById(Mockito.anyString())).thenReturn(Optional.of(cliente));
//		Mockito.when(mecanicoRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
//		
//		Throwable result = catchThrowable(() -> service.update(uuid, dto));
//		
//		assertThat(result).isInstanceOf(ResourceNotFoundException.class)
//			.hasMessage(msgError);
//		
//		Mockito.verify(repository, never()).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve incluir um registro que ainda não existe com sucesso em uma lista vazia")
//	public void addItemServicoNovo() {
//		final var uuidOs = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		final var uuidServico = UUID.randomUUID().toString();
//		final var qtdItem = 1.0;
//		final var valorItem = BigDecimal.valueOf(100.0);
//		
//		Servico servico = new Servico(uuidServico, "Servico", true, BigDecimal.TEN);
//		
//		OrdemServico os = new OrdemServico(data, defeito, observacao, cliente, veiculo, mecanico);
//		os.setUuid(uuidOs);
//		OrdemServicoItemServicoRequest dto = new OrdemServicoItemServicoRequest(qtdItem, valorItem, uuidServico, os.getUuid());
//
//		Mockito.when(repository.findById(uuidOs)).thenReturn(Optional.of(os));
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(os);
//		Mockito.when(servicoRepository.findById(uuidServico)).thenReturn(Optional.of(servico));
//		
//		assertThat(os.getServicos()).isEmpty();
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.ZERO);
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.ZERO);
//		
//		List<OSItemServico> result = service.addItemServico(dto);
//		
//		assertThat(result).isNotNull();
//		assertThat(os.getServicos().size()).isEqualTo(1);
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.valueOf(100).setScale(2));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.valueOf(100).setScale(2));
//		
//		Mockito.verify(repository, times(1)).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve incluir um registro que ainda não existe com sucesso em uma lista com um item cadastrado")
//	public void addItemServicoNovo1() {
//		final var uuidOs = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		final var servicoMock = Mockito.mock(Servico.class);
//		final var uuidServico = UUID.randomUUID().toString();
//		final var qtdItem = 1.0;
//		final var valorItem = BigDecimal.valueOf(100.0);
//		
//		OrdemServico os = new OrdemServico(data, defeito, observacao, cliente, veiculo, mecanico);
//		Servico servico = new Servico(uuidServico, "Servico", true, BigDecimal.TEN);
//		OSItemServico itemExistente = new OSItemServico(UUID.randomUUID().toString(), 1.0, BigDecimal.TEN, os,servicoMock);
//		
//		os.setUuid(uuidOs);
//		os.getServicos().add(itemExistente);
//		os.calcularTotal();
//		OrdemServicoItemServicoRequest dto = new OrdemServicoItemServicoRequest(qtdItem, valorItem, uuidServico, os.getUuid());
//
//		Mockito.when(repository.findById(uuidOs)).thenReturn(Optional.of(os));
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(os);
//		Mockito.when(servicoRepository.findById(uuidServico)).thenReturn(Optional.of(servico));
//		
//		assertThat(os.getServicos().size()).isEqualTo(1);
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.TEN.setScale(1));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.TEN.setScale(2));
//		
//		List<OSItemServico> result = service.addItemServico(dto);
//		
//		assertThat(result).isNotNull();
//		assertThat(os.getServicos().size()).isEqualTo(2);
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.valueOf(110).setScale(2));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.valueOf(110).setScale(2));
//		
//		Mockito.verify(repository, times(1)).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve atualizar o valor e quantidade do item com mesmo servico já cadastrado")
//	public void addItemServicoCadastrado() {
//		final var uuidOs = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		final var uuidServico = UUID.randomUUID().toString();
//		final var qtdItem = 1.0;
//		final var valorItem = BigDecimal.TEN;
//		
//		OrdemServico os = new OrdemServico(data, defeito, observacao, cliente, veiculo, mecanico);
//		Servico servico = new Servico(uuidServico, "Servico", true, BigDecimal.TEN);
//		OSItemServico itemExistente = new OSItemServico(UUID.randomUUID().toString(), 1.0, BigDecimal.TEN, os, servico);
//		
//		os.setUuid(uuidOs);
//		os.getServicos().add(itemExistente);
//		os.calcularTotal();
//		OrdemServicoItemServicoRequest dto = new OrdemServicoItemServicoRequest(qtdItem, valorItem, uuidServico, os.getUuid());
//
//		Mockito.when(repository.findById(uuidOs)).thenReturn(Optional.of(os));
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(os);
//		Mockito.when(servicoRepository.findById(uuidServico)).thenReturn(Optional.of(servico));
//		
//		assertThat(os.getServicos().size()).isEqualTo(1);
//		assertThat(os.getServicos().get(0).getQuantidade()).isEqualTo(1);
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.TEN.setScale(1));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.TEN.setScale(2));
//		
//		List<OSItemServico> result = service.addItemServico(dto);
//		
//		assertThat(result).isNotNull();
//		assertThat(os.getServicos().size()).isEqualTo(1);
//		assertThat(result.get(0).getQuantidade()).isEqualTo(2);
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.valueOf(20).setScale(2));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.valueOf(20).setScale(2));
//		
//		Mockito.verify(repository, times(1)).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve atualizar o valor e quantidade do item com mesmo servico já cadastrado mas com valor diferente")
//	public void addItemServicoCadastrado2() {
//		final var uuidOs = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		final var uuidServico = UUID.randomUUID().toString();
//		final var qtdItem = 1.0;
//		final var valorItem = BigDecimal.valueOf(15.0);
//		
//		OrdemServico os = new OrdemServico(data, defeito, observacao, cliente, veiculo, mecanico);
//		Servico servico = new Servico(uuidServico, "Servico", true, BigDecimal.TEN);
//		OSItemServico itemExistente = new OSItemServico(UUID.randomUUID().toString(), 1.0, BigDecimal.valueOf(10), os, servico);
//		
//		os.setUuid(uuidOs);
//		os.getServicos().add(itemExistente);
//		os.calcularTotal();
//		OrdemServicoItemServicoRequest dto = new OrdemServicoItemServicoRequest(qtdItem, valorItem, uuidServico, os.getUuid());
//
//		Mockito.when(repository.findById(uuidOs)).thenReturn(Optional.of(os));
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(os);
//		Mockito.when(servicoRepository.findById(uuidServico)).thenReturn(Optional.of(servico));
//		
//		assertThat(os.getServicos().size()).isEqualTo(1);
//		assertThat(os.getServicos().get(0).getQuantidade()).isEqualTo(1);
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.TEN.setScale(1));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.TEN.setScale(2));
//		
//		List<OSItemServico> result = service.addItemServico(dto);
//		
//		assertThat(result).isNotNull();
//		assertThat(os.getServicos().size()).isEqualTo(1);
//		assertThat(result.get(0).getQuantidade()).isEqualTo(2);
//		assertThat(result.get(0).getValor()).isEqualTo(BigDecimal.valueOf(12.5).setScale(2));
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.valueOf(25).setScale(3));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.valueOf(25).setScale(2));
//		
//		Mockito.verify(repository, times(1)).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	
//	@Test
//	@DisplayName("Deve dar erro quando não encontrar a Ordem Servico para incluir o item")
//	public void erroUpdateWhenOrdemServicoNotFound() {
//		final var uuidOs = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		final var uuidServico = UUID.randomUUID().toString();
//		final var qtdItem = 1.0;
//		final var valorItem = BigDecimal.valueOf(15.0);
//		
//		final var msgError = "Registro não encontrado com o UUID: " + uuidOs;
//		
//		OrdemServico os = new OrdemServico(data, defeito, observacao, cliente, veiculo, mecanico);
//		Servico servico = new Servico(uuidServico, "Servico", true, BigDecimal.TEN);
//		os.setUuid(uuidOs);
//		OrdemServicoItemServicoRequest dto = new OrdemServicoItemServicoRequest(qtdItem, valorItem, uuidServico, os.getUuid());
//
//		Mockito.when(repository.findById(uuidOs)).thenReturn(Optional.empty());
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(os);
//		Mockito.when(servicoRepository.findById(uuidServico)).thenReturn(Optional.of(servico));
//		
//		Throwable result = catchThrowable(() -> service.addItemServico(dto));
//		
//		assertThat(result).isInstanceOf(ResourceNotFoundException.class)
//			.hasMessage(msgError);
//		
//		Mockito.verify(repository, never()).save(Mockito.any(OrdemServico.class));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve dar erro quando não encontrar o Servico para incluir o item")
//	public void erroUpdateWhenServicoNotFound() {
//		final var uuidOs = UUID.randomUUID().toString();
//		final var data = LocalDate.now();
//		final var defeito = "Não funciona";
//		final var observacao = "";
//		final var mecanico = Mockito.mock(Mecanico.class);
//		final var cliente = Mockito.mock(Cliente.class);
//		final var veiculo = Mockito.mock(Veiculo.class);
//		final var uuidServico = UUID.randomUUID().toString();
//		final var qtdItem = 1.0;
//		final var valorItem = BigDecimal.valueOf(15.0);
//		
//		final var msgError = "Registro não encontrado com o UUID: " + uuidServico;
//		
//		OrdemServico os = new OrdemServico(data, defeito, observacao, cliente, veiculo, mecanico);
//		os.setUuid(uuidOs);
//		OrdemServicoItemServicoRequest dto = new OrdemServicoItemServicoRequest(qtdItem, valorItem, uuidServico, os.getUuid());
//
//		Mockito.when(repository.findById(uuidOs)).thenReturn(Optional.of(os));
//		Mockito.when(repository.save(Mockito.any(OrdemServico.class))).thenReturn(os);
//		Mockito.when(servicoRepository.findById(uuidServico)).thenReturn(Optional.empty());
//		
//		Throwable result = catchThrowable(() -> service.addItemServico(dto));
//		
//		assertThat(result).isInstanceOf(ResourceNotFoundException.class)
//			.hasMessage(msgError);
//		
//		Mockito.verify(repository, never()).save(Mockito.any(OrdemServico.class));
//	}
//	
//	private OSRequestCreate dtoCreate(LocalDate data, String defeito, String observacao, Mecanico mecanico, 
//			Cliente cliente, Veiculo veiculo) {
//		return new OSRequestCreate(data, defeito, observacao, veiculo, cliente, mecanico);
//	}

}
