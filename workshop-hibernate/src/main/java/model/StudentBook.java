package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "student_books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentBook {

    @Id
    private Integer id;

    private String number;

    @OneToOne(mappedBy = "studentBook")
    private Student student;

}
