package model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ComputerGame extends BaseEntity {

    private String name;
    private String description;
    private String type;

}
