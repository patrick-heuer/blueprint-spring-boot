package name.pkrause.blueprint.usecases.catowner;

import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.entities.PageResult;
import name.pkrause.blueprint.entities.CatOwnerRepository;
import name.pkrause.blueprint.usecases.catowner.getowners.GetCatOwners;
import name.pkrause.blueprint.usecases.catowner.getowners.GetCatOwnersRequest;
import name.pkrause.blueprint.usecases.catowner.getowners.GetCatOwnersResponse;
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

@ExtendWith(MockitoExtension.class)
class GetAllCatOwnersUTest {

    private GetCatOwners getCatOwnersUseCase;

    @Mock
    private CatOwnerRepository catOwnerRepository;

    @BeforeEach
    void setUp() {
        getCatOwnersUseCase = new GetCatOwners(catOwnerRepository);
    }

    @Nested
    class GetAllShould {

        @Test
        void returnAllDummyData() {
            // Given
            List<CatOwner> dummyData = List.of(new CatOwner("Cat owner 1"), new CatOwner("Cat owner 2"));

            // When
            when(catOwnerRepository.findAll()).thenReturn(dummyData);
            GetCatOwnersResponse executeResult = getCatOwnersUseCase.execute(null);
            List<CatOwner> result = new ArrayList<>();
            executeResult.getCatOwners().forEach(element -> result.add(new CatOwner(element.getName())));

            // Then
            assertThat(result).isEqualTo(dummyData);
        }

        @Test
        void returnPagedDummyData() {
            // Given
            List<CatOwner> dummyData = List.of(new CatOwner("Cat owner 1"), new CatOwner("Cat owner 2"));
            PageResult<CatOwner> catOwnerPageResult = new PageResult<>();
            catOwnerPageResult.setElements(dummyData);

            // When
            when(catOwnerRepository.findAll(0, 2)).thenReturn(catOwnerPageResult);
            GetCatOwnersResponse executeResult = getCatOwnersUseCase.execute(new GetCatOwnersRequest(0, 2));
            List<CatOwner> result = new ArrayList<>();
            executeResult.getCatOwners().forEach(element -> result.add(new CatOwner(element.getName())));

            // Then
            assertThat(result).isEqualTo(dummyData);
        }
    }

}