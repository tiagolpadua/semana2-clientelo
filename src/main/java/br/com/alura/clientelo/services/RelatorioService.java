package br.com.alura.clientelo.services;

import br.com.alura.clientelo.Main;
import br.com.alura.clientelo.models.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class RelatorioService {

    private static final Logger logger = LoggerFactory.getLogger(RelatorioService.class);

    public void gerar(List<Pedido> pedidos) {
        int totalDeProdutosVendidos = 0;
        int totalDePedidosRealizados = 0;
        BigDecimal montanteDeVendas = BigDecimal.ZERO;
        Pedido pedidoMaisBarato = null;
        Pedido pedidoMaisCaro = null;

        Set<String> categoriasProcessadas = new HashSet<>();

        for (Pedido pedidoAtual : pedidos) {
            if (pedidoAtual == null) {
                break;
            }

            BigDecimal valorPedidoAtual = calculaValorPedido(pedidoAtual);

            if (pedidoMaisBarato == null || valorPedidoAtual.compareTo(calculaValorPedido(pedidoMaisBarato)) < 0) {
                pedidoMaisBarato = pedidoAtual;
            }

            if (pedidoMaisCaro == null || valorPedidoAtual.compareTo(calculaValorPedido(pedidoMaisCaro)) > 0) {
                pedidoMaisCaro = pedidoAtual;
            }

            montanteDeVendas = montanteDeVendas.add(valorPedidoAtual);
            totalDeProdutosVendidos += pedidoAtual.getQuantidade();
            totalDePedidosRealizados++;

            categoriasProcessadas.add(pedidoAtual.getCategoria().toUpperCase());
        }

        logger.info("##### RELATÓRIO DE VALORES TOTAIS #####");

        logger.info("TOTAL DE PEDIDOS REALIZADOS: {}", totalDePedidosRealizados);
        logger.info("TOTAL DE PRODUTOS VENDIDOS: {}", totalDeProdutosVendidos);
        logger.info("TOTAL DE CATEGORIAS: {}", categoriasProcessadas.size());

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        logger.info("MONTANTE DE VENDAS: {}", currencyInstance.format(montanteDeVendas.setScale(2, RoundingMode.HALF_DOWN)));

        if (pedidoMaisBarato != null) {
            logger.info("PEDIDO MAIS BARATO: {} ({})", currencyInstance.format(pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMaisBarato.getProduto());
        }

        if (pedidoMaisCaro != null) {
            logger.info("PEDIDO MAIS CARO: {} ({})\n", currencyInstance.format(pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMaisCaro.getProduto());
        }

        logger.info("### FIM DO RELATÓRIO ###");
    }

    private BigDecimal calculaValorPedido(Pedido pedidoAtual) {
        return pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade()));
    }
}
