package br.com.alura.clientelo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.services.GeradorDeRelatoriosService;
import br.com.alura.clientelo.services.ProcessadorDeArquivoService;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var processadorDeArquivoService = new ProcessadorDeArquivoService();
        var pedidos = processadorDeArquivoService.processarArquivo("pedidos.xml");

        var gerador = new GeradorDeRelatoriosService();
        var relatorio = gerador.gerarRelatorioDeVendas(pedidos);
        logger.info(relatorio);
    }

}
