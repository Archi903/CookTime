package me.pele.cooktime.services.Impl;

import me.pele.cooktime.exception.ValidationException;
import me.pele.cooktime.model.Receipt;
import me.pele.cooktime.services.ReceiptService;
import me.pele.cooktime.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final Map<Long, Receipt> receiptMap = new HashMap<>();
    private static long idCount = 1;
    private final ValidationService validationService;

    public ReceiptServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }


    @Override
    public Receipt save(Receipt receipt) {
        if (!validationService.validate(receipt)) {
            throw new ValidationException(receipt.toString());
        }
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
        return receiptMap.replace(id, receipt);
    }

    @Override
    public Receipt deleteReceipt(Long id){
        return receiptMap.remove(id);
    }
    @Override
    public Map <Long, Receipt> getAll(){
        return receiptMap;
    }

}
