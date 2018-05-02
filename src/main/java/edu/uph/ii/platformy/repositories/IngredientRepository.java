package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Long>
{

    List<Ingredient> findAllByRecipeId(long id);
    void deleteAllByRecipeId(long id);

}
