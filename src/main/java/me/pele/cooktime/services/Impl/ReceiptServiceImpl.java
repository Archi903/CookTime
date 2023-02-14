package me.pele.cooktime.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.pele.cooktime.exception.ValidationException;
import me.pele.cooktime.model.Receipt;
import me.pele.cooktime.services.FilesService;
import me.pele.cooktime.services.ReceiptService;
import me.pele.cooktime.services.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    final private FilesService filesService;
    private Map<Long, Receipt> receiptMap = new HashMap<>();
    private static long idCount = 1;
    private final ValidationService validationService;

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
        saveToFile();
        return receiptMap.put(idCount++, receipt);
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
        saveToFile();
        return receiptMap.replace(id, receipt);
    }

    @Override
    public Receipt deleteReceipt(Long id){
        saveToFile();
        return receiptMap.remove(id);
    }
    @Override
    public Map <Long, Receipt> getAll(){
        return receiptMap;
    }

    private void saveToFile(){
        try {
            String json = new ObjectMapper().writeValueAsString(receiptMap);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    private void readFromFile(){
        try {
            String json = filesService.readFromFile();
            receiptMap = new ObjectMapper().readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
