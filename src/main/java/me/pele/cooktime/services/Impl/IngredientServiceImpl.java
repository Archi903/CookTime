package me.pele.cooktime.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.pele.cooktime.exception.ValidationException;
import me.pele.cooktime.model.Ingredient;
import me.pele.cooktime.services.FilesService;
import me.pele.cooktime.services.IngredientService;
import me.pele.cooktime.services.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    final private FilesService filesService;
    private Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private static long idCount = 1;
    private final ValidationService validationService;

    public IngredientServiceImpl(FilesService filesService, ValidationService validationService) {
        this.filesService = filesService;
        this.validationService = validationService;
    }


    @PostConstruct
    private void init(){
        readFromFile();
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        saveToFile();
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
        saveToFile();
        return ingredientMap.replace(id, ingredient);
    }

    @Override
    public Ingredient deleteIngredient(Long id) {
        saveToFile();
        return ingredientMap.remove(id);
    }
    @Override
    public Map <Long, Ingredient> getAll(){
        return ingredientMap;
    }

    private void saveToFile(){
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    private void readFromFile(){
        try {
            String json = filesService.readFromFile();
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}


