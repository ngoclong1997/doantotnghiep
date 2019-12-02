package com.drato.graduationthesis.service.interfaces;

public interface ExamSubjectService {
    void updatePath(String path, long examId, long subjectId);
    String getPath(long examId, long subjectId);
}
