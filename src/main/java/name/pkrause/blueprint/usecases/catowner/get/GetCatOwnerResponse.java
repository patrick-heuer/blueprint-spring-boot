package name.pkrause.blueprint.usecases.catowner.get;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class GetCatOwnerResponse {

    private Long id;
    private String name;
    private Set<CatDto> cats;


    public void add(CatDto catDto) {
        this.cats.add(catDto);
    }

    public GetCatOwnerResponse() {
        this.cats = new HashSet<>();
    }
}
