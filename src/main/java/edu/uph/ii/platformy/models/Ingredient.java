package edu.uph.ii.platformy.models;


import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 50)
    private String name;

    @Size(max = 30)
    private String measure;

    @ManyToOne
    private Recipe recipe;

    int quantity;

    public Ingredient(@Size(max = 50) String name, @Size(max = 30) String measure, int quantity) {
        this.name = name;
        this.measure = measure;
        this.quantity = quantity;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
