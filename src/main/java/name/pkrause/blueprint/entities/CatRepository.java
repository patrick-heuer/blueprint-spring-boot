package name.pkrause.blueprint.entities;

import java.util.List;

/**  
 * interface for database repository (for "inversion of control" pattern)
 * @author  Patrick Krause 
 */
public interface CatRepository {

    Cat save(Cat cat);

    List<Cat> findAll();

    Cat findById(long id);

    void deleteById(long id);

    PageResult<Cat> findAll(int offset, int limit);
}
