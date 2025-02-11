package br.com.alura.clientelo;

import br.com.alura.clientelo.models.Pedido;
import br.com.alura.clientelo.services.ProcessadorDeArquivoService;
import br.com.alura.clientelo.services.RelatorioService;
import br.com.alura.clientelo.services.processadores.ProcessadorCSV;
import br.com.alura.clientelo.services.processadores.ProcessadorJSON;
import br.com.alura.clientelo.services.processadores.ProcessadorXML;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        var processadorDeArquivoService = new ProcessadorDeArquivoService();
        String nomeDoArquivo = "pedidos.json";

        String extensao = nomeDoArquivo.substring(nomeDoArquivo.lastIndexOf('.') + 1);

        switch (extensao) {
            case "csv" -> {
                processadorDeArquivoService.setProcessador(new ProcessadorCSV());
            }
            case "json" -> {
                processadorDeArquivoService.setProcessador(new ProcessadorJSON());
            }
            case "xml" -> {
                processadorDeArquivoService.setProcessador(new ProcessadorXML());
            }
            default -> {
                throw new RuntimeException("Formato de arquivo não suportado: " + extensao);
            }
        }

        List<Pedido> pedidos = processadorDeArquivoService.processaArquivo(nomeDoArquivo);

        var relatorioService = new RelatorioService();
        relatorioService.gerar(pedidos);
    }
}
