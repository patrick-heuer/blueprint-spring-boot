package name.pkrause.blueprint.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name="CatOwner.findByName",
                query="SELECT c FROM CatOwner c where c.name = :name")
})
@Table(name = "cat_owner")
public class CatOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 255)
    private String name;

    @OneToMany(mappedBy = "catOwner")
    @JsonBackReference
    private Set<Cat> cats = new HashSet<>();

    public void addCat(Cat cat) {
        this.cats.add(cat);
        cat.setCatOwner(this);
    }

    public void removeCats() {
        for (Cat cat : this.cats) {
            cat.setCatOwner(null);
        }
    }

    public CatOwner() {
    }

    public CatOwner(String name) {
        this.name = name;
    }

    public CatOwner(String name, Cat... cats) {
        this.name = name;
        this.cats = Stream.of(cats).collect(Collectors.toSet());
        this.cats.forEach(x -> x.setCatOwner(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatOwner catOwner = (CatOwner) o;
        return Objects.equals(id, catOwner.id) &&
                Objects.equals(name, catOwner.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
