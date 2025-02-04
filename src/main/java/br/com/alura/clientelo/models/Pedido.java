package br.com.alura.clientelo.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Pedido {

    private final String categoria;
    private final String produto;
    private final String cliente;
    private final BigDecimal preco;
    private final int quantidade;
    private final LocalDate data;

    public Pedido(String produto, String categoria, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.produto = Objects.requireNonNull(produto, "Produto não pode ser nulo");
        this.categoria = Objects.requireNonNull(categoria, "Categoria não pode ser nula");
        this.cliente = Objects.requireNonNull(cliente, "Cliente não pode ser nulo");
        this.preco = Objects.requireNonNull(preco, "Preço não pode ser nulo");
        this.quantidade = quantidade;
        this.data = Objects.requireNonNull(data, "Data não pode ser nula");
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pedido pedido = (Pedido) o;
        return quantidade == pedido.quantidade &&
                Objects.equals(categoria, pedido.categoria) &&
                Objects.equals(produto, pedido.produto) &&
                Objects.equals(cliente, pedido.cliente) &&
                Objects.equals(preco, pedido.preco) &&
                Objects.equals(data, pedido.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoria, produto, cliente, preco, quantidade, data);
    }
}
