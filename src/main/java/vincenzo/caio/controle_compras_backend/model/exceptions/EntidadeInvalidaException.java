package vincenzo.caio.controle_compras_backend.model.exceptions;

public class EntidadeInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntidadeInvalidaException(String mensagem) {
        super(mensagem);
    }
}
