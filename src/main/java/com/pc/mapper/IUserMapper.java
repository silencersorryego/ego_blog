package com.pc.mapper;

import com.pc.domain.User;
import org.apache.ibatis.annotations.Select;

public interface IUserMapper {
    @Select("select id,nick_name,user_name,password,email,type,avatar from user where user_name = #{username}")
    User getUserByUserName(String username);

    @Select("select id,nick_name,avatar from user where id = #{user_id}")
    User getUserById(int user_id);
}
