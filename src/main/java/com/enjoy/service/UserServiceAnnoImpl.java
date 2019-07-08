package com.enjoy.service;

import com.enjoy.annotation.DoSomething;
import com.enjoy.dao.UserDao;
import com.enjoy.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DoSomething(key="class-key",cacheName="class-name",needLog=false)
public class UserServiceAnnoImpl implements UserServiceAnno {

    @Resource
    private UserDao userDao;

    public static final String CACHE_NAME = "USER";

    @Override
    @DoSomething(key="#id",cacheName=CACHE_NAME,needLog=true)
    public User getUserById(String id){
        return userDao.find(id);
    }
}
