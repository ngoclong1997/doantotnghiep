package com.drato.graduationthesis.controller.api;

import com.drato.graduationthesis.model.UploadFileResponse;
import com.drato.graduationthesis.service.implementation.FileStorageService;
import com.drato.graduationthesis.service.interfaces.ExamSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    ExamSubjectService examSubjectService;

    @PostMapping("/uploadManualEditLogFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("examId") String examId, @RequestParam("subjectId") String subjectId) throws IOException, NoSuchAlgorithmException {
        String fileName = fileStorageService.storeFile(file);
        examSubjectService.updatePath(fileName, Long.valueOf(examId), Long.valueOf(subjectId));
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadManualEditLogFile?examId="+examId + "&subjectId=" + subjectId)
                .toUriString();

        return new UploadFileResponse(file.getOriginalFilename(), fileDownloadUri, file.getContentType(), file.getSize());
    }
}
