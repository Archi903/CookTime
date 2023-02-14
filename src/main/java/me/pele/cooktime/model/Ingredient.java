package me.pele.cooktime.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private String name;
    private Integer amountOfIngredients;
    private String typeOfMeasure;

}
