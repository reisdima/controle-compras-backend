package vincenzo.caio.controle_compras_backend.api.dto.response;

import vincenzo.caio.controle_compras_backend.model.Compra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CompraResponseDTO {

    private UUID id;
    private LocalDate dataDaCompra;
    private List<ItemDeCompraResponseDTO> itens = new ArrayList<>();
    private String estabelecimento;
    private String notaFiscal;

    public CompraResponseDTO (Compra compra) {
        this.dataDaCompra = compra.getDataDaCompra();
        this.id = compra.getId();
        this.estabelecimento = compra.getEstabelecimento();
        this.notaFiscal = compra.getNotaFiscal();
        this.itens = compra.getItensDeCompra().stream()
                .map(ItemDeCompraResponseDTO::new).collect(Collectors.toList());
    }

    public LocalDate getDataDaCompra() {
        return dataDaCompra;
    }

    public void setDataDaCompra(LocalDate dataDaCompra) {
        this.dataDaCompra = dataDaCompra;
    }

    public List<ItemDeCompraResponseDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemDeCompraResponseDTO> itens) {
        this.itens = itens;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
