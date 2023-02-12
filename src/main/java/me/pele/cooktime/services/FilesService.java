package me.pele.cooktime.services;

public interface FilesService {

    boolean saveToFile(String json);

    String readFromFile();
}
