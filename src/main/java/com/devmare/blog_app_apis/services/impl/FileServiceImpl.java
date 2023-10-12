package com.devmare.blog_app_apis.services.impl;

import com.devmare.blog_app_apis.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();

        //? Random name generate file
        String randomId = UUID.randomUUID().toString();
        String newFilename = randomId.concat(filename.substring(filename.lastIndexOf(".")));

        //? Full path
        String filePath = path + File.separator + newFilename;

        //? Create folder if not created
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }

        //? File copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return newFilename;
    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException {
        String fullPath = path + File.separator + filename;
        return new FileInputStream(fullPath);
    }
}
