package vincenzo.caio.controle_compras_backend.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vincenzo.caio.controle_compras_backend.api.dto.request.ProdutoRequestDTO;
import vincenzo.caio.controle_compras_backend.api.dto.response.ProdutoResponseDTO;
import vincenzo.caio.controle_compras_backend.model.Produto;
import vincenzo.caio.controle_compras_backend.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> obterLista() {
        List<ProdutoResponseDTO> lista = produtoService.obterLista().stream().map(ProdutoResponseDTO::new).toList();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> obterPorId(@PathVariable("id") UUID id) {
        ProdutoResponseDTO produto = new ProdutoResponseDTO(produtoService.obterPorId(id));
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/codigo/{codigoDeBarras}")
    public ResponseEntity<ProdutoResponseDTO> obterPorCodigoDeBarras(@PathVariable("codigoDeBarras") String codigoDeBarras) {
        Produto produto = this.produtoService.obterProdutoPorCodigoDeBarras(codigoDeBarras);
        if (produto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(new ProdutoResponseDTO(produto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") UUID id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> incluir(@Valid @RequestBody ProdutoRequestDTO produto) {
        Produto novoProduto = produtoService.incluir(produto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProdutoResponseDTO(novoProduto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponseDTO> alterar(@PathVariable("id") UUID id, @RequestBody ProdutoRequestDTO produto) {
        Produto produtoAlterado = produtoService.alterar(id, produto);

        return ResponseEntity.ok(new ProdutoResponseDTO(produtoAlterado));
    }

}
