package me.pele.cooktime.services;
import me.pele.cooktime.model.Receipt;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public interface ReceiptService {
    Receipt save(Receipt receipt);

    Optional<Receipt> getById(Long id);


    Receipt editReceipt(Long id, Receipt receipt);

    Receipt deleteReceipt(Long id);


    Map <Long, Receipt> getAll();

    File ReceiptComplete () throws IOException;

    File ReceiptText() throws IOException;

    String receiptToString();
}
