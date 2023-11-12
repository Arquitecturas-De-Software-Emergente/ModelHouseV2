package com.upc.coreservice.Service.Interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    void init(String rootName) throws IOException;
    String store(MultipartFile file, String rootName);
    Resource loadAsResource(String fileName);
}
