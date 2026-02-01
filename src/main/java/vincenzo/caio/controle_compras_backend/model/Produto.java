package vincenzo.caio.controle_compras_backend.model;

import vincenzo.caio.controle_compras_backend.model.enums.TipoUnidade;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    private String marca;

    @Pattern( regexp = "^\\d{13}$", message = "O código de barras deve conter exatamente 13 dígitos")
    private String codigoDeBarras;

    @NotNull(message = "A quantidade relacionada ao produto é obrigatória ")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private Integer quantidade;

    @NotNull(message = "A unidade do produto é obrigatória")
    @Enumerated(EnumType.STRING)
    private TipoUnidade unidade;

    @OneToMany(mappedBy = "produto")
    private List<ItemDeCompra> itens = new ArrayList<>();


    @Override
    public String toString() {
        return String.format(" - %d, %s, %s, %s, %s, %s ", id, nome, marca, codigoDeBarras, quantidade, unidade);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public @NotNull(message = "A unidade do produto é obrigatória") TipoUnidade getUnidade() {
        return unidade;
    }

    public void setUnidade(@NotNull(message = "A unidade do produto é obrigatória") TipoUnidade unidade) {
        this.unidade = unidade;
    }

    public List<ItemDeCompra> getItens() {
        return itens;
    }

    public void setItens(List<ItemDeCompra> itens) {
        this.itens = itens;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
