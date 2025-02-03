package br.com.alura.clientelo.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.clientelo.models.Pedido;

class GeradorDeRelatoriosServiceTest {

    private GeradorDeRelatoriosService geradorDeRelatoriosService;

    @BeforeEach
    void setUp() {
        geradorDeRelatoriosService = new GeradorDeRelatoriosService();
    }

    @Test
    void deveGerarRelatorioDeVendasCorretamente() {
        Pedido pedido1 = new Pedido("Produto A", "Categoria 1", "Foo", new BigDecimal("10.00"), 2, LocalDate.now());
        Pedido pedido2 = new Pedido("Produto B", "Categoria 2", "Foo", new BigDecimal("20.00"), 1, LocalDate.now());
        Pedido pedido3 = new Pedido("Produto C", "Categoria 1", "Foo", new BigDecimal("5.00"), 5, LocalDate.now());

        List<Pedido> pedidos = List.of(pedido1, pedido2, pedido3);

        String relatorio = geradorDeRelatoriosService.gerarRelatorioDeVendas(pedidos);

        assertTrue(relatorio.contains("TOTAL DE PEDIDOS REALIZADOS: 3"));
        assertTrue(relatorio.contains("TOTAL DE PRODUTOS VENDIDOS: 8"));
        assertTrue(relatorio.contains("TOTAL DE CATEGORIAS: 2"));
        assertTrue(relatorio.contains("MONTANTE DE VENDAS: R$ 55,00"));
        assertTrue(relatorio.contains("PEDIDO MAIS BARATO: R$ 25,00 (Produto C)"));
        assertTrue(relatorio.contains("PEDIDO MAIS CARO: R$ 20,00 (Produto B)"));
    }

    @Test
    void deveGerarRelatorioDeVendasComListaVazia() {
        List<Pedido> pedidos = List.of();

        String relatorio = geradorDeRelatoriosService.gerarRelatorioDeVendas(pedidos);

        assertTrue(relatorio.contains("TOTAL DE PEDIDOS REALIZADOS: 0"));
        assertTrue(relatorio.contains("TOTAL DE PRODUTOS VENDIDOS: 0"));
        assertTrue(relatorio.contains("TOTAL DE CATEGORIAS: 0"));
        assertTrue(relatorio.contains("MONTANTE DE VENDAS: R$ 0,00"));
    }

    @Test
    void deveGerarRelatorioDeVendasComPedidoNulo() {
        List<Pedido> pedidos = List.of((Pedido) null);

        String relatorio = geradorDeRelatoriosService.gerarRelatorioDeVendas(pedidos);

        assertTrue(relatorio.contains("TOTAL DE PEDIDOS REALIZADOS: 0"));
        assertTrue(relatorio.contains("TOTAL DE PRODUTOS VENDIDOS: 0"));
        assertTrue(relatorio.contains("TOTAL DE CATEGORIAS: 0"));
        assertTrue(relatorio.contains("MONTANTE DE VENDAS: R$ 0,00"));
    }
}