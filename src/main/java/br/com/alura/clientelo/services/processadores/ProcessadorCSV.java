package br.com.alura.clientelo.services.processadores;

import br.com.alura.clientelo.interfaces.Processador;
import br.com.alura.clientelo.models.Pedido;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProcessadorCSV implements Processador {
    public List<Pedido> processar(Path filePath) {
        try (Reader reader = Files.newBufferedReader(filePath);
             CSVReader csvReader = new CSVReader(reader)) {
            return csvReader.readAll().stream()
                    .skip(1)
                    .map(registro -> {
                        String categoria = registro[0];
                        String produto = registro[1];
                        BigDecimal preco = new BigDecimal(registro[2]);
                        int quantidade = Integer.parseInt(registro[3]);
                        LocalDate data = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        String cliente = registro[5];
                        return new Pedido(categoria, produto, cliente, preco, quantidade, data);
                    }).toList();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
