package br.com.alura.clientelo.services.processadores;

import br.com.alura.clientelo.interfaces.ProcessadorDeArquivo;
import br.com.alura.clientelo.models.Pedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProcessadorDeJSON implements ProcessadorDeArquivo {
    public List<Pedido> processar(Path caminhoDoArquivo) {
        try {
            var objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String json = Files.readString(caminhoDoArquivo);
            return objectMapper.readValue(json,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Pedido.class));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar o arquivo JSON", e);
        }
    }
}
