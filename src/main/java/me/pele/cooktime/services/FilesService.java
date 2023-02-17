package me.pele.cooktime.services;

import java.io.File;

public interface FilesService {

    boolean saveToFileReceipt(String json);

    boolean saveToFileIngredient(String json);

    String readFromFileReceipt();

    String readFromFileIngredient();

    boolean cleanDataFileReceipt();

    boolean cleanDataFileIngredient();

    File getDataFileReceipt();

    File getDataFileIngredient();
}
