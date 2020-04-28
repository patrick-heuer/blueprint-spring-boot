package name.pkrause.blueprint.usecases.catowner.getowners;

import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.entities.PageResult;
import name.pkrause.blueprint.usecases.shared.mapper.SourceTargetMapper;
import name.pkrause.blueprint.entities.CatOwnerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetCatOwners {

    private final CatOwnerRepository catOwnerRepository;

    public GetCatOwners(CatOwnerRepository catOwnerRepository) {
        this.catOwnerRepository = catOwnerRepository;
    }

    public GetCatOwnersResponse execute(GetCatOwnersRequest request) {

        GetCatOwnersResponse response = new GetCatOwnersResponse();

        List<CatOwner> catOwners;

        if (request == null) {
            catOwners = catOwnerRepository.findAll();
            response = addPageInfoToTheResponse(response, catOwners.size());
        } else {
            PageResult<CatOwner> pageResult = catOwnerRepository.findAll(request.getOffset(), request.getLimit());
            catOwners = pageResult.getElements();
            response = SourceTargetMapper.MAPPER.toGetCatOwnersResponse(pageResult);
        }

        for (CatOwner catOwner : catOwners) {
            CatOwnerDto catOwnerDto = SourceTargetMapper.MAPPER.toCatOwnerDto(catOwner);
            response.add(catOwnerDto);
        }

        return response;
    }

    private GetCatOwnersResponse addPageInfoToTheResponse(GetCatOwnersResponse response, int elementsSize) {
        response.setTotalElements(elementsSize);
        response.setOffset(0);
        response.setPageNumber(0);
        response.setPageSize(elementsSize);
        response.setTotalPages(1);
        return response;
    }
}
