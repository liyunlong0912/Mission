package com.lyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyl.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper
 *
 * @author liyl
 * @date 2020-4-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
