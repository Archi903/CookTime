package me.pele.cooktime.services;

import me.pele.cooktime.model.Ingredient;

import java.util.Optional;

public interface IngredientService {

    Ingredient save(Ingredient ingredient);

    Optional<Ingredient> getById(Long id);

}
