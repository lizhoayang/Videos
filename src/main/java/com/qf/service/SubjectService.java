package com.qf.service;

import com.qf.pojo.Subject;

import java.util.List;

public interface SubjectService {

    public Subject findCourseBySubjectId(Integer id);

    public List<Subject> findAll();

    public Integer getId(String subjectName);
}
