package me.pele.cooktime.services.Impl;

import me.pele.cooktime.services.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.data.file}")
    private String dataFileName;
    public Path path;

    @PostConstruct
    public void init(){
        path = Path.of(dataFilePath, dataFileName);
    }

    @Override
    public boolean saveToFile(String json){
        try {
            cleanDataFile();
            Files.writeString(path, json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public String readFromFile(){
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean cleanDataFile (){
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
//    @Override
//    public File getDataFile() {
//        return new File(dataFilePath + "/" + dataFileName);
//    }
}
