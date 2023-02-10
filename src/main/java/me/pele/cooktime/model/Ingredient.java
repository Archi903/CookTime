package me.pele.cooktime.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private String name;
    private int amountOfIngredients;
    private String typeOfMeasure;

}
