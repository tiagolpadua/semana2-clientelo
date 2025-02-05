package br.com.alura.clientelo.services.processadores;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import br.com.alura.clientelo.exceptions.ProcessadorException;
import br.com.alura.clientelo.interfaces.ProcessadorArquivo;
import br.com.alura.clientelo.models.Pedido;

public class ProcessadorCSV implements ProcessadorArquivo {
    @Override
    public List<Pedido> processar(Path caminhoDoArquivo) {
        try {
            return readAllLines(caminhoDoArquivo).stream()
                    .skip(1)
                    .map(ProcessadorCSV::criarPedido)
                    .toList();
        } catch (NumberFormatException e) {
            throw new ProcessadorException("Erro ao processar o arquivo CSV", e);
        }
    }

    private static Pedido criarPedido(String[] registro) {
        var categoria = registro[0];
        var produto = registro[1];
        var preco = new BigDecimal(registro[2]);
        var quantidade = Integer.parseInt(registro[3]);
        var data = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var cliente = registro[5];

        return new Pedido(produto, categoria, cliente, preco, quantidade, data);
    }

    private List<String[]> readAllLines(Path filePath) {
        try (var reader = Files.newBufferedReader(filePath);
                var csvReader = new CSVReader(reader)) {
            return csvReader.readAll();
        } catch (IOException | CsvException e) {
            throw new ProcessadorException("Erro ao processar o arquivo CSV", e);
        }
    }
}
