package com.qf.controller;

import com.qf.pojo.Course;
import com.qf.pojo.Subject;
import com.qf.service.CourseService;
import com.qf.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("course")
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("course/{id}")
    public String findCourseBySubjectId(@PathVariable(name = "id") Integer id, Model model) {

        Subject subject = subjectService.findCourseBySubjectId(id);

        model.addAttribute("subject",subject);

        List<Subject> subjectList = subjectService.findAll();
        model.addAttribute("subjectList",subjectList);

        return "/WEB-INF/jsp/before/course.jsp";
    }

    //findAllCourse
    @RequestMapping("findAllCourse")
    public String findAllCourse(Model model) {
        List<Course> courseList = courseService.findAllCourse();
        model.addAttribute("courseList",courseList);

        return "/WEB-INF/jsp/behind/videoList.jsp";
    }

}
