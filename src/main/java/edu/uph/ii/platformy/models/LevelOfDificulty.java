package edu.uph.ii.platformy.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Entity
@Table(name = "levels_of_dificulty")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelOfDificulty
{
    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 30)
    private String name;

    public LevelOfDificulty(String name) {
        this.name = name;
    }
}
