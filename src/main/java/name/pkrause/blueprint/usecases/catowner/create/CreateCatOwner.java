package name.pkrause.blueprint.usecases.catowner.create;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.usecases.shared.mapper.SourceTargetMapper;
import name.pkrause.blueprint.entities.CatOwnerRepository;
import name.pkrause.blueprint.entities.CatRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateCatOwner {

    private final CatOwnerRepository catOwnerRepository;
    private final CatRepository catRepository;

    public CreateCatOwner(CatOwnerRepository catOwnerRepository,
                          CatRepository catRepository) {
        this.catOwnerRepository = catOwnerRepository;
        this.catRepository = catRepository;
    }

    public CreateCatOwnerResponse execute(CreateCatOwnerRequest request) {

        CatOwner catOwner = SourceTargetMapper.MAPPER.toCatOwner(request);

        if (request.getCatIds() != null) {
            for (long i : request.getCatIds()) {
                Cat cat = catRepository.findById(i);
                catOwner.addCat(cat);
            }
        }

        CatOwner updatedCat = catOwnerRepository.save(catOwner);
        CreateCatOwnerResponse response = SourceTargetMapper.MAPPER.toCreateCatOwnerResponse(updatedCat);

        return response;
    }
}
