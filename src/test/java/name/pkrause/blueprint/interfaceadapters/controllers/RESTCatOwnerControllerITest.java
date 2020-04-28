package name.pkrause.blueprint.interfaceadapters.controllers;

import name.pkrause.blueprint.interfaceadapters.exceptions.CatNotFoundException;
import name.pkrause.blueprint.interfaceadapters.exceptions.CatOwnerNotFoundException;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwner;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwnerResponse;
import name.pkrause.blueprint.usecases.catowner.delete.DeleteCatOwner;
import name.pkrause.blueprint.usecases.catowner.get.GetCatOwner;
import name.pkrause.blueprint.usecases.catowner.get.GetCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.get.GetCatOwnerResponse;
import name.pkrause.blueprint.usecases.catowner.getowners.CatOwnerDto;
import name.pkrause.blueprint.usecases.catowner.getowners.GetCatOwners;
import name.pkrause.blueprint.usecases.catowner.getowners.GetCatOwnersResponse;
import name.pkrause.blueprint.usecases.catowner.getownersbyname.GetCatOwnersByName;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwner;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwnerResponse;
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

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc()
@WebMvcTest(RESTCatOwnerController.class)
class RESTCatOwnerControllerITest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GetCatOwner getCatOwnerUseCase;

    @MockBean
    private GetCatOwners getCatOwnersUseCase;

    @MockBean
    private GetCatOwnersByName getCatOwnersByNameUseCase;

    @MockBean
    private CreateCatOwner createCatOwnerUseCase;

    @MockBean
    private DeleteCatOwner deleteCatOwnerUseCase;

    @MockBean
    private UpdateCatOwner updateCatOwnerUseCase;

    @Test
    void returnCatOwnersAsJsonArray() throws Exception {

        // Given
        var mockedResponse = new GetCatOwnersResponse();

        CatOwnerDto catOwnerDto1 = new CatOwnerDto();
        catOwnerDto1.setId(1L);
        catOwnerDto1.setName("Cat owner 1");
        catOwnerDto1.setCats(null);
        mockedResponse.add(catOwnerDto1);

        CatOwnerDto catOwnerDto2 = new CatOwnerDto();
        catOwnerDto2.setId(1L);
        catOwnerDto2.setName("Cat owner 2");
        catOwnerDto2.setCats(null);
        mockedResponse.add(catOwnerDto2);

        when(getCatOwnersUseCase.execute(null)).thenReturn(mockedResponse);

        // When
        ResultActions resultActions = mvc.perform(get("/api/v1/owners"));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Cat owner 1")))
                .andExpect(content().string(containsString("Cat owner 2")));
    }

    @Test
    void returnCatOwnersAsJsonArrayWithPaging() throws Exception {

        // Given
        var mockedResponse = new GetCatOwnersResponse();

        CatOwnerDto catOwnerDto1 = new CatOwnerDto();
        catOwnerDto1.setId(1L);
        catOwnerDto1.setName("Cat owner 1");
        catOwnerDto1.setCats(null);
        mockedResponse.add(catOwnerDto1);

        CatOwnerDto catOwnerDto2 = new CatOwnerDto();
        catOwnerDto2.setId(1L);
        catOwnerDto2.setName("Cat owner 2");
        catOwnerDto2.setCats(null);
        mockedResponse.add(catOwnerDto2);

        // When
        when(getCatOwnersUseCase.execute(any())).thenReturn(mockedResponse); // Mockito!
        ResultActions resultActions = mvc.perform(get("/api/v1/owners?offset=0&limit=2")
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Cat owner 1")))
                .andExpect(content().string(containsString("Cat owner 2")));
    }

    @Test
    void throwExceptionIfCatNotFound() throws Exception {

        // When
        when(getCatOwnerUseCase.execute(new GetCatOwnerRequest(1L))).thenThrow(new CatOwnerNotFoundException(""));
        ResultActions resultActions = mvc.perform(get("/api/v1/owners/1"));

        // Then
        resultActions
                .andExpect(status().isNotFound());
    }

    @Test
    void returnCatOwnerByIdAsJson() throws Exception {
        // Given
        GetCatOwnerResponse found = new GetCatOwnerResponse();
        found.setId(1L);
        found.setName("Cat owner 1");

        // When
        when(getCatOwnerUseCase.execute(any())).thenReturn(found);
        ResultActions resultActions = mvc.perform(get("/api/v1/owners/{id}", 1L));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    void returnCreatedCatOwnerDataAsJsonObject() throws Exception {

        // Given
        CreateCatOwnerResponse mockedResponse = new CreateCatOwnerResponse();
        mockedResponse.setId(1L);
        mockedResponse.setName("Cat owner 1");

        when(createCatOwnerUseCase.execute(any(CreateCatOwnerRequest.class))).thenReturn(mockedResponse); // Mockito!

        // When
        ResultActions resultActions = mvc.perform(post("/api/v1/owners")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Cat owner 1\"}}"));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"Cat owner 1\"}"));

    }

    @Test
    void checkIfDeleteCatOwnerIsCalledOnlyOnce() throws Exception {
        // Given
        GetCatOwnerResponse found = new GetCatOwnerResponse();
        found.setId(1L);
        found.setName("Cat owner 1");

        // When
        when(getCatOwnerUseCase.execute(any())).thenReturn(found);
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.delete("/api/v1/owners/{id}", 1L));

        // Then
        verify(deleteCatOwnerUseCase, times(1)).execute(any());
    }

    @Test
    void throwExceptionIfCatOwnerNotFound() throws Exception {

        // When
        when(getCatOwnerUseCase.execute(new GetCatOwnerRequest(1L))).thenThrow(new CatNotFoundException(""));
        ResultActions resultActions = mvc.perform(get("/api/v1/owners/1"));

        // Then
        resultActions
                .andExpect(status().isNotFound());
    }

    @Test
    void returnUpdatedCatOwnerDataAsJsonObject() throws Exception {
        // Given
        UpdateCatOwnerResponse mockedResponse = new UpdateCatOwnerResponse();
        mockedResponse.setId(1L);
        mockedResponse.setName("New value");

        // When
        when(updateCatOwnerUseCase.execute(any())).thenReturn(mockedResponse);
        ResultActions resultActions = mvc.perform(put("/api/v1/owners/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"id\":1,\"name\":\"New value\"}"));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"New value\"}"));

    }

    @Test
    void returnBadRequestWhenCatOwnerNameIsEmpty() throws Exception {

        // When
        ResultActions resultActions = mvc.perform(post("/api/v1/owners")
                .contentType(MediaType.APPLICATION_JSON)
                //.content("{\"value\":\"\"}}"));
                .content(" ")); // space, because an emtpy value is not catched by REST controller

        // Then
        resultActions.andExpect(status().isBadRequest());
    }
}
