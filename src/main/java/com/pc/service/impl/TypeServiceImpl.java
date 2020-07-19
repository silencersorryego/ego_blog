package com.pc.service.impl;

import com.pc.domain.Type;
import com.pc.mapper.ITypeMapper;
import com.pc.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeServiceImpl implements ITypeService {
    @Autowired
    ITypeMapper typeMapper;

    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getTypeById(Integer id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Override
    public int deleteType(Integer id) {
        return typeMapper.deleteType(id);
    }

    @Override
    public List<Type> listType() {
        return typeMapper.listType();
    }

    @Override
    public List<Type> listTypeAndCount() {
        List<Type> types = typeMapper.listType();
        List<Type> typeList = new ArrayList<>();

        for (Type type : types) {
            int count = 0;
            count=typeMapper.getTypeCount(type.getId());
            if (count>0){
                type.setCount(count);
                typeList.add(type);
            }
        }
        return typeList;
    }
}
