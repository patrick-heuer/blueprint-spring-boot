package name.pkrause.blueprint.usecases.cat.get;

import name.pkrause.blueprint.entities.CatOwner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCatResponse {

    private Long id;
    private String value;
    private CatOwner catOwner;

    public GetCatResponse() {
    }
}
