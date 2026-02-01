package vincenzo.caio.controle_compras_backend.service;

import vincenzo.caio.controle_compras_backend.model.ItemDeCompra;
import vincenzo.caio.controle_compras_backend.model.Produto;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    public ItemDeCompra obterPorId(UUID id);

    public List<ItemDeCompra> obterItensPorProduto(Produto produto);


}
