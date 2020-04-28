package name.pkrause.blueprint.interfaceadapters.database.repositories;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.PageResult;
import name.pkrause.blueprint.entities.CatRepository;
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

/**  
 * unit tests for database JPA repository
 * @author  Patrick Krause 
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class CatJpaRepositoryITest {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Cat cat;

    @BeforeEach
    void setUp() {
        cat = new Cat("Cat");
    }

    @Test
    void saveShouldPersistCatWithAutoIncrementedId() {

        // Given
        Cat secondCat = new Cat("secondDummy");
        Cat firstPersist = catRepository.save(cat);

        // When
        Cat secondPersist = catRepository.save(secondCat);

        // Then
        assertThat(secondPersist.getId()).isEqualTo(firstPersist.getId() + 1);
    }

    @Test
    void saveShouldThrowDataIntegrityViolationExceptionWhenValueIsNull() {

        // Given
        cat.setValue(null);

        // When
        Throwable throwable = catchThrowable(() -> catRepository.save(cat));

        // Then
        assertThat(throwable).isInstanceOf(DataIntegrityViolationException.class);
        assertThat(throwable).hasStackTraceContaining("value");
    }

    @Test
    void findAllByUserIdShouldReturnAllCats() {
        // Given
        Cat secondCat = new Cat("secondDummy");
        testEntityManager.persist(cat);
        testEntityManager.persist(secondCat);

        // When
        List<Cat> found = catRepository.findAll();

        // Then
        assertThat(found).contains(cat, secondCat);
    }

    @Test
    void findAllWithPaging() {
        // Given
        Cat secondCat = new Cat("secondDummy");
        testEntityManager.persist(cat);
        testEntityManager.persist(secondCat);
        List<Cat> cats = List.of(cat, secondCat);

        // When
        PageResult<Cat> pageResult = catRepository.findAll(0, 5);

        // Then
        assertThat(pageResult.getPageNumber()).isEqualTo(0);
        assertThat(pageResult.getPageSize()).isEqualTo(5);
        assertThat(pageResult.getOffset()).isEqualTo(0);
        assertThat(pageResult.getElements()).contains(cat, secondCat);
        assertThat(pageResult.getTotalElements()).isNotNull();
        assertThat(pageResult.getTotalPages()).isEqualTo(1);
    }

}
