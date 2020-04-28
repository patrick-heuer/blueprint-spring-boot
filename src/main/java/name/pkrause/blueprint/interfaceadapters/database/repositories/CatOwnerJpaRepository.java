package name.pkrause.blueprint.interfaceadapters.database.repositories;

import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.entities.PageResult;
import name.pkrause.blueprint.entities.CatOwnerRepository;
import name.pkrause.blueprint.usecases.shared.paging.OffsetBasedPageRequest;
import name.pkrause.blueprint.usecases.shared.paging.PagingUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatOwnerJpaRepository extends JpaRepository<CatOwner, Long>, CatOwnerRepository {

    @Override
    List<CatOwner> findByName(@Param("name") String name);

    @Override
    default PageResult<CatOwner> findAll(int offset, int limit) {
        Pageable pageable = new OffsetBasedPageRequest(offset, limit, Sort.by("id"));
        Page<CatOwner> catPage = this.findAll(pageable);
        PagingUtils<CatOwner> pagingUtils = new PagingUtils<>();
        return pagingUtils.mapPageToResult(catPage);
    }
}
