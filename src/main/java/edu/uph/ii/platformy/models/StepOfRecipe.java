package edu.uph.ii.platformy.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "stepsOfRecipe")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepOfRecipe
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 500)
    private String content;

    @ManyToOne
    private Recipe recipe;

    public StepOfRecipe(@Size(max = 200) String content) {
        this.content = content;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
