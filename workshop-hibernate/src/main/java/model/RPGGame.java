package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RPGGame extends ComputerGame {

    @NonNull
    private String worldName;

}
