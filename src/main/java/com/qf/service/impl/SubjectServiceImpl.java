package com.qf.service.impl;

import com.qf.dao.SubjectMapper;
import com.qf.pojo.Subject;
import com.qf.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubjectServiceImpl implements SubjectService {


    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public Subject findCourseBySubjectId(Integer id) {

        return subjectMapper.findCourseBySubjectId(id);

    }

    @Override
    public List<Subject> findAll() {
        return subjectMapper.selectByExample(null);
    }

    @Override
    public Integer getId(String subjectName) {
        return subjectMapper.getId(subjectName);
    }
}
