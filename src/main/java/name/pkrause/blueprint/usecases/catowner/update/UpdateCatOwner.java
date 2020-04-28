package name.pkrause.blueprint.usecases.catowner.update;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.usecases.shared.mapper.SourceTargetMapper;
import name.pkrause.blueprint.entities.CatOwnerRepository;
import name.pkrause.blueprint.entities.CatRepository;
import name.pkrause.blueprint.usecases.shared.BeanWrapperUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UpdateCatOwner {

    private final CatOwnerRepository catOwnerRepository;
    private final CatRepository catRepository;

    public UpdateCatOwner(CatOwnerRepository catOwnerRepository,
                          CatRepository catRepository) {
        this.catOwnerRepository = catOwnerRepository;
        this.catRepository = catRepository;
    }

    public UpdateCatOwnerResponse execute(UpdateCatOwnerRequest request) {

        CatOwner existing = catOwnerRepository.findById(request.getId());

        UpdateCatOwnerRequest tempRequest = SourceTargetMapper.MAPPER.toUpdateCatOwnerRequest(existing);

        BeanWrapperUtil beanWrapperUtil = new BeanWrapperUtil();
        beanWrapperUtil.copyNonNullProperties(request, tempRequest);

        CatOwner catOwner = SourceTargetMapper.MAPPER.toCatOwner(tempRequest);

        if (request.getCatIds() != null) {
            existing.removeCats();
            for (long catId : request.getCatIds()) {
                Cat cat = catRepository.findById(catId);
                catOwner.addCat(cat);
            }
        } else if (request.isDeleteCats()) {
            existing.removeCats();
        }

        CatOwner updatedCat = catOwnerRepository.save(catOwner);
        UpdateCatOwnerResponse response = SourceTargetMapper.MAPPER.toUpdateCatOwnerResponse(updatedCat);

        return response;
    }
}
