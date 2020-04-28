package name.pkrause.blueprint.usecases.cat.delete;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCatRequest {

    private Long id;

    public DeleteCatRequest() {
    }

    public DeleteCatRequest(Long id) {
        this.id = id;
    }
}
