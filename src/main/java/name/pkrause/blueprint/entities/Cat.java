package name.pkrause.blueprint.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
// import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Objects;

/**  
 * demo entity "Cat"
 * @author Patrick Krause 
 */
@Getter
@Setter
@Entity
@Table(name = "cat")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value", length = 255)
    private String value;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "owner")
    private CatOwner catOwner;

    public Cat() {
        // Default constructor is required for JPA
    }

    public Cat(String value) {
        if(value==null || value.isEmpty()){
            throw new CatInvalidException("value cannot be null or empty");
        }
        this.value = value;
    }

    public Cat(String value, CatOwner catOwner) {
        if(value==null || value.isEmpty()){
            throw new CatInvalidException("value cannot be null or empty");
        }
        this.catOwner = catOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id) &&
                Objects.equals(value, cat.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}