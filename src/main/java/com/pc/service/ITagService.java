package com.pc.service;

import com.pc.domain.Tag;

import java.util.List;

public interface ITagService {
    List<Tag> listTag();  //获取tag列表

    int saveTag(Tag tag);


    Tag getTagById(Integer id);

    Tag getTagByName(String name);

    int updateTag(Tag tag);


    int deleteTag(Integer id);  // 根据主键删除Type

    List<Tag> getTagByString(String tagIds);

    List<Tag> listTagAndCount();
}
