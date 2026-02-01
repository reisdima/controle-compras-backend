package vincenzo.caio.controle_compras_backend.api.controller;

import vincenzo.caio.controle_compras_backend.api.dto.request.CompraRequestDTO;
import vincenzo.caio.controle_compras_backend.api.dto.response.CompraResponseDTO;
import vincenzo.caio.controle_compras_backend.model.Compra;
import vincenzo.caio.controle_compras_backend.service.CompraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/compras")
public class CompraController {


    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<List<CompraResponseDTO>> obterLista() {
        List<Compra> lista = compraService.obterLista();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<CompraResponseDTO> response = lista.stream().map(CompraResponseDTO::new).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponseDTO> obterPorId(@PathVariable("id") UUID id) {
        CompraResponseDTO compra = new CompraResponseDTO(compraService.obterPorId(id));
        return ResponseEntity.ok(compra);
    }

    @GetMapping("/notaFiscal/{notaFiscal}")
    public ResponseEntity<CompraResponseDTO> obterPorNotaFiscal(@PathVariable("notaFiscal") String notaFiscal) {
        CompraResponseDTO compra = new CompraResponseDTO(compraService.obterPorNotaFiscal(notaFiscal));
        return ResponseEntity.ok(compra);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") UUID id) {
        compraService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CompraResponseDTO> cadastrarCompra(@Valid  @RequestBody CompraRequestDTO compra) {
        Compra novaCompra = compraService.incluir(compra);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaCompra != null ? new CompraResponseDTO(novaCompra) : null);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompraResponseDTO> alterar(@PathVariable("id") UUID id, @RequestBody CompraRequestDTO compra) {
        Compra compraAlterada = compraService.alterar(id, compra);

        return ResponseEntity.ok(new CompraResponseDTO(compraAlterada));
    }

}
