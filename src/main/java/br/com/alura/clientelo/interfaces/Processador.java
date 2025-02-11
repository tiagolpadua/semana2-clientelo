package br.com.alura.clientelo.interfaces;

import br.com.alura.clientelo.models.Pedido;

import java.nio.file.Path;
import java.util.List;

public interface Processador {
    List<Pedido> processar(Path filePath);
}
