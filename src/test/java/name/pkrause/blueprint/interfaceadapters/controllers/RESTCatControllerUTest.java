package name.pkrause.blueprint.interfaceadapters.controllers;

import name.pkrause.blueprint.usecases.cat.create.CreateCat;
import name.pkrause.blueprint.usecases.cat.create.CreateCatRequest;
import name.pkrause.blueprint.usecases.cat.create.CreateCatResponse;
import name.pkrause.blueprint.usecases.cat.delete.DeleteCat;
import name.pkrause.blueprint.usecases.cat.delete.DeleteCatRequest;
import name.pkrause.blueprint.usecases.cat.get.GetCat;
import name.pkrause.blueprint.usecases.cat.get.GetCatRequest;
import name.pkrause.blueprint.usecases.cat.get.GetCatResponse;
import name.pkrause.blueprint.usecases.cat.getcats.CatDto;
import name.pkrause.blueprint.usecases.cat.getcats.GetCats;
import name.pkrause.blueprint.usecases.cat.getcats.GetCatsRequest;
import name.pkrause.blueprint.usecases.cat.getcats.GetCatsResponse;
import name.pkrause.blueprint.usecases.cat.update.UpdateCat;
import name.pkrause.blueprint.usecases.cat.update.UpdateCatRequest;
import name.pkrause.blueprint.usecases.cat.update.UpdateCatResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

/**  
 * unit tests for the cat controller
 * @author  Patrick Krause 
 */
@ExtendWith(MockitoExtension.class)
class RESTCatControllerUTest {

    private RESTCatController RESTCatController;

    @Mock
    private CreateCat createCatUseCase;

    @Mock
    private GetCats getCatsUseCase;

    @Mock
    private DeleteCat deleteCatUseCase;

    @Mock
    private UpdateCat updateCatUseCase;

    @Mock
    private GetCat getCatUseCase;

    @BeforeEach
    void setUp() {
        RESTCatController = new RESTCatController(getCatUseCase, createCatUseCase, deleteCatUseCase,
                updateCatUseCase, getCatsUseCase);
    }

    @Nested
    class GetAllCatsShould {

        @Test
        void returnDummyData() {

            // Given
            GetCatsResponse getCatsResponse = new GetCatsResponse();

            CatDto catDto = new CatDto();
            catDto.setId(1L);
            catDto.setValue("Cat");
            catDto.setCatOwner(null);

            getCatsResponse.add(catDto);

            GetCatsRequest getCatsRequest = new GetCatsRequest();
            getCatsRequest.setOffset(0);
            getCatsRequest.setLimit(1);

            // When
            when(getCatsUseCase.execute(getCatsRequest)).thenReturn(getCatsResponse);
            GetCatsResponse result = getCatsUseCase.execute(getCatsRequest);

            // Then
            assertThat(result).isEqualTo(getCatsResponse);
            assertThat(result.getCats()).isEqualTo(getCatsResponse.getCats());
        }
    }

    @Nested
    class GetCatShould {

        @Test
        void returnDummyData() {

            // Given
            GetCatResponse getCatResponse = new GetCatResponse();
            getCatResponse.setId(4L);

            GetCatRequest getCatRequest = new GetCatRequest();
            getCatRequest.setId(1L);

            // When
            when(getCatUseCase.execute(getCatRequest)).thenReturn(getCatResponse);
            GetCatResponse result = getCatUseCase.execute(getCatRequest);

            // Then
            assertThat(result.getId()).isEqualTo(getCatResponse.getId());
        }
    }

    @Nested
    class CreateCatShould {

        @Test
        void createDummyUsingFormValue() {

            // Given
            CreateCatResponse createCatResponse = new CreateCatResponse();
            createCatResponse.setId(1L);
            createCatResponse.setValue("Cat 1");

            CreateCatRequest createCatRequest = new CreateCatRequest();
            createCatRequest.setCatOwnersId(1L);
            createCatRequest.setValue("Cat 1");

            // When
            when(createCatUseCase.execute(createCatRequest)).thenReturn(createCatResponse);
            CreateCatResponse result = createCatUseCase.execute(createCatRequest);

            // Then
            assertThat(result.getId()).isEqualTo(createCatResponse.getId());
            assertThat(result.getValue()).isEqualTo(createCatResponse.getValue());
        }
    }

    @Nested
    class UpdateCatShould {

        @Test
        void updateCat() {

            // Given
            UpdateCatResponse updateCatResponse = new UpdateCatResponse();
            updateCatResponse.setId(1L);
            updateCatResponse.setValue("New value");

            UpdateCatRequest updateCatRequest = new UpdateCatRequest();
            updateCatRequest.setId(1L);
            updateCatRequest.setCatOwnersId(1L);
            updateCatRequest.setValue("New value");

            // When
            when(updateCatUseCase.execute(updateCatRequest)).thenReturn(updateCatResponse);
            UpdateCatResponse result = updateCatUseCase.execute(updateCatRequest);

            // Then
            assertThat(result.getId()).isEqualTo(updateCatResponse.getId());
            assertThat(result.getValue()).isEqualTo(updateCatResponse.getValue());
        }
    }

    @Nested
    class DeleteCatShould {

        @Test
        void deleteCat() {

            // Given
            DeleteCatRequest deleteCatRequest = new DeleteCatRequest(1L);

            // When
            deleteCatUseCase.execute(deleteCatRequest);

            // Then
            verify(deleteCatUseCase, times(1)).execute(deleteCatRequest);
        }
    }


}