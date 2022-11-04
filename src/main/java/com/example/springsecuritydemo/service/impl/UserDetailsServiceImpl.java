package com.example.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springsecuritydemo.exception.SystemException;
import com.example.springsecuritydemo.model.LoginUser;
import com.example.springsecuritydemo.model.po.User;
import com.example.springsecuritydemo.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 使用数据库验证实现用户信息查询
 *
 * @author Tragoedia
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (Objects.isNull(user)) {
            throw new SystemException("用户名或密码错误");
        }
        // 设置用户权限
        // todo 从数据库读取权限
        List<String> list = new ArrayList<>();
        list.add("system:hello");
        return new LoginUser(user, list);
    }
}
