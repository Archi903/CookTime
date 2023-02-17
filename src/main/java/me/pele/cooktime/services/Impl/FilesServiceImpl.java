package me.pele.cooktime.services.Impl;

import me.pele.cooktime.services.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${path.to.data.file}")
    public String dataFilePath;
    @Value("${name.of.recipe.data.file}")
    public String dataFileNameReceipt;

    @Value("${name.of.ingredient.data.file}")
    public String dataFileNameIngredient;

    public Path pathReceipt;
    public Path pathIngredient;

    @PostConstruct
    public void init() {
        pathReceipt = Path.of(dataFilePath, dataFileNameReceipt);
        pathIngredient = Path.of(dataFilePath, dataFileNameIngredient);
    }

    @Override
    public boolean saveToFileReceipt(String json) {
        try {
            cleanDataFileReceipt();
            Files.writeString(pathReceipt, json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean saveToFileIngredient(String json) {
        try {
            cleanDataFileReceipt();
            Files.writeString(pathIngredient, json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFileReceipt() {
        try {
            return Files.readString(pathReceipt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromFileIngredient() {
        try {
            return Files.readString(pathIngredient);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFileReceipt() {
        try {
            Files.deleteIfExists(pathReceipt);
            Files.createFile(pathReceipt);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean cleanDataFileIngredient() {
        try {
            Files.deleteIfExists(pathIngredient);
            Files.createFile(pathIngredient);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public File getDataFileReceipt() {
        return new File(dataFilePath + "/" + dataFileNameReceipt);
    }

    @Override
    public File getDataFileIngredient() {
        return new File(dataFilePath + "/" + dataFileNameIngredient);
    }

    @Override
    public Path saveToFile(String content, Path path) throws IOException {
        Files.deleteIfExists(path);
        return Files.createFile(path);
    }
}
