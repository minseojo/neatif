package com.minseojo.neatif.service;

import com.minseojo.neatif.domain.item.ImageFile;
import com.minseojo.neatif.domain.item.Item;
import com.minseojo.neatif.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {
    private static String FILE_DEFAULT_IMAGE_PRODUCT = "default_product.png";
    private static String FILE_PROTOCOL = "file:";
    private static String FILE_IMAGE_DIR = "/Users/minseojo/Desktop/neatif/src/main/resources/static/images/";
    @Transactional
    public List<ImageFile> saveFiles(List<MultipartFile> multipartFiles, Item item) {
        return multipartFiles.stream()
                .filter(Objects::nonNull)
                .map(multipartFile -> saveFile(multipartFile, item))
                .collect(Collectors.toList());
    }

    @Transactional
    public ImageFile saveFile(MultipartFile multipartFile, Item item) {
        if (multipartFile.isEmpty()) {
            throw new IllegalArgumentException("Image file is empty");
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        try {
            // Create a File object for storing the uploaded file
            File file = new File(getFullPath(storeFileName));

            // Transfer the data from MultipartFile to the File object
            try (OutputStream outputStream = new FileOutputStream(file)) {
                IOUtils.copy(multipartFile.getInputStream(), outputStream);
            }

            return new ImageFile(originalFilename, storeFileName, item);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }

    private String getFullPath(String filename) {
        return FILE_IMAGE_DIR + filename;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}