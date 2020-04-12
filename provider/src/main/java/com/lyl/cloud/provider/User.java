package com.lyl.cloud.provider;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User类
 *
 * @author liyl
 * @date 2019-10-28
 */
@Setter
@Getter
@ToString
public class User {

    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
