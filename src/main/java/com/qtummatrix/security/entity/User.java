package com.qtummatrix.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("userinfo")
public class User implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("username")
    private String username;
    private String password;
    private Integer points;
    private Timestamp createTime;
    private Integer classId;

}
