package vincenzo.caio.controle_compras_backend.api.dto.response;

import vincenzo.caio.controle_compras_backend.model.ItemDeCompra;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemDeCompraResponseDTO {

    private UUID id;
    private ProdutoResponseDTO produto;
    private BigDecimal preco;
    private Integer quantidade;
    private String notaFiscal;

    public ItemDeCompraResponseDTO(ItemDeCompra itemDeCompra) {
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO(itemDeCompra.getProduto());
        this.produto = produtoResponseDTO;
        this.preco = itemDeCompra.getPreco();
        this.quantidade = itemDeCompra.getQuantidade();
        this.notaFiscal = itemDeCompra.getCompra().getNotaFiscal();
        this.id = itemDeCompra.getId();
    }



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

    public ProdutoResponseDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoResponseDTO produto) {
        this.produto = produto;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
