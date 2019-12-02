package com.drato.graduationthesis.controller.manager;


import com.drato.graduationthesis.dto.ExamDto;
import com.drato.graduationthesis.dto.StudentFilterCriteriaDto;
import com.drato.graduationthesis.model.Exam;
import com.drato.graduationthesis.model.Subject;
import com.drato.graduationthesis.service.interfaces.ExamService;
import com.drato.graduationthesis.service.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ExamController {
    @Autowired
    ExamService examService;

    @Autowired
    SubjectService subjectService;

    @GetMapping("/exam")
    public String index(Model model) {
        model.addAttribute("title", "lang.list-exam");
        model.addAttribute("exams", examService.getAll());
        return "manager/exam/index";
    }

    @GetMapping("/exam/add")
    public String addExam(Model model) {
        model.addAttribute("title", "lang.add-exam");
        model.addAttribute("exam", new ExamDto());
        model.addAttribute("selectableSubjects", subjectService.getAll());
        return "manager/exam/add-exam";
    }

    @PostMapping("/exam/add")
    public String addExam(@ModelAttribute("exam") @Valid ExamDto exam, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()){
            model.addAttribute("selectableSubjects", subjectService.getAll());
            model.addAttribute("title", "lang.add-exam");
            return "manager/exam/add-exam";
        }
        try {
            examService.createExam(exam);
        } catch (ParseException e) {
            model.addAttribute("selectableSubjects", subjectService.getAll());
            model.addAttribute("title", "lang.add-exam");
            result.rejectValue("startDate", "err.invalid-start-date", "Không xác định được ngày bắt đầu");
            return "manager/exam/add-exam";
        }
        redirectAttributes.addFlashAttribute("message", "lang.add-succeed");
        return "redirect:/manager/exam";
    }

    @GetMapping("/exam/edit")
    public String editExam(Model model, @RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        Exam exam = examService.getById(id);
        if (exam == null) {
            redirectAttributes.addFlashAttribute("message", "err.exam-not-found");
            return "redirect:/manager/exam";
        }
        model.addAttribute("exam", examService.convertExamToExamDto(exam));
        model.addAttribute("selectableSubjects", subjectService.getAll());
        model.addAttribute("title", "lang.edit-exam");
        return "manager/exam/edit-exam";
    }

    @PostMapping("/exam/edit")
    public String editUser(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("exam") ExamDto examDto, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("title", "lang.edit-exam");
            model.addAttribute("selectableSubjects", subjectService.getAll());
            return "manager/exam/edit-exam";
        }
        try {
            examService.editExam(examDto);
        } catch (ParseException e) {
            model.addAttribute("selectableSubjects", subjectService.getAll());
            model.addAttribute("title", "lang.add-exam");
            result.rejectValue("startDate", "err.invalid-start-date", "Không xác định được ngày bắt đầu");
            return "manager/exam/add-exam";
        }
        redirectAttributes.addFlashAttribute("message", "lang.edit-succeed");
        return "redirect:/manager/exam";
    }

    @GetMapping("/exam/delete")
    public String deleteUser(RedirectAttributes redirectAttributes, @RequestParam("id") Long id) {
        Exam exam = examService.getById(id);
        if (exam == null) {
            redirectAttributes.addFlashAttribute("message", "err.exam-not-found");
            return "redirect:/manager/exam";
        }
        examService.deleteExam(exam);
        redirectAttributes.addFlashAttribute("message", "lang.delete-succeed");
        return "redirect:/manager/exam";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file) throws Exception {
        if (file == null) {
            throw new RuntimeException("You must select the a file for uploading");
        }
        InputStream inputStream = file.getInputStream();
        String originalName = file.getOriginalFilename();
        String name = file.getName();
        String contentType = file.getContentType();
        long size = file.getSize();
        return new ResponseEntity<String>(originalName, HttpStatus.OK);
    }

    @RequestMapping("/manual-edit-report")
    public String showListManualEdit(Model model, @RequestParam(name = "examId", required = false) String examId) {
        StudentFilterCriteriaDto examFilter = new StudentFilterCriteriaDto();
        List<Exam> exams = examService.getAll();
        List<Subject> subjects = new ArrayList<>();
        Exam exam = null;

        if (exams.size() > 0) {
            if (examId == null || examId.equals("")) {
                exam = Collections.max(exams, Comparator.comparing(Exam::getId));
                examId = exam.getId().toString();
            } else {
                exam = examService.getById(Long.valueOf(examId));
            }
            examFilter.setExamId(examId);
            subjects = new ArrayList<>(exam.getSubjects());
        }
        if (examId == null) {
            return "redirect:/viewer/report-not-found";
        }
        model.addAttribute("exams", exams);
        model.addAttribute("examId", examId);
        model.addAttribute("subjects", subjects);
        model.addAttribute("examFilter", examFilter);
        model.addAttribute("title", "lang.manual-edit-report");
        return "manager/manual-edit-report";
    }

}
