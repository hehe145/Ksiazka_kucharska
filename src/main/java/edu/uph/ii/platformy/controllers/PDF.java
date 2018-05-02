package edu.uph.ii.platformy.controllers;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.uph.ii.platformy.models.Ingredient;
import edu.uph.ii.platformy.models.Recipe;
import edu.uph.ii.platformy.models.StepOfRecipe;
import edu.uph.ii.platformy.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class PDF
{
    @Autowired
    private RecipeService recipeService;
    Recipe recipe;
    List<Ingredient> ingredientList;
    List<StepOfRecipe> stepOfRecipeList;
    long numer;
    @RequestMapping(path = "/recipe/pdf", params = "id", method = RequestMethod.GET)
    public String getPdf(Model model, long id) throws DocumentException
    {
        numer = id;
        ingredientList = recipeService.findAllIngredientsByRecipeId(id);
        stepOfRecipeList = recipeService.findAllStepsofRecipeByRecipeId(id);
        Recipe recipe = recipeService.getRecipe(id);

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Skladniki.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER,"UTF-8", 16);


        PdfPTable table = new PdfPTable(3);

        addTableHeader(table);
        addRows(table);

        document.add(new Paragraph(recipe.getName(),font));
        document.add(new Paragraph("Lista składników: ", FontFactory.getFont(FontFactory.COURIER_BOLD,"UTF-8", 16)));
        document.add(new Paragraph(" "));
        document.add(table);
        document.add(new Paragraph("Przygotowanie:",font));

        for(int i = 0; i < stepOfRecipeList.size(); i++)
        {
            document.add(new Paragraph("Krok " + (i+1), FontFactory.getFont(FontFactory.COURIER_BOLD,"UTF-8", 16)));
            document.add(new Paragraph(stepOfRecipeList.get(i).getContent(),font));
        }

        document.close();



        return "redirect:/recipes";
    }

    private void addRows(PdfPTable table)
    {

        for(int i =0; i < ingredientList.size(); i++)
        {
            table.addCell(ingredientList.get(i).getName());
            table.addCell(ingredientList.get(i).getMeasure());
            table.addCell(Integer.toString( ingredientList.get(i).getQuantity()));
        }
    }

    private void addTableHeader(PdfPTable table)
    {
        Stream.of("Składnik", "Miara", "Ilość")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
}
