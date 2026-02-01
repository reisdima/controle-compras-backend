package vincenzo.caio.controle_compras_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class ItemDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    @Valid
    @NotNull(message = "O produto do item é obrigatório")
    private Produto produto;

    @NotNull(message = "O preço do item é obrigatório.")
    @DecimalMin(value = "0.01", message = "O preço do item deve ser maior que zero.")
    @Digits(integer = 10, fraction = 2, message = "O preço do item deve ter no máximo 10 digitos inteiros e 2 decimais.")
    private BigDecimal preco;

    private int quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id", nullable = false)
    @JsonBackReference
    private Compra compra;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", produto=" + produto +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}
