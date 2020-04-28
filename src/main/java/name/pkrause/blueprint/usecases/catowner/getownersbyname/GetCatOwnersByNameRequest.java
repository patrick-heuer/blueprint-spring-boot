package name.pkrause.blueprint.usecases.catowner.getownersbyname;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCatOwnersByNameRequest {

    private String name;

    public GetCatOwnersByNameRequest() {
    }

    public GetCatOwnersByNameRequest(String name) {
        this.name = name;
    }
}