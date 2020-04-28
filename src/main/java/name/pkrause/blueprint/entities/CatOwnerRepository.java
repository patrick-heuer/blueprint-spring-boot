package name.pkrause.blueprint.entities;

import java.util.List;

public interface CatOwnerRepository {

    CatOwner save(CatOwner catOwner);

    List<CatOwner> findAll();

    CatOwner findById(long id);

    List<CatOwner> findByName(String name);

    void deleteById(long id);

    PageResult<CatOwner> findAll(int offset, int size);
}
