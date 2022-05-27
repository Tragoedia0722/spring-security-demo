package com.example.springSecurityDemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springSecurityDemo.domain.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
