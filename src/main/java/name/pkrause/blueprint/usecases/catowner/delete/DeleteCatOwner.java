package name.pkrause.blueprint.usecases.catowner.delete;

import name.pkrause.blueprint.entities.CatOwnerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeleteCatOwner {

    private final CatOwnerRepository catOwnerRepository;

    public DeleteCatOwner(CatOwnerRepository catOwnerRepository) {
        this.catOwnerRepository = catOwnerRepository;
    }

    public void execute(DeleteCatOwnerRequest request) {
        catOwnerRepository.deleteById(request.getId());
    }

}
