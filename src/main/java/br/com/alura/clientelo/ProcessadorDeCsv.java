package br.com.alura.clientelo;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ProcessadorDeCsv {

    public List<Pedido> processaArquivo(String nomeDoArquivo) {
        try {
            URL recursoCSV = ClassLoader.getSystemResource(nomeDoArquivo);
            Path caminhoDoArquivo = Path.of(recursoCSV.toURI());

            var registros = readAllLines(caminhoDoArquivo);

            return registros.stream()
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
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Arquivo {} não localizado!", nomeDoArquivo));
        }
    }

    private List<String[]> readAllLines(Path filePath) {
        try (Reader reader = Files.newBufferedReader(filePath);
             CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
