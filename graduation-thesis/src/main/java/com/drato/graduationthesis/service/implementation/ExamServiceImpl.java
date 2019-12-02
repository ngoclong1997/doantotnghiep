package com.drato.graduationthesis.service.implementation;

import com.drato.graduationthesis.dto.ExamDto;
import com.drato.graduationthesis.model.Exam;
import com.drato.graduationthesis.model.Subject;
import com.drato.graduationthesis.repository.ExamRepository;
import com.drato.graduationthesis.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamRepository repository;

    @Autowired
    SecurityService securityService;

    @Autowired
    AnswerService answerService;

    @Autowired
    GradeService gradeService;

    @Autowired
    StudentService studentService;

    @Override
    public void createExam(ExamDto examDto) throws ParseException {
        Exam exam = convertExamDtoToExam(examDto);
        Date now = Calendar.getInstance().getTime();
        exam.setCreatedDate(now);
        exam.setModifiedDate(now);
        exam.setAddBy(securityService.getCurrentUsername());
        exam.setModifiedBy(securityService.getCurrentUsername());
        repository.save(exam);
    }

    @Override
    public List<Exam> getAll() {
        return repository.findAll();
    }

    @Override
    public Exam getById(Long id) {
        Optional<Exam> optionalUser = repository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public List<Subject> getExamSubjects(Long id) {
        return null;
    }

    @Override
    public void editExam(ExamDto updExamDto) throws ParseException {
        Exam exam = convertExamDtoToExam(updExamDto);
        Exam examTmp = getById(exam.getId());
        exam.setCreatedDate(examTmp.getCreatedDate());
        exam.setAddBy(examTmp.getAddBy());
        Date now = Calendar.getInstance().getTime();
        exam.setModifiedDate(now);
        exam.setModifiedBy(securityService.getCurrentUsername());
        repository.save(exam);
    }

    @Override
    @Transactional
    public void deleteExam(Exam exam) {
        repository.delete(exam);
        answerService.deleteByExamId(exam.getId());
        gradeService.deleteByExamId(exam.getId());
        studentService.deleteByExamId(exam.getId());
    }

    @Override
    public ExamDto convertExamToExamDto(Exam exam) {
        ExamDto examDto = new ExamDto();
        examDto.setId(exam.getId());
        examDto.setDescription(exam.getDescription());
        examDto.setName(exam.getName());
        examDto.setStartDate(new SimpleDateFormat("dd/MM/yyyy").format(exam.getStartDate()));
        examDto.setSubjects(new ArrayList<>(exam.getSubjects()));
        return examDto;
    }

    @Override
    public Exam convertExamDtoToExam(ExamDto examDto) throws ParseException {
        Exam exam = new Exam();
        exam.setId(examDto.getId());
        exam.setName(examDto.getName());
        exam.setDescription(examDto.getDescription());
        exam.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse(examDto.getStartDate()));
        Collection<Subject> subjects = examDto.getSubjects();
        if (subjects == null) {
            subjects = new ArrayList<>();
        }
        exam.setSubjects(subjects);
        return exam;
    }
}
