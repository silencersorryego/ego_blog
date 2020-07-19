package com.pc.service;

import com.pc.domain.Type;

import java.util.List;

public interface ITypeService {

    int saveType(Type type);

    Type getTypeById(Integer id);

    Type getTypeByName(String name);

    int updateType(Type type);

    int deleteType(Integer id);  // 根据主键删除Type

    List<Type> listType();  //获取tag列表

    public List<Type> listTypeAndCount();
}
