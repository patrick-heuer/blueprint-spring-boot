package name.pkrause.blueprint.usecases.cat;

import name.pkrause.blueprint.entities.CatRepository;
import name.pkrause.blueprint.usecases.cat.delete.DeleteCat;
import name.pkrause.blueprint.usecases.cat.delete.DeleteCatRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteCatUTest {

    private DeleteCat deleteCatUseCase;

    @Mock
    private CatRepository catRepository;

    @BeforeEach
    void setUp() {
        deleteCatUseCase = new DeleteCat(catRepository);
    }

    @Nested
    class DeleteShould {

        @Test
        void deleteCat() {
            // Given

            // When
            deleteCatUseCase.execute(new DeleteCatRequest(1L));

            // Then
            verify(catRepository, times(1)).deleteById(1L);
        }
    }
}
