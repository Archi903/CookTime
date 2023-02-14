package me.pele.cooktime.services.Impl;

import me.pele.cooktime.exception.ValidationException;
import me.pele.cooktime.model.Ingredient;
import me.pele.cooktime.services.IngredientService;
import me.pele.cooktime.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private static long idCount = 1;
    private final ValidationService validationService;

    public IngredientServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }


    @Override
    public Ingredient save(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        return ingredientMap.put(idCount++, ingredient);
    }

    @Override
    public Optional<Ingredient> getById(Long id) {
        return Optional.ofNullable(ingredientMap.get(id));
    }

    @Override
    public Ingredient editIngredient(Long id, Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        return ingredientMap.replace(id, ingredient);
    }

    @Override
    public Ingredient deleteIngredient(Long id) {
        return ingredientMap.remove(id);
    }
    @Override
    public Map <Long, Ingredient> getAll(){
        return ingredientMap;
    }
}


