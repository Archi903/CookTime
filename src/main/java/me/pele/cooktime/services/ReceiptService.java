package me.pele.cooktime.services;
import me.pele.cooktime.model.Receipt;

import java.util.Map;
import java.util.Optional;

public interface ReceiptService {
    Receipt save(Receipt receipt);

    Optional<Receipt> getById(Long id);


    Receipt editReceipt(Long id, Receipt receipt);

    Receipt deleteReceipt(Long id);


    Map <Long, Receipt> getAll();
}
