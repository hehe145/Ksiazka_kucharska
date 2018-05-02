package edu.uph.ii.platformy.controllers.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
public class RecipeFilter {

    private String phrase;
    private int category;
    private int levelOfDificulty;

    public RecipeFilter(String phrase, int category, int levelOfDificulty) {
        this.phrase = phrase;
        this.category = category;
        this.levelOfDificulty = levelOfDificulty;
    }


    public boolean isEmpty(){
        return StringUtils.isEmpty(phrase) && (category == 0) && (levelOfDificulty == 0);
    }

    public void clear(){
        this.phrase = "";
        this.category = 0;
        this.levelOfDificulty = 0;
    }

    public String getPhraseLIKE(){
        if(StringUtils.isEmpty(phrase)) {
            return null;
        }else{
            return "%"+phrase+"%";
        }
    }



}
