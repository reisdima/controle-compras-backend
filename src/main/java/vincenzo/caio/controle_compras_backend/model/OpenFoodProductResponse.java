package vincenzo.caio.controle_compras_backend.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import vincenzo.caio.controle_compras_backend.model.deserializer.OpenFoodProductResponseDesrializer;

@JsonDeserialize(using = OpenFoodProductResponseDesrializer.class)
public class OpenFoodProductResponse {

    private String nome;
    private Integer quantidade;
    private String unidade;
    private String marca;
    private String codigoDeBarras;

    @Override
    public String toString() {
        return "ProdutoResponse{" +
                "nome='" + nome + '\'' +
                ", quantidade='" + quantidade + '\'' +
                ", unidade='" + unidade + '\'' +
                ", marca='" + marca + '\'' +
                ", codigoDeBarras='" + codigoDeBarras + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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


}
