package br.com.alura.clientelo.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import br.com.alura.clientelo.models.Pedido;

public class GeradorDeRelatoriosService {

    public String gerarRelatorioDeVendas(List<Pedido> pedidos) {
        var totalDeProdutosVendidos = 0;
        var totalDePedidosRealizados = 0;
        var montanteDeVendas = BigDecimal.ZERO;
        Pedido pedidoMaisBarato = null;
        Pedido pedidoMaisCaro = null;

        Set<String> categoriasProcessadas = new HashSet<>();

        for (var pedidoAtual : pedidos) {
            if (pedidoAtual == null) {
                break;
            }

            var valorPedidoAtual = pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade()));

            if (pedidoMaisBarato == null || valorPedidoAtual.compareTo(pedidoMaisBarato.getPreco()
                    .multiply(new BigDecimal(pedidoMaisBarato.getQuantidade()))) < 0) {
                pedidoMaisBarato = pedidoAtual;
            }

            if (pedidoMaisCaro == null || valorPedidoAtual.compareTo(pedidoMaisCaro.getPreco()
                    .multiply(new BigDecimal(pedidoMaisCaro.getQuantidade()))) > 0) {
                pedidoMaisCaro = pedidoAtual;
            }

            montanteDeVendas = montanteDeVendas.add(valorPedidoAtual);
            totalDeProdutosVendidos += pedidoAtual.getQuantidade();
            totalDePedidosRealizados++;

            categoriasProcessadas.add(pedidoAtual.getCategoria());
        }

        return gerarRelatorio(totalDeProdutosVendidos, totalDePedidosRealizados, montanteDeVendas, pedidoMaisBarato,
                pedidoMaisCaro,
                categoriasProcessadas);
    }

    private String gerarRelatorio(int totalDeProdutosVendidos, int totalDePedidosRealizados,
            BigDecimal montanteDeVendas, Pedido pedidoMaisBarato, Pedido pedidoMaisCaro,
            Set<String> categoriasProcessadas) {

        var buffer = new StringBuilder();
        buffer.append(String.format("%n##### RELATÓRIO DE VALORES TOTAIS #####%n"));
        buffer.append(String.format("TOTAL DE PEDIDOS REALIZADOS: %d%n", totalDePedidosRealizados));
        buffer.append(String.format("TOTAL DE PRODUTOS VENDIDOS: %d%n" + //
                "", totalDeProdutosVendidos));
        buffer.append(String.format("TOTAL DE CATEGORIAS: %d%n", categoriasProcessadas.size()));

        buffer.append(String.format("MONTANTE DE VENDAS: %s%n",
                formatarValor(montanteDeVendas.setScale(2, RoundingMode.HALF_DOWN))));

        buffer.append(String.format("PEDIDO MAIS BARATO: %s (%s)%n",
                formatarValor(
                        pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade()))
                                .setScale(2, RoundingMode.HALF_DOWN)),
                pedidoMaisBarato.getProduto()));
        buffer.append(String.format("PEDIDO MAIS CARO: %s (%s)%n",
                formatarValor(
                        pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade()))),
                pedidoMaisCaro.getProduto()));

        buffer.append(String.format("### FIM DO RELATÓRIO ###%n"));

        return buffer.toString();
    }

    private String formatarValor(BigDecimal valor) {
        if (valor == null) {
            return "";
        }

        var formatadorDeNumeros = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatadorDeNumeros.format(valor);
    }
}
