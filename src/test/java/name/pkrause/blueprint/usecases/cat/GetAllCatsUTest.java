package name.pkrause.blueprint.usecases.cat;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatRepository;
import name.pkrause.blueprint.entities.PageResult;
import name.pkrause.blueprint.usecases.cat.getcats.GetCats;
import name.pkrause.blueprint.usecases.cat.getcats.GetCatsRequest;
import name.pkrause.blueprint.usecases.cat.getcats.GetCatsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

/**  
 * unit tests for the get all cat use case
 * @author  Patrick Krause 
 */
@ExtendWith(MockitoExtension.class)
class GetAllCatsUTest {

    private GetCats getAllCatsUseCase;

    @Mock
    private CatRepository catRepository;

    @BeforeEach
    void setUp() {
        getAllCatsUseCase = new GetCats(catRepository);
    }

    @Nested
    class GetAllShould {

        @Test
        void returnAllDummyData() {
            // Given
            List<Cat> dummyData = List.of(new Cat("Cat 1"), new Cat("Cat 2"));

            // When
            when(catRepository.findAll()).thenReturn(dummyData);
            GetCatsResponse executeResult = getAllCatsUseCase.execute(null);
            List<Cat> result = new ArrayList<>();
            executeResult.getCats().forEach(element -> result.add(new Cat(element.getValue())));

            // Then
            assertThat(result).isEqualTo(dummyData);
        }

        @Test
        void returnPagedDummyData() {
            // Given
            List<Cat> dummyData = List.of(new Cat("Cat 1"), new Cat("Cat 2"));
            PageResult<Cat> catPageResult = new PageResult<>();
            catPageResult.setElements(dummyData);

            // When
            when(catRepository.findAll(0, 2)).thenReturn(catPageResult);
            GetCatsResponse executeResult = getAllCatsUseCase.execute(new GetCatsRequest(0, 2));
            List<Cat> result = new ArrayList<>();
            executeResult.getCats().forEach(element -> result.add(new Cat(element.getValue())));

            // Then
            assertThat(result).isEqualTo(dummyData);
        }
    }

}