package br.com.alura.clientelo;

import br.com.alura.clientelo.models.Pedido;
import br.com.alura.clientelo.services.ProcessadorDeArquivoService;
import br.com.alura.clientelo.services.RelatorioService;
import br.com.alura.clientelo.services.processadores.ProcessadorDeCSV;
import br.com.alura.clientelo.services.processadores.ProcessadorDeJSON;
import br.com.alura.clientelo.services.processadores.ProcessadorDeXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ProcessadorDeArquivoService processador = new ProcessadorDeArquivoService();

        var  nomeDoArquivo = "pedidos.json";

        String extensao = nomeDoArquivo.substring(nomeDoArquivo.lastIndexOf('.') + 1);

        switch (extensao) {
            case "csv" -> {
                processador.setProcessador(new ProcessadorDeCSV());
            }
            case "json" -> {
                processador.setProcessador(new ProcessadorDeJSON());
            }
            case "xml" -> {
                processador.setProcessador(new ProcessadorDeXML());
            }
            default -> {
                throw new RuntimeException("Formato de arquivo não suportado: " + extensao);
            }
        }

        List<Pedido> pedidos = processador.processaArquivo(nomeDoArquivo);

        RelatorioService relatorioService = new RelatorioService();
        relatorioService.gerar(pedidos);
    }
}
