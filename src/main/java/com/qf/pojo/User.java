package com.qf.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String email;

    private String phonenum;

    private String password;

    private String code;

    private String nickname;

    private String sex;

    private String birthday;

    private String address;

    private String imgurl;

    private Date createtime;


}