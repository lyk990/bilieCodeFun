package com.imooc.bilibili.dao;

import com.imooc.bilibili.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User getUserByPhone(String phone);
}
