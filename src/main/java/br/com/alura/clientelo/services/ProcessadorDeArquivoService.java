package br.com.alura.clientelo.services;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

import br.com.alura.clientelo.exceptions.ProcessadorException;
import br.com.alura.clientelo.interfaces.ProcessadorArquivo;
import br.com.alura.clientelo.models.Pedido;
import br.com.alura.clientelo.services.processadores.ProcessadorCSV;
import br.com.alura.clientelo.services.processadores.ProcessadorJSON;
import br.com.alura.clientelo.services.processadores.ProcessadorXML;

public class ProcessadorDeArquivoService {

    private ProcessadorArquivo processador;

    public void setProcessor(ProcessadorArquivo processador) {
        this.processador = processador;
    }

    public List<Pedido> processarArquivo(String nomeDoArquivo) {
        var recurso = ClassLoader.getSystemResource(nomeDoArquivo);

        if (recurso == null) {
            throw new ProcessadorException(String.format("Arquivo %s não localizado!", nomeDoArquivo));
        }

        String extensao = nomeDoArquivo.substring(nomeDoArquivo.lastIndexOf('.') + 1);

        switch (extensao) {
            case "csv":
                setProcessor(new ProcessadorCSV());
                break;
            case "json":
                setProcessor(new ProcessadorJSON());
                break;
            case "xml":
                setProcessor(new ProcessadorXML());
                break;
            default:
                throw new ProcessadorException(String.format("Extensão de arquivo %s não suportada!", extensao));
        }

        Path caminhoDoArquivo;
        try {
            caminhoDoArquivo = Path.of(recurso.toURI());
        } catch (URISyntaxException e) {
            throw new ProcessadorException(e.getMessage());
        }
        return processador.processar(caminhoDoArquivo);
    }
}
