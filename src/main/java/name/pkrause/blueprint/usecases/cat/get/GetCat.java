package name.pkrause.blueprint.usecases.cat.get;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatRepository;
import name.pkrause.blueprint.usecases.shared.mapper.SourceTargetMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GetCat {

    private final CatRepository catRepository;

    public GetCat(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public GetCatResponse execute(GetCatRequest request) {

        var cat = catRepository.findById(request.getId());

        if (cat == null) {
            return null;
        }

        GetCatResponse response = SourceTargetMapper.MAPPER.toGetCatResponse(cat);

        return response;
    }

}
