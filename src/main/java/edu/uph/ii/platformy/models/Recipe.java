package edu.uph.ii.platformy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private LevelOfDificulty levelOfDificulty;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    private String plik;

    private int punkty =0;

    @Transient
    private List<Ingredient> ingredientList;


    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public List<StepOfRecipe> getListOfSteps() {
        return listOfSteps;
    }

    @Transient
    private List<StepOfRecipe> listOfSteps;

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void setStepOfRecipeList(List<StepOfRecipe> stepOfRecipeList) {
        this.listOfSteps = stepOfRecipeList;
    }
}
