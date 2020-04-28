package name.pkrause.blueprint.usecases.catowner.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCatOwnerResponse {

    private Long id;
    private String name;

    public CreateCatOwnerResponse() {
    }
}
