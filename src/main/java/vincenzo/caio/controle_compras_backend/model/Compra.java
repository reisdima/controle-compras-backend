package vincenzo.caio.controle_compras_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate dataDaCompra;

    @NotBlank(message = "A nota fiscal é obrigatória")
    private String notaFiscal;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemDeCompra> itensDeCompra = new ArrayList<>();

    private String estabelecimento;

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", dataDaCompra=" + dataDaCompra +
                ", produtos=" + itensDeCompra +
                ", estabelecimento='" + estabelecimento + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDataDaCompra() {
        return dataDaCompra;
    }

    public void setDataDaCompra(LocalDate dataDaCompra) {
        this.dataDaCompra = dataDaCompra;
    }

    public List<ItemDeCompra> getItensDeCompra() {
        return itensDeCompra;
    }

    public void setItensDeCompra(List<ItemDeCompra> itensDeCompra) {
        this.itensDeCompra = itensDeCompra;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public BigDecimal getValor() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemDeCompra itemDeCompra : itensDeCompra) {
            BigDecimal subtotal = itemDeCompra.getPreco()
                    .multiply(BigDecimal.valueOf(itemDeCompra.getQuantidade()));
            total = total.add(subtotal);
        }
        return total;
    }

}
