
package vincenzo.caio.controle_compras_backend.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProdutoAnalyticsService {

    public BigDecimal calcularMediaDeProduto(UUID produtoId);


}
