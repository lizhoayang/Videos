package com.qf.service;

import com.qf.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface AdminService {

    public Admin queryOne(Admin admin);
}
