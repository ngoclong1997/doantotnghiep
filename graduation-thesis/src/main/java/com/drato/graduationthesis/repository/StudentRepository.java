package com.drato.graduationthesis.repository;

import com.drato.graduationthesis.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findAllByExamId(Long examId);
    public Student findByCode(String code);
    @Transactional
    public void deleteAllByExamId(Long examId);
    public int countAllByExamId(long examId);

}
