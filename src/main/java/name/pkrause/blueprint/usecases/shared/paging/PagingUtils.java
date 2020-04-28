package name.pkrause.blueprint.usecases.shared.paging;

import name.pkrause.blueprint.entities.PageResult;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

public class PagingUtils<T> {
    public PageResult<T> mapPageToResult(Page<T> page) {
        PageResult<T> pageResult = new PageResult<>();

        pageResult.setPageNumber(page.getPageable().getPageNumber());
        pageResult.setPageSize(page.getPageable().getPageSize());
        pageResult.setOffset(page.getPageable().getOffset());
        pageResult.setElements(page.get().collect(Collectors.toList()));
        pageResult.setTotalElements(page.getTotalElements());
        pageResult.setTotalPages(page.getTotalPages());

        return pageResult;
    }
}
