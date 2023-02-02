package me.pele.cooktime.controlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class FirstController {

    @GetMapping
    public String start() {
        return "Приложение запущено!";
    }

    @GetMapping("info")
    public String name() {
        String name = "Артур <br>";
        String nameProject = "Время готовки <br>";
        String creatTime = "02.02.2022 <br>";
        String description = "Сборник лучших рецептов из не очень хороших продуктов <br>";
        return name + nameProject + creatTime  + description;
    }
}
