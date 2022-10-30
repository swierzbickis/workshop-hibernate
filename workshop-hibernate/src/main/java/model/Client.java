package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "clients")
public class Client {

    @Id
    private Integer id;

    private String firstName;
    private String lastName;

    @ManyToMany //Dostep do listy obiektow orders z poziom encji client
    @JoinTable(
            name = "client_order_data",
            joinColumns = @JoinColumn(name = "clients_id"), //podajemy nazwe kolumny po stronie wlasiciela relacji (wlascicielem jest pole znajdujace sie pod anotacja mappedBy
            inverseJoinColumns = @JoinColumn(name = "orders_id")) //podajemy nazwe kolumny po stronie nie-wlasiciela relacji
    private List<Order> orders;

}
