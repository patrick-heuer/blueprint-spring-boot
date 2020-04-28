package name.pkrause.blueprint.usecases.catowner;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.entities.CatOwnerRepository;
import name.pkrause.blueprint.usecases.catowner.get.GetCatOwner;
import name.pkrause.blueprint.usecases.catowner.get.GetCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.get.GetCatOwnerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCatOwnerUTest {

    private GetCatOwner getCatOwnerUseCase;

    @Mock
    private CatOwnerRepository catOwnerRepository;

    @BeforeEach
    void setUp() {
        getCatOwnerUseCase = new GetCatOwner(catOwnerRepository);
    }

    @Nested
    class GetShould {

        @Test
        void getCatOwner() {
            // Given
            CatOwner catOwner = new CatOwner("Cat Owner 1", new Cat("Cat 1"));
            catOwner.setId(1L);

            // When
            when(catOwnerRepository.findById(1L)).thenReturn(catOwner);
            GetCatOwnerResponse result = getCatOwnerUseCase.execute(new GetCatOwnerRequest(1L));

            // Then
            assertThat(catOwner.getId()).isEqualTo(result.getId());
            assertThat(catOwner.getName()).isEqualTo(result.getName());
        }
    }
}
