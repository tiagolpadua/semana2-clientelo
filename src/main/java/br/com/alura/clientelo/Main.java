package br.com.alura.clientelo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.alura.clientelo.services.GeradorDeRelatoriosService;
import br.com.alura.clientelo.services.ProcessadorDeCSVService;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var processadorDeCSVService = new ProcessadorDeCSVService();
        var pedidos = processadorDeCSVService.processaArquivo("pedidos.csv");

        var gerador = new GeradorDeRelatoriosService();
        var relatorio = gerador.gerarRelatorioDeVendas(pedidos);
        logger.info(relatorio);
    }

}
