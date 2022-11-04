package com.example.springsecuritydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecuritydemo.model.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户实体 Mapper
 *
 * @author Tragoedia
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
