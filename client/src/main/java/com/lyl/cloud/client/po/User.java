package com.lyl.cloud.client.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User类
 *
 * @author liyl
 * @date 2019-10-28
 */
@Getter
@Setter
@ToString
public class User {

    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
