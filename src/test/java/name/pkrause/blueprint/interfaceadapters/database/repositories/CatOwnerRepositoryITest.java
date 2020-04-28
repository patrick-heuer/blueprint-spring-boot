package name.pkrause.blueprint.interfaceadapters.database.repositories;

import name.pkrause.blueprint.entities.CatOwner;
import name.pkrause.blueprint.entities.PageResult;
import name.pkrause.blueprint.entities.CatOwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CatOwnerRepositoryITest {
    @Autowired
    private CatOwnerRepository catOwnerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private CatOwner catOwner;

    @BeforeEach
    void setUp() {
        catOwner = new CatOwner("Cat Owner");
    }

    @Test
    void saveShouldPersistCatOwnerWithAutoIncrementedId() {
        // Given
        CatOwner secondCatOwner = new CatOwner("Second Cat Owner");
        CatOwner firstPersist = catOwnerRepository.save(catOwner);

        // When
        CatOwner secondPersist = catOwnerRepository.save(secondCatOwner);

        // Then
        assertThat(secondPersist.getId()).isEqualTo(firstPersist.getId() + 1);
    }

    @Test
    void saveShouldThrowDataIntegrityViolationExceptionWhenValueIsNull() {
        // Given
        catOwner.setName(null);

        // When
        Throwable throwable = catchThrowable(() -> catOwnerRepository.save(catOwner));

        // Then
        assertThat(throwable).isInstanceOf(DataIntegrityViolationException.class);
        assertThat(throwable).hasStackTraceContaining("value");
    }

    @Test
    void findAllByUserIdShouldReturnAllCats() {
        // Given
        CatOwner secondCatOwner = new CatOwner("Second Cat Owner");
        testEntityManager.persist(catOwner);
        testEntityManager.persist(secondCatOwner);

        // When
        List<CatOwner> found = catOwnerRepository.findAll();

        // Then
        assertThat(found).contains(catOwner, secondCatOwner);
    }

    @Test
    void findAllWithPaging() {
        // Given
        CatOwner secondCatOwner = new CatOwner("Second Cat Owner");
        testEntityManager.persist(catOwner);
        testEntityManager.persist(secondCatOwner);
        List<CatOwner> catOwners = List.of(catOwner, secondCatOwner);

        // When
        PageResult<CatOwner> pageResult = catOwnerRepository.findAll(0, 5);

        //Then
        assertThat(pageResult.getPageNumber()).isEqualTo(0);
        assertThat(pageResult.getPageSize()).isEqualTo(5);
        assertThat(pageResult.getOffset()).isEqualTo(0);
        assertThat(pageResult.getElements()).contains(catOwner, secondCatOwner);
        assertThat(pageResult.getTotalElements()).isNotNull();
        assertThat(pageResult.getTotalPages()).isEqualTo(1);
    }
}
