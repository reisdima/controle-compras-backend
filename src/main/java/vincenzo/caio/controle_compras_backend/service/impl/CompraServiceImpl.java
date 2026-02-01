package vincenzo.caio.controle_compras_backend.service.impl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import vincenzo.caio.controle_compras_backend.api.dto.request.CompraRequestDTO;
import vincenzo.caio.controle_compras_backend.api.dto.request.ItemDeCompraRequestDTO;
import vincenzo.caio.controle_compras_backend.model.Compra;
import vincenzo.caio.controle_compras_backend.model.ItemDeCompra;
import vincenzo.caio.controle_compras_backend.model.Produto;
import vincenzo.caio.controle_compras_backend.model.exceptions.EntidadeNaoEncontradaException;
import vincenzo.caio.controle_compras_backend.repository.CompraRepository;
import vincenzo.caio.controle_compras_backend.service.CompraService;
import vincenzo.caio.controle_compras_backend.service.ProdutoService;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;
    private final ProdutoService produtoService;
    private final ItemServiceImpl itemService;

    public CompraServiceImpl(CompraRepository compraRepository, ProdutoService produtoService,
                         ItemServiceImpl itemService) {
        this.compraRepository = compraRepository;
        this.produtoService = produtoService;
        this.itemService = itemService;
    }


    @Transactional
    public Compra incluir(CompraRequestDTO dto) {
        validarCompra(dto);
        Compra compra = new Compra();
        compra.setDataDaCompra(dto.getDataDaCompra());
        compra.setEstabelecimento(dto.getEstabelecimento());
        compra.setNotaFiscal(dto.getNotaFiscal());

        List<ItemDeCompra> listaDeItens = new ArrayList<>();

        for (ItemDeCompraRequestDTO item : dto.getItens()) {
            Produto produto = this.produtoService.obterProdutoPorCodigoDeBarras(item.getCodigoDeBarras());
            if (produto == null) {
                throw new EntidadeNaoEncontradaException("Produto com codigo de barras " + item.getCodigoDeBarras() + " não encontrado.");
            }
            var itemDeCompra = new ItemDeCompra();
//            itemDeCompra.setProduto(produto.get());
            itemDeCompra.setProduto(produto);
            itemDeCompra.setCompra(compra);
            itemDeCompra.setPreco(item.getPreco());
            itemDeCompra.setQuantidade(item.getQuantidade());

            listaDeItens.add(itemDeCompra);
        }
        compra.setItensDeCompra(listaDeItens);

        compra = compraRepository.save(compra);

        return compra;

    }


    @Transactional
    public Compra alterar(UUID id, CompraRequestDTO dto) {

        validarCompra(dto);

        var compraEncontrada = obterPorId(id);

        if (!compraEncontrada.getNotaFiscal().equals(dto.getNotaFiscal())) {
            this.compraRepository.findByNotaFiscal(dto.getNotaFiscal())
                    .ifPresent(v -> {
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma compra com essa nota fiscal");
                    });
            compraEncontrada.setNotaFiscal(dto.getNotaFiscal());
        }

        // Atualiza itens da compra
        if (dto.getItens() != null) {
            List<ItemDeCompra> itensAtualizados = new ArrayList<>();
            for (ItemDeCompraRequestDTO itemDto : dto.getItens()) {
                ItemDeCompra itemDeCompra;
                if (itemDto.getId() == null) {
                    itemDeCompra = new ItemDeCompra();
                    itemDeCompra.setCompra(compraEncontrada);
                    itemDeCompra.setProduto(this.produtoService
                            .obterProdutoPorCodigoDeBarras(itemDto.getCodigoDeBarras()));
                } else {
                    itemDeCompra = this.itemService.obterPorId(itemDto.getId());
                }
                itemDeCompra.setPreco(itemDto.getPreco());
                itemDeCompra.setQuantidade(itemDto.getQuantidade());
                itensAtualizados.add(itemDeCompra);
            }
            compraEncontrada.getItensDeCompra().clear();
            compraEncontrada.getItensDeCompra().addAll(itensAtualizados);
        }
        compraEncontrada.setDataDaCompra(dto.getDataDaCompra());
        compraEncontrada.setEstabelecimento(dto.getEstabelecimento());

        return compraRepository.save(compraEncontrada);
    }


    public Compra obterPorId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID informado é inválido!");
        }
        return compraRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("A compra com ID " + id + " não foi encontrada!"));
    }


    @Transactional
    public void excluir(UUID id) {
        Compra compra = obterPorId(id);
        compraRepository.delete(compra);
    }


    public List<Compra> obterLista() {
        List<Compra> compras = compraRepository.findAll();
        return compras;
    }

    @Transactional
    public Compra obterPorNotaFiscal(String notaFiscal) {
        if (notaFiscal == null || notaFiscal.isEmpty()) {
            throw new IllegalArgumentException("Nota Fiscal informada é inválida");
        }
        return compraRepository.findByNotaFiscal(notaFiscal).orElseThrow(() -> new EntidadeNaoEncontradaException("A compra de nota fiscal " + notaFiscal + " não foi encontrado!"));
    }

    private void validarCompra(CompraRequestDTO compra) {
        if (compra == null) {
            throw new IllegalArgumentException("O compra não pode estar nulo!");
        }
        if (compra.getItens() == null || compra.getItens().isEmpty()) {
            throw new IllegalArgumentException("A compra não pode ter lista de produtos vazia!");
        }
        if (compra.getDataDaCompra() == null) {
            compra.setDataDaCompra(LocalDate.now());
        }
    }
}
