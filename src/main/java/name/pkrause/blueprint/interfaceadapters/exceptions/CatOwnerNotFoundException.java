package name.pkrause.blueprint.interfaceadapters.exceptions;

public class CatOwnerNotFoundException extends RuntimeException {
    public CatOwnerNotFoundException(String message) {
        super(message);
    }
}
