package name.pkrause.blueprint.usecases.cat.update;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.entities.CatRepository;
import name.pkrause.blueprint.usecases.shared.BeanWrapperUtil;
import name.pkrause.blueprint.usecases.shared.mapper.SourceTargetMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UpdateCat {

    private final CatRepository catRepository;

    public UpdateCat(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public UpdateCatResponse execute(UpdateCatRequest request) {

        Cat existing = catRepository.findById(request.getId());

        UpdateCatRequest tempRequest = SourceTargetMapper.MAPPER.toUpdateCatRequest(existing);

        BeanWrapperUtil beanWrapperUtil = new BeanWrapperUtil();
        beanWrapperUtil.copyNonNullProperties(request, tempRequest);

        Cat cat = SourceTargetMapper.MAPPER.toCat(tempRequest);

        if (tempRequest.getCatOwnersId() != null && !request.isDeleteCatOwner()) {
            CatOwner catOwner = new CatOwner();
            catOwner.setId(tempRequest.getCatOwnersId());
            cat.setCatOwner(catOwner);
        } else {
            cat.setCatOwner(null);
        }

        Cat updatedCat = catRepository.save(cat);

        UpdateCatResponse response = SourceTargetMapper.MAPPER.toUpdateCatResponse(updatedCat);

        return response;
    }


}
