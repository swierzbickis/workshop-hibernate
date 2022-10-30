package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specialization {

    @EmbeddedId
    private TeacherId id;

    private String type;

}
