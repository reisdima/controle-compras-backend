package vincenzo.caio.controle_compras_backend.repository;

import vincenzo.caio.controle_compras_backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    Optional<Produto> findByCodigoDeBarras(String codigoDeBarras);
}
