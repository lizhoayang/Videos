package com.qf.service;

import com.qf.pojo.Params;
import com.qf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserService {

    public User queryOne(Params params);

    public void insertUser(User user);

    public void update(User user);

}
