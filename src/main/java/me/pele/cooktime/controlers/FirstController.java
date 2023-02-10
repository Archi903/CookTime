package me.pele.cooktime.controlers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CookTime", description = "Стартовая траница")
@RestController
public class FirstController {

    @GetMapping
    @Operation(
            summary = "Start",
            description = "Проверка работоспособности сайта"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сайт работает",
                            content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
                    )
            }
    )
    public String start() {
        return "Приложение запущено!";
    }

    @Operation(
            summary = "Обложка",
            description = "Описание сайта"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация о сайте выведена",
                            content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
                    )
            }
    )
    @GetMapping("info")
    public String name() {
        String name = "Артур <br>";
        String nameProject = "Время готовки <br>";
        String creatTime = "02.02.2022 <br>";
        String description = "Сборник лучших рецептов из не очень хороших продуктов <br>";
        return name + nameProject + creatTime  + description;
    }
}