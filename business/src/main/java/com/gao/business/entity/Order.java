package com.gao.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_order")  // 对应数据库表名
public class Order {
    @TableId(type = IdType.INPUT)
    private String id;
    private String remark;
}
