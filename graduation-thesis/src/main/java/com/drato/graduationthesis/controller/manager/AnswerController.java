package com.drato.graduationthesis.controller.manager;

import com.drato.graduationthesis.dto.AnswerFileUploadDto;
import com.drato.graduationthesis.dto.AnswerFilterCriteriaDto;
import com.drato.graduationthesis.model.Answer;
import com.drato.graduationthesis.model.Exam;
import com.drato.graduationthesis.model.Subject;
import com.drato.graduationthesis.service.interfaces.AnswerService;
import com.drato.graduationthesis.service.interfaces.ExamService;
import com.drato.graduationthesis.service.interfaces.SubjectService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/manager")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @Autowired
    ExamService examService;

    @Autowired
    SubjectService subjectService;

    @GetMapping("/answer")
    public String index(Model model, @RequestParam(value = "subjectId", required = false) String subjectId, @RequestParam(value = "examId", required = false) String examId) {
        AnswerFilterCriteriaDto answerFilterCriteriaDto = new AnswerFilterCriteriaDto();
        List<Exam> exams = examService.getAll();
        List<Subject> subjects = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        Exam exam = null;

        if (exams.size() > 0) {
            if (examId == null || examId.equals("")) {
                exam = Collections.max(exams, Comparator.comparing(Exam::getId));
                examId = exam.getId().toString();
                if (exam.getSubjects() != null && exam.getSubjects().size() > 0) {
                    subjectId = ((Subject)exam.getSubjects().toArray()[0]).getId().toString();
                } else {
                    subjectId = "";
                }
            } else {
                exam = examService.getById(Long.valueOf(examId));
            }
            answerFilterCriteriaDto.setExamId(examId);
            answerFilterCriteriaDto.setSubjectId(subjectId);
            subjects = new ArrayList<>(exam.getSubjects());
            answers = answerService.getAllByExamAndSubject(Long.valueOf(examId), Long.valueOf(subjectId));
        }
        model.addAttribute("answers", answers);
        model.addAttribute("subjects", subjects);
        model.addAttribute("exams", exams);
        model.addAttribute("answerFilterCriteriaDto", answerFilterCriteriaDto);
        model.addAttribute("title", "lang.list-answer");
        return "manager/answer/index";
    }

    @GetMapping("/answer/getExamSubjects")
    public ResponseEntity<?> getExamSubjects(@RequestParam("examId") Long examId) {
        try {
            Exam exam = examService.getById(examId);
            return ResponseEntity.ok(exam.getSubjects());
        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<Subject>());
        }
    }

    @GetMapping("/answer/importAnswer")
    public String importAnswer(Model model) {
        model.addAttribute("exams", examService.getAll());
        model.addAttribute("subjects", new ArrayList<Subject>());
        model.addAttribute("fileAnswer", new AnswerFileUploadDto());
        model.addAttribute("title", "lang.import-answer");
        return "manager/answer/add-answer";
    }

    @PostMapping("/answer/importAnswer")
    public String handleImportAnswer(@ModelAttribute("fileAnswer") @Valid AnswerFileUploadDto answerFileUploadDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("exams", examService.getAll());
        if (answerFileUploadDto.getExamId() != null && !answerFileUploadDto.getExamId().equals("")) {
            Exam exam = examService.getById(Long.valueOf(answerFileUploadDto.getExamId()));
            model.addAttribute("subjects", exam.getSubjects());
        }
        if (answerFileUploadDto.getFile() == null || answerFileUploadDto.getFile().getOriginalFilename() == null || answerFileUploadDto.getFile().getOriginalFilename().equals("")) {
            result.rejectValue("file", "constraints.not-empty", "Trường này không được bỏ trống");
        }
        if (result.hasErrors()) {
            model.addAttribute("title", "lang.import-answer");
            return "manager/answer/add-answer";
        }
        try {
            MultipartFile multipartFile = answerFileUploadDto.getFile();
            List<Answer> lstAnswers = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            String val;
            StringBuilder answers;
            Answer ans;
            for(int i=0; i < worksheet.getPhysicalNumberOfRows() ;i++) {
                answers = new StringBuilder();
                ans = new Answer();
                XSSFRow row = worksheet.getRow(i);
                if (row.getCell(0).getStringCellValue().length() != 3) {
                    throw new Exception();
                }
                ans.setTestCode(row.getCell(0).getStringCellValue());
                for(int j = 1; j < row.getPhysicalNumberOfCells(); j++) {
                    val = row.getCell(j).getStringCellValue();
                    if (val == null || val.equals("") || val.length() > 1 || !Arrays.asList("A", "B", "C", "D", "E").contains(val)) {
                        throw new Exception();
                    }
                    answers.append(val);
                }
                ans.setAnswers(answers.toString());
                ans.setExamId(Long.valueOf(answerFileUploadDto.getExamId()));
                ans.setSubjectId(Long.valueOf(answerFileUploadDto.getSubjectId()));
                lstAnswers.add(ans);
            }
            answerService.insertAnswers(lstAnswers);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "err.invalid-answer-file");
            return "redirect:/manager/answer/importAnswer";
        }
        redirectAttributes.addFlashAttribute("message", "lang.import-answer-succeed");
        return "redirect:/manager/answer?examId=" + answerFileUploadDto.getExamId() + "&subjectId=" + answerFileUploadDto.getSubjectId();
    }
}
