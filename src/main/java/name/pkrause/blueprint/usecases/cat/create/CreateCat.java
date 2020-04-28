package name.pkrause.blueprint.usecases.cat.create;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.entities.CatRepository;
import name.pkrause.blueprint.usecases.shared.mapper.SourceTargetMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**  
 * use case for create a new cat
 * @author  Patrick Krause 
 */
@Service
@Transactional
public class CreateCat {

    private final CatRepository catRepository;

    public CreateCat(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public CreateCatResponse execute(CreateCatRequest request) {

        Cat cat = SourceTargetMapper.MAPPER.toCat(request);

        if (request.getCatOwnersId() != null) {
            CatOwner catOwner = new CatOwner();
            catOwner.setId(request.getCatOwnersId());
            cat.setCatOwner(catOwner);
        }

        Cat updatedCat = catRepository.save(cat);

        CreateCatResponse response = SourceTargetMapper.MAPPER.toCreateCatResponse(updatedCat);

        return response;
    }
}
