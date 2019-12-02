package com.drato.graduationthesis.dto;

import com.drato.graduationthesis.model.Subject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class ExamDto {
    private Long id;

    @NotEmpty(message = "{constraints.not-empty}")
    @Size(max = 512, message = "{constraints.length-smaller-512}")
    private String name;

    private List<Subject> subjects;

    @NotEmpty(message = "{constraints.not-empty}")
    private String startDate;

    @Size(max = 1024, message = "{constraints.length-smaller-1024}")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
