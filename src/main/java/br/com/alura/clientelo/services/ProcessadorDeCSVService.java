package br.com.alura.clientelo.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.alura.clientelo.exceptions.CSVProcessingException;
import br.com.alura.clientelo.models.Pedido;

public class ProcessadorDeCSVService {

    public List<Pedido> processaArquivo(String nomeDoArquivo) {
        try {
            var recursoCSV = ClassLoader.getSystemResource(nomeDoArquivo);
            var caminhoDoArquivo = Path.of(recursoCSV.toURI());
            return lerPedidos(caminhoDoArquivo);
        } catch (URISyntaxException e) {
            throw new CSVProcessingException(String.format("Arquivo %s não localizado!", nomeDoArquivo));
        } catch (IOException e) {
            throw new CSVProcessingException("Erro ao abrir Scanner para processar arquivo!");
        }
    }

    private List<Pedido> lerPedidos(Path caminhoDoArquivo) throws IOException {
        try {
            var linhas = Files.readAllLines(caminhoDoArquivo);
            return linhas.stream()
                    .skip(1)
                    .map(ProcessadorDeCSVService::criarPedido)
                    .toList();
        } catch (NumberFormatException e) {
            throw new CSVProcessingException("Erro ao processar o arquivo CSV", e);
        }
    }

    private static Pedido criarPedido(String linha) {
        var registro = linha.split(",");

        var categoria = registro[0];
        var produto = registro[1];
        var preco = new BigDecimal(registro[2]);
        var quantidade = Integer.parseInt(registro[3]);
        var data = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var cliente = registro[5];

        return new Pedido(categoria, produto, cliente, preco, quantidade, data);
    }
}
