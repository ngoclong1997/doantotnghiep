package com.drato.graduationthesis.service.implementation;

import com.drato.graduationthesis.exception.FileStorageException;
import com.drato.graduationthesis.exception.MyFileNotFoundException;
import com.drato.graduationthesis.utils.CommonUtils;
import com.drato.graduationthesis.utils.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (originalFileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + originalFileName);
        }
        String fileNameHashed = CommonUtils.getMd5Hash(originalFileName + Calendar.getInstance().getTime().toString());
        String filePath = fileNameHashed.substring(0, 2) + "/" + fileNameHashed.substring(2, 4) + "/";
            Path targetLocation = this.fileStorageLocation.resolve(filePath);
            try {
                Files.createDirectories(targetLocation);
            } catch (Exception ex) {
                throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
        Files.copy(file.getInputStream(), targetLocation.resolve(fileNameHashed + CommonUtils.getFileExtension(originalFileName)), StandardCopyOption.REPLACE_EXISTING);
        return filePath + fileNameHashed + CommonUtils.getFileExtension(originalFileName);

    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}