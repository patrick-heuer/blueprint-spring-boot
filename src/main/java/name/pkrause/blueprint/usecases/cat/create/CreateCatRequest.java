package name.pkrause.blueprint.usecases.cat.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**  
 * DTO for crossing borders and for different versions in controller (never use an entity directly)
 * @author  Patrick Krause 
 */
@Getter
@Setter
public class CreateCatRequest {

    @NotEmpty(
            message = "Parameter 'value' cannot be null or empty"
    )
    private String value;

    private Long catOwnersId;

    // Default constructor needed for deserialazion of JSON
    public CreateCatRequest() {
    }

    public CreateCatRequest(String name) {
        this.value = name;
    }

    public CreateCatRequest(String value, Long catOwnersId) {
        this.value = value;
        this.catOwnersId = catOwnersId;
    }
}
