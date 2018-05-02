package edu.uph.ii.platformy.config;


import edu.uph.ii.platformy.models.Category;
import edu.uph.ii.platformy.models.LevelOfDificulty;
import edu.uph.ii.platformy.models.Role;
import edu.uph.ii.platformy.models.User;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;


@Configuration
public class RepositoriesInitializer {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private LevelOfDificultyRepository levelOfDificultyRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private StepOfRecipeRepository stepOfRecipeRepository;


    @Bean
    InitializingBean init() {

        return () ->
        {

            if( levelOfDificultyRepository.findAll().isEmpty())
            {
                levelOfDificultyRepository.save(new LevelOfDificulty("Łatwe"));
                levelOfDificultyRepository.save(new LevelOfDificulty("Średnie"));
                levelOfDificultyRepository.save(new LevelOfDificulty("Trudne"));
            }
            if( categoryRepository.findAll().isEmpty())
            {
                categoryRepository.save(new Category("Ciasta"));
                categoryRepository.save(new Category("Lody"));
                categoryRepository.save(new Category("Napoje"));
                categoryRepository.save(new Category("Przekąski"));
                categoryRepository.save(new Category("Sosy"));
                categoryRepository.save(new Category("Dania główne"));
                categoryRepository.save(new Category("Zupy"));
                categoryRepository.save(new Category("Zapiekanki"));

            }

            if(roleRepository.findAll().isEmpty() == true){
                try {
                    Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                    Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));

                    User user = new User("user", true);
                    user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    user.setPassword(passwordEncoder.encode("user"));

                    User admin = new User("admin", true);
                    admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setPassword(passwordEncoder.encode("admin"));

                    User test = new User("test", true);
                    test.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleUser)));
                    test.setPassword(passwordEncoder.encode("test"));

                    userRepository.save(user);
                    userRepository.save(admin);
                    userRepository.save(test);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }


        };
    }

}
