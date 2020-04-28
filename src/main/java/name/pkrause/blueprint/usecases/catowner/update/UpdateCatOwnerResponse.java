package name.pkrause.blueprint.usecases.catowner.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCatOwnerResponse {

    private Long id;
    private String name;

    public UpdateCatOwnerResponse() {
    }
}
