package name.pkrause.blueprint.usecases.catowner.getowners;

import name.pkrause.blueprint.usecases.catowner.get.CatDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CatOwnerDto {

    private Long id;
    private String name;
    private Set<CatDto> cats;

    public void add(CatDto catDto) {
        this.cats.add(catDto);
    }


}
