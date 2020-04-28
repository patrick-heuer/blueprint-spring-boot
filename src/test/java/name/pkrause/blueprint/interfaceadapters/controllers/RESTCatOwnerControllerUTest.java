package name.pkrause.blueprint.interfaceadapters.controllers;

import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwner;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwnerResponse;
import name.pkrause.blueprint.usecases.catowner.delete.DeleteCatOwner;
import name.pkrause.blueprint.usecases.catowner.delete.DeleteCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.get.GetCatOwner;
import name.pkrause.blueprint.usecases.catowner.get.GetCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.get.GetCatOwnerResponse;
import name.pkrause.blueprint.usecases.catowner.getowners.CatOwnerDto;
import name.pkrause.blueprint.usecases.catowner.getowners.GetCatOwners;
import name.pkrause.blueprint.usecases.catowner.getowners.GetCatOwnersRequest;
import name.pkrause.blueprint.usecases.catowner.getowners.GetCatOwnersResponse;
import name.pkrause.blueprint.usecases.catowner.getownersbyname.GetCatOwnersByName;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwner;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwnerResponse;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RESTCatOwnerControllerUTest {

    private RESTCatOwnerController RESTCatOwnerController;

    @Mock
    private GetCatOwner getCatOwnerUseCase;

    @Mock
    private GetCatOwners getCatOwnersUseCase;

    @Mock
    private GetCatOwnersByName getCatOwnersByNameUseCase;

    @Mock
    private CreateCatOwner createCatOwnerUseCase;

    @Mock
    private DeleteCatOwner deleteCatOwnerUseCase;

    @Mock
    private UpdateCatOwner updateCatOwnerUseCase;

    @BeforeEach
    void setUp() {
        RESTCatOwnerController = new RESTCatOwnerController(
                getCatOwnerUseCase,
                getCatOwnersUseCase,
                getCatOwnersByNameUseCase,
                createCatOwnerUseCase,
                deleteCatOwnerUseCase,
                updateCatOwnerUseCase);
    }

    @Nested
    class GetCatOwnersShould {

        @Test
        void returnDummyData() {

            // Given
            GetCatOwnersResponse getCatOwnersResponse = new GetCatOwnersResponse();

            CatOwnerDto catOwnerDto = new CatOwnerDto();
            catOwnerDto.setId(1L);
            catOwnerDto.setName("Cat owner 1");
            catOwnerDto.setCats(null);

            getCatOwnersResponse.add(catOwnerDto);

            GetCatOwnersRequest getCatOwnersRequest = new GetCatOwnersRequest();

            // When
            when(getCatOwnersUseCase.execute(getCatOwnersRequest)).thenReturn(getCatOwnersResponse);
            GetCatOwnersResponse result = getCatOwnersUseCase.execute(getCatOwnersRequest);

            // Then
            assertThat(result).isEqualTo(getCatOwnersResponse);
            assertThat(result.getCatOwners()).isEqualTo(getCatOwnersResponse.getCatOwners());
        }
    }

    @Nested
    class GetCatOwnerShould {

        @Test
        void returnDummyData() {

            // Given
            GetCatOwnerResponse catOwnerResponse = new GetCatOwnerResponse();
            catOwnerResponse.setId(1L);
            catOwnerResponse.setName("Cat owner 1");
            catOwnerResponse.setCats(null);

            GetCatOwnerRequest getCatOwnerRequest = new GetCatOwnerRequest(1L);

            // When
            when(getCatOwnerUseCase.execute(getCatOwnerRequest)).thenReturn(catOwnerResponse);
            GetCatOwnerResponse result = getCatOwnerUseCase.execute(getCatOwnerRequest);

            // Then
            assertThat(result).isEqualTo(catOwnerResponse);
        }
    }

    @Nested
    class CreateCatShould {

        @Test
        void createDummyUsingFormValue() {

            // Given
            CreateCatOwnerResponse createCatOwnerResponse = new CreateCatOwnerResponse();
            createCatOwnerResponse.setId(1L);
            createCatOwnerResponse.setName("Cat owner 1");

            CreateCatOwnerRequest createCatOwnerRequest = new CreateCatOwnerRequest();
            long[] catIds = {1L, 2L};
            createCatOwnerRequest.setCatIds(catIds);
            createCatOwnerRequest.setName("Cat owner 1");

            // When
            when(createCatOwnerUseCase.execute(createCatOwnerRequest)).thenReturn(createCatOwnerResponse);
            CreateCatOwnerResponse result = createCatOwnerUseCase.execute(createCatOwnerRequest);

            // Then
            AssertionsForClassTypes.assertThat(result.getId()).isEqualTo(createCatOwnerResponse.getId());
            AssertionsForClassTypes.assertThat(result.getName()).isEqualTo(createCatOwnerResponse.getName());
        }
    }

    @Nested
    class UpdateCatShould {

        @Test
        void updateCat() {

            // Given
            UpdateCatOwnerResponse updateCatOwnerResponse = new UpdateCatOwnerResponse();
            updateCatOwnerResponse.setId(1L);
            updateCatOwnerResponse.setName("New value");

            UpdateCatOwnerRequest updateCatOwnerRequest = new UpdateCatOwnerRequest();
            updateCatOwnerRequest.setId(1L);
            updateCatOwnerRequest.setName("New value");

            // When
            when(updateCatOwnerUseCase.execute(updateCatOwnerRequest)).thenReturn(updateCatOwnerResponse);
            UpdateCatOwnerResponse result = updateCatOwnerUseCase.execute(updateCatOwnerRequest);

            // Then
            AssertionsForClassTypes.assertThat(result.getId()).isEqualTo(updateCatOwnerResponse.getId());
            AssertionsForClassTypes.assertThat(result.getName()).isEqualTo(updateCatOwnerResponse.getName());
        }
    }

    @Nested
    class DeleteCatShould {

        @Test
        void deleteCat() {

            // Given
            DeleteCatOwnerRequest deleteCatOwnerRequest = new DeleteCatOwnerRequest(1L);

            // When
            deleteCatOwnerUseCase.execute(deleteCatOwnerRequest);

            // Then
            verify(deleteCatOwnerUseCase, times(1)).execute(deleteCatOwnerRequest);
        }
    }
}
