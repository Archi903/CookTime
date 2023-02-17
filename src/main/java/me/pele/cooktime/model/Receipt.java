package me.pele.cooktime.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {

    private String name;
    private int cookingTime;
    private List<Ingredient> ingredient;
    private List<String> steps;

    @Override
    public String toString() {
        return "Название рецепта - " + name + '\'' +
                ", время приготовления - " + cookingTime+ '\'' +  ingredient + '\'' +
                ", шаги - " + steps +
                '}';
    }
}
