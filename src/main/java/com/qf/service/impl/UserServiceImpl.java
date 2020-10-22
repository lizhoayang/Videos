package com.qf.service.impl;

import com.qf.dao.UserMapper;
import com.qf.pojo.Params;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryOne(Params params) {
        return userMapper.queryOne(params);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);

    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
