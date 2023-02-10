package me.pele.cooktime.controlers;

import me.pele.cooktime.model.Receipt;
import me.pele.cooktime.services.Impl.ReceiptServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    private final ReceiptServiceImpl receiptService;

    public ReceiptController(ReceiptServiceImpl receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public ResponseEntity<Receipt> save(@RequestBody Receipt receipt) {
        return ResponseEntity.ok(receiptService.save(receipt));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getById(@PathVariable Long id) {
        return ResponseEntity.of(receiptService.getById(id));
    }
}
