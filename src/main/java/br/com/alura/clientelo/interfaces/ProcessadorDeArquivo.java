package br.com.alura.clientelo.interfaces;

import br.com.alura.clientelo.models.Pedido;

import java.nio.file.Path;
import java.util.List;

public interface ProcessadorDeArquivo {
    List<Pedido> processar(Path filePath);
}
