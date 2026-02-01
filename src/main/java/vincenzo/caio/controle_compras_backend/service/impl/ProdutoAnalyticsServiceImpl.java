package vincenzo.caio.controle_compras_backend.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import vincenzo.caio.controle_compras_backend.model.ItemDeCompra;
import vincenzo.caio.controle_compras_backend.model.Produto;
import vincenzo.caio.controle_compras_backend.service.ProdutoAnalyticsService;
import vincenzo.caio.controle_compras_backend.service.ProdutoService;

@Service
public class ProdutoAnalyticsServiceImpl implements ProdutoAnalyticsService {
    private final ProdutoService produtoService;
    private final ItemServiceImpl itemService;

    public ProdutoAnalyticsServiceImpl(ProdutoService produtoService, ItemServiceImpl itemService) {
        this.itemService = itemService;
        this.produtoService = produtoService;
    }

    public BigDecimal calcularMediaDeProduto(UUID produtoId) {
        Produto produto = produtoService.obterPorId(produtoId);
        List<ItemDeCompra> itens = itemService.obterItensPorProduto(produto);
        if(itens == null || itens.size() == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal soma = BigDecimal.ZERO;
        for(ItemDeCompra item : itens) {
            soma = soma.add(item.getPreco());
        }
        return soma.divide(new BigDecimal(itens.size()));
    }


}
