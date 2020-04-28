package name.pkrause.blueprint.usecases.catowner.delete;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCatOwnerRequest {

    private Long id;

    public DeleteCatOwnerRequest() {
    }

    public DeleteCatOwnerRequest(Long id) {
        this.id = id;
    }
}
