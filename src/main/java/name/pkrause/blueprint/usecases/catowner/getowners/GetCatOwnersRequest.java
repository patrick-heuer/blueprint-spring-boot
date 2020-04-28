package name.pkrause.blueprint.usecases.catowner.getowners;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCatOwnersRequest {

    private int offset;
    private int limit;

    public GetCatOwnersRequest() {
    }

    public GetCatOwnersRequest(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }
}
