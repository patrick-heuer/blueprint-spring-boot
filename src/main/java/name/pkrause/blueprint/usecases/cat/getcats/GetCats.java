package name.pkrause.blueprint.usecases.cat.getcats;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.PageResult;
import name.pkrause.blueprint.usecases.shared.mapper.SourceTargetMapper;
import name.pkrause.blueprint.entities.CatRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetCats {

    private final CatRepository catRepository;

    public GetCats(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public GetCatsResponse execute(GetCatsRequest request) {

        GetCatsResponse response = new GetCatsResponse();

        List<Cat> cats;

        if (request == null) {
            cats = catRepository.findAll();
            response = addPageInfoToTheResponse(response, cats.size());
        } else {
            PageResult<Cat> pageResult = catRepository.findAll(request.getOffset(), request.getLimit());
            cats = pageResult.getElements();
            response = SourceTargetMapper.MAPPER.toGetCatsResponse(pageResult);
        }

        for (Cat cat : cats) {
            CatDto catDto = SourceTargetMapper.MAPPER.toCatDto(cat);
            response.add(catDto);
        }

        return response;
    }

    private GetCatsResponse addPageInfoToTheResponse(GetCatsResponse response, int elementsSize) {
        response.setTotalElements(elementsSize);
        response.setOffset(0);
        response.setPageNumber(0);
        response.setPageSize(elementsSize);
        response.setTotalPages(1);
        return response;
    }
}
