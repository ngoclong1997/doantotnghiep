package com.drato.graduationthesis.service.implementation;

import com.drato.graduationthesis.model.Subject;
import com.drato.graduationthesis.repository.SubjectRepository;
import com.drato.graduationthesis.service.interfaces.SecurityService;
import com.drato.graduationthesis.service.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository repository;

    @Autowired
    SecurityService securityService;

    @Override
    public List<Subject> getAll() {
        return repository.findAll();
    }

    @Override
    public Subject getById(Long id) {
        Optional<Subject> optionalUser = repository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public Collection<Subject> getSubjectsByIds(Long[] ids) {
        Collection<Subject> subjects = new ArrayList<>();
        for (Long id : ids) {
            subjects.add(getById(id));
        }
        return subjects;
    }

    @Override
    public boolean nameUsedByOther(Long id, String name) {
        Subject subj = repository.findByName(name);
        if (subj == null) {
            return false;
        } else {
            return !subj.getId().equals(id);
        }
    }

    @Override
    public boolean nameExisted(String name) {
        return repository.existsByName(name);
    }

    @Override
    public void createSubject(Subject subject) {
        Date now = Calendar.getInstance().getTime();
        subject.setCreatedDate(now);
        subject.setModifiedDate(now);
        subject.setAddBy(securityService.getCurrentUsername());
        subject.setModifiedBy(securityService.getCurrentUsername());
        repository.save(subject);
    }

    @Override
    public void editSubject(Subject subject) {
        Date now = Calendar.getInstance().getTime();
        subject.setModifiedDate(now);
        subject.setModifiedBy(securityService.getCurrentUsername());
        repository.save(subject);
    }

    @Override
    public void deleteSubject(Subject subject) {
        repository.delete(subject);
    }
}
