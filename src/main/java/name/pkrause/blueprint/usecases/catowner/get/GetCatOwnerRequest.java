package name.pkrause.blueprint.usecases.catowner.get;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCatOwnerRequest {

    private Long id;

    public GetCatOwnerRequest() {
    }

    public GetCatOwnerRequest(Long id) {
        this.id = id;
    }
}
