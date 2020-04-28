package name.pkrause.blueprint.usecases.cat.getcats;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetCatsResponse {

    private List<CatDto> cats;
    private int pageNumber;
    private int pageSize;
    private long offset;
    private long totalElements;
    private long totalPages;

    public void add(CatDto catDto) {
        this.cats.add(catDto);
    }

    public GetCatsResponse() {
        this.cats = new ArrayList<>();
    }

}
