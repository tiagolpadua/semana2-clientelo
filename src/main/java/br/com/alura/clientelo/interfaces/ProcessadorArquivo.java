package br.com.alura.clientelo.interfaces;

import java.nio.file.Path;
import java.util.List;

import br.com.alura.clientelo.models.Pedido;

public interface ProcessadorArquivo {
    List<Pedido> processar(Path caminhoDoArquivo);
}
