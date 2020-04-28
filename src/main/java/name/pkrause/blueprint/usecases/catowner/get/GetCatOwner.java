package name.pkrause.blueprint.usecases.catowner.get;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.usecases.shared.mapper.SourceTargetMapper;
import name.pkrause.blueprint.entities.CatOwnerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class GetCatOwner {

    private CatOwnerRepository catOwnerRepository;

    public GetCatOwner(CatOwnerRepository catOwnerRepository) {
        this.catOwnerRepository = catOwnerRepository;
    }

    public GetCatOwnerResponse execute(GetCatOwnerRequest request) {
        
        var response = new GetCatOwnerResponse();

        CatOwner catOwner = catOwnerRepository.findById(request.getId());

        if (catOwner == null) {
            return null;
        }

        response.setId(catOwner.getId());
        response.setName(catOwner.getName());

        Set<Cat> cats = catOwner.getCats();

        for (Cat cat : cats) {
            CatDto catDto = SourceTargetMapper.MAPPER.toCatDtoOwner(cat);
            response.add(catDto);
        }

        return response;
    }
}
