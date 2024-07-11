package one.digitalinnovation.gof.handlers;

/**
 * Essa classe <b>ClienteInsertionException</b> é personalizada
 * para tratar exceção que será gerenciada
 * pela handler global {@link GlobalExceptionHandler}.
 *
 * @author Kaio Abreu
 */

public class ClienteInsertionException extends RuntimeException {
    public ClienteInsertionException(String message) {
        super(message);
    }
}
