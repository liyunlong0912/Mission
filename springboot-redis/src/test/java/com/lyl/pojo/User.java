package com.lyl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
// 在企业中，所有的pojo类需要序列化
public class User implements Serializable {

    private static final long serialVersionUID = 3373423148268860898L;
    private String name;
    private Integer age;
}
