package name.pkrause.blueprint.usecases.cat.delete;

import name.pkrause.blueprint.entities.CatRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeleteCat {

    private final CatRepository catRepository;

    public DeleteCat(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void execute(DeleteCatRequest request) {
        catRepository.deleteById(request.getId());
    }

}
