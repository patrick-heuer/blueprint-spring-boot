package name.pkrause.blueprint.usecases.cat;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatRepository;
import name.pkrause.blueprint.usecases.cat.create.CreateCat;
import name.pkrause.blueprint.usecases.cat.create.CreateCatRequest;
import name.pkrause.blueprint.usecases.cat.create.CreateCatResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**  
 * unit tests for the cat create use case
 * @author  Patrick Krause 
 */
@ExtendWith(MockitoExtension.class)
class CreateCatUTest {

    private CreateCat createCatUseCase;

    @Mock
    private CatRepository catRepository;

    @BeforeEach
    void setUp() {
        createCatUseCase = new CreateCat(catRepository);
    }

    @Nested
    class CreateShould {

        @Test
        void returnSaveAndReturnSavedDummyData() {
            // Given

            Cat cat = new Cat("some dummy value");
            when(catRepository.save(cat)).thenReturn(cat);

            // When
            CreateCatResponse result = createCatUseCase.execute(new CreateCatRequest("some dummy value"));

            // Then
            assertThat(result.getId()).isEqualTo(cat.getId());
            assertThat(result.getValue()).isEqualTo(cat.getValue());

        }
    }

}