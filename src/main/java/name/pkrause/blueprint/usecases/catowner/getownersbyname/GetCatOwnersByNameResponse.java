package name.pkrause.blueprint.usecases.catowner.getownersbyname;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetCatOwnersByNameResponse {

    private List<CatOwnerDto> catOwners;
    private int pageNumber;
    private int pageSize;
    private long offset;
    private long totalElements;
    private long totalPages;

    public void add(CatOwnerDto catDto) {
        this.catOwners.add(catDto);
    }

    public GetCatOwnersByNameResponse() {
        this.catOwners = new ArrayList<>();
    }
}