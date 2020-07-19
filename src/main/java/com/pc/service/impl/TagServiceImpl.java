package com.pc.service.impl;

import com.pc.domain.Tag;
import com.pc.domain.Type;
import com.pc.mapper.ITagMapper;
import com.pc.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements ITagService {
    @Autowired
    ITagMapper tagMapper;

    @Override
    public List<Tag> listTag() {
        return tagMapper.listTag();
    }

    @Override
    public int saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagMapper.getTagById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public int deleteTag(Integer id) {
        return tagMapper.deleteTag(id);
    }

    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Integer> tagIds = convertToList(text);
        for (Integer tagId : tagIds) {
            tags.add(tagMapper.getTagById(tagId));
        }
        return tags;
    }
    private List<Integer> convertToList(String ids) {
        List<Integer> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Integer(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public List<Tag> listTagAndCount() {
        List<Tag> tags = tagMapper.listTag();
        List<Tag> tagList = new ArrayList<>();
        for (Tag tag : tags) {
            int count = 0;
            count=tagMapper.getTagCount(tag.getId());
            if (count>0){
                tag.setCount(count);
                tagList.add(tag);
            }
        }
        return tagList;
    }
}
