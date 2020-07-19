package com.pc.mapper;

import com.pc.domain.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ITagMapper {

    @Insert("insert into tag(name) values (#{name})")
    int saveTag(Tag tag);

    @Select("select id,name from tag where id = #{id}")
    Tag getTagById(Integer id);

    @Select("select id,name from tag where name = #{name}")
    Tag getTagByName(String name);

    @Update("update tag set name=#{name} where id=#{id}")
    int updateTag(Tag tag);

    @Delete("delete from tag where id=#{id}")
    int deleteTag(Integer id);  // 根据主键删除Type

    @Select("select id,name from tag")
    List<Tag> listTag();  //获取tag列表



    @Select("select count(tag_id) from blog_tag where tag_id = #{tag_id}")
    int getTagCount(int tag_id);  //获取tag列表
}
