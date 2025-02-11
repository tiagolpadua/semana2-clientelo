package br.com.alura.clientelo.services;

import br.com.alura.clientelo.interfaces.Processador;
import br.com.alura.clientelo.models.Pedido;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

public class ProcessadorDeArquivoService {
    private Processador processador;

    public List<Pedido> processaArquivo(String nomeDoArquivo) {
        try {
            URL recurso = ClassLoader.getSystemResource(nomeDoArquivo);
            Path caminhoDoArquivo = Path.of(recurso.toURI());
            return processador.processar(caminhoDoArquivo);
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Arquivo %s não localizado!", nomeDoArquivo));
        }
    }

    public Processador getProcessador() {
        return processador;
    }

    public void setProcessador(Processador processador) {
        this.processador = processador;
    }
}
