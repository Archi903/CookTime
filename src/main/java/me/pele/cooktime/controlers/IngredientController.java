package me.pele.cooktime.controlers;

import me.pele.cooktime.model.Ingredient;
import me.pele.cooktime.services.Impl.IngredientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientServiceImpl ingredientService;


    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }


    @PostMapping
    public ResponseEntity<Ingredient> save(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.save(ingredient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@PathVariable Long id) {
        return ResponseEntity.of(ingredientService.getById(id));
    }

    @PutMapping ("/{id}")
    ResponseEntity <Ingredient> editIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient){
        return ResponseEntity.ok(ingredientService.editIngredient(id, ingredient));
    }
    @DeleteMapping ("/{id}")
    ResponseEntity <Ingredient> deleteIngredient(@PathVariable Long id){
        return ResponseEntity.ok(ingredientService.deleteIngredient(id));
    }

    @GetMapping
    ResponseEntity < Map <Long, Ingredient>> getAll(){
        return ResponseEntity.ok(ingredientService.getAll());
    }
}

