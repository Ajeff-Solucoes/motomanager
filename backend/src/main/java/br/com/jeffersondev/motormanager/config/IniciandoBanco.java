package br.com.jeffersondev.motormanager.config;

import br.com.jeffersondev.motormanager.entity.*;
import br.com.jeffersondev.motormanager.entity.cliente.Cliente;
import br.com.jeffersondev.motormanager.entity.cliente.Veiculo;
import br.com.jeffersondev.motormanager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

@Component
public class IniciandoBanco implements CommandLineRunner {

    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private MecanicoRepository mecanicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {

        //Marcas
        Marca marca1 = new Marca(UUID.randomUUID().toString(), "Honda", true);
        Marca marca2 = new Marca(UUID.randomUUID().toString(), "Yamaha", true);
        Marca marca3 = new Marca(UUID.randomUUID().toString(), "Harley-Devidson", true);
        Marca marca4 = new Marca(UUID.randomUUID().toString(), "Suzuki", true);
        Marca marca5 = new Marca(UUID.randomUUID().toString(), "Outro", false);
        marcaRepository.saveAll(Arrays.asList(marca1, marca2, marca3, marca4, marca5));

        //Modelos
        Modelo modeloHonda1 = new Modelo(UUID.randomUUID().toString(), "CBR1000RR Fireblade", true, marca1);
        Modelo modeloHonda3 = new Modelo(UUID.randomUUID().toString(), "Gold Wing", true, marca1);
        Modelo modeloYamaha1 = new Modelo(UUID.randomUUID().toString(), "Tenere 700", true, marca2);
        Modelo modeloYamaha2 = new Modelo(UUID.randomUUID().toString(), "XSR900", false, marca2);
        Modelo modeloSuzuki1 = new Modelo(UUID.randomUUID().toString(), "Hayabusa", true, marca3);
        Modelo modeloSuzuki2 = new Modelo(UUID.randomUUID().toString(), "Boulevard M109R", true, marca3);
        Modelo modeloHarley1 = new Modelo(UUID.randomUUID().toString(), "Sportster Iron 883", true, marca4);
        Modelo modeloHarley2 = new Modelo(UUID.randomUUID().toString(), "Road King", true, marca4);
        modeloRepository.saveAll(Arrays.asList(modeloHonda1, modeloHonda3, modeloYamaha1, modeloYamaha2));
        modeloRepository.saveAll(Arrays.asList(modeloSuzuki1, modeloSuzuki2, modeloHarley1, modeloHarley2));

        //Serviços
        Servico servico1 = new Servico(UUID.randomUUID().toString(), "Troca de óleo", true, new BigDecimal("50.00"));
        Servico servico2 = new Servico(UUID.randomUUID().toString(), "Reparo de freios", true, new BigDecimal("100.00"));
        Servico servico3 = new Servico(UUID.randomUUID().toString(), "Troca de velas de ignição", true, new BigDecimal("30.00"));
        servicoRepository.saveAll(Arrays.asList(servico1, servico2, servico3));

        //Produto
        Produto produto1 = new Produto(UUID.randomUUID().toString(), "Óleo de Motor", "1L", true, new BigDecimal("50.00"));
        Produto produto2 = new Produto(UUID.randomUUID().toString(), "Pastilhas de Freio", "Con", true, new BigDecimal("30.00"));
        Produto produto3 = new Produto(UUID.randomUUID().toString(), "Filtro de Ar", "Und", true, new BigDecimal("10.50"));
        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));

        //Mecanico
        Mecanico mecanico1 = new Mecanico(UUID.randomUUID().toString(), "João Silva", true);
        Mecanico mecanico2 = new Mecanico(UUID.randomUUID().toString(), "Maria Santos", true);
        Mecanico mecanico3 = new Mecanico(UUID.randomUUID().toString(), "Carlos Souza", true);
        mecanicoRepository.saveAll(Arrays.asList(mecanico1, mecanico2, mecanico3));

        //Veiculos
        Veiculo veic1 = new Veiculo(UUID.randomUUID().toString(), "HGD0022", "VERMELHA", 2020, true, modeloHonda1);
        Veiculo veic2 = new Veiculo(UUID.randomUUID().toString(), "JKF6325", "PRETA", 2010, true, modeloSuzuki2);
        Veiculo veic3 = new Veiculo(UUID.randomUUID().toString(), "POU9987", "VERMELHA", 2009, true, modeloHarley2);
        Veiculo veic4 = new Veiculo(UUID.randomUUID().toString(), "WSD9621", "PRETA", 2013, true, modeloYamaha1);
        Veiculo veic5 = new Veiculo(UUID.randomUUID().toString(), "MFP6347", "VERMELHA", 2023, true, modeloHonda1);
        Veiculo veic6 = new Veiculo(UUID.randomUUID().toString(), "HGG2235", "VERDE", 2002, true, modeloSuzuki2);
        Veiculo veic7 = new Veiculo(UUID.randomUUID().toString(), "EWW9966", "VERDE", 2015, true, modeloHarley1);
        Veiculo veic8 = new Veiculo(UUID.randomUUID().toString(), "PLN2214", "VERMELHA", 2015, true, modeloHonda1);
        Veiculo veic9 = new Veiculo(UUID.randomUUID().toString(), "MMN2214", "AZUL", 2018, true, modeloSuzuki2);
        Veiculo veic10 = new Veiculo(UUID.randomUUID().toString(), "JJH8877", "CINZA", 2019, true, modeloYamaha1);

        //Clients
        Cliente cliente1 = new Cliente(UUID.randomUUID().toString(), "João da Silva", true, "joao@email.com", "85898565521", "8588565521",
                LocalDate.of(1990, 5, 15), "Rua Antonio Alves, 525", "Bairro Centro", "Fortaleza/CE", "12345-678",
                new HashSet<>(Arrays.asList(veic1, veic2)));
        Cliente cliente2 = new Cliente(UUID.randomUUID().toString(), "Maria Oliveira", true, "maria@email.com", "98976543210", "1234567890",
                LocalDate.of(1985, 8, 20), "Rua Bruno Mezenga, 55", "Aldeota", "Fortaleza/CE", "54321-876",
                new HashSet<>(Arrays.asList(veic3)));
        Cliente cliente3 = new Cliente(UUID.randomUUID().toString(), "Carlos Santos", false, "carlos@email.com", "55955555555", "4444444444",
                LocalDate.of(1982, 3, 10), "Avenida Carlos Aragão, 562", "Centro", "Iguatu/CE", "98765-432",
                new HashSet<>(Arrays.asList(veic4, veic5, veic6, veic7)));
        Cliente cliente4 = new Cliente(UUID.randomUUID().toString(), "Ana Pereira", true, "ana@email.com", "11911111111", "2222222222",
                LocalDate.of(1995, 7, 2), "Rua Darius Alvarenga, 22", "Veneza", "Iguatu/CE", "65432-109",
                new HashSet<>(Arrays.asList(veic8)));
        Cliente cliente5 = new Cliente(UUID.randomUUID().toString(), "Fernando Gomes", true, "fernando@email.com", "66966666666", "7777777777",
                LocalDate.of(1988, 10, 30), "Avenida Eduardo Girão, 874", "Centro", "Acopiara/CE", "77777-888",
                new HashSet<>(Arrays.asList(veic9, veic10)));
        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3, cliente4, cliente5));
    }
}
