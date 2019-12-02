package com.drato.graduationthesis.service.interfaces;

import com.drato.graduationthesis.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAll();
    public List<Student> getAllByExamId(Long examId);
    public Student getById(Long id);
    public void deleteByExamId(Long examId);
    public int countStudentInExam(long examId);
    void insertStudents(List<Student> lstStudents);
}
