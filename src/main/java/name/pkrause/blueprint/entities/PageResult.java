package name.pkrause.blueprint.entities;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
* Stellt eine Page für gepagte Ergenismengen dar  
*/
@Getter
@Setter
public class PageResult<T> {

    private int pageNumber;
    private int pageSize;
    private long offset;
    private long totalElements;
    private long totalPages;
    private List<T> elements;

    public PageResult() {
    }
}
