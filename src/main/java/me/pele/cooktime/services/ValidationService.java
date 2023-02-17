package me.pele.cooktime.services;

import me.pele.cooktime.model.Ingredient;
import me.pele.cooktime.model.Receipt;

public interface ValidationService {

    boolean validate (Receipt receipt);
    boolean validate (Ingredient ingredient);
}
