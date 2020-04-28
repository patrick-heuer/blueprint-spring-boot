package name.pkrause.blueprint.entities;

/**  
 * Exception for cat entity
 * @author Patrick Krause 
 */
public class CatInvalidException extends RuntimeException {
    public CatInvalidException(String message) {
        super(message);
    }
}
