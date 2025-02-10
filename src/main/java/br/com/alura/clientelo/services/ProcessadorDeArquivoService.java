package br.com.alura.clientelo.services;

import br.com.alura.clientelo.interfaces.ProcessadorDeArquivo;
import br.com.alura.clientelo.models.Pedido;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

public class ProcessadorDeArquivoService {
    public ProcessadorDeArquivo processador;

    public List<Pedido> processaArquivo(String nomeDoArquivo) {
        try {
            URL recurso = ClassLoader.getSystemResource(nomeDoArquivo);
            Path caminhoDoArquivo = Path.of(recurso.toURI());

            return processador.processar(caminhoDoArquivo);
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Arquivo {} não localizado!", nomeDoArquivo));
        }
    }

    public ProcessadorDeArquivo getProcessador() {
        return processador;
    }

    public void setProcessador(ProcessadorDeArquivo processador) {
        this.processador = processador;
    }
}
