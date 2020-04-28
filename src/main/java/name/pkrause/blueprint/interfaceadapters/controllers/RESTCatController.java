package name.pkrause.blueprint.interfaceadapters.controllers;

import name.pkrause.blueprint.interfaceadapters.exceptions.CatNotFoundException;
import name.pkrause.blueprint.usecases.cat.create.CreateCat;
import name.pkrause.blueprint.usecases.cat.create.CreateCatRequest;
import name.pkrause.blueprint.usecases.cat.create.CreateCatResponse;
import name.pkrause.blueprint.usecases.cat.delete.DeleteCat;
import name.pkrause.blueprint.usecases.cat.delete.DeleteCatRequest;
import name.pkrause.blueprint.usecases.cat.get.GetCat;
import name.pkrause.blueprint.usecases.cat.get.GetCatRequest;
import name.pkrause.blueprint.usecases.cat.get.GetCatResponse;
import name.pkrause.blueprint.usecases.cat.getcats.GetCats;
import name.pkrause.blueprint.usecases.cat.getcats.GetCatsRequest;
import name.pkrause.blueprint.usecases.cat.getcats.GetCatsResponse;
import name.pkrause.blueprint.usecases.cat.update.UpdateCat;
import name.pkrause.blueprint.usecases.cat.update.UpdateCatRequest;
import name.pkrause.blueprint.usecases.cat.update.UpdateCatResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**  
 * RESTful controller for accessing the cat use cases
 * @author  Patrick Krause 
 */
@RestController
@RequestMapping("/api")
public class RESTCatController {

    private final GetCat getCatUseCase;
    private final CreateCat createCatUseCase;
    private final DeleteCat deleteCatUseCase;
    private final UpdateCat updateCatUseCase;
    private final GetCats getCatsUseCase;


    public RESTCatController(GetCat getCatUseCase,
                             CreateCat createCatUseCase,
                             DeleteCat deleteCatUseCase,
                             UpdateCat updateCatUseCase, GetCats getCatsUseCase) {
        this.getCatUseCase = getCatUseCase;
        this.createCatUseCase = createCatUseCase;
        this.deleteCatUseCase = deleteCatUseCase;
        this.updateCatUseCase = updateCatUseCase;
        this.getCatsUseCase = getCatsUseCase;
    }

    @GetMapping(value = "/v1/cats", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCatsResponse getCats() {
        return getCatsUseCase.execute(null);
    }

    @GetMapping(value = "/v1/cats", params = {"offset", "limit"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCatsResponse getCatsPage(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return getCatsUseCase.execute(new GetCatsRequest(offset, limit));
    }

    @GetMapping(value = "/v1/cats/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetCatResponse> getCat(@PathVariable(value = "id", required = true) Long id) {
        final GetCatResponse response = getCatUseCase.execute(new GetCatRequest(id));

        if (response == null) {
            throw new CatNotFoundException("Cat with id " + id + " not found");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/v1/cats", produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateCatResponse createCat(@Valid @RequestBody CreateCatRequest request) {
        CreateCatResponse response = createCatUseCase.execute(request);
        return response;
    }

    @DeleteMapping("/v1/cats/{id}")
    public void deleteCat(@PathVariable(value = "id", required = true) long id) {
        GetCatResponse getCatResponse = getCatUseCase.execute(new GetCatRequest(id));

        if (getCatResponse == null) {
            throw new CatNotFoundException("Cat with id " + id + " not found");
        }

        deleteCatUseCase.execute(new DeleteCatRequest(id));
    }

    @PutMapping("/v1/cats/{id}")
    public UpdateCatResponse updateCat(@PathVariable(value = "id", required = true) long id,
                                       @Valid @RequestBody UpdateCatRequest request) {
        request.setId(id);
        return updateCatUseCase.execute(request);
    }

}
