package com.drato.graduationthesis.service.interfaces;

public interface ReportService {
    int getTotalStudent(long examId);
    int getTotalValidAnswerSheet(long examId, long subjectId);
    int getTotalStudentBiggerThanMediumScore(long examId, long subjectId);
    float getAverageScore(long examId, long subjectId);

    String getScoreAnalyticData(long examId, long subjectId);
    String getStudentAnswerSheetAnalyticData(long examId, long subjectId);
    float getMaxScore(long examId, long subjectId);
    float getMinScore(long examId, long subjectId);
}
