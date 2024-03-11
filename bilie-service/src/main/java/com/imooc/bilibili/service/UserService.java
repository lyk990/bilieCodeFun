package com.imooc.bilibili.service;

import com.imooc.bilibili.dao.UserDao;
import com.imooc.bilibili.domain.User;
import com.imooc.bilibili.domain.exception.ConditionalException;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Condition;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
        String phone = user.getPhone();

        if(StringUtils.isNullOrEmpty(phone)){
            throw new ConditionalException("手机号不能为空");
        }
    }

    public User getUserByPhone(String phone) {
      return userDao.getUserByPhone(phone);
    }

}
