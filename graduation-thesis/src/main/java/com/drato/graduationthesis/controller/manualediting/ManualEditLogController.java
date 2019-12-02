package com.drato.graduationthesis.controller.manualediting;

import com.drato.graduationthesis.service.implementation.FileStorageService;
import com.drato.graduationthesis.service.interfaces.ExamSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class ManualEditLogController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    ExamSubjectService examSubjectService;

    @GetMapping("/downloadManualEditLogFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam("examId") Long examId, @RequestParam("subjectId") Long subjectId, HttpServletRequest request) throws Exception {
        String examSubjectPath = examSubjectService.getPath(examId, subjectId);
        Resource resource = fileStorageService.loadFileAsResource(examSubjectPath);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new Exception("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}