package me.pele.cooktime.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private String name;
    private Integer amountOfIngredients;
    private String typeOfMeasure;

    @Override
    public String toString() {
        return "Название ингредиента - " + name + '\'' +
                ", количество - " + amountOfIngredients +
                ", единица измерения - " + typeOfMeasure;
    }
}
