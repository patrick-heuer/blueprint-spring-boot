package name.pkrause.blueprint.usecases.catowner;

import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.entities.CatOwnerRepository;
import name.pkrause.blueprint.entities.CatRepository;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwner;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.update.UpdateCatOwnerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCatOwnerUTest {

    private UpdateCatOwner updateCatOwnerUseCase;

    @Mock
    private CatOwnerRepository catOwnerRepository;

    @Mock
    private CatRepository catRepository;

    @BeforeEach
    void setUp() {
        updateCatOwnerUseCase = new UpdateCatOwner(catOwnerRepository, catRepository);
    }

    @Nested
    class UpdateShould {

        @Test
        void updateCatOwner() {
            // Given
            CatOwner catOwner = new CatOwner("Cat owner 1");
            catOwner.setId(1L);
            catOwner.setCats(null);

            // When
            when(catOwnerRepository.save(catOwner)).thenReturn(catOwner);

            UpdateCatOwnerRequest request = new UpdateCatOwnerRequest();
            request.setId(1L);
            request.setName("Cat owner 1");
            request.setCatIds(null);

            UpdateCatOwnerResponse result = updateCatOwnerUseCase.execute(request);

            // Then
            assertThat(catOwner.getId()).isEqualTo(result.getId());
            assertThat(catOwner.getName()).isEqualTo(result.getName());
        }
    }
}
