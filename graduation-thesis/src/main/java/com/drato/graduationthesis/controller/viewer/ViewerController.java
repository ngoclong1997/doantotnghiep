package com.drato.graduationthesis.controller.viewer;

import com.drato.graduationthesis.dto.AnswerFilterCriteriaDto;
import com.drato.graduationthesis.model.Exam;
import com.drato.graduationthesis.model.Subject;
import com.drato.graduationthesis.service.interfaces.ExamService;
import com.drato.graduationthesis.service.interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/viewer")
public class ViewerController {

    @Autowired
    ReportService reportService;

    @Autowired
    ExamService examService;

    @RequestMapping({"/", ""})
    public String index(Model model, @RequestParam(name = "examId", required = false) String examId, @RequestParam(name = "subjectId", required = false) String subjectId) {
        model.addAttribute("title", "lang.view-statistic");
        AnswerFilterCriteriaDto subjectExamFilter = new AnswerFilterCriteriaDto();
        List<Exam> exams = examService.getAll();
        List<Subject> subjects = new ArrayList<>();
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
            subjectExamFilter.setExamId(examId);
            subjectExamFilter.setSubjectId(subjectId);
            subjects = new ArrayList<>(exam.getSubjects());
        }
        if (examId == null) {
            return "redirect:/viewer/report-not-found";
        }
        long idInt = Long.valueOf(examId);
        long subjectIdInt = Long.valueOf(subjectId);
        assert exam != null;
        model.addAttribute("examName", exam.getName());
        model.addAttribute("subjectExamFilter", subjectExamFilter);
        model.addAttribute("chart1Data", reportService.getScoreAnalyticData(idInt, subjectIdInt));
        model.addAttribute("chart2Data", reportService.getStudentAnswerSheetAnalyticData(idInt, subjectIdInt));
        int totalStudent = reportService.getTotalStudent(idInt);
        model.addAttribute("totalStudent", totalStudent);

        model.addAttribute("maxPoint", reportService.getMaxScore(idInt, subjectIdInt));
        model.addAttribute("minPoint", reportService.getMinScore(idInt, subjectIdInt));
        model.addAttribute("subjects", subjects);
        model.addAttribute("exams", exams);
        model.addAttribute("totalAnswerSheet", reportService.getTotalValidAnswerSheet(idInt, subjectIdInt));
        int totalUpperMedium = reportService.getTotalStudentBiggerThanMediumScore(idInt, subjectIdInt);
        model.addAttribute("totalUpperMediumScore", totalUpperMedium*100/totalStudent);
        model.addAttribute("averageScore", reportService.getAverageScore(idInt, subjectIdInt));
        return "viewer/chart/index";
    }

    @RequestMapping("/report-not-found")
    public String noExamHandler(Model model) {
        model.addAttribute("title","lang.create-report-failure");
        return "viewer/chart/no-exam";
    }



    private Integer[] generateInts(int n) {
        Random random = new Random();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(100) + 1;
        }
        return arr;
    }
}
