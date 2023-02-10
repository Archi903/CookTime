package me.pele.cooktime.services;
import me.pele.cooktime.model.Receipt;
import java.util.Optional;

public interface ReceiptService {
    Receipt save(Receipt receipt);

    Optional<Receipt> getById(Long id);

}
