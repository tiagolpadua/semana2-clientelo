package br.com.alura.clientelo.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

    private String categoria;
    private String produto;
    private String cliente;

    private BigDecimal preco;
    private int quantidade;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    public Pedido() {
    }

    public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.categoria = categoria;
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", produto='" + produto + '\'' +
                ", cliente='" + cliente + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

}
