package name.pkrause.blueprint.usecases.cat;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.entities.CatRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCatUTest {

    private UpdateCat updateCatUseCase;

    @Mock
    private CatRepository catRepository;

    @BeforeEach
    void setUp() {
        updateCatUseCase = new UpdateCat(catRepository);
    }

    @Nested
    class UpdateShould {

        @Test
        void updateCat() {
            // Given
            Cat cat = new Cat("Cat1");
            cat.setId(1L);
            cat.setCatOwner(new CatOwner("Cat owner 1"));

            // When
            when(catRepository.save(cat)).thenReturn(cat);
            UpdateCatResponse result = updateCatUseCase.execute(new UpdateCatRequest(1L, "Cat1", 1L));

            // Then
            assertThat(cat.getId()).isEqualTo(result.getId());
            assertThat(cat.getValue()).isEqualTo(result.getValue());
        }
    }
}
