package com.example.springsecuritydemo.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户实体
 *
 * @author Tragoedia
 */
@Data
@TableName("t_user")
public class User {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String type;
}
