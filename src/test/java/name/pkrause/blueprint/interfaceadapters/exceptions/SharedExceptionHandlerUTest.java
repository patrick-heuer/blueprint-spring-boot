package name.pkrause.blueprint.interfaceadapters.exceptions;

import name.pkrause.blueprint.entities.Cat;
import name.pkrause.blueprint.entities.CatInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SharedExceptionHandlerUTest {
    private SharedExceptionHandler sharedExceptionHandler;

    @BeforeEach
    void setUp() {
        sharedExceptionHandler = new SharedExceptionHandler();
    }

    @Test
    void returnResponseEntityWithExceptionMessageForBadRequestForCatControllerInvalidException() {
        // Given
        CatControllerInvalidException exception = new CatControllerInvalidException("Cat Controller Invalid Exception");

        // When
        ResponseEntity<ErrorResponse> responseEntity = sharedExceptionHandler.handleBadRequestExceptions(exception);

        // Then
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Cat Controller Invalid Exception");
    }

    @Test
    void returnResponseEntityWithExceptionMessageForBadRequestForCatInvalidException() {
        // Given
        CatInvalidException exception = new CatInvalidException("Cat Invalid Exception");

        // When
        ResponseEntity<ErrorResponse> responseEntity = sharedExceptionHandler.handleBadRequestExceptions(exception);

        // Then
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Cat Invalid Exception");
    }

    @Test
    void returnResponseEntityForHandleException() throws Exception {
        // Given
        Method method = Cat.class.getMethod("setId", Long.class);
        WebDataBinder binder = new WebDataBinder(method, "method");
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(new MethodParameter(method, 0),
                binder.getBindingResult());

        // When
        ResponseEntity<ErrorResponse> responseEntity = sharedExceptionHandler.handleException(exception);

        // Then
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(responseEntity.getBody().getMessage()).isNotEmpty();
    }
}
