package me.pele.cooktime.controlers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.pele.cooktime.model.Ingredient;
import me.pele.cooktime.model.Receipt;
import me.pele.cooktime.services.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/receipt")
@Tag(name = "Рецепты", description = "Операции по добавлению, реадактированию и удалению рецептов")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Operation(
            summary = "Добавление рецептов",
            description = "Необходимо добавить название рецепта, время приготовления, ингредиенты и шаги"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт был добавлен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Receipt.class))
                                    )
                            }
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Receipt> save(@RequestBody Receipt receipt) {
        return ResponseEntity.ok(receiptService.save(receipt));
    }
    @Operation(
            summary = "Просмотр рецептов",
            description = "Необходимо указать ID рецепта"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт был найден",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Receipt.class))
                                    )
                            }
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getById(@PathVariable Long id) {
        return ResponseEntity.of(receiptService.getById(id));
    }

    @Operation(
            summary = "Редактирование рецепта",
            description = "Можно изменить название рецепта, время приготовления, ингредиенты и шаги"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт был изменен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Receipt.class))
                                    )
                            }
                    )
            }
    )
    @PutMapping ("/{id}")
    ResponseEntity <Receipt> editReceipt(@PathVariable Long id, @RequestBody Receipt receipt){
        return ResponseEntity.ok(receiptService.editReceipt(id, receipt));
    }
    @Operation(
            summary = "Удаление рецепта",
            description = "Необходимо указать ID рецепта для удаления"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт был удален",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Receipt.class))
                                    )
                            }
                    )
            }
    )
    @DeleteMapping ("/{id}")
    ResponseEntity <Receipt> deleteReceipt(@PathVariable Long id){
        return ResponseEntity.ok(receiptService.deleteReceipt(id));
    }
    @Operation(
            summary = "Показать все рецепты",
            description = "Функция для просмотра всех сохраненных рецептов"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список сохраненных рецептов",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Receipt.class))
                                    )
                            }
                    )
            }
    )
    @GetMapping
    ResponseEntity <Map<Long, Receipt>> getAll(){
        return ResponseEntity.ok(receiptService.getAll());
    }

}
