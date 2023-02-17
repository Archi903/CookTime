package me.pele.cooktime.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


public interface FilesService {

    boolean saveToFileReceipt(String json);

    boolean saveToFileIngredient(String json);

    String readFromFileReceipt();

    String readFromFileIngredient();

    boolean cleanDataFileReceipt();

    boolean cleanDataFileIngredient();

    File getDataFileReceipt();

    File getDataFileIngredient();

    Path saveToFile(String content, Path path) throws IOException;
}
