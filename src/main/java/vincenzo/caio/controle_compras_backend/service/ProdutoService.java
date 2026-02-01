package vincenzo.caio.controle_compras_backend.service;

import java.util.List;
import java.util.UUID;

import vincenzo.caio.controle_compras_backend.api.dto.request.ProdutoRequestDTO;
import vincenzo.caio.controle_compras_backend.model.Produto;

public interface ProdutoService {

    public Produto obterProdutoPorCodigoDeBarras(String codigoDeBarras);
    public Produto incluir(ProdutoRequestDTO dto);
    public Produto alterar(UUID id, ProdutoRequestDTO dto);
    public void excluir(UUID id);
    public Produto obterPorId(UUID id);
    public List<Produto> obterLista();
}
