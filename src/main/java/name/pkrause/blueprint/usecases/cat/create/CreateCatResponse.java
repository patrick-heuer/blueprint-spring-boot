
package name.pkrause.blueprint.usecases.cat.create;

import lombok.Getter;
import lombok.Setter;

/**  
 * DTO for crossing borders and for different versions in controller (never use an entity directly)
 * @author  Patrick Krause 
 */
@Getter
@Setter
public class CreateCatResponse {

    private Long id;
    private String value;

    public CreateCatResponse() {
    }
}
