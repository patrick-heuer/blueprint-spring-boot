package name.pkrause.blueprint.interfaceadapters.exceptions;

/**  
 * exception class for cat controller
 * @author  Patrick Krause 
 */
public class CatControllerInvalidException extends RuntimeException {
    public CatControllerInvalidException(String message) {
        super(message);
    }
}
