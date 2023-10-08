package com.upc.coreservice.Service;

import com.upc.coreservice.Service.Interfaces.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {
    @Value("${media.location}")
    private String mediaLocation;
    private Path rootLocation;

    public void init(String rootName) throws IOException {
        rootLocation = Paths.get(mediaLocation + rootName);
        Files.createDirectories(rootLocation);
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if(file.isEmpty()){
                throw new RuntimeException("Failed to store empty file.");
            }
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            System.out.println(filename);
            Path destinationFile = rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
            System.out.println(destinationFile);
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return filename;
        }catch (IOException e){
            throw new RuntimeException("Failed to store empty file.",e);
        }
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = rootLocation.resolve(fileName);
            Resource resource = new UrlResource((file.toUri()));
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("Could not read file: " + fileName);
            }
        }catch (MalformedURLException e){
            throw new RuntimeException("Could not read file: " + fileName);
        }
    }
}
