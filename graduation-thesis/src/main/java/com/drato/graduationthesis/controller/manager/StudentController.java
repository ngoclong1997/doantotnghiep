package com.drato.graduationthesis.controller.manager;

import com.drato.graduationthesis.dto.StudentFileUploadDto;
import com.drato.graduationthesis.dto.StudentFilterCriteriaDto;
import com.drato.graduationthesis.model.Exam;
import com.drato.graduationthesis.model.Student;
import com.drato.graduationthesis.service.interfaces.ExamService;
import com.drato.graduationthesis.service.interfaces.StudentService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    ExamService examService;

    @GetMapping("/student")
    public String index(Model model, @RequestParam(value = "examId", required = false) String examId) {
        StudentFilterCriteriaDto studentFilterCriteriaDto = new StudentFilterCriteriaDto();
        List<Exam> exams = examService.getAll();
        List<Student> students = new ArrayList<>();

        if (exams.size() > 0) {
            if (examId == null || examId.equals("")) {
                Exam nearestExam = Collections.max(exams, Comparator.comparing(Exam::getId));
                examId = nearestExam.getId().toString();
            }
            studentFilterCriteriaDto.setExamId(examId);
            students = studentService.getAllByExamId(Long.valueOf(studentFilterCriteriaDto.getExamId()));
        }

        model.addAttribute("students", students);
        model.addAttribute("exams", exams);
        model.addAttribute("studentFilter", studentFilterCriteriaDto);
        model.addAttribute("title", "lang.list-student");
        return "manager/student/index";
    }

    @GetMapping("/student/importStudent")
    public String importStudent(Model model) {
        model.addAttribute("exams", examService.getAll());
        StudentFileUploadDto studentFileUploadDto = new StudentFileUploadDto();
        studentFileUploadDto.setHasHeader(true);
        model.addAttribute("fileStudent", studentFileUploadDto);
        model.addAttribute("title", "lang.import-student");
        return "manager/student/add-student";
    }

    @PostMapping("/student/importStudent")
    public String handleImportStudent(@ModelAttribute("fileStudent") @Valid StudentFileUploadDto studentFileUploadDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("exams", examService.getAll());

        if (studentFileUploadDto.getFile() == null || studentFileUploadDto.getFile().getOriginalFilename() == null || studentFileUploadDto.getFile().getOriginalFilename().equals("")) {
            result.rejectValue("file", "err.not-empty", "Trường này không được bỏ trống");
        }
        if (result.hasErrors()) {
            model.addAttribute("title", "lang.import-student");
            return "manager/student/add-student";
        }
        try {
            MultipartFile multipartFile = studentFileUploadDto.getFile();
            List<Student> lstStudents = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            String val;
            Student student;
            int i = 0;

            if(studentFileUploadDto.getHasHeader()) {
                i = 1;
            }
            for(; i < worksheet.getPhysicalNumberOfRows() ;i++) {
                student = new Student();
                XSSFRow row = worksheet.getRow(i);
                if (row.getPhysicalNumberOfCells() != 5) {
                    throw new Exception();
                }
                if (row.getCell(0).getStringCellValue().length() != 6) {
                    throw new Exception();
                }
                for (int j = 1; j < row.getPhysicalNumberOfCells(); j++) {
                    if (row.getCell(j).getStringCellValue().equals("")) {
                        throw new Exception();
                    }
                }

                student.setCode(row.getCell(0).getStringCellValue());
                student.setLastName(row.getCell(1).getStringCellValue());
                student.setFirstName(row.getCell(2).getStringCellValue());
                student.setDob(row.getCell(3).getStringCellValue());
                student.setGender(row.getCell(4).getStringCellValue());
                student.setExamId(Long.valueOf(studentFileUploadDto.getExamId()));
                lstStudents.add(student);
            }
            studentService.insertStudents(lstStudents);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "err.invalid-student-file");
            return "redirect:/manager/student/importStudent";
        }
        redirectAttributes.addFlashAttribute("message", "lang.import-student-succeed");
        return "redirect:/manager/student?examId=" + studentFileUploadDto.getExamId();
    }
}
