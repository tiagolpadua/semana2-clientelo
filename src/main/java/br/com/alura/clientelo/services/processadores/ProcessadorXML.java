package br.com.alura.clientelo.services.processadores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.alura.clientelo.exceptions.ProcessadorException;
import br.com.alura.clientelo.interfaces.ProcessadorArquivo;
import br.com.alura.clientelo.models.Pedido;

public class ProcessadorXML implements ProcessadorArquivo {
    @Override
    public List<Pedido> processar(Path caminhoDoArquivo) {
        try {
            String xml = Files.readString(caminhoDoArquivo);
            var xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            return xmlMapper.readValue(xml,
                    xmlMapper.getTypeFactory().constructCollectionType(List.class, Pedido.class));
        } catch (IOException e) {
            throw new ProcessadorException("Erro ao processar o arquivo JSON", e);
        }
    }
}
