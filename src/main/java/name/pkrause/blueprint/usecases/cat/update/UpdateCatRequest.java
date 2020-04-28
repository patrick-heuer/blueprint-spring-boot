package name.pkrause.blueprint.usecases.cat.update;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UpdateCatRequest {

    @JsonIgnore
    private Long id;

    @NotEmpty(
            message = "Parameter 'value' cannot be null or empty"
    )
    private String value;

    private Long catOwnersId;

    private boolean deleteCatOwner = false;

    // Default constructor needed for deserialazion of JSON
    public UpdateCatRequest() {
    }

    public UpdateCatRequest(String value) {
        this.value = value;
    }

    public UpdateCatRequest(Long id, String value, Long catOwnersId) {
        this.id = id;
        this.value = value;
        this.catOwnersId = catOwnersId;
    }
}
