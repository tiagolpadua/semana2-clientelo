package br.com.alura.clientelo.services.processadores;

import br.com.alura.clientelo.interfaces.Processador;
import br.com.alura.clientelo.models.Pedido;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

public class ProcessadorXML implements Processador {
    public List<Pedido> processar(Path caminhoDoArquivo) {
        try {
            String xml = Files.readString(caminhoDoArquivo);
            var xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            return xmlMapper.readValue(xml,
                    xmlMapper.getTypeFactory().constructCollectionType(List.class, Pedido.class));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar o arquivo XML", e);
        }
    }
}
