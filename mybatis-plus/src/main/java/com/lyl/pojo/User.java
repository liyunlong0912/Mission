package com.lyl.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * User
 *
 * @author liyl
 * @date 2020-4-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // id主键 （UUID,自增id，雪花算法，redis，zookeeper）
    // mybatis_plus默认ID策略 ID_WORKER
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    @Version //MP乐观锁
    private Integer version;
    @TableLogic
    private Integer deleted;


    // 主键生成策略
    /*
    AUTO(0),              id自增
    NONE(1),              未设置主键
    INPUT(2),             手动输入
    ID_WORKER(3),          默认
    UUID(4),               uuid全局唯一
    ID_WORKER_STR(5);      字符串截取
    */
}
