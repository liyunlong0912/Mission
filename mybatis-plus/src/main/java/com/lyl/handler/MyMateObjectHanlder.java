package com.lyl.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyMateObjectHanlder
 *
 * @author liyl
 * @date 2020-4-11
 */
@Slf4j
@Component
public class MyMateObjectHanlder implements MetaObjectHandler {
    // 插入策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert!");
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }


    // 更新策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update!");
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
