package name.pkrause.blueprint.interfaceadapters.database.repositories;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.PageResult;
import name.pkrause.blueprint.entities.CatRepository;
import name.pkrause.blueprint.usecases.shared.paging.OffsetBasedPageRequest;
import name.pkrause.blueprint.usecases.shared.paging.PagingUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**  
 * extends the normal cat repository interface with JPA specific stuff
 * @author  Patrick Krause 
 */
@Repository
public interface CatJpaRepository extends JpaRepository<Cat, Long>, CatRepository {

    @Override
    default PageResult<Cat> findAll(int offset, int limit) {
        Pageable pageable = new OffsetBasedPageRequest(offset, limit, Sort.by("id"));
        Page<Cat> catPage = this.findAll(pageable);
        PagingUtils<Cat> pagingUtils = new PagingUtils<>();
        return pagingUtils.mapPageToResult(catPage);
    }
}
