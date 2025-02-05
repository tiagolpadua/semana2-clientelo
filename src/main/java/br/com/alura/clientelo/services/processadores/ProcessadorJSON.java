package br.com.alura.clientelo.services.processadores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.alura.clientelo.exceptions.ProcessadorException;
import br.com.alura.clientelo.interfaces.ProcessadorArquivo;
import br.com.alura.clientelo.models.Pedido;

public class ProcessadorJSON implements ProcessadorArquivo {
    @Override
    public List<Pedido> processar(Path caminhoDoArquivo) {
        try {
            var objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String json = Files.readString(caminhoDoArquivo);
            return objectMapper.readValue(json,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Pedido.class));
        } catch (IOException e) {
            throw new ProcessadorException("Erro ao processar o arquivo JSON", e);
        }
    }
}
