package com.drato.graduationthesis.service.interfaces;

import com.drato.graduationthesis.model.Subject;

import java.util.Collection;
import java.util.List;

public interface SubjectService {
    public List<Subject> getAll();
    public Subject getById(Long id);
    public Collection<Subject> getSubjectsByIds(Long[] ids);


    boolean nameUsedByOther(Long id, String name);

    boolean nameExisted(String name);

    void createSubject(Subject subject);

    void editSubject(Subject subject);

    void deleteSubject(Subject subject);
}
