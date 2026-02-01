package vincenzo.caio.controle_compras_backend.service.impl;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import vincenzo.caio.controle_compras_backend.model.ItemDeCompra;
import vincenzo.caio.controle_compras_backend.model.Produto;
import vincenzo.caio.controle_compras_backend.model.exceptions.EntidadeNaoEncontradaException;
import vincenzo.caio.controle_compras_backend.repository.ItemRepository;
import vincenzo.caio.controle_compras_backend.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
    private final vincenzo.caio.controle_compras_backend.repository.ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public ItemDeCompra obterPorId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID para alteração é inválido!");
        }
        return itemRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("O item com ID " + id + " não foi encontrado!"));
    }

    public List<ItemDeCompra> obterItensPorProduto(Produto produto) {
        return this.itemRepository.findByProduto(produto).get();
    }

}
