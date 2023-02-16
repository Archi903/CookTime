package me.pele.cooktime.controlers;

import me.pele.cooktime.services.FilesService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//@RestController
//public class FileController {
//    private final FilesService filesService;
//
//    public FileController(FilesService filesService) {
//        this.filesService = filesService;
//    }
//
//
//@GetMapping("/export")
//    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
//        File file = filesService.getDataFile();
//
//        if (file.exists()){
//            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .contentLength(file.length())
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"CookTime.json\"")
//                    .body(resource);
//        } else {
//            return ResponseEntity.noContent().build();
//        }
//    }
//
//}
