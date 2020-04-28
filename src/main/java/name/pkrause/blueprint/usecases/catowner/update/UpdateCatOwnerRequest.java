package name.pkrause.blueprint.usecases.catowner.update;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UpdateCatOwnerRequest {

    @JsonIgnore
    private Long id;

    @NotEmpty(
            message = "Parameter 'name' cannot be null or empty"
    )
    private String name;

    private long[] catIds;

    private boolean deleteCats = false;

    // Default constructor needed for deserialazion of JSON
    public UpdateCatOwnerRequest() {
    }
}
