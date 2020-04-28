package name.pkrause.blueprint.usecases.cat;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatRepository;
import name.pkrause.blueprint.usecases.cat.get.GetCat;
import name.pkrause.blueprint.usecases.cat.get.GetCatRequest;
import name.pkrause.blueprint.usecases.cat.get.GetCatResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCatUTest {

    private GetCat getCatUseCase;

    @Mock
    private CatRepository catRepository;

    @BeforeEach
    void setUp() {
        getCatUseCase = new GetCat(catRepository);
    }

    @Nested
    class GetShould {

        @Test
        void getCat() {
            // Given
            Cat cat = new Cat("Cat 1");
            cat.setId(1L);

            // When
            when(catRepository.findById(1L)).thenReturn(cat);
            GetCatResponse result = getCatUseCase.execute(new GetCatRequest(1L));

            // Then
            assertThat(cat.getId()).isEqualTo(result.getId());
            assertThat(cat.getValue()).isEqualTo(result.getValue());
        }
    }
}
