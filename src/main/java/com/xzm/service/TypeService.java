package com.xzm.service;

import com.xzm.bean.Type;

import java.util.List;

public interface TypeService {

    int saveType(Type type);

    Type selectType(int id);

    Type selectTypeByName(String name);

    List<Type> selectType();

    List<Type> selectTypeTop(int size);

    int updateType(int id,Type type);

    int deleteType(int id);

}
