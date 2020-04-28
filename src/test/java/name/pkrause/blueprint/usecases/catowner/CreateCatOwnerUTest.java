package name.pkrause.blueprint.usecases.catowner;

import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.entities.CatOwnerRepository;
import name.pkrause.blueprint.entities.CatRepository;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwner;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwnerRequest;
import name.pkrause.blueprint.usecases.catowner.create.CreateCatOwnerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCatOwnerUTest {

    private CreateCatOwner createCatUseCase;

    @Mock
    private CatOwnerRepository catOwnerRepository;

    @Mock
    private CatRepository catRepository;

    @BeforeEach
    void setUp() {
        createCatUseCase = new CreateCatOwner(catOwnerRepository, catRepository);
    }

    @Nested
    class CreateShould {

        @Test
        void returnSaveAndReturnSavedDummyData() {
            // Given

            CatOwner catOwner = new CatOwner("some dummy value");
            when(catOwnerRepository.save(catOwner)).thenReturn(catOwner);

            // When
            CreateCatOwnerResponse result = createCatUseCase.execute(new CreateCatOwnerRequest("some dummy value"));

            // Then
            assertThat(result.getId()).isEqualTo(catOwner.getId());
            assertThat(result.getName()).isEqualTo(catOwner.getName());

        }
    }

}