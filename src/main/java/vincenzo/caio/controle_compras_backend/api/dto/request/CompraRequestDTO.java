package vincenzo.caio.controle_compras_backend.api.dto.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompraRequestDTO {

    private LocalDate dataDaCompra;
    private List<ItemDeCompraRequestDTO> itens = new ArrayList<>();
    private String estabelecimento;
    private String notaFiscal;

    public LocalDate getDataDaCompra() {
        return dataDaCompra;
    }

    public void setDataDaCompra(LocalDate dataDaCompra) {
        this.dataDaCompra = dataDaCompra;
    }

    public List<ItemDeCompraRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemDeCompraRequestDTO> itens) {
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
}
