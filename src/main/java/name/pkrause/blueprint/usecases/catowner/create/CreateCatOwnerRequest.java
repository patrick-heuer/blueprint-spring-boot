package name.pkrause.blueprint.usecases.catowner.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateCatOwnerRequest {

    @NotEmpty(
            message = "Parameter 'name' cannot be null or empty"
    )
    private String name;

    private long[] catIds;

    // Default constructor needed for deserialazion of JSON
    public CreateCatOwnerRequest() {
    }

    public CreateCatOwnerRequest(String name) {
        this.name = name;
    }
}
