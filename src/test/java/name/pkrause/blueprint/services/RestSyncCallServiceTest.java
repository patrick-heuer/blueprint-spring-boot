package name.pkrause.blueprint.services;

import name.pkrause.blueprint.usecases.cat.get.GetCatResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class RestSyncCallServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RestSyncCallService restSyncCallService = new RestSyncCallService();

    @Test
    public void callMethod() {
        GetCatResponse getCatResponse = new GetCatResponse();
        getCatResponse.setId(1L);
        getCatResponse.setValue("Cat 1");
        getCatResponse.setCatOwner(null);

        Mockito
                .when(restTemplate.getForEntity("http://localhost:8080/api/v2/cats/1", GetCatResponse.class))
                .thenReturn(new ResponseEntity(getCatResponse, HttpStatus.OK));

        GetCatResponse response = restSyncCallService.getCat(1L);
        Assert.assertEquals(getCatResponse, response);
    }
}
