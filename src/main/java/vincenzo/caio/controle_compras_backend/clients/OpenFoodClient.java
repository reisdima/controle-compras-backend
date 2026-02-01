package vincenzo.caio.controle_compras_backend.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vincenzo.caio.controle_compras_backend.model.OpenFoodProductResponse;

@FeignClient(name = "openfood-products", url = "${api.openfood.url}")
public interface OpenFoodClient {

    @GetMapping("/product/{codigoDeBarras}.json")
    OpenFoodProductResponse obterProduto(@PathVariable("codigoDeBarras") String codigoDeBarras);
}
