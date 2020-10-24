package com.xzm.blog.dao;

import com.xzm.blog.bean.Type;

import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Type> selectAll();
    List<Type> selectTypeTop(Integer size);
    Type selectTypeByName(String name);
}