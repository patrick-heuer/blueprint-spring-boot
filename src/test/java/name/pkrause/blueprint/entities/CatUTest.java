package name.pkrause.blueprint.entities;

import name.pkrause.blueprint.entities.CatInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**  
 * Unittests for cat entities
 * @author  Patrick Krause 
 */
@ExtendWith(MockitoExtension.class)
class CatUTest {

    private String value;

    @BeforeEach
    void setUp() {
        value = "some dummy value";
    }

    @Nested
    class ConstructorShould {

        @Test
        void failWhenValueIsNull() {
            // Given
            value = null;

            // When
            Throwable throwable = catchThrowable(() -> new Cat(value));

            // Then
            assertThat(throwable).isInstanceOf(CatInvalidException.class)
                    .hasMessage("value cannot be null or empty");
        }

        @Test
        void failWhenValueIsEmpty() {
            // Given
            value = "";

            // When
            Throwable throwable = catchThrowable(() -> new Cat(value));

            // Then
            assertThat(throwable).isInstanceOf(CatInvalidException.class)
                    .hasMessage("value cannot be null or empty");
        }
    }

}