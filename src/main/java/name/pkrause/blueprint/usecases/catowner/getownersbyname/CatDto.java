package name.pkrause.blueprint.usecases.catowner.getownersbyname;

import name.pkrause.blueprint.entities.CatOwner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatDto {

    private Long id;
    private String value;
    private CatOwner catOwner;
}