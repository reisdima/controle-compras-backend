package vincenzo.caio.controle_compras_backend.api.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemDeCompraRequestDTO {

    private UUID id;
    private String codigoDeBarras;
    private BigDecimal preco;
    private Integer quantidade;


    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
