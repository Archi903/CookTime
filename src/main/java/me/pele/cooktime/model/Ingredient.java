package me.pele.cooktime.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private String name;
    private Integer amountOfIngredients;
    private String typeOfMeasure;

}
