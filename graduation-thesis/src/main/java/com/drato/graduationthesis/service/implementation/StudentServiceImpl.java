package com.drato.graduationthesis.service.implementation;

import com.drato.graduationthesis.model.Student;
import com.drato.graduationthesis.repository.StudentRepository;
import com.drato.graduationthesis.service.interfaces.SecurityService;
import com.drato.graduationthesis.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository repository;

    @Autowired
    SecurityService securityService;

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Student> getAllByExamId(Long examId) {
        return repository.findAllByExamId(examId);
    }

    @Override
    public Student getById(Long id) {
        Optional<Student> optionalUser = repository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public void deleteByExamId(Long examId) {
        repository.deleteAllByExamId(examId);
    }

    @Override
    public int countStudentInExam(long examId) {
        return repository.countAllByExamId(examId);
    }

    @Override
    public void insertStudents(List<Student> lstStudents) {
        List<Student> students = getAllByExamId(lstStudents.get(0).getExamId());
        String username = securityService.getCurrentUsername();
        if (students.size() == 0) {
            for (Student e : lstStudents) {
                Date now = Calendar.getInstance().getTime();
                e.setCreatedDate(now);
                e.setModifiedDate(now);
                e.setAddBy(username);
                e.setModifiedBy(username);
            }
            repository.saveAll(lstStudents);
        } else {
            repository.deleteAllByExamId(lstStudents.get(0).getExamId());
            for (Student e : lstStudents) {
                Date now = Calendar.getInstance().getTime();
                e.setCreatedDate(students.get(0).getCreatedDate());
                e.setModifiedDate(now);
                e.setAddBy(students.get(0).getAddBy());
                e.setModifiedBy(username);
            }
            repository.saveAll(lstStudents);
        }

    }
}
