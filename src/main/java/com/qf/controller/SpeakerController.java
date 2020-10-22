package com.qf.controller;

import com.qf.pojo.Speaker;
import com.qf.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

//    @RequestMapping()
//    public Speaker selectByPrimaryKey(Integer id) {
//        return speakerService.selectByPrimaryKey(id);
//    }

    @RequestMapping("showSpeakerList")
    public String showSpeakerList(Model model, Speaker speaker, @RequestParam(defaultValue = "1",required = false)Integer pageNum,
                                  @RequestParam(defaultValue = "3")Integer pageSize) {

//        PageHelper.startPage(pageNum,pageSize);

        List<Speaker> speakerList = speakerService.findAll();
        model.addAttribute("speakerList",speakerList);

//        PageInfo<Speaker> pageInfo = new PageInfo<>(speakerList);
//        model.addAttribute("pageInfo",pageInfo);

        return "/WEB-INF/jsp/behind/speakerList.jsp";


    }

    @RequestMapping("addSpeaker")
    public String addSpeaker() {
        return "/WEB-INF/jsp/behind/addSpeaker.jsp";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Speaker speaker, Model model, Integer id) {

        if (id!=null) {
            speakerService.updateSpeaker(speaker);
        } else {
            model.addAttribute("speaker",speaker);
            speakerService.addSpeaker(speaker);
        }
        return "redirect:/speaker/showSpeakerList";
//        Speaker speaker1 = speakerService.selectByPrimaryKey(id);
//        model.addAttribute("speaker1",speaker1);
    }

    @RequestMapping("speakerDel")
    @ResponseBody
    public String delSpeakerById(Integer id) {
        int ids = speakerService.delSpeakerById(id);
        if (ids==0) {
            return "default";
        } else {
            return "success";
        }
    }

    @RequestMapping("edit")
    public String alter(Integer id,Model model) {

        Speaker speaker = speakerService.selectByPrimaryKey(id);
        model.addAttribute("speaker",speaker);
        return "/WEB-INF/jsp/behind/addSpeaker.jsp";
    }
}
