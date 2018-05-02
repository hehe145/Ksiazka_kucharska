package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.StepOfRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepOfRecipeRepository extends JpaRepository<StepOfRecipe,Long>
{
    List<StepOfRecipe> findAllByRecipeId(long id);
    List<StepOfRecipe> findAllByRecipeIdOrderByIdAsc(long id);
    void deleteAllByRecipeId(long id);
}
