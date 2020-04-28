package name.pkrause.blueprint.usecases.shared.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IsLongValidator.class)
public @interface IsLong {
    String message() default "Value is not a Long type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
