package com.qf.controller;


import com.qf.pojo.Params;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("loginUser")
    @ResponseBody
    public String loginUser(Params params, HttpServletRequest request) {

        User one = userService.queryOne(params);

        if (one ==null) {
            return "defeat";
        } else {

            String email = one.getEmail();
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(60*60*24*10);
            session.setAttribute("email",email);
            return "success";
        }
    }

    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();

        return "redirect:/subject/findAll";
    }

    @RequestMapping("validateEmail")
    @ResponseBody
    public String validateEmail(Params params) {
        User user = userService.queryOne(params);
        if (user == null) {
            return "success";
        } else {
            return "邮箱已存在";
        }
    }


    @ResponseBody
    @RequestMapping("insertUser")
    public String insertUser (User user) {
        userService.insertUser(user);

        return "success";
    }


    @RequestMapping("showMyProfile")
    public String showMyProfile(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        Params params = new Params();
        params.setEmail(email);
        User user = userService.queryOne(params);

        request.setAttribute("user",user);

        return "/WEB-INF/jsp/before/my_profile.jsp";
    }


    @RequestMapping("changeProfile")
    public String changeProfile(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        Params params = new Params();
        params.setEmail(email);
        User user = userService.queryOne(params);

        request.setAttribute("user",user);

        return "/WEB-INF/jsp/before/change_profile.jsp";
    }

    @RequestMapping("updateUser")
    public String updateUser(User user1) {
        userService.update(user1);
       return "redirect:/user/showMyProfile";
    }

    @RequestMapping("changeAvatar")
    public String changeAvatar(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        Params params = new Params();
        params.setEmail(email);
        User user = userService.queryOne(params);

        request.setAttribute("user",user);

        return "/WEB-INF/jsp/before/change_avatar.jsp";
    }

    @RequestMapping("upLoadImage")
    public String upLoadImage(MultipartFile image_file, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        Params params = new Params();
        params.setEmail(email);
        User user = userService.queryOne(params);

        String photoOriginalFilename = image_file.getOriginalFilename();
        String route = "F:\\apache-tomcat-8.5.41\\webapps\\video\\";
        File file = new File(route + photoOriginalFilename);

        if (photoOriginalFilename !="") {
            try {
                image_file.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setImgurl(photoOriginalFilename);
        }

        userService.update(user);

        return "redirect:/user/showMyProfile";
    }

    @RequestMapping("passwordSafe")
    public String passwordSafe (HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        Params params = new Params();
        params.setEmail(email);
        User user = userService.queryOne(params);

        request.setAttribute("user",user);
        return "/WEB-INF/jsp/before/password_safe.jsp";
    }

    @ResponseBody
    @RequestMapping("validatePassword")
    public String validatePassword(@RequestParam(value="password",required=true) String  password,
                                   HttpServletRequest request) {


        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        Params params = new Params();
        params.setEmail(email);
        User user = userService.queryOne(params);

        if (user.getPassword().equals(password)) {
            return "success";
        } else {
            return "false";
        }
    }


    @RequestMapping("updatePassword")
    public String updatePassword( String  newPassword, HttpServletRequest request){

        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        Params params = new Params();
        params.setEmail(email);
        User user = userService.queryOne(params);

        user.setPassword(newPassword);
        userService.update(user);

        return "redirect:/user/loginOut";
    }

    @RequestMapping("loginOut2")
    public String loginOut2() {
        return "redirect:/user/loginOut";
    }


    @RequestMapping("forgetPassword")
    public String forgetPassword(){
        return "/WEB-INF/jsp/before/forget_password.jsp";
    }


    @RequestMapping("sendEmail")
    @ResponseBody
    public String sendEmail(@RequestParam(value="email",required=true) String  email, HttpServletRequest request) {
        Params params = new Params();
        params.setEmail(email);
        User user = userService.queryOne(params);

        if (user != null) {

            String validateCode = MailUtils.getValidateCode(6);
            MailUtils.sendMail(email, "你好，这是一封测试邮件，无需回复。", "测试邮件随机生成的验证码是：" + validateCode);

            HttpSession emailCodeSession = request.getSession(false);
            emailCodeSession.setMaxInactiveInterval(60*3);

            emailCodeSession.setAttribute("validateCode",validateCode);

            return "success";
        } else {
            return "hasNoUser";
        }

    }


    @RequestMapping("validateEmailCode")
    public String validateEmailCode(String code, String email, HttpServletRequest request) {
        HttpSession emailCodeSession = request.getSession(false);
        String validateCode = (String) emailCodeSession.getAttribute("validateCode");

        if (validateCode.equals(code)) {
            request.setAttribute("email",email);

            return "/WEB-INF/jsp/before/reset_password.jsp";
        } else {
            return "/WEB-INF/jsp/before/forget_password.jsp";
        }
    }


    @RequestMapping("resetPassword")
    public String resetPassword(String email,String password,HttpServletRequest request) {
        Params params = new Params();
        params.setEmail(email);
        User user = userService.queryOne(params);
        user.setPassword(password);
        userService.update(user);

        HttpSession emailCodeSession = request.getSession(false);
        emailCodeSession.invalidate();

        return "redirect:/subject/findAll";
    }

}
