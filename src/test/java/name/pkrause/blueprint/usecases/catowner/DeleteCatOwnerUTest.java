package name.pkrause.blueprint.usecases.catowner;

import name.pkrause.blueprint.entities.CatOwnerRepository;
import name.pkrause.blueprint.usecases.catowner.delete.DeleteCatOwner;
import name.pkrause.blueprint.usecases.catowner.delete.DeleteCatOwnerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteCatOwnerUTest {

    private DeleteCatOwner deleteCatOwnerUseCase;

    @Mock
    private CatOwnerRepository catOwnerRepository;

    @BeforeEach
    void setUp() {
        deleteCatOwnerUseCase = new DeleteCatOwner(catOwnerRepository);
    }

    @Nested
    class DeleteShould {

        @Test
        void deleteCat() {
            // Given

            // When
            deleteCatOwnerUseCase.execute(new DeleteCatOwnerRequest(1L));

            // Then
            verify(catOwnerRepository, times(1)).deleteById(1L);
        }
    }
}
