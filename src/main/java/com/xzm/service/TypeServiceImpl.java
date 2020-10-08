package com.xzm.service;

import com.xzm.NotFoundException;
import com.xzm.bean.Type;
import com.xzm.dao.TypeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeMapper.insert(type);
    }

    @Cacheable(value = "type",key="#id")
    @Override
    public Type selectType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Type selectTypeByName(String name) {
            return typeMapper.selectTypeByName(name);
    }

    @Override
    public List<Type> selectType() {
        return typeMapper.selectAll( );
    }

    @Override
    public List<Type> selectTypeTop(Integer size) {
        return typeMapper.selectTypeTop(size);
    }


    @Transactional
    @Override
    public int updateType(Integer id, Type type) {
        Type t = typeMapper.selectByPrimaryKey(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        return typeMapper.updateByPrimaryKey(type);
    }

    @Transactional
    @Override
    public int deleteType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }
}
