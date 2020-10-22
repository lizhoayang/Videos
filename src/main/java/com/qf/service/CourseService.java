package com.qf.service;

import com.qf.pojo.Course;
import com.qf.pojo.Subject;

import java.util.List;

public interface CourseService {


    Course findCourseById(Integer id);

    int getId(Integer videoId);

    List<Course> findAllCourse();
}
