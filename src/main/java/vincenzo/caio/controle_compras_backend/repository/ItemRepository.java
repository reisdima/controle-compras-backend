package vincenzo.caio.controle_compras_backend.repository;

import vincenzo.caio.controle_compras_backend.model.ItemDeCompra;
import vincenzo.caio.controle_compras_backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<ItemDeCompra, UUID> {

    Optional<List<ItemDeCompra>> findByProduto(Produto produto);
}
