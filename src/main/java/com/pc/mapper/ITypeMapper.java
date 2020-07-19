package com.pc.mapper;

import com.pc.domain.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ITypeMapper {

    @Insert("insert into type(name) values (#{name})")
    int saveType(Type type);

    @Select("select id,name from type where id = #{id}")
    Type getTypeById(Integer id);

    @Select("select id,name from type where name = #{name}")
    Type getTypeByName(String name);

    @Update("update type set name=#{name} where id=#{id}")
    int updateType(Type type);

    @Delete("delete from type where id=#{id}")
    int deleteType(Integer id);  // 根据主键删除Type

    @Select("select id,name from type")
    List<Type> listType();  //获取tag列表

    @Select("select count(*) from blog where type_id = #{type_id}")
    int getTypeCount(int type_id);  //获取tag列表
}
