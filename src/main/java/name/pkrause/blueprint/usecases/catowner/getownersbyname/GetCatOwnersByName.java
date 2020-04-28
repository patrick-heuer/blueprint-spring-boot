package name.pkrause.blueprint.usecases.catowner.getownersbyname;

import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.entities.CatOwnerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetCatOwnersByName {

    private final CatOwnerRepository catOwnerRepository;

    public GetCatOwnersByName(CatOwnerRepository catOwnerRepository) {
        this.catOwnerRepository = catOwnerRepository;
    }

    public GetCatOwnersByNameResponse execute(GetCatOwnersByNameRequest request) {

        GetCatOwnersByNameResponse response = new GetCatOwnersByNameResponse();

        List<CatOwner> catOwners;

        catOwners = catOwnerRepository.findByName(request.getName());
        response = addPageInfoToTheResponse(response, catOwners.size());


        for (CatOwner catOwner : catOwners) {
            CatOwnerDto catOwnerDto = new CatOwnerDto();
            catOwnerDto.setId(catOwner.getId());
            catOwnerDto.setName(catOwner.getName());

            response.add(catOwnerDto);
        }

        return response;
    }

    private GetCatOwnersByNameResponse addPageInfoToTheResponse(GetCatOwnersByNameResponse response, int elementsSize) {
        response.setTotalElements(elementsSize);
        response.setOffset(0);
        response.setPageNumber(0);
        response.setPageSize(elementsSize);
        response.setTotalPages(1);
        return response;
    }
}