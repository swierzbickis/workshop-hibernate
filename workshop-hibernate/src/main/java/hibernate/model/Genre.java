package hibernate.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "movies")
@ToString(exclude = "movies")
public class Genre extends BaseEntity {

    private String name;

    @OneToMany
    @JoinColumn(name = "genre_id")
    private List<Movie> movies;

}
