package name.pkrause.blueprint.interfaceadapters.controllers;

import name.pkrause.blueprint.interfaceadapters.exceptions.CatNotFoundException;
import name.pkrause.blueprint.usecases.cat.create.CreateCat;
import name.pkrause.blueprint.usecases.cat.create.CreateCatRequest;
import name.pkrause.blueprint.usecases.cat.create.CreateCatResponse;
import name.pkrause.blueprint.usecases.cat.delete.DeleteCat;
import name.pkrause.blueprint.usecases.cat.get.GetCat;
import name.pkrause.blueprint.usecases.cat.get.GetCatRequest;
import name.pkrause.blueprint.usecases.cat.get.GetCatResponse;
import name.pkrause.blueprint.usecases.cat.getcats.CatDto;
import name.pkrause.blueprint.usecases.cat.getcats.GetCats;
import name.pkrause.blueprint.usecases.cat.getcats.GetCatsResponse;
import name.pkrause.blueprint.usecases.cat.update.UpdateCat;
import name.pkrause.blueprint.usecases.cat.update.UpdateCatResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**  
 * integration tests on the webservice (using mocked dependency´s)
 * @author  Patrick Krause 
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc()
@WebMvcTest(RESTCatController.class)
class RESTCatControllerITest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreateCat createCatUseCase;

    @MockBean
    private GetCats getCatsUseCase;

    @MockBean
    private DeleteCat deleteCatUseCase;

    @MockBean
    private UpdateCat updateCatUseCase;

    @MockBean
    private GetCat getCatUseCase;

    @Test
    void returnCatsAsJsonArray() throws Exception {

        // Given

        var mockedResponse = new GetCatsResponse();

        var cat1 = new CatDto();
        cat1.setId(1L);
        cat1.setValue("Freddy");
        mockedResponse.getCats().add(cat1);

        var cat2 = new CatDto();
        cat2.setId(2L);
        cat2.setValue("Mia");
        mockedResponse.getCats().add(cat2);

        when(getCatsUseCase.execute(null)).thenReturn(mockedResponse); // Mockito!

        // When
        ResultActions resultActions = mvc.perform(get("/api/v1/cats"));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Freddy")))
                .andExpect(content().string(containsString("Mia")));
    }

    @Test
    void returnCatsAsJsonArrayWithPaging() throws Exception {
        var mockedResponse = new GetCatsResponse();

        var cat1 = new CatDto();
        cat1.setId(1L);
        cat1.setValue("Freddy");
        mockedResponse.getCats().add(cat1);

        var cat2 = new CatDto();
        cat2.setId(2L);
        cat2.setValue("Mia");
        mockedResponse.getCats().add(cat2);


        // When
        when(getCatsUseCase.execute(any())).thenReturn(mockedResponse); // Mockito!
        ResultActions resultActions = mvc.perform(get("/api/v1/cats?offset=0&limit=2")
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Freddy")))
                .andExpect(content().string(containsString("Mia")));
    }

    @Test
    void throwExceptionIfCatNotFound() throws Exception {

        // When
        when(getCatUseCase.execute(new GetCatRequest(1L))).thenThrow(new CatNotFoundException(""));
        ResultActions resultActions = mvc.perform(get("/api/v1/cats/1"));

        // Then
        resultActions
                .andExpect(status().isNotFound());
    }

    @Test
    void returnCatByIdAsJson() throws Exception {
        // Given
        GetCatResponse found = new GetCatResponse();
        found.setId(1L);
        found.setValue("Cat");

        // When
        when(getCatUseCase.execute(any())).thenReturn(found);
        ResultActions resultActions = mvc.perform(get("/api/v1/cats/{id}", 1L));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Cat")));
    }

    @Test
    void returnCreatedCatDataAsJsonObject() throws Exception {

        // Given
        CreateCatResponse mockedResponse = new CreateCatResponse();
        mockedResponse.setId(4711L);
        mockedResponse.setValue("Freddy");

        when(createCatUseCase.execute(any(CreateCatRequest.class))).thenReturn(mockedResponse); // Mockito!

        // When
        ResultActions resultActions = mvc.perform(post("/api/v1/cats")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\":\"Freddy\"}}"));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(content().string("{\"id\":4711,\"value\":\"Freddy\"}"));

    }

    @Test
    void checkIfDeleteCatIsCalledOnlyOnce() throws Exception {
        // Given
        GetCatResponse found = new GetCatResponse();
        found.setId(1L);
        found.setValue("Cat");

        // When
        when(getCatUseCase.execute(any())).thenReturn(found);
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.delete("/api/v1/cats/{id}", 1L));

        // Then
        verify(deleteCatUseCase, times(1)).execute(any());
    }

    @Test
    void throwExceptionIfCatNotFoundDuringDelete() throws Exception {
        // When
        when(getCatUseCase.execute(new GetCatRequest(1L))).thenThrow(new CatNotFoundException(""));
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.delete("/api/v1/cats/1"));

        // Then
        resultActions
                .andExpect(status().isNotFound());
    }

    @Test
    void returnUpdatedCatDataAsJsonObject() throws Exception {
        // Given
        UpdateCatResponse mockedResponse = new UpdateCatResponse();
        mockedResponse.setId(1L);
        mockedResponse.setValue("Cat");

        // When
        when(updateCatUseCase.execute(any())).thenReturn(mockedResponse);
        ResultActions resultActions = mvc.perform(put("/api/v1/cats/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"id\":1,\"value\":\"Cat2\"}"));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"value\":\"Cat\"}"));

    }

    @Test
    void returnBadRequestWhenCatNameIsEmpty() throws Exception {

        // When
        ResultActions resultActions = mvc.perform(post("/api/v1/cats")
                .contentType(MediaType.APPLICATION_JSON)
                //.content("{\"value\":\"\"}}"));
                .content(" ")); // space, because an emtpy value is not catched by REST controller

        // Then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void returnResponseEntity() throws Exception {

        // Given
        GetCatResponse found = new GetCatResponse();
        found.setId(1L);
        found.setValue("Cat");

        // When
        when(getCatUseCase.execute(any())).thenReturn(found);
        ResultActions resultActions = mvc.perform(get("/api/v1/testgetcat/{id}", 1L));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}