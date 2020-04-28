package name.pkrause.blueprint.interfaceadapters.controllers;

import name.pkrause.blueprint.interfaceadapters.exceptions.CatOwnerNotFoundException;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwner;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwnerResponse;
import name.pkrause.blueprint.usecases.catowner.delete.DeleteCatOwner;
import name.pkrause.blueprint.usecases.catowner.delete.DeleteCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.get.GetCatOwner;
import name.pkrause.blueprint.usecases.catowner.get.GetCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.get.GetCatOwnerResponse;
import name.pkrause.blueprint.usecases.catowner.getowners.GetCatOwners;
import name.pkrause.blueprint.usecases.catowner.getowners.GetCatOwnersRequest;
import name.pkrause.blueprint.usecases.catowner.getowners.GetCatOwnersResponse;
import name.pkrause.blueprint.usecases.catowner.getownersbyname.GetCatOwnersByName;
import name.pkrause.blueprint.usecases.catowner.getownersbyname.GetCatOwnersByNameRequest;
import name.pkrause.blueprint.usecases.catowner.getownersbyname.GetCatOwnersByNameResponse;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwner;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwnerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RESTCatOwnerController {

    private final GetCatOwner getCatOwnerUseCase;
    private final GetCatOwners getCatOwnersUseCase;
    private final GetCatOwnersByName getCatOwnersByNameUseCase;
    private final CreateCatOwner createCatOwnerUseCase;
    private final DeleteCatOwner deleteCatOwnerUseCase;
    private final UpdateCatOwner updateCatOwnerUseCase;

    @Autowired
    public RESTCatOwnerController(GetCatOwner getCatOwnerUseCase,
                                  GetCatOwners getCatOwnersUseCase,
                                  GetCatOwnersByName getCatOwnersByNameUseCase,
                                  CreateCatOwner createCatOwnerUseCase,
                                  DeleteCatOwner deleteCatOwnerUseCase,
                                  UpdateCatOwner updateCatOwnerUseCase) {
        this.getCatOwnerUseCase = getCatOwnerUseCase;
        this.getCatOwnersUseCase = getCatOwnersUseCase;
        this.getCatOwnersByNameUseCase = getCatOwnersByNameUseCase;
        this.createCatOwnerUseCase = createCatOwnerUseCase;
        this.deleteCatOwnerUseCase = deleteCatOwnerUseCase;
        this.updateCatOwnerUseCase = updateCatOwnerUseCase;
    }

    @GetMapping(value = "/v1/owners", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCatOwnersResponse getCatOwners() {
        return getCatOwnersUseCase.execute(null);
    }

    @GetMapping(value = "/v1/owners", params = {"offset", "limit"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCatOwnersResponse getCatOwnersPage(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return getCatOwnersUseCase.execute(new GetCatOwnersRequest(offset, limit));
    }

    @GetMapping(value = "/v1/owners/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetCatOwnerResponse> getCatOwner(@PathVariable(value = "id", required = true) Long id) {
        final GetCatOwnerResponse response = getCatOwnerUseCase.execute(new GetCatOwnerRequest(id));

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/v1/owners", params = {"name"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCatOwnersByNameResponse getCatOwnersByName(
            @RequestParam(value = "name", required = true) String name) {
        return getCatOwnersByNameUseCase.execute(new GetCatOwnersByNameRequest(name));
    }

    @PostMapping(value = "/v1/owners", produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateCatOwnerResponse createCatOwner(@Valid @RequestBody CreateCatOwnerRequest request) {
        CreateCatOwnerResponse response = createCatOwnerUseCase.execute(request);
        return response;
    }

    @DeleteMapping("/v1/owners/{id}")
    public void deleteCatOwner(@PathVariable(value = "id", required = true) long id) {
        GetCatOwnerResponse getCatOwnerResponse = getCatOwnerUseCase.execute(new GetCatOwnerRequest(id));

        if (getCatOwnerResponse == null) {
            throw new CatOwnerNotFoundException("Cat owner with id " + id + " not found");
        }

        deleteCatOwnerUseCase.execute(new DeleteCatOwnerRequest(id));
    }

    @PutMapping("/v1/owners/{id}")
    public UpdateCatOwnerResponse updateCatOwner(@PathVariable(value = "id", required = true) long id,
                                                 @Valid @RequestBody UpdateCatOwnerRequest request) {
        request.setId(id);
        return updateCatOwnerUseCase.execute(request);
    }
}
