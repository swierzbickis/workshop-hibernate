package hibernate.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "actors")
@ToString(exclude = "actors")
public class Movie extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors = new ArrayList<>();

    @ManyToOne
    private Genre genre;

}
