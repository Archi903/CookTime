package me.pele.cooktime.controlers;

import me.pele.cooktime.model.Receipt;
import me.pele.cooktime.services.Impl.ReceiptServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PutMapping ("/{id}")
    ResponseEntity <Receipt> editReceipt(@PathVariable Long id, @RequestBody Receipt receipt){
        return ResponseEntity.ok(receiptService.editReceipt(id, receipt));
    }
    @DeleteMapping ("/{id}")
    ResponseEntity <Receipt> deleteReceipt(@PathVariable Long id){
        return ResponseEntity.ok(receiptService.deleteReceipt(id));
    }

    @GetMapping
    ResponseEntity <Map<Long, Receipt>> getAll(){
        return ResponseEntity.ok(receiptService.getAll());
    }

}
