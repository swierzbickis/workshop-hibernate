package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    private Integer id;

    private float price;
    private String name;
    private Date orderDate;

    @ManyToMany(mappedBy = "orders") //Dostep do listy obiektow client z poziom encji order
    private List<Client> clients;

}
