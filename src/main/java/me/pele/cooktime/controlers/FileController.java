package me.pele.cooktime.controlers;

import me.pele.cooktime.services.FilesService;
import me.pele.cooktime.services.ReceiptService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FilesService filesService;
    private final ReceiptService receiptService;

    public FileController(FilesService filesService, ReceiptService receiptService) {
        this.filesService = filesService;
        this.receiptService = receiptService;
    }


@GetMapping("/export/receipt")
    public ResponseEntity<InputStreamResource> downloadDataFileReceipt() throws FileNotFoundException {
        File file = filesService.getDataFileReceipt();

        if (file.exists()){
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Receipt.txt\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
@GetMapping("/export/ingredient")
    public ResponseEntity<InputStreamResource> downloadDataFileIngredient() throws FileNotFoundException {
        File file = filesService.getDataFileIngredient();

        if (file.exists()){
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Ingredient.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping(value = "/import/receipt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <Void> uploadDataFileReceipt(@RequestParam MultipartFile file){
        filesService.cleanDataFileReceipt();
        File dataFile = filesService.getDataFileReceipt();

        try(FileOutputStream fos =  new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PostMapping(value = "/import/ingredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <Void> uploadDataFileIngredient(@RequestParam MultipartFile file){
        filesService.cleanDataFileIngredient();
        File dataFile = filesService.getDataFileIngredient();

        try(FileOutputStream fos =  new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/export/receipt/txt")
    public ResponseEntity<InputStreamResource> downloadDataFileReceiptTXT() throws IOException {
        File file = receiptService.ReceiptText();
        if (file.exists()){
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Receipt.txt\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
