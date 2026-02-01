package vincenzo.caio.controle_compras_backend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import feign.FeignException;
import jakarta.transaction.Transactional;
import vincenzo.caio.controle_compras_backend.api.dto.request.ProdutoRequestDTO;
import vincenzo.caio.controle_compras_backend.clients.OpenFoodClient;
import vincenzo.caio.controle_compras_backend.model.OpenFoodProductResponse;
import vincenzo.caio.controle_compras_backend.model.Produto;
import vincenzo.caio.controle_compras_backend.model.enums.TipoUnidade;
import vincenzo.caio.controle_compras_backend.model.exceptions.EntidadeInvalidaException;
import vincenzo.caio.controle_compras_backend.model.exceptions.EntidadeNaoEncontradaException;
import vincenzo.caio.controle_compras_backend.repository.ProdutoRepository;
import vincenzo.caio.controle_compras_backend.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final OpenFoodClient openFoodClient;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, OpenFoodClient openFoodClient) {
        this.produtoRepository = produtoRepository;
        this.openFoodClient = openFoodClient;
    }

    @Transactional
    public Produto obterProdutoPorCodigoDeBarras(String codigoDeBarras) {
        if (codigoDeBarras == null || codigoDeBarras.isEmpty()) {
            throw new IllegalArgumentException("Codigo de barras informado é inválido");
        }

        Produto produto = new Produto();
        System.out.println("Procurando no banco...");
        Optional<Produto> produtoDoBanco = this.produtoRepository.findByCodigoDeBarras(codigoDeBarras);
        if (produtoDoBanco.isPresent()) {
            System.out.println("Achou no banco");
            produto = produtoDoBanco.get();
        } else {
            System.out.println("Buscando no client...");
            try {

                OpenFoodProductResponse clientResponse = this.openFoodClient.obterProduto(codigoDeBarras);
                produto.setCodigoDeBarras(codigoDeBarras);
                produto.setMarca(clientResponse.getMarca());
                produto.setNome(clientResponse.getNome());
                produto.setUnidade(TipoUnidade.fromString(clientResponse.getUnidade()));
                produto.setQuantidade(clientResponse.getQuantidade());

                this.produtoRepository.save(produto);
            } catch (FeignException.NotFound ex) {
                throw new EntidadeNaoEncontradaException("Produto com codigo de barra " + codigoDeBarras + " não foi encontrado");
            }

        }

        return produto;

    }

    public Produto incluir(ProdutoRequestDTO dto) {
        validarProduto(dto);
        if (this.produtoRepository.findByCodigoDeBarras(dto.getCodigoDeBarras()).isPresent()) {
            throw new IllegalArgumentException("Este produto, de código de barras " + dto.getCodigoDeBarras() + " já existe");
        }

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setMarca(dto.getMarca());
        produto.setUnidade(TipoUnidade.fromString(dto.getUnidade()));
        produto.setCodigoDeBarras(dto.getCodigoDeBarras());
        produto.setQuantidade(dto.getQuantidade());

        return this.produtoRepository.save(produto);
    }

    public Produto alterar(UUID id, ProdutoRequestDTO dto) {
        obterPorId(id);
        validarProduto(dto);

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setMarca(dto.getMarca());
        produto.setUnidade(TipoUnidade.fromString(dto.getUnidade()));
        produto.setCodigoDeBarras(dto.getCodigoDeBarras());
        produto.setQuantidade(dto.getQuantidade());
        produto.setId(id);

        return this.produtoRepository.save(produto);
    }

    public void excluir(UUID id) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("O produto com ID " + id + " não foi encontrado!"));
        produtoRepository.delete(produto);
    }

    public Produto obterPorId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID para alteração é inválido!");
        }
        return produtoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("O produto com ID " + id + " não foi encontrado!"));
    }

    public List<Produto> obterLista() {
        List<Produto> compras = produtoRepository.findAll();
        return compras;
    }


    private void validarProduto(ProdutoRequestDTO produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode estar nulo!");
        }
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new EntidadeInvalidaException("O nome do produto não pode estar vazio!");
        }
    }
}
