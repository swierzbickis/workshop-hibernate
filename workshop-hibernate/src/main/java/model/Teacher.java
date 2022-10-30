package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@SecondaryTable(name = "Specialization")
@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(TeacherId.class)
public class Teacher {

    @Id
    private int documentId;

    @Id
    private String license;

    @Embedded
    private Person person;

    @Column(table = "Specialization")
    private String type;

}
