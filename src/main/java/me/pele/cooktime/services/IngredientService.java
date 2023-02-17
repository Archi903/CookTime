package me.pele.cooktime.services;

import me.pele.cooktime.model.Ingredient;

import java.util.Map;
import java.util.Optional;

public interface IngredientService {

    Ingredient save(Ingredient ingredient);

    Optional<Ingredient> getById(Long id);

    Ingredient editIngredient(Long id, Ingredient ingredient);

    Ingredient deleteIngredient(Long id);

    Map<Long, Ingredient> getAll();
}
