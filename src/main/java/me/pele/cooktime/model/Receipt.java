package me.pele.cooktime.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Receipt {

    private String name;
    private int cookingTime;
    private List<Ingredient> ingredient;
    private List<String> steps;

}
