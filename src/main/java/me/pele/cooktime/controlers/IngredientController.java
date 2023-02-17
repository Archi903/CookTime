package me.pele.cooktime.controlers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.pele.cooktime.model.Ingredient;
import me.pele.cooktime.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "Операции по добавлению, реадактированию и удалению ингредиентов")
public class IngredientController {

    private final IngredientService ingredientService;


    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Operation(
            summary = "Добавление ингредиентов",
            description = "Необходимо добавить название ингрединта, количество и тип измерение ингредтиентов"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент был добавлен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Ошибка в запросе",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                                    )
                            }
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Ingredient> save(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.save(ingredient));
    }

    @Operation(
            summary = "Просмотр ингредиентов",
            description = "Необходимо указать ID ингредиента"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент найден",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ), @ApiResponse(
                    responseCode = "404",
                    description = "Ингредиент не найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@PathVariable Long id) {
        return ResponseEntity.of(ingredientService.getById(id));
    }

    @Operation(
            summary = "Редактирование ингредиентов",
            description = "Можно изменить название ингрединта, количество и тип измерение ингредтиентов"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент был изменен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Ошибка в запросе",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                                    )
                            }
                    )
            }
    )

    @PutMapping ("/{id}")
    ResponseEntity <Ingredient> editIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient){
        return ResponseEntity.ok(ingredientService.editIngredient(id, ingredient));
    }

    @Operation(
            summary = "Удаление ингредиентов",
            description = "Необходимо указать ID ингредиента для удаления"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент был удален",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                                    )
                            }
                    )
            }
    )
    @DeleteMapping ("/{id}")
    ResponseEntity <Ingredient> deleteIngredient(@PathVariable Long id){
        return ResponseEntity.ok(ingredientService.deleteIngredient(id));
    }

    @Operation(
            summary = "Показать все ингредиенты",
            description = "Функция для просмотра всех сохраненных ингредиентов"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список сохраненных ингредиентов",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                                    )
                            }
                    )
            }
    )
    @GetMapping
    ResponseEntity < Map <Long, Ingredient>> getAll(){
        return ResponseEntity.ok(ingredientService.getAll());
    }
}

