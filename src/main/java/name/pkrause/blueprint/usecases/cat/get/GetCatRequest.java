package name.pkrause.blueprint.usecases.cat.get;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCatRequest {

    private Long id;

    public GetCatRequest() {
    }

    public GetCatRequest(Long id) {
        this.id = id;
    }
}
