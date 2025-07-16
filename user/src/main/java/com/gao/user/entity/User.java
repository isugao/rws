package com.gao.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")  // 对应数据库表名
public class User {
    @TableId(type = IdType.INPUT)
    private String id;
    private String name;
    private Integer age;
}
