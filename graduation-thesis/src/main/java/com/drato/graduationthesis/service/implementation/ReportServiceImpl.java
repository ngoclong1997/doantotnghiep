package com.drato.graduationthesis.service.implementation;

import com.drato.graduationthesis.model.Grade;
import com.drato.graduationthesis.service.interfaces.GradeService;
import com.drato.graduationthesis.service.interfaces.ReportService;
import com.drato.graduationthesis.service.interfaces.StudentService;
import com.drato.graduationthesis.utils.ChartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {


    @Autowired
    StudentService studentService;

    @Autowired
    GradeService gradeService;

    @Override
    public int getTotalStudent(long examId) {
        return studentService.countStudentInExam(examId);
    }

    @Override
    public int getTotalValidAnswerSheet(long examId, long subjectId) {
        return gradeService.countNumberOfValidAnswerSheetInExam(examId, subjectId);
    }

    @Override
    public int getTotalStudentBiggerThanMediumScore(long examId, long subjectId) {
        return gradeService.countNumberOfStudentScoreBetterThanMedium(examId, subjectId);
    }

    @Override
    public float getAverageScore(long examId, long subjectId) {
        return gradeService.calculateAvgScore(examId, subjectId);
    }

    @Override
    public String getScoreAnalyticData(long examId, long subjectId) {
        List<Grade> grades = gradeService.getStudentSubjectScore(examId, subjectId);
        Map<Float, Integer> mp = new HashMap<>();
        for (Grade grade : grades) {
            if (mp.containsKey(grade.getPoint())) {
                mp.put(grade.getPoint(), mp.get(grade.getPoint()) + 1);
            } else {
                mp.put(grade.getPoint(), 1);
            }
        }
        List<Integer> lstData = new ArrayList<>();
        float i = 0;
        while (i <= 10) {
            lstData.add(mp.getOrDefault(i, 0));
            i += 0.25;
        }
        return ChartUtils.getBarChartData(ChartUtils.generatePointLabels(), lstData, null);
    }

    @Override
    public String getStudentAnswerSheetAnalyticData(long examId, long subjectId) {
        int nOfStudent = getTotalStudent(examId);
        int nOfValidAnswerSheet = getTotalValidAnswerSheet(examId, subjectId);
        List<Float> data = Arrays.asList((float)(nOfStudent-nOfValidAnswerSheet), (float)nOfValidAnswerSheet);
        List<String> labels = Arrays.asList("Sinh viên không có bài hoặc bài không hợp lệ", "Sinh viên có bài hợp lệ");
        return ChartUtils.getPieChartData(data, labels, null);
    }

    @Override
    public float getMaxScore(long examId, long subjectId) {
        return gradeService.getMaxPoint(examId, subjectId);
    }

    @Override
    public float getMinScore(long examId, long subjectId) {
        return gradeService.getMinPoint(examId, subjectId);
    }
}
