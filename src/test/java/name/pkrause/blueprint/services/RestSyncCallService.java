package name.pkrause.blueprint.services;

import name.pkrause.blueprint.usecases.cat.get.GetCatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestSyncCallService {

    @Autowired
    private RestTemplate restTemplate;

    public GetCatResponse getCat(Long id) {
        ResponseEntity<GetCatResponse> resp = restTemplate.getForEntity("http://localhost:8080/api/v2/cats/" + id,
                GetCatResponse.class);
        return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : null;
    }
}
