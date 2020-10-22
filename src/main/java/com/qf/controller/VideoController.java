package com.qf.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.*;
import com.qf.service.CourseService;
import com.qf.service.SpeakerService;
import com.qf.service.SubjectService;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("video")
public class VideoController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private SpeakerService speakerService;

    @RequestMapping("showVideo")
    public String showVideo(Integer videoId, String subjectName, Model model) {


        int id = courseService.getId(videoId);
        Course course = courseService.findCourseById(id);

        Video video = videoService.selectVideoById(videoId);

        List<Subject> subjectList = subjectService.findAll();
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("subjectName",subjectName);
        model.addAttribute("course",course);
        model.addAttribute("video",video);

        return "/WEB-INF/jsp/before/section.jsp";

    }

    @RequestMapping("list")
    public String listVideo(Model model, @RequestParam(defaultValue = "1",required = false)Integer pageNum,
                            @RequestParam(defaultValue = "3")Integer pageSize, QueryVo queryVo
    ) {
        PageHelper.startPage(pageNum,pageSize);

        model.addAttribute("queryVo",queryVo);

        //查询所有的/分条件查询
        List<Video> page = videoService.findAllVideos(queryVo);
        model.addAttribute("page", page);


        PageInfo<Video> pageInfo = new PageInfo<>(page);
        model.addAttribute("pageInfo",pageInfo);;

        //Speaker下拉列表
        List<Speaker> speakerList = speakerService.findAll();
        model.addAttribute("speakerList",speakerList);

        //course下拉列表
        List<Course> courseList = courseService.findAllCourse();
        model.addAttribute("courseList",courseList);

        return "/WEB-INF/jsp/behind/videoList.jsp";
    }

    @RequestMapping("addVideo")
    public String addVideo(Video video, Model model,Course course) {

        List<Course> courseList1 = courseService.findAllCourse();
        model.addAttribute("courseList1",courseList1);

        List<Speaker> speakerList1 = speakerService.findAll();
        model.addAttribute("speakerList1",speakerList1);

        List<Video> videoList = videoService.findAllVideos(null);
        model.addAttribute("videoList",videoList);
        return "/WEB-INF/jsp/behind/addVideo.jsp";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Video video,Model model,Course course) {
        videoService.insert(video);

        return "redirect:/video/list";
    }

    @RequestMapping("videoDel")
    @ResponseBody
    public String videoDel(Integer id) {
        int i = videoService.deleteByPrimaryKey(id);
        if (i==0) {
            return "default";
        } else {
            return "success";
        }

    }

    @RequestMapping("edit")
    public String edit(Integer id) {
        videoService.updateByPrimaryKey(id);

        return "redirect:/video/list";
    }

    @RequestMapping("delBatchVideos")
    public String delBatchVideos(Integer[] ids) {
        for(Integer id:ids) {
            videoService.deleteByPrimaryKey(id);
        }
        return "redirect:/video/list";
    }

    @RequestMapping("updatePlayNum")
    public void updatePlayNum(Video video) {

        video.setPlayNum(video.getPlayNum() + 1);

        videoService.updatePlayNum(video);
    }

}
