package name.pkrause.blueprint.usecases.cat.getcats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCatsRequest {

    private int offset;
    private int limit;

    public GetCatsRequest() {
    }

    public GetCatsRequest(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }
}
