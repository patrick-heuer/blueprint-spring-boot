package name.pkrause.blueprint.usecases.cat.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCatResponse {

    private Long id;
    private String value;

    public UpdateCatResponse() {
    }
}
