package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipeRepository extends JpaRepository<Recipe,Long>
{
    Recipe findAllById(long id);
    Recipe getRecipeById(long id);
    void deleteById(long did);


    @Query("SELECT r FROM Recipe r WHERE" +
            "(:phrase is null OR :phrase = '' OR   upper(r.name) LIKE upper(:phrase)) AND " +
            "(:levelOfDificulty = 0 OR :levelOfDificulty = r.levelOfDificulty) AND " +
            "(:category = 0 OR :category = r.category) ")
    Page<Recipe> findAllByPhrase(@Param("phrase") String p,@Param("category") int c, @Param("levelOfDificulty")int l, Pageable pageable);

}
