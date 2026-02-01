package vincenzo.caio.controle_compras_backend.service;

import java.util.List;
import java.util.UUID;

import vincenzo.caio.controle_compras_backend.api.dto.request.CompraRequestDTO;
import vincenzo.caio.controle_compras_backend.model.Compra;

public interface CompraService {

    public Compra incluir(CompraRequestDTO dto); 
    public Compra alterar(UUID id, CompraRequestDTO dto);
    public Compra obterPorId(UUID id);
    public void excluir(UUID id);
    public List<Compra> obterLista();
    public Compra obterPorNotaFiscal(String notaFiscal);
}
