package com.qf.service.impl;

import com.qf.dao.CourseMapper;
import com.qf.pojo.Course;
import com.qf.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public Course findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public int getId(Integer videoId) {
        return courseMapper.getId(videoId);
    }

    @Override
    public List<Course> findAllCourse() {
        return courseMapper.findAllCourse();
    }
}

