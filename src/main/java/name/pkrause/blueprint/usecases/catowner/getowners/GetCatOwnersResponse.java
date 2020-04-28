package name.pkrause.blueprint.usecases.catowner.getowners;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetCatOwnersResponse {

    private List<CatOwnerDto> catOwners;
    private int pageNumber;
    private int pageSize;
    private long offset;
    private long totalElements;
    private long totalPages;

    public void add(CatOwnerDto catDto) {
        this.catOwners.add(catDto);
    }

    public GetCatOwnersResponse() {
        this.catOwners = new ArrayList<>();
    }

}
