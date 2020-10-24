package com.xzm.blog.service;

import com.xzm.blog.bean.Type;

import java.util.List;

public interface TypeService {

    int saveType(Type type);

    Type selectType(Integer id);

    Type selectTypeByName(String name);

    List<Type> selectType();

    List<Type> selectTypeTop(Integer size);

    int updateType(Integer id,Type type);

    int deleteType(Integer id);

}
