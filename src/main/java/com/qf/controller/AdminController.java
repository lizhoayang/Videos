package com.qf.controller;

import com.qf.pojo.Admin;
import com.qf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("loginView")
    public String loginView() {
        return "/WEB-INF/jsp/behind/login.jsp";
    }


    @RequestMapping("login")
    @ResponseBody
    public String adminLogin(Admin admin, HttpSession session) {

        Admin admin1 = adminService.queryOne(admin);

        String username = admin1.getUsername();
        System.out.println(username);

        session.setAttribute("username",username);
        session.setMaxInactiveInterval(60*60*24*15);

        if (admin1 == null ) {
            return "defeat";
        } else {
            return "success";
        }
    }


    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.invalidate();

        return "redirect:/admin/loginView";
    }

}
