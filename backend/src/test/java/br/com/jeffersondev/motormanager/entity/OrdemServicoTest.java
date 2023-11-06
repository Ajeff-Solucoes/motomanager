package br.com.jeffersondev.motormanager.entity;

//@ActiveProfiles("tst")
public class OrdemServicoTest {
//	
//	
//	@Test
//	@DisplayName("Deve retornar zerado quando não tiver itens de servico e produto")
//	public void calculaComZeroItem() {
//		OrdemServico os = getEntity();
//		
//		BigDecimal result = os.calcularTotal();
//		
//		assertThat(result).isEqualTo(BigDecimal.ZERO);
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.ZERO);
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.ZERO);
//		assertThat(os.getTotalProduto()).isEqualTo(BigDecimal.ZERO);
//	}
//	
//	
//	@Test
//	@DisplayName("Deve retornar zerado quando não tiver itens de servico")
//	public void calculaServicosZeroItem() {
//		OrdemServico os = getEntity();
//		
//		BigDecimal result = os.calculaServicos();
//		
//		assertThat(result).isEqualTo(BigDecimal.ZERO);
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.ZERO);
//	}
//	
//	
//	@Test
//	@DisplayName("Deve retornar zerado quando não tiver itens de produto")
//	public void calculaProdutoZeroItem() {
//		OrdemServico os = getEntity();
//		
//		BigDecimal result = os.calculaProdutos();
//		
//		assertThat(result).isEqualTo(BigDecimal.ZERO);
//		assertThat(os.getTotalProduto()).isEqualTo(BigDecimal.ZERO);
//	}
//	
//	
//	@Test
//	@DisplayName("Deve calcular total somente com um item servico")
//	public void calculaTotalServicosComUmItem() {
//		final var uuid = UUID.randomUUID().toString();
//		final var qtd = 1.0;
//		final var valor = BigDecimal.TEN;
//		final var servico = Mockito.mock(Servico.class);
//		OrdemServico os = getEntity();
//		OSItemServico item = new OSItemServico(uuid, qtd, valor, os, servico);
//		os.getServicos().add(item);
//		
//		BigDecimal result = os.calcularTotal();
//		BigDecimal result2 = os.calculaServicos();
//		BigDecimal result3 = os.calculaProdutos();
//		assertThat(result).isEqualTo(BigDecimal.TEN.setScale(1));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.TEN.setScale(1));
//		assertThat(result2).isEqualTo(BigDecimal.TEN.setScale(1));
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.TEN.setScale(1));
//		assertThat(os.getTotalProduto()).isEqualTo(BigDecimal.ZERO);
//		
//		item.setQuantidade(3.0);
//		result = os.calcularTotal();
//		result2 = os.calculaServicos();
//		assertThat(result).isEqualTo(BigDecimal.valueOf(30).setScale(1));
//		assertThat(result2).isEqualTo(BigDecimal.valueOf(30).setScale(1));
//		assertThat(result3).isEqualTo(BigDecimal.ZERO);
//	}
//	
//	
//	@Test
//	@DisplayName("Deve calcular total com cinco itens servico")
//	public void calculaTotalServicosComCincoItem() {
//		final var servico = Mockito.mock(Servico.class);
//		OrdemServico os = getEntity();
//		OSItemServico item1 = new OSItemServico(UUID.randomUUID().toString(), 1.0, BigDecimal.TEN, os, servico);
//		OSItemServico item2 = new OSItemServico(UUID.randomUUID().toString(), 2.0, BigDecimal.TEN, os, servico);
//		OSItemServico item3 = new OSItemServico(UUID.randomUUID().toString(), 3.0, BigDecimal.TEN, os, servico);
//		OSItemServico item4 = new OSItemServico(UUID.randomUUID().toString(), 4.0, BigDecimal.TEN, os, servico);
//		OSItemServico item5 = new OSItemServico(UUID.randomUUID().toString(), 5.0, BigDecimal.ONE, os, servico);
//		os.getServicos().add(item1);
//		os.getServicos().add(item2);
//		os.getServicos().add(item3);
//		os.getServicos().add(item4);
//		os.getServicos().add(item5);
//		
//		BigDecimal result = os.calcularTotal();
//		BigDecimal result2 = os.calculaServicos();
//		BigDecimal result3 = os.calculaProdutos();
//		assertThat(result).isEqualTo(BigDecimal.valueOf(105).setScale(1));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.valueOf(105).setScale(1));
//		assertThat(result2).isEqualTo(BigDecimal.valueOf(105).setScale(1));
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.valueOf(105).setScale(1));
//		assertThat(result3).isEqualTo(BigDecimal.ZERO);
//		
//		item1.setValor(BigDecimal.valueOf(105));
//		result = os.calcularTotal();
//		result2 = os.calculaServicos();
//		assertThat(result).isEqualTo(BigDecimal.valueOf(200).setScale(1));
//		assertThat(result2).isEqualTo(BigDecimal.valueOf(200).setScale(1));
//		assertThat(os.getTotalProduto()).isEqualTo(BigDecimal.ZERO);
//	}
//	
//	
//	@Test
//	@DisplayName("Deve calcular total somente com um item produto")
//	public void calculaTotalProdutoComUmItem() {
//		final var uuid = UUID.randomUUID().toString();
//		final var qtd = 1.0;
//		final var valor = BigDecimal.TEN;
//		final var produto = Mockito.mock(Produto.class);
//		OrdemServico os = getEntity();
//		OSItemProduto item = new OSItemProduto(uuid, qtd, valor, os, produto);
//		os.getProdutos().add(item);
//		
//		BigDecimal result = os.calcularTotal();
//		BigDecimal result2 = os.calculaServicos();
//		BigDecimal result3 = os.calculaProdutos();
//		assertThat(result).isEqualTo(BigDecimal.TEN.setScale(1));
//		assertThat(result3).isEqualTo(BigDecimal.TEN.setScale(1));
//		assertThat(result2).isEqualTo(BigDecimal.ZERO);
//		
//		item.setQuantidade(3.0);
//		result = os.calcularTotal();
//		result3 = os.calculaProdutos();
//		assertThat(result).isEqualTo(BigDecimal.valueOf(30).setScale(1));
//		assertThat(result3).isEqualTo(BigDecimal.valueOf(30).setScale(1));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve calcular total com cinco itens produto")
//	public void calculaTotalProdutosComCincoItem() {
//		final var produto = Mockito.mock(Produto.class);
//		OrdemServico os = getEntity();
//		OSItemProduto item1 = new OSItemProduto(UUID.randomUUID().toString(), 1.0, BigDecimal.TEN, os, produto);
//		OSItemProduto item2 = new OSItemProduto(UUID.randomUUID().toString(), 2.0, BigDecimal.TEN, os, produto);
//		OSItemProduto item3 = new OSItemProduto(UUID.randomUUID().toString(), 3.0, BigDecimal.TEN, os, produto);
//		OSItemProduto item4 = new OSItemProduto(UUID.randomUUID().toString(), 4.0, BigDecimal.TEN, os, produto);
//		OSItemProduto item5 = new OSItemProduto(UUID.randomUUID().toString(), 5.0, BigDecimal.ONE, os, produto);
//		os.getProdutos().add(item1);
//		os.getProdutos().add(item2);
//		os.getProdutos().add(item3);
//		os.getProdutos().add(item4);
//		os.getProdutos().add(item5);
//		
//		BigDecimal result = os.calcularTotal();
//		BigDecimal result2 = os.calculaServicos();
//		BigDecimal result3 = os.calculaProdutos();
//		assertThat(result).isEqualTo(BigDecimal.valueOf(105).setScale(1));
//		assertThat(result3).isEqualTo(BigDecimal.valueOf(105).setScale(1));
//		assertThat(result2).isEqualTo(BigDecimal.ZERO);
//		
//		item1.setValor(BigDecimal.valueOf(105));
//		result = os.calcularTotal();
//		result3 = os.calculaProdutos();
//		assertThat(result).isEqualTo(BigDecimal.valueOf(200).setScale(1));
//		assertThat(result3).isEqualTo(BigDecimal.valueOf(200).setScale(1));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve calcular total com um item servico e um item produto")
//	public void calculaTotalServicoProdutoComUmItem() {
//		final var servico = Mockito.mock(Servico.class);
//		final var produto = Mockito.mock(Produto.class);
//		OrdemServico os = getEntity();
//		OSItemServico item1 = new OSItemServico(UUID.randomUUID().toString(), 1.0, BigDecimal.TEN, os, servico);
//		OSItemProduto item2 = new OSItemProduto(UUID.randomUUID().toString(), 2.0, BigDecimal.TEN, os, produto);
//		os.getServicos().add(item1);
//		os.getProdutos().add(item2);
//		
//		BigDecimal result = os.calcularTotal();
//		BigDecimal result2 = os.calculaServicos();
//		BigDecimal result3 = os.calculaProdutos();
//		assertThat(result).isEqualTo(BigDecimal.valueOf(30).setScale(1));
//		assertThat(result2).isEqualTo(BigDecimal.valueOf(10).setScale(1));
//		assertThat(result3).isEqualTo(BigDecimal.valueOf(20).setScale(1));
//		
//		item1.setValor(BigDecimal.valueOf(100));
//		item2.setValor(BigDecimal.valueOf(200));
//		result = os.calcularTotal();
//		result2 = os.calculaServicos();
//		result3 = os.calculaProdutos();
//		assertThat(result).isEqualTo(BigDecimal.valueOf(500).setScale(1));
//		assertThat(result2).isEqualTo(BigDecimal.valueOf(100).setScale(1));
//		assertThat(result3).isEqualTo(BigDecimal.valueOf(400).setScale(1));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve calcular total com dois servicos e um item produto")
//	public void calculaTotalServicoProdutoComTresItem() {
//		final var servico = Mockito.mock(Servico.class);
//		final var produto = Mockito.mock(Produto.class);
//		OrdemServico os = getEntity();
//		OSItemServico item0 = new OSItemServico(UUID.randomUUID().toString(), 10.0, BigDecimal.ONE, os, servico);
//		OSItemServico item1 = new OSItemServico(UUID.randomUUID().toString(), 1.0, BigDecimal.TEN, os, servico);
//		OSItemProduto item2 = new OSItemProduto(UUID.randomUUID().toString(), 2.0, BigDecimal.TEN, os, produto);
//		os.getServicos().add(item0);
//		os.getServicos().add(item1);
//		os.getProdutos().add(item2);
//		
//		BigDecimal result = os.calcularTotal();
//		BigDecimal result2 = os.calculaServicos();
//		BigDecimal result3 = os.calculaProdutos();
//		assertThat(result).isEqualTo(BigDecimal.valueOf(40).setScale(1));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.valueOf(40).setScale(1));
//		assertThat(result2).isEqualTo(BigDecimal.valueOf(20).setScale(1));
//		assertThat(os.getTotalServico()).isEqualTo(BigDecimal.valueOf(20).setScale(1));
//		assertThat(result3).isEqualTo(BigDecimal.valueOf(20).setScale(1));
//		assertThat(os.getTotalProduto()).isEqualTo(BigDecimal.valueOf(20).setScale(1));
//		
//		item1.setValor(BigDecimal.valueOf(100));
//		item2.setValor(BigDecimal.valueOf(200));
//		item2.setQuantidade(5.0);
//		result = os.calcularTotal();
//		result2 = os.calculaServicos();
//		result3 = os.calculaProdutos();
//		assertThat(result).isEqualTo(BigDecimal.valueOf(1110).setScale(1));
//		assertThat(result2).isEqualTo(BigDecimal.valueOf(110).setScale(1));
//		assertThat(result3).isEqualTo(BigDecimal.valueOf(1000).setScale(1));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve calcular total com servicos e produto com desconto e acrescimos")
//	public void calculaTotalServicoProdutoComDescontoEAcrescimoItem() {
//		final var servico = Mockito.mock(Servico.class);
//		final var produto = Mockito.mock(Produto.class);
//		OrdemServico os = getEntity();
//		OSItemServico item0 = new OSItemServico(UUID.randomUUID().toString(), 10.0, BigDecimal.ONE, os, servico);
//		OSItemServico item1 = new OSItemServico(UUID.randomUUID().toString(), 1.0, BigDecimal.TEN, os, servico);
//		OSItemProduto item2 = new OSItemProduto(UUID.randomUUID().toString(), 2.0, BigDecimal.TEN, os, produto);
//		os.getServicos().add(item0);
//		os.getServicos().add(item1);
//		os.getProdutos().add(item2);
//		
//		os.setTotalDesconto(BigDecimal.valueOf(5));
//		BigDecimal result = os.calcularTotal();
//		assertThat(result).isEqualTo(BigDecimal.valueOf(35).setScale(1));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.valueOf(35).setScale(1));
//		
//		os.setTotalAcrescimo(BigDecimal.TEN);
//		result = os.calcularTotal();
//		assertThat(result).isEqualTo(BigDecimal.valueOf(45).setScale(1));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.valueOf(45).setScale(1));
//	}
//	
//	
//	@Test
//	@DisplayName("Deve dar erro se o valor do desconto for maior que o total")
//	public void errocalculaTotalServicoProdutoComDescontoMaiorTotal() {
//		final var servico = Mockito.mock(Servico.class);
//		final var produto = Mockito.mock(Produto.class);
//		
//		final var msgError = "O valor do desconto não pode ser maior que o total";
//		
//		OrdemServico os = getEntity();
//		OSItemServico item0 = new OSItemServico(UUID.randomUUID().toString(), 10.0, BigDecimal.ONE, os, servico);
//		OSItemServico item1 = new OSItemServico(UUID.randomUUID().toString(), 1.0, BigDecimal.TEN, os, servico);
//		OSItemProduto item2 = new OSItemProduto(UUID.randomUUID().toString(), 2.0, BigDecimal.TEN, os, produto);
//		os.getServicos().add(item0);
//		os.getServicos().add(item1);
//		os.getProdutos().add(item2);
//		
//		os.setTotalDesconto(BigDecimal.valueOf(45));
//		Throwable result = catchThrowable( () -> os.calcularTotal());
//		assertThat(result).isInstanceOf(ValorInformadoInvalidoException.class)
//			.hasMessage(msgError);
//		
//		os.setTotalAcrescimo(BigDecimal.valueOf(100));
//		BigDecimal result3 = os.calcularTotal();
//		assertThat(result3).isEqualTo(BigDecimal.valueOf(95).setScale(1));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.valueOf(95).setScale(1));
//		
//		os.setTotalDesconto(BigDecimal.valueOf(200));
//		result = catchThrowable( () -> os.calcularTotal());
//		assertThat(result).isInstanceOf(ValorInformadoInvalidoException.class)
//			.hasMessage(msgError);
//	}
//	
//	
//	@Test
//	@DisplayName("Deve dar retornar zerado quando o desconto for igual ao valor total")
//	public void retornaZerocalculaTotalServicoProdutoComDescontoMaiorTotal() {
//		final var servico = Mockito.mock(Servico.class);
//		final var produto = Mockito.mock(Produto.class);
//
//		OrdemServico os = getEntity();
//		OSItemServico item0 = new OSItemServico(UUID.randomUUID().toString(), 10.0, BigDecimal.ONE, os, servico);
//		OSItemServico item1 = new OSItemServico(UUID.randomUUID().toString(), 1.0, BigDecimal.TEN, os, servico);
//		OSItemProduto item2 = new OSItemProduto(UUID.randomUUID().toString(), 2.0, BigDecimal.TEN, os, produto);
//		os.getServicos().add(item0);
//		os.getServicos().add(item1);
//		os.getProdutos().add(item2);
//		
//		os.setTotalDesconto(BigDecimal.valueOf(40));
//		BigDecimal result = os.calcularTotal();
//		assertThat(result).isEqualTo(BigDecimal.ZERO.setScale(1));
//		assertThat(os.getTotal()).isEqualTo(BigDecimal.ZERO.setScale(1));
//		
//	}
//
//	
//	
//	
//	private OrdemServico getEntity() {
//		OrdemServico os =  new OrdemServico();
//		os.setCliente(Mockito.mock(Cliente.class));
//		os.setCodigo(1L);
//		os.setData(LocalDate.now());
//		os.setDefeito("Qualquer");
//		os.setMecanico(Mockito.mock(Mecanico.class));
//		os.setProdutos(new ArrayList<>());
//		os.setServicos(new ArrayList<>());
//		os.setStatus(StatusOS.ABERTO);
//		os.setUuid(UUID.randomUUID().toString());
//		os.setVeiculo(Mockito.mock(Veiculo.class));
//		os.setTotalAcrescimo(BigDecimal.ZERO);
//		os.setTotalDesconto(BigDecimal.ZERO);
//		return os;
//	}

}
