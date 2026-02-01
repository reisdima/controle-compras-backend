package vincenzo.caio.controle_compras_backend.api.dto.response;

import java.util.UUID;

import vincenzo.caio.controle_compras_backend.model.Produto;

public class ProdutoResponseDTO {

    private UUID id;
    private String marca;
    private String codigoDeBarras;
    private Integer quantidade;
    private String unidade;
    private String nome;

    public ProdutoResponseDTO (Produto produto) {
        this.marca = produto.getMarca();
        this.nome = produto.getNome();
        this.codigoDeBarras = produto.getCodigoDeBarras();
        this.unidade = produto.getUnidade().toString();
        this.quantidade = produto.getQuantidade();
        this.id = produto.getId();
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
