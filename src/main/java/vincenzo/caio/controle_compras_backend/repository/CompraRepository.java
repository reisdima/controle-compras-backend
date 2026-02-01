package vincenzo.caio.controle_compras_backend.repository;

import vincenzo.caio.controle_compras_backend.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompraRepository extends JpaRepository<Compra, UUID> {

    Optional<Compra> findByNotaFiscal(String notaFiscal);
}
