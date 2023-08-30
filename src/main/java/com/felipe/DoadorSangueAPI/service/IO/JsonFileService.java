package com.felipe.DoadorSangueAPI.service.IO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class JsonFileService {

    public void saveJsonFile(String Json, String directory, String fileName) throws IOException {
        File directoryFile = new File(directory);
        if (!directoryFile.getAbsoluteFile().exists())
            directoryFile.mkdirs();

        File targetFile = new File(directory + File.separator + fileName);
        Files.write(targetFile.toPath(), Json.getBytes());
    }

    public <T> T loadJsonFile(String directory, String fileName, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(directory + File.separator + fileName);
        return objectMapper.readValue(file, clazz);
    }

    public void deleteJsonFile(String directory, String fileName) {
        File file = new File(directory + File.separator + fileName);
        file.delete();
    }
}
