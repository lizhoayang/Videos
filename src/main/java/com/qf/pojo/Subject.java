package com.qf.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Subject {
    private Integer id;

    private String subjectName;

    private List<Course> courseList;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }
}