package me.pele.cooktime.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.pele.cooktime.exception.ValidationException;
import me.pele.cooktime.model.Ingredient;
import me.pele.cooktime.model.Receipt;
import me.pele.cooktime.services.FilesService;
import me.pele.cooktime.services.ReceiptService;
import me.pele.cooktime.services.ValidationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    final private FilesService filesService;
    private Map<Long, Receipt> receiptMap = new HashMap<>();
    private static long idCount = 1;
    private final ValidationService validationService;
    @Value("${path.to.data.file}")
    public String dataFilePath;
    @Value("${name.of.recipe.txt.data.file}")
    public String dataFileTxtReceipt;

    public ReceiptServiceImpl(FilesService filesService, ValidationService validationService) {
        this.filesService = filesService;
        this.validationService = validationService;
    }


    @PostConstruct
    private void init(){
        readFromFile();
    }
    @Override
    public Receipt save(Receipt receipt) {
        if (!validationService.validate(receipt)) {
            throw new ValidationException(receipt.toString());
        }
        receiptMap.put(idCount++, receipt);
        saveToFile();
        return receiptMap.get(idCount);
    }

    @Override
    public Optional<Receipt> getById(Long id) {
        return Optional.ofNullable(receiptMap.get(id));
    }

    @Override
    public Receipt editReceipt(Long id, Receipt receipt){
        if (!validationService.validate(receipt)) {
            throw new ValidationException(receipt.toString());
        }
        receiptMap.replace(id, receipt);
        saveToFile();
        return receiptMap.get(id);
    }

    @Override
    public Receipt deleteReceipt(Long id){
        receiptMap.remove(id);
        saveToFile();
        return receiptMap.get(id);
    }
    @Override
    public Map <Long, Receipt> getAll(){
        return receiptMap;
    }

    @Override
    public File ReceiptComplete() throws IOException {
        return null;
    }

    private void saveToFile(){
        try {
            String json = new ObjectMapper().writeValueAsString(receiptMap);
            filesService.saveToFileReceipt(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    private void readFromFile(){
        try {
            String json = filesService.readFromFileReceipt();
            receiptMap = new ObjectMapper().readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File ReceiptText() throws IOException {
        return filesService.saveToFile(receiptToString(), Path.of(dataFilePath, dataFileTxtReceipt)).toFile();
    }

    @Override
    public String receiptToString() {
        StringBuilder sb = new StringBuilder();
        String listEL = "•";
        for (Receipt receipt : receiptMap.values()) {
            sb.append(receipt.toString()).append("\n");

            sb.append("\n Ингредиенты \n");
            for (Ingredient ingredient : receipt.getIngredient()) {
                sb.append(listEL).append(ingredient.toString()).append("\n");
            }
            sb.append("\n Шаги \n");
            for (String step : receipt.getSteps()){
                sb.append(listEL).append(step).append("\n");
            }
        }
        return sb.append("\n").toString();
    }
}
