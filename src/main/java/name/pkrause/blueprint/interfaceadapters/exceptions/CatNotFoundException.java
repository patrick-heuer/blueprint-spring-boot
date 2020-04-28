package name.pkrause.blueprint.interfaceadapters.exceptions;

public class CatNotFoundException extends RuntimeException {
    public CatNotFoundException(String message) {
        super(message);
    }
}
